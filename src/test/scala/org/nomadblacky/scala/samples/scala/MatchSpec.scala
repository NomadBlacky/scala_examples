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
     def func(i: Int): String = i match {
       case 1 => "one"
       case 2 => "two"
       case _ => "other"
     }

    func(1) shouldBe "one"
    func(2) shouldBe "two"
    func(3) shouldBe "other"
  }
  
  it("型のマッチング") {
    def func(a: Any): String = a match {
      case i: Int    => s"OK: $i"
      case s: String => s"OK: $s"
      case _         => "other"
    }

    func(1)      shouldBe "OK: 1"
    func("hoge") shouldBe "OK: hoge"
    func(1.0)    shouldBe "other"
  }

  it("パターンガード") {
    def func(i: Int): String = i match {
      case i: Int if 100 <= i => "more than 100"
      case _                  => "other"
    }

    func(1)   shouldBe "other"
    func(99)  shouldBe "other"
    func(100) shouldBe "more than 100"
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

  it("複数のパターンをまとめる") {
    // `|` を使うことで、複数のパターンをまとめることができる。
    // switch文のフォールスルーのような場合を書きたいときに使える。
    def func(i: Int): String = i match {
      case 1 | 2 => "one or two"
      // パターンガードは複数書けない
      case x if x == 3 /* | y if y == 4 */ => "three"
      case y if y == 4 | y == 5 => "four or five"
      case _ => "other"
    }

    func(1) shouldBe "one or two"
    func(2) shouldBe "one or two"
    func(3) shouldBe "three"
    func(4) shouldBe "four or five"
    func(5) shouldBe "four or five"
    func(6) shouldBe "other"
  }
}