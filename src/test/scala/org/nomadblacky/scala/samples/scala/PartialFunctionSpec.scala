package org.nomadblacky.scala.samples.scala

import org.scalatest.{FunSpec, Matchers}

import scala.util.{Failure, Success, Try}

/** Created by blacky on 17/07/09.
  *
  * PartialFunction ... 部分関数
  *
  * 特定の引数に対してのみ結果を返す関数。 引数により値を返さない場合がある。
  */
class PartialFunctionSpec extends FunSpec with Matchers {

  override def suiteName: String = "部分関数"

  it("PartialFunctionを定義する") {
    val pf: PartialFunction[Int, String] = {
      case 1 => "one"
      case 2 => "two"
    }
    pf(1) shouldBe "one"
    pf(2) shouldBe "two"
    // 引数がマッチしない場合はMatchErrorが投げられる
    assertThrows[MatchError](pf(3))
  }

  it("caseはPartialFunctionのシンタックスシュガー") {
    val pf1: PartialFunction[Int, String] = { case 1 =>
      "one"
    }
    val pf2: PartialFunction[Int, String] = new PartialFunction[Int, String] {
      override def isDefinedAt(x: Int): Boolean = x match {
        case 1 => true
        case _ => false
      }
      override def apply(v1: Int): String = v1 match {
        case 1 => "one"
        case _ => throw new MatchError(v1)
      }
    }

    pf1.isDefinedAt(1) shouldBe true
    pf2.isDefinedAt(1) shouldBe true

    pf1.isDefinedAt(2) shouldBe false
    pf2.isDefinedAt(2) shouldBe false

    pf1(1) shouldBe "one"
    pf2(1) shouldBe "one"

    assertThrows[MatchError](pf1(2))
    assertThrows[MatchError](pf2(2))
  }

  it("isDefinedAt ... 引数に対して値が返される場合はtrueを返す") {
    val pf: PartialFunction[Int, String] = {
      case i if i % 2 == 0 => "even"
    }
    pf.isDefinedAt(1) shouldBe false
    pf.isDefinedAt(2) shouldBe true
    pf.isDefinedAt(3) shouldBe false
    pf.isDefinedAt(4) shouldBe true
  }

  it("andThen ... PartialFunction合成する") {
    val pf1: PartialFunction[Int, String] = {
      case 1 => "one"
      case 2 => "two"
    }
    val pf2: PartialFunction[String, Int] = {
      case "one"   => 1
      case "three" => 3
    }
    val andThen = pf1 andThen pf2
    andThen(1) shouldBe 1
    andThen.isDefinedAt(2) shouldBe true
    assertThrows[MatchError](andThen(2))
    andThen.isDefinedAt(3) shouldBe false
    assertThrows[MatchError](andThen(3))
  }

  it("compose ... PartialFunctionを合成する") {
    val pf1: PartialFunction[Int, String] = {
      case 1 => "one"
      case 2 => "two"
    }
    val pf2: PartialFunction[String, Int] = {
      case "one"   => 1
      case "three" => 3
    }
    // Function1( PartialFunction(x) )
    // という形で合成されるので、戻り値の型はFunction1となる
    val compose = pf1 compose pf2
    compose("one") shouldBe "one"
    assertThrows[MatchError](compose("two"))
    assertThrows[MatchError](compose("three"))
  }

  it("orElse ... 部分関数にマッチしなかった引数を次の部分関数にマッチさせる関数合成") {
    val pf1: PartialFunction[Int, String] = { case 1 =>
      "one"
    }
    val pf2: PartialFunction[Int, String] = { case 2 =>
      "two"
    }
    val pf = pf1 orElse pf2

    pf(1) shouldBe "one"

    pf.isDefinedAt(2) shouldBe true
    pf(2) shouldBe "two"

    pf.isDefinedAt(3) shouldBe false
    assertThrows[MatchError](pf(3))
  }

  it("runWith ... 部分関数の結果を利用する関数と合成する") {
    val pf: PartialFunction[Int, String] = { case 1 =>
      "one"
    }
    pf.runWith(s => s.length)(1) shouldBe true
    pf.runWith(_ => fail())(2) shouldBe false
  }

  it("lift ... 関数の結果をOptionで返す関数に変換する") {
    val pf: PartialFunction[Int, String] = {
      case 1 => "one"
      case 2 => "two"
    }
    val f: (Int) => Option[String] = pf.lift
    f(1) shouldBe Some("one")
    f(2) shouldBe Some("two")
    f(3) shouldBe None
  }

  it("applyOrElse ... 引数がマッチすればその結果を返し、マッチしなければデフォルト値を返す") {
    val pf: PartialFunction[Int, String] = { case 1 =>
      "one"
    }
    pf.applyOrElse(1, (i: Int) => i.toString) shouldBe "one"
    pf.applyOrElse(2, (i: Int) => i.toString) shouldBe "2"
    // 同義
    val arg    = 2
    val result = if (pf.isDefinedAt(arg)) pf(arg) else arg.toString
    result shouldBe "2"
  }

  it("[Usage] ふつうの関数(全域関数)の代わりに使う") {
    // PartialFunctionはFunction1を継承しているので、
    // Function1と同様に使用することができる。
    val list: List[(Int, Int)] = List((1, 2), (3, 4), (5, 6))

    // この場合、引数がタプルであることは自明なので、このように書ける。
    val result1 = list.map { case (i, j) => i * j }
    // 全域関数を使った場合
    val result2 = list.map { t =>
      t._1 * t._2
    }

    result1 shouldBe List(2, 12, 30)
    result2 shouldBe result1
  }

  it("[Usage] TraversableLike#collect") {
    val list = List(1, 2, 3)
    // filterとmapを組み合わせたようなメソッド
    // 部分関数にマッチしたものだけを取り出し、変換する。
    val result = list.collect {
      case 1 => "one"
      case 2 => "two"
    }
    result shouldBe List("one", "two")
  }

  it("[Usage] TraversableOnce#collectFirst") {
    // 部分関数に最初にマッチした要素を取り出し、変換する。
    val pf: PartialFunction[Int, String] = { case 2 =>
      "two"
    }
    List(1, 2, 3).collectFirst(pf) shouldBe Some("two")
    List(4, 5, 6).collectFirst(pf) shouldBe None
  }

  it("[Usage] Try#collect") {
    val result1: Try[String] = Try { 1 }.collect { case 1 => "one" }
    val result2: Try[String] = Try { 1 }.collect { case 2 => "two" }
    val result3: Try[String] = Try { throw new RuntimeException(); 1 }.collect { case 1 => "one" }

    result1 shouldBe Success("one")
    result2 shouldBe a[Failure[_]]
    result3 shouldBe a[Failure[_]]
  }

  it("ListはPartialFunction[Int,A]をmix-inしている") {
    val list = List("a", "b", "c")
    list(0) shouldBe "a"
    list(1) shouldBe "b"
    list(2) shouldBe "c"

    val result = List(2, 1, 0) map list
    result shouldBe List("c", "b", "a")
  }

  it("MapはPartialFunction[K,V]をmix-inしている") {
    val map = Map("one" -> 1, "two" -> 2, "three" -> 3)
    map("one") shouldBe 1
    map("two") shouldBe 2
    map("three") shouldBe 3

    val result = List("two", "three", "four") collect map
    result shouldBe List(2, 3)
  }

}
