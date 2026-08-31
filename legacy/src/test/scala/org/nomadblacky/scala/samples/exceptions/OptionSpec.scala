package org.nomadblacky.scala.samples.exceptions

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

/** Created by blacky on 16/12/04.
  */
class OptionSpec extends AnyFunSpec with Matchers {

  override def suiteName: String = "Option - 値が存在しない可能性があることを表すクラス"

  it("Optionの基本") {
    // Option[+A] ... 値が存在しない可能性があることを表すクラス

    // Some の場合は値が存在し、
    val o1: Option[Int] = Some(1)

    // None の場合は値が存在しない
    val o2: Option[Int] = None

    o1.get shouldBe 1

    // Noneのときにgetするとエラー
    assertThrows[NoSuchElementException](o2.get)
  }

  it("Optionから値を取り出す") {
    val o1 = Some(1)
    val o2 = None

    // 主にmatch式や、
    o1 match {
      case Some(i) => i shouldBe 1
      case _       => fail()
    }
    o2 match {
      case None =>
      case _    => fail()
    }

    // getOrElseを使う
    o1.getOrElse(2) shouldBe 1
    o2.getOrElse(2) shouldBe 2
  }

  it("foreach ... Optionに値が含まれる場合のみに実行させる") {
    val o1 = Some(1)
    val o2 = None

    // foreach を使うと、Someの場合のみに実行させるといったことができる
    // 要素数1のリストとイメージすると foreach という命名がわかりやすい
    o1 foreach { i =>
      i shouldBe 1
    }
    o2 foreach { _ =>
      fail()
    }
  }

  it("map ... 中身の値を関数に適用し値を変換する") {
    val o1: Option[Int] = Some(1)
    val o2: Option[Int] = None

    o1.map(_ + 10) shouldBe Some(11)
    o2.map(_ + 10) shouldBe None
  }

  it("flatMap ... 中身の値を関数に適用し、SomeならSomeを、NoneならNoneを返す") {
    val o1: Option[Int] = Some(1)
    val o2: Option[Int] = None

    o1.flatMap(i => Some(i + 10)) shouldBe Some(11)
    o1.flatMap(_ => None) shouldBe None
    o2.flatMap(i => Some(i + 10)) shouldBe None
    o2.flatMap(_ => None) shouldBe None
  }

  it("collect ... PartialFunctionを適用し、値が返る場合はその結果をSomeに包んで返す") {
    val o1: Option[Int]   = Some(1)
    val o2: Option[Int]   = Some(2)
    val none: Option[Int] = None

    val pf: PartialFunction[Int, String] = { case 1 =>
      "one"
    }
    o1.collect(pf) shouldBe Some("one")
    o2.collect(pf) shouldBe None
    none.collect(pf) shouldBe None

    // Someを返す関数を渡すflatMapはcollectで簡略化できる
    val pf2: PartialFunction[Int, Option[String]] = {
      case 1 => Some("one")
      case _ => None
    }
    o1.flatMap(pf2) shouldBe Some("one")
    o2.flatMap(pf2) shouldBe None
    none.flatMap(pf2) shouldBe None
  }

  it("fold ... Noneなら初期値を、Someなら関数を適用した値を返す") {
    val o1: Option[Int] = Some(10)
    val o2: Option[Int] = None

    o1.fold(-1)(_ * 10) shouldBe 100
    o2.fold(-1)(_ * 10) shouldBe -1

    // map と getOrElse を使った場合と同義
    o1.fold(-1)(_ * 10) shouldBe o1.map(_ * 10).getOrElse(-1)
    o2.fold(-1)(_ * 10) shouldBe o2.map(_ * 10).getOrElse(-1)
  }
}
