package org.nomadblacky.scala.samples.libraries.cats

import cats.data.NonEmptyList
import org.scalatest.{FunSpec, Matchers}

class CatsSpec extends FunSpec with Matchers {

  override def suiteName: String = "Catsの基礎"

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
}
