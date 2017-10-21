package org.nomadblacky.scala.samples.event.understanding_scala

import org.scalatest.FunSpec

import scala.annotation.tailrec

/**
  * 6/10 Understanding Scala ~Scalaを理解しよう~
  * https://connpass.com/event/55308/
  *
  * Scalaの実行時の挙動を学ぶ
  * http://kmizu.github.io/understanding_scala/semantics/#/22
  */
class UnderstandingScalaFormulaSpec extends FunSpec {

  override def suiteName: String = "[勉強会] Understanding Scala - Scalaの実行時の挙動を学ぶ"

  it("メソッド呼び出し式") {
    /**
      * 関数内関数を除いたすべての操作はメソッド呼び出し
      * 演算子の優先順位は考慮される
      * → 最初の一文字で決まる
      * → :で終わるメソッドは右結合
      */
    // TODO: Add samples.
  }

  it("while式") {
    /**
      * Javaと変わらない
      * 式なので値を返す→Unit
      */
    var a = 1
    val unit: Unit = while(a < 3) {
      a += 1
    }
  }

  it("if式") {
    /**
      * else部がない場合、Unitの値の()が補われる
      */
    val i = 1
    val a: String = if (0 <= i) "positive" else "negative"
    // StringとUnitの共通のスーパータイプAnyが返る
    val b: Any = if (0 <= i) "positive"
  }

  it("for式(1)") {
    /**
      * foreach,map,flatMapなどのシンタックスシュガー
      */
    for (i <- 1 to 5; j <- 1 to 5) {
      println(i, j)
    }
    // ↑同等↓
    (1 to 5).foreach { i =>
      (1 to 5).foreach { j =>
        println(i, j)
      }
    }
  }

  it("for式(2)") {
    /**
      * yield
      */
    for (i <- 1 to 5) yield i
    // ↑同等↓
    (1 to 5).map { i => i }
  }

  it("for式(3)") {
    for (i <- 1 to 5; j <- 1 to 5) yield (i, j)
    // ↑同等↓
    (1 to 5).flatMap { i =>
      (1 to 5).map { j =>
        (i, j)
      }
    }
  }

  it("for式(4)") {
    /**
      * 実際のfor式の意味は定義されたデータ型によって異なる
      * + Future
      * + Option
      * + Try   ...など
      * 正確な理解nためには、どのように展開されるかを知る必要がある
      * 参考
      * https://www.scala-lang.org/files/archive/spec/2.12/06-expressions.html#for-comprehensions-and-for-loops
      */
    // TODO: Add samples.
  }

  it("match式") {
    case class Hoge(a: Int, b: Int)
    Hoge(1, 2) match {
      // Scalaの言語仕様として、引数が2つのcase classなどのパターンマッチには
      // 型名を中置することができる
      case 1 Hoge 2 => println("ok")
      case Hoge(1, 2) => fail()
    }

    // ===

    // パターンマッチと(末尾)再帰関数と相性がいい
    def reverse[A](list: List[A]): List[A] = {
      @tailrec def go(acc: List[A], rest: List[A]): List[A] = rest match {
        case x::xs => go(x :: acc, xs)
        case _ => acc
      }
      go(Nil, list)
    }
    assert(reverse(List(1, 2, 3)) == List(3, 2, 1))

    // ===

    // 自分で作ったデータ構造(特に木構造)い対するマッチを行うこともできる
    // 言語処理系を作る時にも役立つ機能
    trait E
    case class Add(e1: E, e2: E) extends E
    case class Sub(e1: E, e2: E) extends E
    case class Num(v: Int) extends E
    def eval(e: E): Int = e match {
      case Add(e1, e2) => eval(e1) + eval(e2)
      case Sub(e1, e2) => eval(e1) - eval(e2)
      case Num(v) => v
    }
    // 1 + 2 = 3
    assert(eval(Add(Num(1), Num(2))) == 3)
    // 10 - (5 + 5) = 0
    assert(eval(Sub(Num(10), Add(Num(5), Num(5)))) == 0)

    // ===

    // unapplyを定義したオブジェクトはパターンとして利用できる
    // Extractorという機能
    object PositiveNumber {
      def unapply(n: Int): Option[Int] =
        if (0 <= n) Some(n)
        else None
    }
    1 match {
      case PositiveNumber(n) => assert(n == 1)
      case _ => fail()
    }
    -1 match {
      case PositiveNumber(_) => fail()
      case _ => println("ok")
    }
  }


}
