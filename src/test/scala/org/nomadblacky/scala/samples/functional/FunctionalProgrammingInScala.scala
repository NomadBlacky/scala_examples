package org.nomadblacky.scala.samples.functional

import org.scalatest.FunSpec

/**
  * Created by blacky on 17/02/13.
  *
  * Scala関数型デザイン&プログラミング―Scalazコントリビューターによる関数型徹底ガイド
  * https://www.amazon.co.jp/dp/B00WM54V5Q/ref=dp-kindle-redirect?_encoding=UTF8&btkr=1
  */
class FunctionalProgrammingInScala extends FunSpec {

  it("[EXERCISE 2.1] フィボナッチ数") {
    def fib(n: Int): Int = {
      // 末尾呼び出しの除去が出来ない場合に、コンパイルエラーにするアノテーション
      @annotation.tailrec
      def go(i: Int, n: Int, a: Int, b: Int): Int =
        if (n <= i) a
        else go(i + 1, n, b, a + b)
      go(0, n, 0, 1)
    }

    List(0, 1, 1, 2, 3, 5, 8, 13, 21).zipWithIndex.foreach { case(actual, index) =>
      assert(fib(index) == actual)
    }
  }

  it("[EXERCISE 2.1] フィボナッチ数(Stream)") {
    def fibStream(a: Int, b: Int): Stream[Int] = a #:: fibStream(b, a + b)

    List(0, 1, 1, 2, 3, 5, 8, 13, 21).zipWithIndex.foreach { case(actual, index) =>
      assert(fibStream(0, 1)(index) == actual)
    }
  }
}
