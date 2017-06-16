package org.nomadblacky.scala.samples.event.understanding_scala

import java.io.{File, FileInputStream}
import java.util

import org.scalatest.FunSpec

/**
  * 6/10 Understanding Scala ~Scalaを理解しよう~
  * https://connpass.com/event/55308/
  *
  * Scalaの型システムを学ぶ
  * http://kmizu.github.io/understanding_scala/type_system/#/14
  */
class UnderstandingScalaTypeSpec extends FunSpec {

  override def suiteName: String = "Understanding Scala - Scalaの型システムを学ぶ"

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

  it("ジェネリクス") {
    /**
      * 最近の静的型付き言語のほとんどがもっている
      * 型をパラメータとして取ること柔軟な型定義ができる
      */
    val strings = new util.ArrayList[String]
    strings.add("hoge")
    // コンパイルエラー↓
    // strings.add(1)
  }

  it("共変") {
    val x:Array[String] = Array("a", "b", "c")
    // コンパイルエラー↓
    // val y:Array[Any] = x

    // これを許すと、Stringの配列にIntが入るといった状況が生まれる
    // y(0) = 1

    /**
      * + String と Any について、StringがAnyのサブタイプである場合、
      *   Array[String]がArray[Any]のサブタイプであるとき、
      *   Arrayは共変であるという。
      * + 実際にはScalaのArrayは共変ではない(不変)
      *   一方、Javaの配列は共変だが、実行時に例外が投げられる危険がある。
      * + 共変は便利だが、制限無しで取り扱うのは危険。
      *   何らかの制限が必要→共変性に関する注釈をつける
      */

    sealed trait Link[+T] // + がだいじ
    case class Cons[T](head: T, tail: Link[T]) extends Link[T]
    // EmptyはどのようなLinkの変数にも代入できる
    // Linkの終端を表すのに使うことができる
    case object Empty extends Link[Nothing]
    // 1,2,3 からなる単方向連結リスト
    val cons = Cons(1, Cons(2, Cons(3, Empty)))

    cons match {
      case Cons(head, tail) =>
        assert(head == 1)
        assert(tail == Cons(2, Cons(3, Empty)))
      case _ => fail()
    }
  }

  it("反変") {
    /**
      * 関数の型は引数の型に関して「共変ではない」
      */
    val x: Int => Any = {i => i}
    // コンパイルエラー
    // val y: Any => Any = x

    // これを許すと、Int引数にStringを渡せてしまうという状況が生まれる。
    // y("hoge")

    /**
      * 引数の型は引数の型に関して、「反変である」
      */
    val xx: Any => Any = {a => a}
    val yy: Int => Any = x // OK

    /**
      * 型の汎化(スーパタイプ)を許容する。
      * Scalaでは、[-T]のように、型パラメータに - を付けると反変になる。
      */
    class Animal
    class Human extends Animal
    class Kaban extends Human
    class JapariPark[-A] {
      def welcomeTo(arg: A): String = "ようこそジャパリパークへ"
    }

    val japari1: JapariPark[Kaban] = new JapariPark[Human]
    assert(japari1.welcomeTo(new Kaban) == "ようこそジャパリパークへ")
    val japari2: JapariPark[Human] = new JapariPark[Animal]
    assert(japari2.welcomeTo(new Human) == "ようこそジャパリパークへ")
    // だめ
    // val japari3: JapariPark[Human] = new JapariPark[Kaban]
  }

  it("構造的部分型") {
    /**
      * 継承関係によらず、必要なメソッドを持っていれば要求を満たす、としたい場合がある。
      * 動的型付け言語における、duck typing的な考え。
      */
    import scala.language.reflectiveCalls

    def using[T <: { def close() }, U](r: T)(f: T => U): U = try {
      f(r)
    } finally {
      r.close()
    }

    using(new FileInputStream("build.sbt")) { f =>
      // do something
    }

    /**
      * 内部的にはリフレクションを使っているので多用に注意。
      * import scala.language.reflectiveCalls
      * をつけないと警告が出る。
      */
  }

  it("高階多相") {
    /**
      * List などのコレクションや Option など様々な型が map メソッドを持っている
      * とにかく map を持っている型を抽象化したい
      * そのような型 Mapper を定義してみる
      */
    trait Mapper[C] {
      def map[A, B](c: C)(f: C => C): C
    }
    /**
      * C の要素の型は map の呼び出しによって変わるので通常のジェネリクスでは表現できない
      */

    trait Mapper2[C[_]] {
      def map[A, B](c: C[A])(f: A => B): C[B]
    }
    object Mapper2 {
      implicit object ListMapper extends Mapper2[List] {
        override def map[A, B](c: List[A])(f: (A) => B): List[B] = c.map(f)
      }
      implicit object OptionMapper extends Mapper2[Option] {
        override def map[A, B](c: Option[A])(f: (A) => B): Option[B] = c.map(f)
      }
    }
    def add2[C[_]](c: C[Int])(implicit m: Mapper2[C]): C[Int] = {
      m.map(c)(n => n + 2)
    }
    assert(add2(List(1, 2, 3)) == List(3, 4, 5))
    assert(add2(Option(1)) == Some(3))

    /**
      * C[_]が肝心
      * 型コンストラクタを引数に取ることを表す宣言
      * 型コンストラクタ … ジェネリックなクラスに型が与えられる前の名前のこと
      * + List[T] → List
      * + Option[T] → Option
      */
  }

}
