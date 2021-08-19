package org.nomadblacky.scala.samples.scala_kansai.y2018

import java.io.IOException

import org.scalatest.{FunSpec, Matchers}

import scala.util.{Failure, Success, Try}

class N01PatternMatching extends FunSpec with Matchers {

  override def suiteName: String = "Readable Code in Scala ~ パターンマッチ編"

  it("match式おさらい") {
    val anyObject: Any = "nanika"
    anyObject match {
      case 1                          => "one"      // 値のマッチング
      case d: Double                  => d.toString // 型のマッチング
      case Some(x)                    => x.toString // 構造のマッチング
      case s: String if 5 <= s.length => s          // パターンガード
      case any                        => "Any"      // 任意の値
    }
  }

  case class User(name: Option[String], isActive: Boolean)

  val userList = List(
    User(Some("user1"), isActive = true),
    User(None, isActive = true),
    User(Some("user3"), isActive = false),
    User(Some("user444444444444"), isActive = true),
    User(Some("user555555555555"), isActive = false),
    User(None, isActive = false),
    User(Some("user7"), isActive = true)
  )

  val expect = List("user1", "user4", "user7")

  it("愚直に実装する") {

    /** + `List[User]` に対して + `isActive` が `true` のものだけを抜き出し + 名前が10文字以上の場合は最初の5文字だけを抜き出し + `List[String]` を返す
      */
    def extractUserNameWithTop10Chars(users: List[User]): List[String] = {
      users
        .withFilter(u => u.isActive)
        .withFilter(u => u.name.isDefined)
        .map { user =>
          user match {
            case User(Some(name), _) =>
              if (10 <= name.length) {
                name.take(5)
              } else {
                name
              }
          }
        }
    }

    extractUserNameWithTop10Chars(userList) shouldBe expect
  }

  it("パターンマッチを使ってみる") {
    def extractUserNameWithTop10Chars02(users: List[User]): List[String] = {
      users
        .withFilter(u => u.isActive)
        .withFilter(u => u.name.isDefined)
        .map { user =>
          user match {
            case User(Some(name), _) if 10 <= name.length => name.take(5)
            case User(Some(name), _)                      => name
          }
        }
    }
    extractUserNameWithTop10Chars02(userList) shouldBe expect

    def extractUserNameWithTop10Chars03(users: List[User]): List[String] = {
      users.flatMap { user =>
        user match {
          case User(Some(name), true) if 10 <= name.length => List(name.take(5))
          case User(Some(name), true)                      => List(name)
          case _                                           => Nil
        }
      }
    }
    extractUserNameWithTop10Chars03(userList) shouldBe expect
  }

  it("collectを使う") {
    def extractUserNameWithTop10Chars04(users: List[User]): List[String] =
      users.collect {
        case User(Some(name), true) if 10 <= name.length => name.take(5)
        case User(Some(name), true)                      => name
      }

    extractUserNameWithTop10Chars04(userList) shouldBe expect
  }

  it("PartialFunctionとは") {
    // trait PartialFunction[-A, +B] extends (A => B)
    // * 特定の引数に対してのみ結果を返す関数。
    // * 引数により値を返さない場合がある。

    val pf: PartialFunction[Int, String] = {
      case 0 => "zero"
      case i if i % 2 == 0 => "even"
    }

    // isDefinedAt ... 値をを返すか調べる
    pf.isDefinedAt(0) shouldBe true
    pf.isDefinedAt(1) shouldBe false
    pf.isDefinedAt(2) shouldBe true

    // lift ... 結果をOptionに包む
    pf.lift(0) shouldBe Some("zero")
    pf.lift(1) shouldBe None
    pf.lift(2) shouldBe Some("even")

    // タプルのリストに使う
    val strings = List(("a", 1), ("b", 2), ("c", 3)).map { tuple =>
      tuple._1 * tuple._2
    }
    val strings2 = List(("a", 1), ("b", 2), ("c", 3)).map { case (str, times) =>
      str * times
    }
    strings shouldBe List("a", "bb", "ccc")
    strings shouldBe strings2

    def extractUserNameWithTop10Chars05(users: List[User]): List[String] = {
      users.flatMap {
        case User(Some(name), true) if 10 <= name.length => Some(name.take(5))
        case User(Some(name), true)                      => Some(name)
        case _                                           => None
      }
    }
    extractUserNameWithTop10Chars05(userList) shouldBe expect
  }

  describe("標準ライブラリにおけるPartialFunctionの利用例") {
    it("TraversableOnce#collectFirst") {
      def isActiveUser(username: String): Option[Boolean] =
        userList.find(_.name.contains(username)).map(_.isActive)

      isActiveUser("user1") shouldBe Some(true)
      isActiveUser("user3") shouldBe Some(false)
      isActiveUser("unknown") shouldBe None

      def isActiveUser2(username: String): Option[Boolean] =
        userList.collectFirst {
          case User(Some(name), isActive) if name == username => isActive
        }

      isActiveUser2("user1") shouldBe Some(true)
      isActiveUser2("user3") shouldBe Some(false)
      isActiveUser2("unknown") shouldBe None
    }

    it("Try#recoverWith") {
      def storeUser(user: User): Try[Unit] = Try {
        if (user.isActive) println("stored") else throw new IOException
      }
      def storeError(t: Throwable): Try[Unit] = Try(throw new IllegalStateException)

      def tryStoringUser(user: User): Try[Unit] = {
        storeUser(user) match {
          case Success(_)              => Success(())
          case Failure(e: IOException) => storeError(e)
          case Failure(e)              => Failure(e)
        }
      }
      tryStoringUser(User(None, isActive = true)) shouldBe Success(())
      tryStoringUser(User(None, isActive = false)) shouldBe a[Failure[IllegalStateException]]

      def tryStoringUser2(user: User): Try[Unit] =
        storeUser(user).recoverWith { case e: IOException =>
          storeError(e)
        }
      tryStoringUser2(User(None, isActive = true)) shouldBe Success(())
      tryStoringUser2(User(None, isActive = false)) shouldBe a[Failure[IllegalStateException]]
    }
  }
}
