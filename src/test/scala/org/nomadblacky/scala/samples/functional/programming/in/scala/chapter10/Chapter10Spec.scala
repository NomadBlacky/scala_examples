package org.nomadblacky.scala.samples.functional.programming.in.scala.chapter10

import org.scalatest.FunSpec

/**
  * Created by blacky on 17/03/08.
  *
  * Scala関数型デザイン&プログラミング―Scalazコントリビューターによる関数型徹底ガイド
  * https://www.amazon.co.jp/dp/B00WM54V5Q/ref=dp-kindle-redirect?_encoding=UTF8&btkr=1
  *
  * Github
  * https://github.com/fpinscala/fpinscala/tree/master/answerkey/monads
  */
class Chapter10Spec extends FunSpec {

  override def suiteName: String = "第10章 モノイド"

  /**
    * モノイドは以下の要素で構成される
    * ・何らかの型A
    * ・A型の2つの値を受け取り、それらをひとつにまとめる2項連想演算opがあり、
    *   任意の x:A y:A z:A に対し、 op(op(x,y),z) == op(x, op(y, z)) が成り立つ。
    * ・この演算の単位元である zero:A の値。
    *   任意の x:A に対し、 op(x, zero) == x と、 op(zero, x) == x が成り立つ。
   */

  val stringMonoid = new Monoid[String] {
    override def op(a1: String, a2: String): String = a1 + a2
    override def zero: String = ""
  }

  def listMonoid[A] = new Monoid[List[A]] {
    override def op(a1: List[A], a2: List[A]): List[A] = a1 ++ a2
    override def zero: List[A] = Nil
  }

  it("[EXERCISE 10.1] 整数の加算、乗算、論理演算子") {
    val add: Monoid[Int] = new Monoid[Int] {
      override def op(a1: Int, a2: Int): Int = a1 + a2
      override def zero: Int = 0
    }
    val multi: Monoid[Int] = new Monoid[Int] {
      override def op(a1: Int, a2: Int): Int = a1 * a2
      override def zero: Int = 1
    }
    val or: Monoid[Boolean] = new Monoid[Boolean] {
      override def op(a1: Boolean, a2: Boolean): Boolean = a1 || a2
      override def zero: Boolean = false
    }
    val and: Monoid[Boolean] = new Monoid[Boolean] {
      override def op(a1: Boolean, a2: Boolean): Boolean = a1 && a2
      override def zero: Boolean = true
    }

    for (x <- 1 to 5; y <- 1 to 5; z <- 1 to 5) {
      assert(add.op(add.op(x, y), z) == add.op(x, add.op(y, z)))
      assert(add.op(add.zero, x) == add.op(x, add.zero))
    }
    for (x <- 1 to 5; y <- 1 to 5; z <- 1 to 5) {
      assert(multi.op(multi.op(x, y), z) == multi.op(x, multi.op(y, z)))
      assert(multi.op(multi.zero, x) == multi.op(x, multi.zero))
    }
    for (x <- List(false, true); y <- List(false, true); z <- List(false, true)) {
      assert(or.op(or.op(x, y), z) == or.op(x, or.op(y, z)))
      assert(or.op(or.zero, x) == or.op(x, or.zero))
    }
    for (x <- List(false, true); y <- List(false, true); z <- List(false, true)) {
      assert(and.op(and.op(x, y), z) == and.op(x, and.op(y, z)))
      assert(and.op(and.zero, x) == and.op(x, and.zero))
    }
  }


}
