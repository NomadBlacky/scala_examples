package org.nomadblacky.scala.samples.functional.programming.in.scala.chapter02

import org.scalatest.FunSpec

/** Created by blacky on 17/02/13.
  *
  * Scala関数型デザイン&プログラミング―Scalazコントリビューターによる関数型徹底ガイド
  * https://www.amazon.co.jp/dp/B00WM54V5Q/ref=dp-kindle-redirect?_encoding=UTF8&btkr=1
  */
class Chapter02Spec extends FunSpec {

  override def suiteName: String = "[FP in Scala] 第2章 Scala関数型プログラミングの準備"

  it("[EXERCISE 2.1] フィボナッチ数") {
    def fib(n: Int): Int = {
      // 末尾呼び出しの除去が出来ない場合に、コンパイルエラーにするアノテーション
      @annotation.tailrec
      def go(i: Int, n: Int, a: Int, b: Int): Int =
        if (n <= i) a
        else go(i + 1, n, b, a + b)
      go(0, n, 0, 1)
    }

    List(0, 1, 1, 2, 3, 5, 8, 13, 21).zipWithIndex.foreach { case (actual, index) =>
      assert(fib(index) == actual)
    }
  }

  it("[EXERCISE 2.1] フィボナッチ数(Stream)") {
    def fibStream(a: Int, b: Int): Stream[Int] = a #:: fibStream(b, a + b)

    List(0, 1, 1, 2, 3, 5, 8, 13, 21).zipWithIndex.foreach { case (actual, index) =>
      assert(fibStream(0, 1)(index) == actual)
    }
  }

  it("[EXERCISE 2.2] isSortedの実装") {
    def isSorted[A](as: Array[A], ordered: (A, A) => Boolean): Boolean = {
      @annotation.tailrec
      def compare(i: Int): Boolean = {
        if (as.length <= i + 1) true
        else if (ordered(as(i), as(i + 1))) compare(i + 1)
        else false
      }
      compare(0)
    }

    assert(isSorted(Array(1, 2, 3), (a: Int, b: Int) => a <= b) == true)
    assert(isSorted(Array(1, 3, 2), (a: Int, b: Int) => a <= b) == false)
    assert(isSorted(Array("a", "bb", "ccc"), (a: String, b: String) => a.length <= b.length) == true)
    assert(isSorted(Array("aa", "bbb", "c"), (a: String, b: String) => a.length <= b.length) == false)
  }

  it("[EXERCISE 2.3] カリー化") {
    def curry[A, B, C](f: (A, B) => C): A => (B => C) = { (a: A) => (b: B) =>
      f(a, b)
    }

    val f1: (Int, Int) => String = (a: Int, b: Int) => (a + b).toString
    assert(f1(1, 2) == "3")
    assert(curry(f1)(1)(2) == "3")

    val f2 = (a: String, b: Int) => a * b
    assert(f2("a", 3) == "aaa")
    assert(curry(f2)("a")(3) == "aaa")
  }

  it("[EXERCISE 2.4] 逆カリー化") {
    def uncurry[A, B, C](f: A => B => C): (A, B) => C = { (a: A, b: B) =>
      f(a)(b)
    }

    val f1: (Int) => (Int) => String = (a: Int) => (b: Int) => (a + b).toString
    assert(f1(1)(2) == "3")
    assert(uncurry(f1)(1, 2) == "3")

    val f2 = (a: String) => (b: Int) => a * b
    assert(f2("a")(3) == "aaa")
    assert(uncurry(f2)("a", 3) == "aaa")
  }

  it("[EXERCISE 2.5] 関数の合成") {
    def compose[A, B, C](f: B => C, g: A => B): A => C = { (a: A) =>
      f(g(a))
    }

    val f1: (Int) => String = (b: Int) => b.toString
    val f2: (Int) => Int    = (a: Int) => a + 10
    assert(f1(f2(1)) == "11")
    assert(compose(f1, f2)(1) == "11")
    assert(f1.compose(f2)(1) == "11")
  }

}
