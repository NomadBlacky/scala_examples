package org.nomadblacky.scala.samples.scala

import org.scalatest.{FunSpec, Matchers}

/**
 * match式
 * 
 * x match {
 *   case [選択肢1] => [xが選択肢1にmatchした時の処理]
 *   case [選択肢2] => [xが選択肢2にmatchした時の処理]
 *   case _ => [xが選択肢にmatchしなかった時の処理]
 * }
 */
class MatchSpec extends FunSpec with Matchers {
  
  it("基本的なマッチング") {
     val x = 10
     val actual = x match {
       case  1 => fail()
       case 10 => "OK"
       case  _ => fail()
     }
    actual shouldBe "OK"
  }
  
  it("型のマッチング") {
    val x: Any = "hoge"
    val actual = x match {
      case _: Int    => fail()
      case s: String => s"OK: $s"
      case _         => fail()
    }
    actual shouldBe "OK: hoge"
  }
  
  it("パターンガード") {
    val x: Any = 50
    val actual = x match {
      case i: Int if 100 <= i => fail()
      case _                  => "OK"
    }
    actual shouldBe "OK"
  }

  it("リストのマッチング") {
    val list = List(1,2,3,4,5)
    val actual = list match {
      // リストの2番目の要素を変数に束縛して、それ以外を捨てる
      case List(_, i, _*) => i
      case _ => fail()
    }
    actual shouldBe 2
  }
}