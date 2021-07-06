package org.nomadblacky.scala.samples.scala

import org.scalatest.FunSpec

import scala.math.Ordering

/** Created by blacky on 17/05/23.
  *
  * 参考
  *
  * + 型クラスをOrderingを用いて説明してみる
  *   + http://kmizu.hatenablog.com/entry/2017/05/22/224622
  * + Scalaの型クラス（typeclass）の分かりやすい説明(Cats公式翻訳)
  *   + http://qiita.com/mizunowanko/items/7d8658c765567677b280
  * + Scalaで型クラス入門
  *   + http://chopl.in/post/2012/11/06/introduction-to-typeclass-with-scala/
  * + Twitter検索
  *   + https://twitter.com/search?l=&q=型クラス&src=typd&lang=ja
  */
class TypeClassSpec extends FunSpec {

  override def suiteName: String = "型クラスの使い方"

  it("型クラスとは") {

    /** + 型クラスとは
      *   + ある型に振る舞いを提供する仕組み。
      *   + 「アドホック多相」を実現する。
      *     + 引数の型に応じて複数の実装を提供できる。
      *     + 型の宣言時に振る舞いを与えず、アドホック(場当たり的に)実装を提供できる。
      *     + (特にScalaでは)異なるスコープにおいて、型クラスの実装を有効・無効にできる。
      * + 複数の型に対して共通の振る舞いをもたせる、という意味の用途はinterfaceとほぼ変わらない。
      * + が、interfaceにはない利点が多い
      *   + interfaceは元のクラス定義を書き換える必要がある。
      *     (ex. Stringに新しいinterfaceを実装することはできない。)
      *   + ある型に対する操作を後付けで加えることができる。
      * + 型クラスのインスタンス(OPPのインスタンスとは異なる)を作る ≒ Strategyパターンの具体的なStrategyを定義する
      */
  }

  it("型クラスの例") {
    assert(List(1, 2, 3).max == 3)
    assert(List("a", "b", "c").max == "c")

    /** implicit parameter によって、Orderingの引数に与えられる
      *
      * def max[B >: A](implicit cmp: Ordering[B]): A
      *
      * [scala.math.Ordering.scala]
      *
      * trait IntOrdering extends Ordering[Int] {
      *   def compare(x: Int, y: Int) = java.lang.Integer.compare(x, y)
      * }
      * implicit object Int extends IntOrdering
      *
      * trait StringOrdering extends Ordering[String] {
      *   def compare(x: String, y: String) = x.compareTo(y)
      * }
      * implicit object String extends StringOrdering
      */
  }

  it("Orderedを使った実装例") {
    case class Person(id: Int, age: Int)
    val persons = List(Person(2, 20), Person(1, 30), Person(3, 10))

    implicit object PersonOrderingById extends Ordering[Person] {
      override def compare(x: Person, y: Person): Int = {
        if (x.id < y.id) -1 else if (x.id > y.id) 1 else 0
      }
    }
    assert(persons.sorted == List(Person(1, 30), Person(2, 20), Person(3, 10)))

//    implicit object PersonOrderingByAge extends Ordering[Person] {
//      override def compare(x: Person, y: Person): Int = {
//        if (x.age < y.age) -1 else if (x.age > y.age) 1 else 0
//      }
//    }
//    assert(persons.sorted == List(Person(3, 10), Person(2, 20), Person(1, 30)))
  }

}
