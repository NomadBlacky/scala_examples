package org.nomadblacky.scala.samples.libraries.cats

import cats.data.NonEmptyList
import org.scalatest.{FunSpec, Matchers}

class CatsSpec extends FunSpec with Matchers {

  override def suiteName: String = "Catsの基礎"

  it("HogeSyntaxで型クラスが提供される") {
    // cats.syntax 以下のパッケージで数々の型クラスが提供されている。
    // 例えば、ListSyntaxでは、Listに対する拡張が提供されている。
    import cats.syntax.list._

    List().toNel shouldBe None
    // NonEmptyList はひとつ以上の要素が含まれるリスト
    List(1, 2, 3).toNel shouldBe Some(NonEmptyList.of(1, 2, 3))

    List("hoge", "foo", "bar").groupByNel(_.length) shouldBe Map(
      3 -> NonEmptyList.of("foo", "bar"),
      4 -> NonEmptyList.of("hoge")
    )
  }
}
