package org.nomadblacky.scala.samples.event.understanding_scala

import org.scalatest.funspec.AnyFunSpec

/** 6/10 Understanding Scala ~Scalaを理解しよう~ https://connpass.com/event/55308/
  *
  * Scalaのimplicit parameterを学ぶ http://kmizu.github.io/understanding_scala/implicit_parameter/#/14
  */
class UnderstandingScalaImplicitSpec extends AnyFunSpec {

  override def suiteName: String = "[勉強会] Understanding Scala - Scalaのimplicit parameterを学ぶ"

  it("implicit parameter") {

    /** implicit conversion とごっちゃにされやすい 正しく使えば非常に強力 こわくない!
      */
    /** 例題 + リストの要素をすべて足し合わせた値を返す関数 + ただし、あとから特定の型を足すことができること ++ 有理数(Rational)、複素数(Complext)など
      */
    // 素直な回答
    def sum(list: List[Int]): Int = list.foldLeft(0)(_ + _)

    assert(sum(List(1, 2, 3)) == 6)
    // だめ
    // sum(List(1.0, 2.0, 3.0) == 9.0)
  }

  it("Monoidを使ってsumを書き直す") {
    trait Monoid[A] {
      def plus(a: A, b: A): A
      def zero: A
    }
    def sum2[A](list: List[A])(m: Monoid[A]): A =
      list.foldLeft(m.zero)(m.plus)
    object IntMonoid extends Monoid[Int] {
      override def plus(a: Int, b: Int): Int = a + b
      override def zero: Int                 = 0
    }
    object DoubleMonoid extends Monoid[Double] {
      override def plus(a: Double, b: Double): Double = a + b
      override def zero: Double                       = 0.0
    }
    assert(sum2(List(1, 2, 3))(IntMonoid) == 6)
    assert(sum2(List(1.0, 2.0, 3.0))(DoubleMonoid) == 6.0)
    //                               ↑ 省略したい! ↑
    // そこでimplicit parameterを使う↓
  }

  it("implicit parameterの導入") {
    trait Monoid[A] {
      def plus(a: A, b: A): A
      def zero: A
    }
    object Monoid {
      implicit object IntMonoid extends Monoid[Int] {
        override def plus(a: Int, b: Int): Int = a + b
        override def zero: Int                 = 0
      }
      implicit object DoubleMonoid extends Monoid[Double] {
        override def plus(a: Double, b: Double): Double = a + b
        override def zero: Double                       = 0.0
      }
    }
    def sum3[A](list: List[A])(implicit m: Monoid[A]): A =
      list.foldLeft(m.zero)(m.plus)
    // 省略できた!
    assert(sum3(List(1, 2, 3)) == 6)
    assert(sum3(List(1.0, 2.0, 3.0)) == 6.0f)
  }

  it("implicit parameterの仕組み") {

    /** + implicit修飾子が付いた引数m: Monoid[A]があったときに Aが特定の型に確定していれば + MonoidのコンパニオンオブジェクトからMonoidのインスタンスを探す +
      * Monoidの型パラメータ（たとえばInt`)のコンパニオンオブジェクトを探す + importされたオブジェクトの下にimplicit宣言されたオブジェクトがないか探す
      */
  }

  it("Scala標準ライブラリにおけるimplicit parameterの例") {
    assert(List(1, 2, 3).sum == 6)
    assert(List(4, 5, 6).product == 120)
  }
}
