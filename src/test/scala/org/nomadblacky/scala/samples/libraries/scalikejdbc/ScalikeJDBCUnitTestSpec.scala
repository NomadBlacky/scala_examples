package org.nomadblacky.scala.samples.libraries.scalikejdbc

import org.scalatest.{BeforeAndAfterAll, Matchers, fixture}
import scalikejdbc._
import scalikejdbc.config.DBs
import scalikejdbc.scalatest.AutoRollback

class ScalikeJDBCUnitTestSpec extends fixture.FunSpec with Matchers with BeforeAndAfterAll with AutoRollback {

  override def suiteName: String = "ScalikeJDBCでのユニットテスト"

  object User {
    def create(name: String, organization: Option[String])(implicit session: DBSession): Unit =
      sql"insert into users2(name, organization) values($name, $organization)".update().apply()

    def count()(implicit session: DBSession): Long =
      sql"select count(1) from users2".map(_.long(1)).single().apply().get
  }

  override protected def beforeAll(): Unit = {
    DBs.setupAll()

    DB.localTx { implicit s =>
      sql"create table users2(id bigint primary key auto_increment, name varchar(50) not null, organization varchar(50))"
        .update()
        .apply()
    }
  }

  override def fixture(implicit session: DBSession): Unit = {
    (1 to 3).foreach { i =>
      User.create(s"User$i", Some(s"Org$i"))
    }
  }

  describe("ユニットテスト") {

    it("接続情報の設定") { implicit s =>
      // scalikejdbc-config を依存関係に追加する。
      // DBs.setupAll() を呼ぶと、 application.conf を読み込んで接続情報を初期化する
      import scalikejdbc.config._
      DBs.setupAll()
      DB.getAllTableNames() contains "users2"
    }

    it("自動ロールバック") { implicit session =>
      // AutoRollback トレイトをmixinして、テストのパラメタにimplicitでDBSessionを受け取る
      // テストごとにひとつのトランザクションが生成され、テスト終了後にロールバックされる。
      User.create("User4", None)
      User.count() shouldBe 4
    }
  }
}
