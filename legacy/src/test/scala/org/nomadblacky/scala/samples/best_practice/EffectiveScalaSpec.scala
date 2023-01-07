package org.nomadblacky.scala.samples.best_practice

import java.util.concurrent.{ConcurrentHashMap, ConcurrentLinkedQueue}


import scala.collection.mutable
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

/** Effective Scala http://twitter.github.io/effectivescala/index-ja.html
  */
class EffectiveScalaSpec extends AnyFunSpec with Matchers {

  it("関数定義の中で直接パターンマッチを使う") {
    // これは
    def sample1(list: Seq[Option[Int]], default: Int): Seq[Int] = {
      list map { item =>
        item match {
          case Some(x) => x
          case None    => default
        }
      }
    }

    // こう書ける
    def sample2(list: Seq[Option[Int]], default: Int): Seq[Int] = {
      list map {
        case Some(x) => x
        case None    => default
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

  it("コレクション処理の可読性を保つ") {
    val votes = Seq(("scala", 1), ("java", 4), ("scala", 10), ("scala", 1), ("python", 10))

    // 正しい処理ではあるが、読み手にとって理解しづらい
    val orderedVotes = votes
      .groupBy(_._1)
      .map { case (which, counts) =>
        (which, counts.foldLeft(0)(_ + _._2))
      }
      .toSeq
      .sortBy(_._2)
      .reverse

    // 中間結果やパラメータに名前をつけることで可読性を保つ
    val voteByLang = votes groupBy { case (lang, _) => lang }
    val sumByLang = voteByLang map { case (lang, counts) =>
      val countsOnly = counts map { case (_, count) => count }
      (lang, countsOnly.sum)
    }
    val orderedVotes2 = sumByLang.toSeq.sortBy { case (_, count) => count }.reverse

    // 名前空間を汚したくなければ、式を {} でグループ化する
    val orderedVotes3 = {
      val voteByLang = votes groupBy { case (lang, _) => lang }
      val sumByLang = voteByLang map { case (lang, counts) =>
        val countsOnly = counts map { case (_, count) => count }
        (lang, countsOnly.sum)
      }
      sumByLang.toSeq.sortBy { case (_, count) => count }.reverse
    }
  }

  it("Javaコレクションとの相互変換") {
    // JavaConvertersは、asJava メソッドと asScala メソッドを追加する
    // ※暗黙の変換を行うJavaConversionsは非推奨となっている。
    import scala.collection.JavaConverters._

    // Scala → Java
    val jList: java.util.List[Int]       = Seq(1, 2, 3).asJava
    val jMap: java.util.Map[Int, String] = Map(1 -> "a", 2 -> "b").asJava

    // Java → Scala
    val sList: Seq[Int] = java.util.Arrays.asList(1, 2, 3).asScala
    val sMap: mutable.Map[Int, String] = new java.util.HashMap[Int, String]() {
      put(1, "a")
      put(2, "b")
    }.asScala
  }
}
