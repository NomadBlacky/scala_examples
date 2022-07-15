package dev.nomadblacky.scala_examples.basics

import com.github.ghik.silencer.silent
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class VariousFeaturesSpec extends AnyFunSpec with Matchers {

  override def suiteName: String = "その他細かな機能やAPI"

  describe("scala.language.Dynamic ... 動的言語のような構文をサポートする") {
    // Dynamicの構文を利用するのに必要
    import scala.language.dynamics

    it("applyDynamic") {
      case class MyMap[V](m: Map[String, V]) extends Dynamic {
        def applyDynamic(key: String)(default: V): V = m.getOrElse(key, default)
      }
      val mymap = MyMap(Map("aaa" -> 10))

      // フィールドに対してapplyしているような構文を書ける
      mymap.aaa(-1) shouldBe 10
      mymap.bbb(-1) shouldBe -1
    }

    it("applyDynamicNamed") {
      case class MyMap[V](m: Map[String, V]) extends Dynamic {
        def applyDynamicNamed[U](key: String)(args: (String, V => U)*): Map[String, U] = {
          val kvs = for {
            v      <- m.lift(key).toSeq
            (k, f) <- args
          } yield (k, f(v))
          kvs.toMap
        }
      }
      val mymap = MyMap(Map("aaa" -> 10))

      val f = (i: Int) => i * 2
      val g = (i: Int) => i * 10
      mymap.aaa(x = f, y = g) shouldBe Map("x" -> 20, "y" -> 100)
      mymap.bbb(x = f, y = g) shouldBe Map()
    }

    it("selectDynamic") {
      case class MyMap[V](m: Map[String, V]) extends Dynamic {
        def selectDynamic(key: String): Option[V] = m.get(key)
      }
      val mymap = MyMap(Map("aaa" -> 10))

      mymap.aaa shouldBe Some(10)
      mymap.bbb shouldBe None
    }

    it("updateDynamic") {
      case class MyMap[V](m: Map[String, V]) extends Dynamic {
        def updateDynamic(key: String)(value: V): Map[String, V] = m + (key -> value)
      }
      val mymap = MyMap(Map("aaa" -> 10))

      (mymap.aaa = 100) shouldBe Map("aaa" -> 100)
      (mymap.bbb = 200) shouldBe Map("aaa" -> 10, "bbb" -> 200)
    }
  }

  describe("String Interpolation") {
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
      import StringInterpolations._

      val num = 1
      val str = "STR"

      spacing"hoge" shouldBe "h o g e"
      getParts"hoge, $num, foo, ${num + 1}, bar, $str" shouldBe Seq("hoge, ", ", foo, ", ", bar, ", "")
      args"hoge, $num, foo, ${num + 1}, bar, $str" shouldBe Seq(1, 2, "STR")
      double"hoge, $num, foo, ${num + 1}, bar, $str" shouldBe "hoge, 2, foo, 4, bar, STRSTR"
    }
  }
}

object StringInterpolations {
  implicit class MyStringInterpolation(val sc: StringContext) extends AnyVal {
    def spacing(args: Any*): String = sc.parts.flatten.mkString(" ")

    def getParts(args: Any*): Seq[String] = sc.parts

    def args(args: Any*): Seq[Any] = args

    @silent("match may not be exhaustive")
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
