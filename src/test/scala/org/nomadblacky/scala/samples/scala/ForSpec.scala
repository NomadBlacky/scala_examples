package org.nomadblacky.scala.samples.scala

import org.scalatest.FunSpec
import scala.collection.mutable.ListBuffer

/**
 * for (
 *   [ジェネレータ]
 *   [フィルタ]
 * ) 式
 * 
 * ※フィルタは任意
 * ※処理に複数の式を記述したい場合{}で囲む
 */
class ForSpec extends FunSpec {
  
  it("コレクションのイテレート") {
    for (i <- List(1,2,3,4,5)) {
      println(i)
    }
  }
  
  it("要素のフィルタ") {
    val l: ListBuffer[Int] = ListBuffer.empty
    for(i <- List(1,2,3,4,5) if 3 < i) {
      l += i
    }
    assert(l == ListBuffer(4,5))
  }
  
  it("yield ... forの結果を新しいコレクションとして返す") {
    val l = for(name <- List("Taro", "Jiro")) yield "I am " + name
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

  it("[Sample] 2つのコレクションを同じ順序で取り出して処理する") {
    val l = for((a, b) <- (List(1,2,3) zip List(3,4,5))) yield a * b
    assert(l == List(3, 8, 15))
  }
}