package org.nomadblacky.scala.samples.scala

import org.scalatest.{FunSpec, Matchers}

/**
  * 文字列の補間(String Interpolation)
  * データを利用して文字列を加工できる。
  */
class StringInterpolationSpec extends FunSpec with Matchers {

  it("s補間子") {
    // 文字列リテラルで直接変数を扱ったり、式を埋め込むことができる
    val age = 20
    s"I'm $age years old." shouldBe "I'm 20 years old."
    s"I'm ${age / 2} years old." shouldBe "I'm 10 years old."
  }

  it("f補間子") {
    // printf のような形式で書式を設定できる
    val name = "Mike"
    val height = 160.5
    f"$name%s is $height%2.2f meters tall." shouldBe "Mike is 160.50 meters tall."
  }

  it("raw補間子") {
    // エスケープを実行しない、生の文字列を定義する
    raw"hoge\nfoo" shouldBe "hoge\\nfoo"
    raw"hoge\nfoo" shouldBe """hoge\nfoo"""
  }
}
