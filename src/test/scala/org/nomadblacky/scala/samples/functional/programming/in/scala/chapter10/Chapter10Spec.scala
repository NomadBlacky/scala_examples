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

//  it("[EXERCISE 10.1] 整数の加算、乗算、論理演算子") {
//
//  }
}
