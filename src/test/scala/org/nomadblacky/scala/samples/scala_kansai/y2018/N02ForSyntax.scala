package org.nomadblacky.scala.samples.scala_kansai.y2018

import org.scalatest.{FunSpec, Matchers}

import scala.util.Try

class N02ForSyntax extends FunSpec with Matchers {

  case class User(name: Option[String], isActive: Boolean)

  val userList = List(
    User(Some("user1"), isActive = true),
    User(None, isActive = true),
    User(Some("user3"), isActive = false),
    User(Some("tooooooooo long name user4"), isActive = true),
    User(Some("tooooooooo long name user5"), isActive = false),
    User(None, isActive = false),
    User(Some("user7"), isActive = true)
  )

  describe("for式のおさらい") {
    it("for式は withFilter,flatMap,map,foreach のシュガーシンタックス") {
      def findUser(name: String): Option[User] = userList.find(_.name.contains(name))

      // これと
      def namePair(userName1: String, userName2: String): Option[(String, String)] =
        findUser("user1").flatMap { user1 =>
          user1.name.flatMap { user1Name =>
            findUser("user2").flatMap { user2 =>
              user2.name.withFilter(userName2 => 10 <= userName2.length).map { user2Name =>
                (user1Name, user2Name)
              }
            }
          }
        }

      namePair("user1", "user2") shouldBe None
      namePair("user1", "user3") shouldBe Some("user1 & user3")

      // これは同義
      def namePair2(userName1: String, userName2: String): Option[(String, String)] =
        for {
          user1     <- findUser("user1")
          user1Name <- user1.name
          user2     <- findUser("user2")
          user2Name <- user2.name if 10 <= userName2.length
        } yield (user1Name, user2Name)

      namePair2("user1", "user2") shouldBe None
      namePair2("user1", "user3") shouldBe Some("user1 & user3")

      // yield がないと foreach 展開となる
    }

    it("for式を紐解いてみよう") {
      // これを展開(desuger)するとどうなる?
      for {
        i1 <- Try(10 / 2) if 0 < i1
        i2 <- Try(10 / 0) if 1 < i2
      } yield i1 + i2

      // ↓
      for {
        i1 <- Try(10 / 2).withFilter(i1 => 0 < i1)
        i2 <- Try(10 / 0).withFilter(i2 => 1 < i2)
      } yield i1 + i2

      // ↓
      Try(10 / 2).withFilter(i1 => 0 < i1).flatMap { i1 =>
        for {
          i2 <- Try(10 / 0).withFilter(i2 => 1 < i2)
        } yield i1 + i2
      }

      // ↓
      Try(10 / 2).withFilter(i1 => 0 < i1).flatMap { i1 =>
        Try(10 / 0).withFilter(i2 => 1 < i2).map { i2 =>
          i1 + i2
        }
      }

      // Scalaを使う上でfor式は可読性においても強力です。
      // ぜひ使いこなして行きましょう~
    }
  }

  describe("for式ベストプラクティス") {

    def resolveAllUsers(): Try[Seq[User]] = ???

    it("× ジェネレータに処理を詰め込む") {
      for {
      } yield
    }

    it("○ 内部関数やprivateメソッドに切り出す") {

    }

    it("処理の中断を例外で表現する") {

    }

    it("") {

    }
  }
}
