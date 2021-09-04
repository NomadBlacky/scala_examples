package dev.nomadblacky.scala_examples.basics

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class EnumInScalaSpec extends AnyFunSpec with Matchers {

  override def suiteName: String = "Scalaで列挙型を扱う"

  it("Enumerationを使った方法") {
    // 単純な列挙型を扱う場合はEnumerationを使う
    object Enum extends Enumeration {
      val One, Two, Three = Value
    }

    // 列挙型はそれぞれidを持つ
    import Enum._
    One.id shouldBe 0
    Two.id shouldBe 1

    // 列挙体は順序を持つ
    One < One shouldBe false
    One <= One shouldBe true
    One < Two shouldBe true
    One <= Two shouldBe true

    // valuesで列挙体のSetが返る
    Enum.values shouldBe Enum.ValueSet(One, Two, Three)
  }

  it("余計な `Value` を排除する") {
    object Enum extends Enumeration {
      // `type` を使うことで、型につく余計な `Value` を排除できる
      type Enum = Value
      val One, Two, Three = Value
    }
    import Enum._

    //val enumOne: Enum.Value = Enum.One
    //                  ↑これが余計
    val enumOne: Enum = One

    enumOne.id shouldBe 0
  }

  it("列挙型に値をもたせる") {
    object Enum extends Enumeration {
      type Enum = Value

      // Valを継承したクラスを定義して、列挙型とする
      case class EnumVal(toDouble: Double) extends Val

      val One   = EnumVal(1.0)
      val Two   = EnumVal(2.0)
      val Three = EnumVal(3.0)
    }
    import Enum._

    One.toDouble shouldBe 1.0
    Two.toDouble shouldBe 2.0
    Three.toDouble shouldBe 3.0

    // しかし、valuesで取ってきた列挙型はEnum.Valueのため、直接値は取れない…
    val enumOne: Enum.Value = Enum.values.head
    // コンパイルエラー
    //enumOne.toDouble

    // こう…?
    enumOne match {
      case EnumVal(d) => d shouldBe 1.0
      case _          => fail()
    }
  }

  it("sealed trait と case objectを使った列挙型") {
    sealed trait Enum
    case object One   extends Enum
    case object Two   extends Enum
    case object Three extends Enum

    val enum: Enum = One
    enum match {
      case One   => succeed
      case Two   => fail()
      case Three => fail()
    }
  }

  it("sealed abstract classで値と振る舞いをもたせる") {
    // この場合、列挙型というよりも代数的データ型である
    sealed abstract class Enum(val value: Int) {
      def describe: String = s"value: $value"
    }
    case object One                           extends Enum(1)
    case object Two                           extends Enum(2)
    final case class Three(otherName: String) extends Enum(3)

    val enum: Enum = Three("drei")
    enum match {
      case One => fail()
      case Two => fail()
      case t: Three =>
        t.value shouldBe 3
        t.otherName shouldBe "drei"
    }
  }
}
