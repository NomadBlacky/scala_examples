package org.nomadblacky.scala.samples.functional.programming.in.scala.chapter11

import org.scalatest.FunSpec

/**
  * Created by blacky on 17/04/25.
  *
  * Scala関数型デザイン&プログラミング―Scalazコントリビューターによる関数型徹底ガイド
  * 第11章 モナド
  * https://www.amazon.co.jp/dp/B00WM54V5Q/ref=dp-kindle-redirect?_encoding=UTF8&btkr=1
  *
  * Github
  * https://github.com/fpinscala/fpinscala/tree/master/answerkey/monads
  */
class Chapter11Spec extends FunSpec {

  override def suiteName: String = "第11章 モナド"

  it("11.1 ファンクタ : map関数の一般化") {
    val listFunctor = new Functor[List] {
      override def map[A, B](list: List[A])(f: (A) => B): List[B] = list map f
    }
    val optionFunctor = new Functor[Option] {
      override def map[A, B](op: Option[A])(f: (A) => B): Option[B] = op map f
    }

    assert(listFunctor.distribute(List((1, "a"), (2, "b"))) == (List(1, 2), List("a", "b")))
    assert(optionFunctor.distribute(Some((1, "a"))) == (Some(1), Some("a")))
    assert(optionFunctor.distribute(None) == (None, None))

    assert(listFunctor.codistribute(Left(List(1, 2))) == List(Left(1), Left(2)))
    assert(optionFunctor.codistribute(Right(Some("a"))) == Some(Right("a")))
    assert(optionFunctor.codistribute(Right(None)) == None)
  }
}
