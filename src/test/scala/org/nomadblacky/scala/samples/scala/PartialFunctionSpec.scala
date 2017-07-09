package org.nomadblacky.scala.samples.scala

import org.scalatest.Matchers

/**
  * Created by blacky on 17/07/09.
  *
  * PartialFunction ... 部分関数
  *
  * 特定の引数に対してのみ結果を返す関数。
  * 引数により値を返さない場合がある。
  */
class PartialFunctionSpec extends ForSpec with Matchers {

  it("PartialFunctionを定義する") {
    val pf: PartialFunction[Int, String] = {
      case 1 => "one"
      case 2 => "two"
    }
    assert(pf(1) == "one")
    assert(pf(2) == "two")
    // 引数がマッチしない場合はMatchErrorが投げられる
    assertThrows[MatchError](pf(3))
  }

  it("isDefinedAt ... 引数に対して値が返される場合はtrueを返す") {
    val pf: PartialFunction[Int, String] = {
      case i if i % 2 == 0 => "even"
    }
    assert(pf.isDefinedAt(1) == false)
    assert(pf.isDefinedAt(2) == true)
    assert(pf.isDefinedAt(3) == false)
    assert(pf.isDefinedAt(4) == true)
  }

  it("andThen ... PartialFunction合成する") {
    val pf1: PartialFunction[Int, String] = {
      case 1 => "one"
      case 2 => "two"
    }
    val pf2: PartialFunction[String, Int] = {
      case "one" => 1
      case "three" => 3
    }
    val andThen = pf1 andThen pf2
    assert(andThen(1) == 1)
    assert(andThen.isDefinedAt(2) == true)
    assertThrows[MatchError](andThen(2))
    assert(andThen.isDefinedAt(3) == false)
    assertThrows[MatchError](andThen(3))
  }

  it("compose ... PartialFunctionを合成する") {
    val pf1: PartialFunction[Int, String] = {
      case 1 => "one"
      case 2 => "two"
    }
    val pf2: PartialFunction[String, Int] = {
      case "one" => 1
      case "three" => 3
    }
    // TODO: andThenがPartialFunctionを返すのに対し、composeがFunctionを返すのなんでだろ…
    val compose = pf1 compose pf2
    assert(compose("one") == "one")
    assertThrows[MatchError](compose("two"))
    assertThrows[MatchError](compose("three"))
  }

  it("orElse ... 部分関数にマッチしなかった引数を次の部分関数にマッチさせる関数合成") {
    val pf1: PartialFunction[Int, String] = {
      case 1 => "one"
    }
    val pf2: PartialFunction[Int, String] = {
      case 2 => "two"
    }
    val pf = pf1 orElse pf2
    assert(pf(1) == "one")
    assert(pf.isDefinedAt(2) == true)
    assert(pf(2) == "two")
    assert(pf.isDefinedAt(3) == false)
    assertThrows[MatchError](pf(3))
  }

  it("runWith ... 部分関数の結果を利用する関数と合成する") {
    val pf: PartialFunction[Int, String] = {
      case 1 => "one"
    }
    assert(pf.runWith(s => s.length)(1) == true)
    assert(pf.runWith(_ => fail())(2) == false)
  }

  it("lift ... 関数の結果をOptionで返す関数に変換する") {
    val pf: PartialFunction[Int, String] = {
      case 1 => "one"
      case 2 => "two"
    }
    val f: (Int) => Option[String] = pf.lift
    assert(f(1) == Some("one"))
    assert(f(2) == Some("two"))
    assert(f(3) == None)
  }

  it("applyOrElse ... 引数がマッチすればその結果を返し、マッチしなければデフォルト値を返す") {
    val pf: PartialFunction[Int, String] = {
      case 1 => "one"
    }
    assert(pf.applyOrElse(1, (i:Int) => i.toString) == "one")
    assert(pf.applyOrElse(2, (i:Int) => i.toString) == "2")
    // 同義
    val arg = 2
    val result = if (pf.isDefinedAt(arg)) pf(arg) else arg.toString
    assert(result == "2")
  }
}
