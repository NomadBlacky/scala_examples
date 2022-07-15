package dev.nomadblacky.scala_examples.basics

import com.github.ghik.silencer.silent
import org.scalatest.funspec.AnyFunSpec

import scala.collection.mutable.ListBuffer

/** for ( [ジェネレータ] [フィルタ] ) 式
  *
  * ※フィルタは任意 ※処理に複数の式を記述したい場合{}で囲む
  */
class ForSpec extends AnyFunSpec {

  override def suiteName: String = "for式 (for内包表記)"

  it("コレクションのイテレート") {
    for (i <- List(1, 2, 3, 4, 5)) {
      println(i)
    }
  }

  it("要素のフィルタ") {
    val l: ListBuffer[Int] = ListBuffer.empty
    for (i <- List(1, 2, 3, 4, 5) if 3 < i) {
      l += i
    }
    assert(l == ListBuffer(4, 5))
  }

  it("yield ... forの結果を新しいコレクションとして返す") {
    val l = for (name <- List("Taro", "Jiro")) yield "I am " + name
    assert(l == List("I am Taro", "I am Jiro"))
  }

  it("for式のforeach展開") {
    class Hoge {
      type A = Int
      def foreach[U](f: A => U): Unit = {
        f(1); f(2); f(3)
      }
    }
    // yield を持たないfor式は、foreachとして展開される
    // (foreachを実装していればfor式で利用できる)
    for (x <- new Hoge) {
      println(x)
    }
    // new Hoge.foreach(println(_)) と同義
  }

  it("for式のmap展開") {
    class Hoge {
      type A = Int
      def map[B](f: A => B): IterableOnce[B] = {
        List(f(1), f(2), f(3))
      }
    }
    // yield を持つfor式は、mapとして展開される
    val result = for (x <- new Hoge) yield x.toString * x
    // new Hoge().map{ x => x.toString * x } と同義

    assert(result == List("1", "22", "333"))
  }

  /** すべてのfor式は、map,flatMap,withFilterの3つの高階関数で表現できる。
    */
  it("ジェネレータが1個のときの変換") {
    val a = for (x <- List(1, 2, 3)) yield x * 2
    val b = List(1, 2, 3).map(x => x * 2)
    assert(a == b)
  }

  it("1個のジェネレータと1個のフィルターで始まるfor式の変換") {
    val a = for (x <- List(1, 2, 3) if 1 < x) yield x * 2
    val b = for (x <- List(1, 2, 3) withFilter (x => 1 < x)) yield x * 2
    val c = List(1, 2, 3) withFilter (x => 1 < x) map (x => x * 2)
    assert(a == b)
    assert(b == c)
  }

  it("2個のジェネレータで始まるfor式の変換") {
    val a = for {
      x <- List(1, 2)
      y <- List(3, 4)
    } yield x * y
    val b = List(1, 2).flatMap(x => for (y <- List(3, 4)) yield x * y)
    val c = List(1, 2).flatMap(x => List(3, 4).map(y => x * y))
    assert(a == b)
    assert(b == c)
  }

  it("ジェネレータに含まれるパターンの変換 - タプルの場合") {
    val a = for ((x, y) <- List((1, 2), (3, 4))) yield x * y
    val b = List((1, 2), (3, 4)).map { case (x, y) => x * y }
    assert(a == b)
  }

  it("ジェネレータに含まれるパターンの変換 - その他パターンの場合") {
    @silent("match may not be exhaustive")
    def foo(): Unit = {
      val list = List(Some(1), None, Some(3))
      val a    = for (Some(i) <- list) yield i
      // desugared
      val b = list.withFilter {
        case Some(i) => true
        case _       => false
      } map { case Some(i) =>
        i
      }
      assert(a == b)
    }
    foo()
  }

  it("[Sample] 2つのコレクションを同じ順序で取り出して処理する") {
    val l = for ((a, b) <- (List(1, 2, 3) zip List(3, 4, 5))) yield a * b
    assert(l == List(3, 8, 15))
  }
}
