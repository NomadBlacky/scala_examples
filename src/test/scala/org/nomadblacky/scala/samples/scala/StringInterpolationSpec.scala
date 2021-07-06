package org.nomadblacky.scala.samples.scala

import org.scalatest.{FunSpec, Matchers}

/** 文字列の補間(String Interpolation)
  * データを利用して文字列を加工できる。
  */
class StringInterpolationSpec extends FunSpec with Matchers {

  override def suiteName: String = "文字列の補間 (String Interpolation)"

  it("s補間子") {
    // 文字列リテラルで直接変数を扱ったり、式を埋め込むことができる
    val age = 20
    s"I'm $age years old." shouldBe "I'm 20 years old."
    s"I'm ${age / 2} years old." shouldBe "I'm 10 years old."
  }

  it("f補間子") {
    // printf のような形式で書式を設定できる
    val name   = "Mike"
    val height = 160.5
    f"$name%s is $height%2.2f meters tall." shouldBe "Mike is 160.50 meters tall."
  }

  it("raw補間子") {
    // エスケープを実行しない、生の文字列を定義する
    raw"hoge\nfoo" shouldBe "hoge\\nfoo"
    raw"hoge\nfoo" shouldBe """hoge\nfoo"""
  }

  it("自分で実装する") {
    import StringInterpolationSpec._

    val num = 1
    val str = "STR"

    spacing"hoge" shouldBe "h o g e"
    getParts"hoge, $num, foo, ${num + 1}, bar, $str" shouldBe Seq("hoge, ", ", foo, ", ", bar, ", "")
    args"hoge, $num, foo, ${num + 1}, bar, $str" shouldBe Seq(1, 2, "STR")
    double"hoge, $num, foo, ${num + 1}, bar, $str" shouldBe "hoge, 2, foo, 4, bar, STRSTR"
  }
}

object StringInterpolationSpec {

  implicit class MyStringInterpolation(val sc: StringContext) extends AnyVal {
    def spacing(args: Any*): String       = sc.parts.flatten.mkString(" ")
    def getParts(args: Any*): Seq[String] = sc.parts
    def args(args: Any*): Seq[Any]        = args
    def double(args: Any*): String = {
      val ai = args.iterator
      val f = (a: Any) =>
        a match {
          case i: Int    => i * 2
          case s: String => s + s
        }
      sc.parts.reduceLeft { (acc, s) =>
        acc + f(ai.next()) + s
      }
    }

    // ↓ 利用するとコンパイル通らない。なんで?
    def parts(args: Any*): Seq[String] = sc.parts
    /*
     * [error] /home/blacky/projects/scala/samples/src/test/scala/org/nomadblacky/scala/samples/scala/StringInterpolationSpec.scala:41:13: not enough arguments for method apply: (idx: Int)String in trait SeqLike.
     * [error] Unspecified value parameter idx.
     * [error]     parts"hoge, $num, foo, ${num + 1}, bar, $str"
     * [error]     ^
     */
  }

}
