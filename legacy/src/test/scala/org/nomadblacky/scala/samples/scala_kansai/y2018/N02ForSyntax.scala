package org.nomadblacky.scala.samples.scala_kansai.y2018


import scala.util.Try
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class N02ForSyntax extends AnyFunSpec with Matchers {

  override def suiteName: String = "Readable Code in Scala ~ for式編"

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

  describe("for式のおさらい") {
    it("for式は withFilter,flatMap,map,foreach のシュガーシンタックス") {
      def findUser(name: String): Option[User] = userList.find(_.name.contains(name))

      // これと
      def namePair(userName1: String, userName2: String): Option[(String, String)] =
        findUser(userName1).flatMap { user1 =>
          user1.name.flatMap { user1Name =>
            findUser(userName2).flatMap { user2 =>
              user2.name.withFilter(userName2 => 10 <= userName2.length).map { user2Name =>
                (user1Name, user2Name.take(5))
              }
            }
          }
        }

      namePair("user1", "user2") shouldBe None
      namePair("user1", "user444444444444") shouldBe Some(("user1", "user4"))

      // これは同義
      def namePair2(userName1: String, userName2: String): Option[(String, String)] =
        for {
          user1     <- findUser(userName1)
          user1Name <- user1.name
          user2     <- findUser(userName2)
          user2Name <- user2.name if 10 <= userName2.length
        } yield (user1Name, user2Name.take(5))

      namePair2("user1", "user2") shouldBe None
      namePair2("user1", "user444444444444") shouldBe Some(("user1", "user4"))

      // yield がないと foreach 展開となる
    }

    it("mapの展開") {
      for {
        a <- List(1, 2, 3, 4, 5)
      } yield a * 2

      List(1, 2, 3, 4, 5).map(a => a * 2)
    }

    it("withFilterの展開") {
      for {
        a <- List(1, 2, 3, 4, 5) if a < 3
      } yield a * 2

      List(1, 2, 3, 4, 5)
        .withFilter(a => a < 3)
        .map(a => a * 2)
    }

    it("flatMapの展開") {
      for {
        a <- List(1, 2, 3) if a < 3
        b <- List(4, 5, 6)
      } yield a * b

      List(1, 2, 3).withFilter(a => a < 3).flatMap { a =>
        List(4, 5, 6).map { b =>
          a * b
        }
      }
    }

    it("foreachの展開") {
      for {
        a <- List(1, 2, 3) if a < 3
        b <- List(4, 5, 6)
      } println(a * b)

      List(1, 2, 3).withFilter(a => a < 3).foreach { a =>
        List(4, 5, 6).foreach { b =>
          println(a * b)
        }
      }
    }

    it("for式を紐解いてみよう") {
      // これを展開(desugar)するとどうなる?
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

  describe("本当にあった怖いfor式") {

    it("恐ろしく長いfor式") {
      def とてもすごい処理: Try[Seq[String]] = {
        for {
          activeUsersGroupIds <- Try {
            memberRepository.resolveAll().collect {
              case Member(_, _, isActive, Some(groupId)) if isActive => groupId
            }
          }
          activeGroupNames <- Try {
            groupRepository.resolveIn(activeUsersGroupIds.toSet).collect {
              case Group(_, name, isDeleted) if !isDeleted => name
            }
          }
          // 100行近く続く …
          sugoiResult: Seq[String] = {
            {
              {
                Seq( /* なにか */ )
              }
            }
          }
        } yield sugoiResult
      }
    }

    case class Member(id: Long, name: String, isActive: Boolean, groupId: Option[Long])
    case class Group(id: Long, name: String, isDeleted: Boolean)

    object memberRepository {
      def resolveAll(): Seq[Member] = ???
    }
    object groupRepository {
      def resolveIn(ids: Set[Long]): Seq[Group] = ???
    }

    it("Bad Practice: ジェネレータに処理を詰め込む") {
      def resolveActiveGroupNames: Try[Seq[String]] = {
        for {
          activeUsersGroupIds <- Try {
            memberRepository.resolveAll().collect {
              case Member(_, _, isActive, Some(groupId)) if isActive => groupId
            }
          }
          activeGroupNames <- Try {
            groupRepository.resolveIn(activeUsersGroupIds.toSet).collect {
              case Group(_, name, isDeleted) if !isDeleted => name
            }
          }
        } yield activeGroupNames
      }
    }

    it("内部関数やprivateメソッドに切り出す") {
      def resolveActiveGroupNames: Try[Seq[String]] = {
        for {
          members <- resolveAllMembers()
          groupIds = extractGroupIdFromActiveMember(members)
          groups <- resolveGroupsIn(groupIds)
          activeGroupNames = extractNameFromExistsGroup(groups)
        } yield activeGroupNames
      }

      def resolveAllMembers() =
        Try(memberRepository.resolveAll())

      def extractGroupIdFromActiveMember(members: Seq[Member]) = members.collect {
        case Member(_, _, isActive, Some(groupId)) if isActive => groupId
      }

      def resolveGroupsIn(groupIds: Seq[Long]) = Try(groupRepository.resolveIn(groupIds.toSet))

      def extractNameFromExistsGroup(groups: Seq[Group]) = groups.collect {
        case Group(_, name, isDeleted) if !isDeleted => name
      }
    }

    it("複数のfor式に書き換える") {
      def resolveActiveGroupNames: Try[Seq[String]] = {
        for {
          groupIds   <- resolveActiveGroupIds()
          groupNames <- resolveExistsGroupNameIn(groupIds)
        } yield groupNames
      }

      def resolveActiveGroupIds(): Try[Seq[Long]] =
        for {
          members <- resolveAllMembers()
          groupIds = extractGroupIdFromActiveMember(members)
        } yield groupIds

      def resolveExistsGroupNameIn(groupIds: Seq[Long]) =
        for {
          groups <- resolveGroupsIn(groupIds)
          activeGroupNames = extractNameFromExistsGroup(groups)
        } yield activeGroupNames

      def resolveAllMembers() =
        Try(memberRepository.resolveAll())

      def extractGroupIdFromActiveMember(members: Seq[Member]) = members.collect {
        case Member(_, _, isActive, Some(groupId)) if isActive => groupId
      }

      def resolveGroupsIn(groupIds: Seq[Long]) = Try(groupRepository.resolveIn(groupIds.toSet))

      def extractNameFromExistsGroup(groups: Seq[Group]) = groups.collect {
        case Group(_, name, isDeleted) if !isDeleted => name
      }
    }
  }
}
