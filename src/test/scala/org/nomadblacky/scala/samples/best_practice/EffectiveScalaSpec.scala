package org.nomadblacky.scala.samples.best_practice

import java.net.{Socket, SocketAddress}
import java.util.concurrent.{ConcurrentHashMap, ConcurrentLinkedQueue}

import org.scalatest.{FunSpec, Matchers}

/**
  * Effective Scala
  * http://twitter.github.io/effectivescala/index-ja.html
  */
class EffectiveScalaSpec extends FunSpec with Matchers {

  it("関数定義の中で直接パターンマッチを使う") {
    // これは
    def sample1(list: Seq[Option[Int]], default: Int): Seq[Int] = {
      list map { item =>
        item match {
          case Some(x) => x
          case None => default
        }
      }
    }

    // こう書ける
    def sample2(list: Seq[Option[Int]], default: Int): Seq[Int] = {
      list map {
        case Some(x) => x
        case None => default
      }
    }

    sample1(List(Some(1), None, Some(3)), 0) shouldBe List(1, 0, 3)
    sample2(List(Some(1), None, Some(3)), 0) shouldBe List(1, 0, 3)
  }

  it("型エイリアスを使う") {
    // 型情報が複雑になる場合は、型エイリアスで簡潔にできる
    class ConcurrentPool[K, V] {
      type Queue = ConcurrentLinkedQueue[V]
      type Map   = ConcurrentHashMap[K, Queue]
    }
    // ※型エイリアスは新しい型ではない。その型に置換しているだけ。

    // 型エイリアスが使える場合、サブクラスにしてはいけない

    trait IntToStr1 extends (Int => String)
    // コンパイルエラー
    // val factory: IntToStr1 = i => i.toString

    // IntToStr2 として関数リテラルが書ける
    type IntToStr2 = Int => String
    val factory: IntToStr2 = i => i.toString
  }
}
