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
}