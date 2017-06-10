package org.nomadblacky.scala.samples.event.understanding_scala

import java.util

import org.scalatest.FunSpec

import scala.annotation.tailrec
import scala.concurrent.Future

/**
  * 6/10 Understanding Scala ~Scalaを理解しよう~
  * https://connpass.com/event/55308/
  *
  * Scalaの構文を学ぶ
  * http://kmizu.github.io/understanding_scala/syntax/index.html#/14
  *
  * Scalaの型システムを学ぶ
  * http://kmizu.github.io/understanding_scala/type_system/#/14
  *
  * Scalaの実行時の挙動を学ぶ
  * http://kmizu.github.io/understanding_scala/semantics/#/22
  *
  * Scalaのimplicit parameterを学ぶ
  * http://kmizu.github.io/understanding_scala/implicit_parameter/#/14
  *
  * Scalaの謎・落とし穴を学ぶ
  * http://kmizu.github.io/understanding_scala/pitfall/#/7
  */
class UnderstandingScalaSpec extends FunSpec {

  /**
    * + 構文を暗記するのではなく理解する
    * + BNFを元にして構文を理解する
    * ++ 何が省略記法で何が本質的なのか
    * ++ プログラムのどこに何を書けるのかを把握するにはBNF等のを見るのが早い
    * ++ 
    */

  it("Any あらゆる型のスーパータイプ") {
    /**
      * 最低限のメソッドのみを提供
       */
    val a: Any = 10
    assert(a == 10)
    assert(a != 20)
    assert(a.toString == "10")
    assert(a.hashCode == 10)
    assert(a.equals(10))
  }

  it("AnyVal: あらゆる値型のスーパータイプ") {
    /**
      * Javaでいうプリミティブ型をまとめたもの
      * AnyValにnullは代入できない
      * 便宜上存在しているが、AnyValに意味があることは少ない
      */
    val int: AnyVal = 10
    val double: AnyVal = 1.0
    val char: AnyVal = 'a'
    val bool: AnyVal = true
    val unit: AnyVal = {}
    // だめ
    // val nul: AnyVal = null
  }

  it("AnyRef: あらゆる参照型のスーパータイプ") {
    /**
      * Javaでいうjava.lang.Object
      * 参照型のすべての値はAnyRefに代入できる
      */
    // だめ
    // val int: AnyRef = 10
    val string: AnyRef = "hoge"
    // eq ... 参照の等価性を判定する
    assert(string eq string)
  }

  it("Nothing: あらゆる型のサブタイプ") {
    /**
      * Javaでは相当する型が存在しない
      * あらゆる型のサブタイプ
      * → あらゆる型の変数に代入可能
      * → Nothing型の値は存在しない
      * 存在意義
      * → 必ず例外を投げて正常にreturnしないメソッドの型になる
      * → ジェネリクスと組み合わせて使う
      */
    def method(): Nothing = { throw new Exception() }
  }

  it("Null") {
    /**
      * あらゆる参照型のサブタイプ
      * 値はnullのみ
      * nullはあらゆる参照型に代入可能
      */
    val string: String = null
    // だめ
    // val int: Int = null
  }

  it("共変") {

  }

  it("反変") {

  }

  it("構造的部分型") {

  }

  it("高階多相") {

  }

  it("メソッド呼び出し式") {
    /**
      * 関数内関数を除いたすべての操作はメソッド呼び出し
      * 演算子の優先順位は考慮される
      * → 最初の一文字で決まる
      * → :で終わるメソッドは右結合
      */

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

  it("for式") {
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

    // =====

    for (i <- 1 to 5) yield i
    // ↑同等↓
    (1 to 5).map { i => i }

    // =====

    for (i <- 1 to 5; j <- 1 to 5) yield (i, j)
    // ↑同等↓
    (1 to 5).flatMap { i =>
      (1 to 5).map { j =>
        (i, j)
      }
    }

    /**
      * 実際のfor式の意味は定義されたデータ型によって異なる
      * + Future
      * + Option
      * + Try   ...など
      * 正確な理解nためには、どのように展開されるかを知る必要がある
      * 参考
      * https://www.scala-lang.org/files/archive/spec/2.12/06-expressions.html#for-comprehensions-and-for-loops
      */
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

  it("implicit parameter") {
    /**
      * implicit conversion とごっちゃにされやすい
      * 正しく使えば非常に強力
      * こわくない!
      */

    /**
      * 例題
      * + リストの要素をすべて足し合わせた値を返す関数
      * + ただし、あとから特定の型を足すことができること
      * ++ 有理数(Rational)、複素数(Complext)など
      */
    // 素直な回答
    def sum(list: List[Int]): Int = list.foldLeft(0)(_ + _)
    assert(sum(List(1, 2, 3)) == 6)
    // だめ
    // sum(List(1.0, 2.0, 3.0) == 9.0)

    // Monoidを使ってsumを書き直す
    trait Monoid[A] {
      def plus(a: A, b: A): A
      def zero: A
    }
    def sum2[A](list: List[A])(m: Monoid[A]): A =
      list.foldLeft(m.zero)(m.plus)
    object IntMonoid extends Monoid[Int] {
      override def plus(a: Int, b: Int): Int = a + b
      override def zero: Int = 0
    }
    object DoubleMonoid extends Monoid[Double] {
      override def plus(a: Double, b: Double): Double = a + b
      override def zero: Double = 0.0
    }
    assert(sum2(List(1, 2, 3))(IntMonoid) == 6)
    assert(sum2(List(1.0, 2.0, 3.0))(DoubleMonoid) == 6.0)
    //                               ↑ 省略したい! ↑

    // そこでimplicit parameterを使う
    def sum3[A](list: List[A])(implicit m: Monoid[A]): A =
      list.foldLeft(m.zero)(m.plus)
    // 省略できた!
    assert(sum3(List(1, 2, 3)) == 6)
    assert(sum3(List(1.0, 2.0, 3.0)) == 6.0f)

    /**
      * implicit parameterの仕組み
      * + implicit修飾子が付いた引数m: Monoid[A]があったときに Aが特定の型に確定していれば
      * + MonoidのコンパニオンオブジェクトからMonoidのインスタンスを探す
      * + Monoidの型パラメータ（たとえばInt`)のコンパニオンオブジェクトを探す
      * + importされたオブジェクトの下にimplicit宣言されたオブジェクトがないか探す
      */

    // Scala標準ライブラリにおけるimplicit parameterの例
    assert(List(1, 2, 3).sum == 6)
  }

  it("Scalaの落とし穴") {
    // Unitを返す関数を意図せず書いてしまう
    def double(x: Int) {
      x * 2
    }
    // 明示的に書く
    def double2(x: Int): Int = {
      x * 2
    }

    // 変更可能コレクションの変更しない操作を呼び出してしまう
    val buffer = scala.collection.mutable.Buffer(1, 2, 3)
    assert(buffer.drop(1) == scala.collection.mutable.Buffer(2, 3))
    assert(buffer == scala.collection.mutable.Buffer(1, 2, 3))

    // 配列同士の==
    val x = Array(1, 2, 3)
    val y = Array(1, 2, 3)
    // 配列はJVMの配列が使われるので、参照が一致しているかが比較される
    assert((x == y) == false)
    // Arrays.deepEqualsを使う
    assert(util.Arrays.deepEquals(x, y))


    // 誤ったFutureの使い方
    // 以下のコードでは、処理が平行に実行されない
    for (f1 <- Future(1 + 2); f2 <- Future(3 + 4)) yield f1 + f2
    // 先にFutureを格納する変数を用意する
    val f1 = Future(1 + 2)
    val f2 = Future(3 + 4)
    for (a <- f1; b <- f2) yield a + b

    // 意図しない結果のパターンマッチ

    // 誤った正規表現のパターンマッチ

    // 既存の型同士のimplicit conversionは使わない
    {
      // だめ!
      implicit def int2boolean(n: Int): Boolean = n != 0
    }

    // 改行とブロックの組み合わせに注意

    // Javaのメソッドの返り値に注意
    // Javaのメソッドはnullが返って来る
    val m = new java.util.HashMap[String, Int]
    assertThrows[NullPointerException](m.get())

    // Set#mapの罠
    // Scalaのコレクションは可能な限り自分と同じ型を返そうとする
    assert(Set(1, 2, 3).map(_ => 2) == Set(2))
    // 対策: toListなどで他のコレクションに変換する
    assert(Set(1, 2, 3).toList.map(_ => 2) == List(2, 2, 2))

    // インナークラスのインスタンス
    class Outer {
      class Inner
      val I = new Inner
    }
    val outer = new Outer
    val inner: Outer#Inner = outer.I

    // アンダースコア七変化
    // ワイルドカードインポート
    {
      import java.util._
    }
    // 特定クラスを除外してインポート
    {
      import java.util.{List => _}
    }
    // ワイルドカードパターン
    1 match {
      case _ => // 必ずマッチする
    }
    // 仮引数を使わない
    List(1, 2, 3).map(_ => 4)
    // 可変長引数にコレクションを分解して渡す
    // _単体では意味はなく、:_*の組み合わせで意味を持つ
    printf("%d", List(1):_*)
    // メソッドを関数の型に変換する
    // メソッドはファーストクラスの型ではない
    val f: () => List[Int] = List(1, 2, 3).reverse _
    // プレースホルダ構文
    // 構文解析に作用する点で特異
    List(1, 2, 3).map(_ * 2)
  }
}
