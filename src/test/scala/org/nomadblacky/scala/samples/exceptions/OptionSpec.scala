package org.nomadblacky.scala.samples.exceptions

import org.scalatest.FunSpec

/**
  * Created by blacky on 16/12/04.
  */
class OptionSpec extends FunSpec {

  it("Optionの基本") {
    // Option[+A] ... 値が存在しない可能性があることを表すクラス

    // Some の場合は値が存在し、
    val o1:Option[Int] = Some(1)
    // None の場合は値が存在しない
    val o2:Option[Int] = None

    assert(o1.get == 1)
    assertThrows[NoSuchElementException] {
      o2.get
    }
  }

  it("Optionから値を取り出す") {
    val o1 = Some(1)
    val o2 = None

    // 主にmatch式や、
    o1 match {
      case Some(i) => assert(i == 1)
      case _ => fail()
    }
    o2 match {
      case None =>
      case _ => fail()
    }

    // getOrElseを使う
    assert(o1.getOrElse(2) == 1)
    assert(o2.getOrElse(2) == 2)
  }

  it("foreach ... Optionに値が含まれる場合のみに実行させる") {
    val o1 = Some(1)
    val o2 = None

    // foreach を使うと、Someの場合のみに実行させるといったことができる
    // Optionは要素数1のリストというイメージ
    o1 foreach { i => assert(i == 1) }
    o2 foreach { _ => fail() }
  }

  it("map ... 中身の値を関数に適用し値を変換する") {
    val o1: Option[Int] = Some(1)
    val o2: Option[Int] = None

    assert(o1.map(_ + 10) == Some(11))
    assert(o2.map(_ + 10) == None)
  }

  it("flatMap ... 中身の値を関数に適用し、SomeならSomeを、NoneならNoneを返す") {
    val o1: Option[Int] = Some(1)
    val o2: Option[Int] = None

    assert(o1.flatMap(i => Some(i + 10)) == Some(11))
    assert(o1.flatMap(_ => None) == None)
    assert(o2.flatMap(i => Some(i + 10)) == None)
    assert(o2.flatMap(_ => None) == None)
  }
}
