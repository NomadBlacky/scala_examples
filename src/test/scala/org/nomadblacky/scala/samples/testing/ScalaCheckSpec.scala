package org.nomadblacky.scala.samples.testing

import org.scalacheck.{Arbitrary, Gen}
import org.scalatest.{FunSpec, Matchers}
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class ScalaCheckSpec extends FunSpec with Matchers with GeneratorDrivenPropertyChecks {

  it("forAll ... ランダムに生成された値でテストを行う") {
    forAll { (s1: String, s2: String) =>
      s1.length + s2.length shouldBe (s1 + s2).length
    }
  }

  it("whenever ... 値のフィルタ") {
    forAll { (i: Int) =>
      whenever(1 < i) {
        i / 2 should be > 0
      }
    }
  }

  case class User(name: String, age: Int) {
    def isAdult: Boolean = 20 <= age
  }

  it("Gen ... 値を生成するジェネレータ") {
    val userGen: Gen[User] = for {
      name <- Gen.alphaNumStr
      age  <- Gen.choose(1, 100)
    } yield User(name, age)

    forAll(userGen) { (u: User) =>
      (1 <= u.age && u.age <= 100) shouldBe true
    }
  }

  it("suchThat ... ジェネレータに対するフィルタ") {
    val userGen = for {
      name <- Gen.alphaNumStr
      age  <- Gen.choose(1, 100).suchThat(20 <= _)
    } yield User(name, age)

    forAll(userGen)(_.isAdult shouldBe true)

    // for式のガードでも同様
    val userGen2 = for {
      name <- Gen.alphaNumStr
      age  <- Gen.choose(1, 100) if 20 <= age
    } yield User(name, age)

    forAll(userGen2)(_.isAdult shouldBe true)
  }

  it("Arbitrary ... forAllのimplicit parameterとして使う") {
    val userGen = for {
      name <- Gen.alphaNumStr
      age  <- Gen.choose(1, 100).suchThat(20 <= _)
    } yield User(name, age)

    implicit val arbitraryUser: Arbitrary[User] = Arbitrary(userGen)

    forAll { (u: User) =>
      u.isAdult shouldBe true
    }
  }
}
