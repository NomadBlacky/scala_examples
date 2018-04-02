package org.nomadblacky.scala.samples.testing

import org.scalacheck.Gen
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

  case class User(id: Long, name: String) {
    def isValidName: Boolean = 1 <= name.length && name.length <= 10
  }

  it("Gen ... 値を生成するジェネレータ") {
    pending

    val userGen: Gen[User] = for {
      id   <- Gen.choose(1, 999999L)
      name <- Gen.alphaNumStr
    } yield User(id, name)

    forAll(userGen) { (u: User) =>
      whenever(u.name.isEmpty) {
        u.isValidName shouldBe false
      }
    }
  }
}
