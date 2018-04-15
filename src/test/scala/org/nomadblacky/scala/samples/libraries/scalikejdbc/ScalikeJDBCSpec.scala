package org.nomadblacky.scala.samples.libraries.scalikejdbc

import java.sql.SQLException

import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, FunSpec, Matchers}
import scalikejdbc._

import scala.util.Try

/**
  * scalikejdbc-cookbook
  * https://github.com/scalikejdbc/scalikejdbc-cookbook
  */
class ScalikeJDBCSpec extends FunSpec with Matchers with BeforeAndAfterAll with BeforeAndAfter {

  override def suiteName: String = "ScalikeJDBC"

  override protected def beforeAll(): Unit = {
    Class.forName("org.h2.Driver")
    ConnectionPool.singleton("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1", "username", "password")
    DB localTx { implicit s =>
      val sqls = Seq(
        sql"create table members(id bigint auto_increment primary key, name varchar(50) not null);",
        sql"create table team_members(id bigint primary key auto_increment, team_id bigint not null)",
        sql"create table teams(id bigint primary key auto_increment, name varchar(50))"
      )
      sqls.foreach(_.update().apply())
    }
  }

  before {
    Class.forName("org.h2.Driver")
    ConnectionPool.singleton("jdbc:h2:mem:db", "username", "password")
  }

  /**
    * https://github.com/scalikejdbc/scalikejdbc-cookbook/blob/master/ja/03_connection.md
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
      ConnectionPool.singleton("jdbc:h2:mem:db", "user", "pass",
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
      a [SQLException] should be thrownBy {
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

        } catch { case e: Exception =>
          db.rollback()         // 例外が投げられる可能性があるロールバック
          db.rollbackIfActive() // 例外セーフなロールバック
          throw e
        }
      }
    }

    case class Member(id: Long, name: String)

    it("AutoSession ... 有効なセッションがなければ自動的に新しいセッションを開始する") {
      def createMember(name: String): Member = {
        val id = DB localTx { implicit s =>
          sql"insert into members(name) values(${name})".updateAndReturnGeneratedKey().apply()

          // localTx で囲っているので、この時点でトランザクションが終了してしまう
        }
        Member(id, name)
      }
      createMember("hoge")
      // この設計だと同じトランザクションを使って処理を続けられない

      // implicit parameter としてセッションを受け取るようにした
      def createMember2(name: String)(implicit s: DBSession = AutoSession): Member = {
        val id = sql"insert into members(name) values(${name})".updateAndReturnGeneratedKey().apply()
        Member(id, name)
      }

      // こうすることで、 localTx などを使うこともできるし、
      DB localTx { implicit s =>
        createMember2("foo")

        // 同じトランザクションを使う処理
      }

      // 単体で実行もできる
      createMember2("bar")
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
          'id -> 100L,
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
          'id -> 100L,
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
            insert.into(Team)
              .columns(column.id, column.name)
              .values(id, name)
          }.update().apply()
        }
      }

      val ordering = sqls"desc"
      val id = 1234

      val t = Team.syntax("t")
      val names = DB.readOnly { implicit s =>
        withSQL {
          select(t.name).from(Team as t).where.eq(t.id, id).orderBy(t.id).append(ordering)
        }.map(rs => rs.string(t.name)).list().apply()
      }
    }

  }

}
