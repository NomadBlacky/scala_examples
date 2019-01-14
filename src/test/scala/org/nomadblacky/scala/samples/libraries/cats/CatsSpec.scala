package org.nomadblacky.scala.samples.libraries.cats

import cats.data.NonEmptyList
import org.scalatest.{FunSpec, Matchers}
import cats.instances.int._

class CatsSpec extends FunSpec with Matchers {

  override def suiteName: String = "[Cats] Catsの基礎"

  it("cats.syntax で型クラス・既存型に対する拡張などが提供される") {
    // cats.syntax 以下のパッケージで、
    // 型クラスや、既存クラスに対する拡張が提供されている。

    // 例えば、ListSyntaxでは、Listに対する拡張が提供されている。
    import cats.syntax.list._

    // NonEmptyList を返す #toNel
    // NonEmptyList はひとつ以上の要素が含まれるリスト
    List().toNel shouldBe None
    List(1, 2, 3).toNel shouldBe Some(NonEmptyList.of(1, 2, 3))

    // #groupByNel は要素を Map[Key, NonEmptyList[Value]] に変換する
    List("hoge", "foo", "bar").groupByNel(_.length) shouldBe Map(
      3 -> NonEmptyList.of("foo", "bar"),
      4 -> NonEmptyList.of("hoge")
    )
  }

  it("cats.instances で型クラスの実装が提供される") {
    // 型クラスShowは、toStringのような、人が読むための文字列を提供する型クラス
    // ShowSyntaxで AnyRef => Show の暗黙的変換が定義される
    import cats.syntax.show._

    // ただし、この時点では、Intに対するShowのインスタンスが定義されていない
    // ↓コンパイルエラー
//  10.show

    // cats.instnces 以下に型クラスのインスタンスが定義されている
    import cats.instances.int._

    // 利用できた
    10.show shouldBe "10"
  }

  it("Eq ... 型安全な等価比較を提供する") {
    // EqSyntax により、型安全な比較をするメソッドが提供される
    import cats.instances.string._
    import cats.syntax.eq._

    // FIXME: Conflict to org.scalastics.TripleEqualsSupport
//  "hoge" === "hoge" shouldBe true
    // 型安全なので、これはコンパイルエラー
//  "hoge" === 10 shouldBe false

    "hoge" =!= "hoge" shouldBe false
    // 同じくコンパイルエラー
//  "hoge" =!= 10 shouldBe true
  }

  it("Monoid ... 二項演算と単位元を持つ代数的構造") {
    import cats.Monoid
    import cats.syntax.monoid._

    implicit val joinMonoid: Monoid[String] = new Monoid[String] {
      override def empty                                 = ""
      override def combine(x: String, y: String): String = x + y
    }

    "a" |+| "b" shouldBe "ab"
  }

}
