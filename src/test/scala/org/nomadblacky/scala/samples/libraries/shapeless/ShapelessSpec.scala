package org.nomadblacky.scala.samples.libraries.shapeless

import org.scalatest.{FunSpec, Matchers}
import shapeless._

/** Shapeless
  *
  * Scalaでジェネリックプログラミングをサポートするライブラリ
  */
class ShapelessSpec extends FunSpec with Matchers {

  override def suiteName: String = "Shapeless"

  it("Poly ... 複数の型を処理できる関数") {
    object size extends Poly1 {
      implicit def caseInt    = at[Int](_ => 1)
      implicit def caseString = at[String](_.length)
      implicit def caseTuple[T, U](implicit st: Case.Aux[T, Int], su: Case.Aux[U, Int]) =
        at[(T, U)](t => size(t._1) + size(t._2))
    }

    size(12) shouldBe 1
    size("hoge") shouldBe 4
    size((12, "hoge")) shouldBe 5
    size(((12, "hoge"), 99)) shouldBe 6
    // 対応する型が定義されていないのでコンパイルエラー
    // size(1.0)
  }

  it("HList ... 複数の型を持てるList") {
    val xs = 1 :: "hoge" :: HNil

    // 要素の追加
    xs :+ 3.0 shouldBe 1 :: "hoge" :: 3.0 :: HNil
    4.0 +: xs shouldBe 4.0 :: 1 :: "hoge" :: HNil

    // 要素の取得
    xs(0) shouldBe 1
    xs.head shouldBe 1
    xs.last shouldBe "hoge"
  }

  it("HListの操作") {
    val xs = 1 :: "hoge" :: HNil

    object minusOne extends Poly1 {
      implicit val caseInt    = at[Int](_ - 1)
      implicit val caseString = at[String](_.init)
    }
    xs.map(minusOne) shouldBe (0 :: "hog" :: HNil)

    object totalSize extends Poly2 {
      implicit val caseInt    = at[Int, Int](_ + _)
      implicit val caseString = at[Int, String](_ + _.length)
    }
    xs.foldLeft(0)(totalSize) shouldBe 5
  }

  it("Coproduct ... Eitherを任意の数の選択肢にしたもの") {
    // Int or String or Boolean
    type ISB = Int :+: String :+: Boolean :+: CNil

    // 値を定義
    val isb = Coproduct[ISB]("hoge")

    // 値を取得
    isb.select[Int] shouldBe None
    isb.select[String] shouldBe Some("hoge")
    isb.select[Boolean] shouldBe None
    // コンパイルエラー
    // isb.select[Double]

    // 変換
    object size extends Poly1 {
      implicit val intCase     = at[Int](i => (i, i))
      implicit val stringCase  = at[String](s => (s, s.length))
      implicit val booleanCase = at[Boolean](b => (b, if (b) 1 else 0))
    }
    val result = isb.map(size)
    type SIZE = (Int, Int) :+: (String, Int) :+: (Boolean, Int) :+: CNil

    result shouldBe a[SIZE]
    result.select[(Int, Int)] shouldBe None
    result.select[(String, Int)] shouldBe Some(("hoge", 4))
    result.select[(Boolean, Int)] shouldBe None
    // コンパイルエラー
    // result.select[(Double, Double)]
  }

  it("Generic ... case classなどをHListやCoproductに変換する") {
    // HList
    case class User(id: Int, name: String, age: Int)

    val userGen = Generic[User]

    val user = User(1, "hoge", 20)
    userGen.to(user) shouldBe (1 :: "hoge" :: 20 :: HNil)
    userGen.from(2 :: "foo" :: 30 :: HNil) shouldBe User(2, "foo", 30)

    // Coproduct
    sealed trait Lang
    case class Java()  extends Lang
    case class Scala() extends Lang

    val lang = Generic[Lang].to(Scala())

    lang.select[Java] shouldBe None
    lang.select[Scala] shouldBe Some(Scala())
  }

  it("Record ... HListにキーがついたもの") {
    import syntax.singleton._
    import record._

    val user =
      ("id" ->> 1) ::
        ("name" ->> "hoge") ::
        ("age" ->> 20) ::
        HNil

    user("id") shouldBe 1
    user("name") shouldBe "hoge"
    user("age") shouldBe 20
    // 定義されていないのでコンパイルエラー
    // user("foo")
  }

  it("Sindleton-typed literals ... リテラルをひとつの型として扱う") {
    import syntax.singleton._

    // 1しか受け付けない関数
    val one         = 1.witness
    def f(x: one.T) = x

    f(1) shouldBe 1
    // コンパイルエラー
    // f(2)
  }

  it("case classをMapに変換する") {
    import Mappable._

    case class User(id: Int, name: String, age: Int)
    val user = User(1, "hoge", 20)

    user.toMap shouldBe Map("id" -> 1, "name" -> "hoge", "age" -> 20)
  }

}
