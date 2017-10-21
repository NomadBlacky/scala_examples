package org.nomadblacky.scala.samples.collections

import org.scalatest.FunSpec
import scala.collection.immutable.List
import scala.collection.immutable.Nil
import scala.collection.mutable.ListBuffer

/**
 * 要素同士を連結するデータ構造のリスト。
 * 順次呼び出しや再帰アクセスに強い。
 * ランダムアクセスは遅い。
 */
class ListSpec extends FunSpec {

  override def suiteName: String = "List - 単方向リスト"
  
  it("List() ... Listを生成する") {
    val l: List[Int] = List(1, 2, 3)
  }
  
  it("() ... Listから値を取り出す") {
    val l: List[Int] = List(1, 2, 3)
    assert(l(0) == 1)
    assert(l(1) == 2)
    assert(l(2) == 3)
  }
  
  it("Nil ... 空のListを作成する") {
    val l: List[Int] = Nil
    assert(l.isEmpty == true)
  }
  
  it("ListBuffer ... ミュータブルなList") {
    val l: ListBuffer[Int] = ListBuffer.empty
    l += 1
    l += 2
    assert(l == ListBuffer(1, 2))
    l(0) = 10
    assert(l == ListBuffer(10, 2))
  }
  
  it(":: :+ ... Listに値を追加する") {
    val l: List[String] = List("b")
    // 先頭に追加
    assert(("a" :: l) == List("a", "b"))
    // 末尾に追加
    assert((l :+ "c") == List("b", "c"))
  }
  
  it("::: ... List同士を連結する") {
    val l1 = List(1,2,3)
    val l2 = List(4,5,6)
    assert((l1 ::: l2) == List(1,2,3,4,5,6))
  }

  it("withFilter ... 中間データを作らない") {
    val list = List(1, 2, 3, 4, 5)

    // filterの時点で中間データが作成されてしまう
    assert(list.filter{ _ % 2 == 0 }.map{ _ * 2 } == List(4, 8))

    // withFilterを使うと中間データを作らずメモリにやさしい
    assert(list.withFilter{ _ % 2 == 0 }.map{ _ * 2 } == List(4, 8))
  }

  it("view ... none-strict(中間データを作らない)なコレクションに変換する") {
    val list = List(1, 2, 3, 4, 5)

    // strict → (view) → none-strict → (force) → strict
    assert((list.view.filter{ _ % 2 == 0 }.map{ _ * 2 }).force == List(4, 8))
  }

  it("lengthCompare ... コレクションの要素数と引数の長さを比較する") {
    // lengthを使って比較するよりも高速
    val list = List(1, 2, 3)
    assert(list.lengthCompare(1) == 1)
    assert(list.lengthCompare(2) == 1)
    assert(list.lengthCompare(3) == 0)
    assert(list.lengthCompare(4) == -1)
    assert(list.lengthCompare(5) == -1)
  }

  it("lift") {
    val list = List(1, 2, 3)
    val i = 3
    val a = if (i < list.length) Some(list(i)) else None
    val b = list.lift(i)
    assert(a == b)
  }
}