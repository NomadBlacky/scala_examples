
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
      sql"""
            create table members(
              id bigint auto_increment primary key,
              name varchar(50) not null
            )
        """
        .stripMargin
        .execute
        .apply()
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
      SQL(
        """insert into members(id, name) values (
          |  /*'id*/12345,
          |  /*'name*/'dummy-user'
          |)
        """.stripMargin)
        .bindByName(
          'id -> 100L,
          'name -> "user01"
        )
    }
  }

}
