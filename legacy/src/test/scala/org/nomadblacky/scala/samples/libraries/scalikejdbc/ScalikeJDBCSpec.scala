package org.nomadblacky.scala.samples.libraries.scalikejdbc

import java.sql.SQLException
import java.time.Instant

import org.scalatest._
import scalikejdbc._
import skinny.orm.{Alias, SkinnyCRUDMapper}

import scala.util.Try
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

/** scalikejdbc-cookbook https://github.com/scalikejdbc/scalikejdbc-cookbook
  *
  * FIXME: Unit tests are failed
  */
@Ignore
class ScalikeJDBCSpec extends AnyFunSpec with Matchers with BeforeAndAfterAll with BeforeAndAfter {

  override def suiteName: String = "ScalikeJDBC"

  override protected def beforeAll(): Unit = {
    Class.forName("org.h2.Driver")
    ConnectionPool.singleton("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1", "username", "password")
    DB localTx { implicit s =>
      val sqls = Seq(
        sql"create table members(id bigint auto_increment primary key, name varchar(50) not null, team_id bigint not null);",
        sql"create table team_members(id bigint primary key auto_increment, team_id bigint not null)",
        sql"create table teams(id bigint primary key auto_increment, name varchar(50))",
        sql"create table users(id bigint primary key auto_increment, name varchar(50) not null, organization varchar(50), created_at timestamp not null)"
      )
      sqls.foreach(_.update().apply())
    }
  }

  before {
    Class.forName("org.h2.Driver")
    ConnectionPool.singleton("jdbc:h2:mem:db", "username", "password")
  }

  /** https://github.com/scalikejdbc/scalikejdbc-cookbook/blob/master/ja/03_connection.md
    */
  describe("接続設定とコネクション") {

    it("コネクションプールの設定") {
      Class.forName("org.h2.Driver")
      ConnectionPool.singleton("jdbc:h2:mem:db", "username", "password")

      DB readOnly { implicit session =>
        // ...
      }
    }

    it("複数データソースへの接続") {
      Class.forName("org.h2.Driver")

      ConnectionPool.add('db01, "jdbc:h2:mem:db01", "user01", "pass01")
      ConnectionPool.add('db02, "jdbc:h2:mem:db02", "user01", "pass01")

      NamedDB('db01) readOnly { implicit s =>
        // ...
      }
      NamedDB('db02) readOnly { implicit s =>
        // ...
      }
    }

    it("その他オプションの設定") {
      Class.forName("org.h2.Driver")
      ConnectionPool.singleton(
        "jdbc:h2:mem:db",
        "user",
        "pass",
        ConnectionPoolSettings(
          initialSize = 20,                   // プールするコネクションの最小値
          maxSize = 50,                       // プールするコネクションの最大値
          validationQuery = "select 1 as one" // 正常に接続できているかを確認するSQL
        )
      )
    }

  }

  describe("DBブロックとトランザクション") {

    it("readOnly ... select文のみ実行できる") {
      DB readOnly { implicit s =>
        sql"select count(1) from members".map(_.long(1)).single.apply().get
      }

      // select文以外を叩こうとすると例外
      a[SQLException] should be thrownBy {
        DB readOnly { implicit s =>
          sql"insert into mambers(name) values('foooo!')".update().apply()
        }
      }
    }

    it("autoCommit ... 更新毎にコミットを行う") {
      DB autoCommit { implicit s =>
        val updateMembers = SQL("update members set name = ? where id = ?")

        updateMembers.bind("hoge", 1).update().apply() // auto-commit
        updateMembers.bind("foo", 2).update().apply()  // auto-commit
      }
    }

    it("localTx ... ブロックのスコープに閉じた同一トランザクションで実行する") {
      Try {
        DB localTx { implicit s =>
          val insertMember = SQL("insert into members(name) values(?)")

          insertMember.bind("hoge").update().apply()
          insertMember.bind("foo").update().apply()

          // 例外が投げられた場合、トランザクションがロールバックされる
          throw new RuntimeException("Opps!")
        }
      }

      val count = DB readOnly { implicit s =>
        sql"select count(*) from members".map(_.long(1)).single().apply().get
      }
      count shouldBe 0
    }

    it("withTx ... すでに存在するトランザクション内で実行する") {
      using(DB(ConnectionPool.borrow())) { implicit db =>
        try {
          db.begin() // トランザクションの開始

          // トランザクションが開始されていない場合、IllegalStateExceptionが投げられる
          DB withinTx { implicit s =>
            sql"select name from members".map(_.string("name")).list.apply()
          }

          db.commit() // コミット

        } catch {
          case e: Exception =>
            db.rollback()         // 例外が投げられる可能性があるロールバック
            db.rollbackIfActive() // 例外セーフなロールバック
            throw e
        }
      }
    }

    case class Member(id: Long, name: String)

    it("AutoSession ... 有効なセッションがなければ自動的に新しいセッションを開始する") {
      def createMember(name: String, teamId: Long): Member = {
        val id = DB localTx { implicit s =>
          sql"insert into members(name, team_id) values($name, $teamId)".updateAndReturnGeneratedKey().apply()

          // localTx で囲っているので、この時点でトランザクションが終了してしまう
        }
        Member(id, name)
      }
      createMember("hoge", 100L)
      // この設計だと同じトランザクションを使って処理を続けられない

      // implicit parameter としてセッションを受け取るようにした
      def createMember2(name: String, teamId: Long)(implicit s: DBSession = AutoSession): Member = {
        val id = sql"insert into members(name, team_id) values($name, $teamId)".updateAndReturnGeneratedKey().apply()
        Member(id, name)
      }

      // こうすることで、 localTx などを使うこともできるし、
      DB localTx { implicit s =>
        createMember2("foo", 200L)

        // 同じトランザクションを使う処理
      }

      // 単体で実行もできる
      createMember2("bar", 300L)
    }
  }

  describe("SQLテンプレート") {

    it("JDBCのSQLテンプレート") {
      val sql = SQL("insert into members(id, name) values(?, ?)")
        .bind(100L, "user01")
    }

    it("名前付きSQLテンプレート") {
      SQL("insert into members(id, name) values({id}, {name})")
        .bindByName(
          'id   -> 100L,
          'name -> "user01"
        )
    }

    it("実行可能なSQLテンプレート") {
      SQL("""
        insert into members(id, name) values (
          |  /*'id*/12345,
          |  /*'name*/'dummy-user'
          |)
        """.stripMargin)
        .bindByName(
          'id   -> 100L,
          'name -> "user01"
        )
    }

    it("SQLインターポレーション") {
      val name = "user01"
      sql"""
           insert into members(id, name) values (${100L}, $name)
         """
    }

    it("SQLSyntax") {
      def ordering(isAsk: Boolean): SQLSyntax =
        if (isAsk) sqls"ask" else sqls"desc" // SQLSyntaxは sqls のみで生成可能

      // SQLの一部として組み込まれる
      sql"""
            select * from members order by ${ordering(true)}
        """
    }

    it("Seqの展開") {
      // Seqのみ、in句を想定してカンマ区切りで展開される
      val ids = Seq(1, 2, 3)
      val sql = sql"select * from members where id in ($ids)"
    }

    it("SQLSyntaxSupport") {
      case class TeamMember(id: Long, teamId: Long)
      case class Team(id: Long, name: String)

      // ベタに書くと
      sql"""
            select m.id as m_id, m.team_id as m_tid, t.id as t_id, t.name as t_name
            from team_members m inner join team t on m.team_id = t.id
        """
        .map(rs => (TeamMember(rs.long("m_id"), rs.long("m_tid")), Team(rs.long("t_id"), rs.string("t_name"))))

      // SQLSyntaxSupportを使う
      object TeamMember extends SQLSyntaxSupport[TeamMember] {
        override def tableName: String = "team_members"
        def apply(m: ResultName[TeamMember])(implicit rs: WrappedResultSet): TeamMember =
          new TeamMember(id = rs.long(m.id), teamId = rs.long(m.teamId))
      }
      object Team extends SQLSyntaxSupport[Team] {
        override def tableName: String = "teams"
        def apply(t: ResultName[Team])(implicit rs: WrappedResultSet): Team =
          new Team(id = rs.long(t.id), name = rs.string(t.name))
      }

      val (m, t) = (TeamMember.syntax("m"), Team.syntax("t"))
      sql"""
            select ${m.result.*} ${t.result.*}
            from ${TeamMember.as(m)} inner join ${Team.as(t)} on ${m.teamId} = ${t.id}
        """
        .map(implicit rs => (TeamMember(m.resultName), Team(t.resultName)))

      // ・文字列指定がなくなり、タイプセーフになる
      // ・applyを定義すると、マッピング処理はどんなジョインクエリでも再利用できる

      // ボイラープレートが増えるのを避けたい → scalikejdbc-syntax-support-macro を使う
      // http://scalikejdbc.org/documentation/auto-macros.html
    }

    it("QueryDSL") {
      case class Team(id: Long, name: String)
      object Team extends SQLSyntaxSupport[Team] {
        override def tableName: String = "teams"
        def create(id: Long, name: String): Int = DB.localTx { implicit s =>
          withSQL {
            insert
              .into(Team)
              .columns(column.id, column.name)
              .values(id, name)
          }.update().apply()
        }
      }

      val ordering = sqls"desc"
      val id       = 1234

      val t = Team.syntax("t")
      val names = DB.readOnly { implicit s =>
        withSQL {
          select(t.name).from(Team as t).where.eq(t.id, id).orderBy(t.id).append(ordering)
        }.map(rs => rs.string(t.name)).list().apply()
      }
    }

    it("[Tips] SQLSyntaxSupportFeature#selectDynamic におけるカラム名展開の違い") {
      case class Team(id: Long, name: String)
      object Team extends SQLSyntaxSupport[Team] {
        override def tableName: String = "teams"
      }

      val t = Team.syntax("t")
      t.id shouldBe sqls"t.id"                  // カラム名
      t.result.id shouldBe sqls"t.id as i_on_t" // カラム名 as エイリアス
      t.resultName.id shouldBe sqls"i_on_t"     // エイリアス
    }

  }

  describe("一般的な利用のサンプル例") {

    case class Member(id: Long, name: String, teamId: Long)
    val * = (rs: WrappedResultSet) => Member(rs.long("id"), rs.string("name"), rs.long("team_id"))

    case class Team(id: Long, name: String)

    it("single ... Primary Key での検索") {
      val id = 1234

      // single はOptionで結果を返す
      // ２件以上ヒットした場合は例外が投げられる
      val member: Option[Member] = DB.readOnly { implicit s =>
        sql"select * from members where id = $id".map(*).single().apply()
      }
    }

    it("count文 & single ... 件数を取得") {
      // countの結果は single で取得して Option#get で取得する
      val count: Long = DB.readOnly { implicit s =>
        sql"select count(1) from members".map(_.long(1)).single().apply().get
      }
    }

    it("list ... 複数件取得") {
      val members: Seq[Member] = DB.readOnly { implicit s =>
        sql"select * from members".map(*).list().apply()
      }
    }

    it("first ... 最初の１件のみ取得") {
      val member = DB.readOnly { implicit s =>
        sql"select * from members order by id desc".map(*).first().apply()
      }
    }

    it("foreach ... １行ずつ読み込む") {
      // 巨大な結果など、１行ずつ読みたい場合に使う
      DB.readOnly { implicit s =>
        sql"select * from members".foreach { rs =>
          // ...
        }
      }
    }

    it("in句 と SQLInterpolation") {
      val memberIds = Seq(1, 2, 3)

      // SQLInterpolation は Seq でパラメタを受け取ることができる
      val members = DB.readOnly { implicit s =>
        sql"select * from members where id in ($memberIds)".map(*).list().apply()
      }

      // 従来のSQL構文では in句 はサポートされていないので、自分で組み立てる
      val query = "select * from members where id in (%s)".format(memberIds.map(_ => "?").mkString(","))
      val members2 = DB.readOnly { implicit s =>
        SQL(query).bind(memberIds: _*).map(*).list().apply()
      }
    }

    it("join クエリ") {
      // SQLSyntaxSupportがおすすめ
      object Member extends SQLSyntaxSupport[Member] {
        override def tableName: String = "team_members"
      }
      object Team extends SQLSyntaxSupport[Team] {
        override def tableName: String = "teams"
      }

      case class JoinedMember(id: Long, team: Team)
      object JoinedMember {
        def apply(m: ResultName[Member], t: ResultName[Team])(implicit rs: WrappedResultSet): JoinedMember =
          new JoinedMember(id = rs.long(m.id), new Team(id = rs.long(t.id), rs.string(t.name)))
      }

      val (m, t) = (Member.syntax("m"), Team.syntax("t"))
      val joinedMembers = DB.readOnly { implicit s =>
        sql"""
           select ${m.result.*}, ${t.result.*}
           from ${Member as m} left join ${Team as t} on ${m.teamId} = ${t.id}
        """
          .map(implicit rs => JoinedMember(m.resultName, t.resultName))
          .list()
          .apply()
      }
    }

    it("Insert") {
      DB.autoCommit { implicit s =>
        // nullableな値はOptionが使える
        // 日付・時刻は JavaSE8 の Date Time API もしくは Joda Time を使う
        // (ScalikeJDBCのVersion3からは Date Time API を推奨)
        val (name, organization, createdAt) = ("User", Some("Hoge Inc."), Instant.now())
        sql"insert into users (name, organization, created_at) values ($name, $organization, $createdAt)"
          .update()
          .apply()
      }
    }

    it("auto-increment な id を取得する") {
      val (name, organization, createdAt) = ("User", None, Instant.now())
      val id: Long = DB localTx { implicit s =>
        // auto-increment である PK を扱うには updateAndReturnGeneratedKey を指定する
        sql"insert into users (name, organization, created_at) values ($name, $organization, $createdAt)"
          .updateAndReturnGeneratedKey()
          .apply()
      }
    }

    it("Update") {
      // insert と変わらず update を指定する
      val (id, newName) = (100L, "newUser!")
      DB localTx { implicit s =>
        sql"update users set name = $newName where id = $id".update().apply()
      }
    }

    it("Delete") {
      // insert, update と同様に update を指定する
      val id = 100L
      DB localTx { implicit s =>
        sql"delete from users where id = $id".update().apply()
      }
    }

    it("Batch") {
      // バッチ処理には batch, batchByName を使用する
      // バインド引数のリストを実行数分だけ一括で渡す
      // ある程度大きな件数を更新する場合はこちらがおすすめ
      val now                    = Instant.now()
      val params1: Seq[Seq[Any]] = (1 to 100).map(i => Seq("user_" + i, now))
      DB localTx { implicit s =>
        // batch メソッドはJDBCのSQLテンプレートに対してSeq[Any]を実行数分渡すメソッド
        SQL("insert into users (name, created_at) values(?, ?)").batch(params1: _*).apply()
      }

      val params2: Seq[Seq[(Symbol, Any)]] = (101 to 200).map(i => Seq('name -> ("user_" + i), 'createdAt -> now))
      DB localTx { implicit s =>
        // batchByName メソッドはJDBCのSQLテンプレート or 実行可能なSQLテンプレートに対してSeq[(Symbol, Any)]を実行数分渡すメソッド
        SQL("insert into users (name, created_at) values({name}, {createdAt})").batchByName(params2: _*).apply()
      }
    }
  }

  describe("SQL ロギング") {

    /** ScalikeJDBCでは、実行したSQLとそのレスポンスタイムをログ出力する機能がある。 スタックトレースを併せて出力することで、どのクラスのどのメソッドから発行されたかも確認できる。
      *
      * デフォルトでは、DEBUGレベルですべてのSQLを出力、 一定以上の時間がかかったSQLはWARNレベルでログを出力するようになっている。
      */
    def insertMember(name: String, teamId: Long): Unit = DB.localTx { implicit s =>
      sql"insert into members(name, team_id) values($name, $teamId)".update().apply()
    }

    it("コードでの設定") {
      GlobalSettings.loggingSQLAndTime = LoggingSQLAndTimeSettings(
        enabled = true,
        logLevel = 'DEBUG,
        warningEnabled = true,
        warningThresholdMillis = 1000L,
        warningLogLevel = 'WARN
      )
      insertMember("hoge", 111L)
    }

    it("SLF4Jの実装を設定") {
      // src/test/resources/logback.xml
      /*
      <configuration>
          <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
              <encoder>
                  <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
              </encoder>
          </appender>
          <root level="info">
              <appender-ref ref="STDOUT" />
          </root>
      </configuration>
       */
    }

    it("シングルラインモード") {
      // スタックトレースが不要な場合
      GlobalSettings.loggingSQLAndTime = new LoggingSQLAndTimeSettings(
        enabled = true,
        singleLineMode = true,
        logLevel = 'DEBUG
      )
      insertMember("foo", 222L)
    }
  }

  describe("Skinny ORM") {
    it("SkinnyCRUDMapper") {
      case class Member(id: Long, name: String, teamId: Long)
      object Member extends SkinnyCRUDMapper[Member] {
        override def tableName: String                                                        = "members"
        override lazy val defaultAlias: Alias[Member]                                         = createAlias("m")
        override def extract(rs: WrappedResultSet, n: scalikejdbc.ResultName[Member]): Member = autoConstruct(rs, n)
      }

      // Create
      Member.createWithAttributes('name -> "hoge", 'teamId -> 100L)
      // Read
      Member.findById(123)
      Member.where('name -> "hoge")
      // Update
      Member.updateById(123).withAttributes('name                               -> "foo")
      Member.updateBy(sqls.eq(Member.column.name, "hoge")).withAttributes('name -> "foo")
      // Delete
      Member.deleteById(123)
      Member.deleteBy(sqls.eq(Member.column.name, "hoge"))
    }
  }
}
