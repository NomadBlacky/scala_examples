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
    // 1,2,3 の中からひとつランダムに生成されるGen
    val intGen: Gen[Int] = Gen.oneOf(1, 2, 3)

    forAll(intGen) { i =>
      (1 <= i && i <= 3) shouldBe true
    }

    // for式でGenを組み合わせられる
    val userGen: Gen[User] = for {
      name <- Gen.oneOf("a", "b", "c")
      age  <- Gen.oneOf(10, 20, 30)
    } yield User(name, age)

    forAll(userGen) { (u: User) =>
      (u.name + u.age.toString).length shouldBe 3
    }
  }

  it("Gen.oneOf ... 複数の要素からひとつを選択する") {
    val stringGen = Gen.oneOf("hoge", "foo", "bar")
    forAll(stringGen)(s => s.toLowerCase shouldEqual s)
  }

  it("Gen.someOf ... 複数の要素から0個以上選択する") {
    val stringListGen = Gen.someOf(Seq("a", "b", "c"))
    forAll(stringListGen)(ss => ss.mkString shouldBe ss.fold("")(_ + _))
  }

  it("Gen.choose ... 範囲の中からひとつ選択する") {
    val intGen = Gen.choose(-100, 100)
    forAll(intGen)(i => i * 2 shouldEqual i + i)
  }

  it("Gen.alphaNumStr ... ランダムな文字列を生成する") {
    val stringGen = Gen.alphaNumStr
    forAll(stringGen)(_.contains("@") shouldEqual false)

    // そのほかのバリエーション
    forAll(Gen.alphaStr)(_.contains("0") shouldEqual false)
    forAll(Gen.alphaLowerStr)(_.contains("A") shouldEqual false)
    forAll(Gen.alphaUpperStr)(_.contains("a") shouldEqual false)
    forAll(Gen.numStr)(s => (s.contains("a") || s.contains("A")) shouldEqual false)
  }

  it("Gen#suchThat ... ジェネレータに対するフィルタ") {
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
