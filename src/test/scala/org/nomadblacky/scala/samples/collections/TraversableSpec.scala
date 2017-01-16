package org.nomadblacky.scala.samples.collections

import org.scalatest.FunSpec

/**
  * Created by blacky on 17/01/16.
  *
  * Traversable ... 操作可能
  */
class TraversableSpec extends FunSpec {

  it("++ ... Traversableを連結する") {
    assert((List(1, 2) ++ List(3,4)) == List(1, 2, 3, 4))
  }

  it("head / headOptional ... 先頭の要素を取得する") {
    assert(List(1, 2, 3).head == 1)
    assert(List(1, 2, 3).headOption == Some(1))
    assertThrows[NoSuchElementException] {
      List().head
    }
  }

  it("last / lastOption ... 最後の要素を取得する") {
    assert(List(1, 2, 3).last == 3)
    assert(List(1, 2, 3).lastOption == Some(3))
    assertThrows[NoSuchElementException] {
      List().last
    }
  }

  it("init ... 最後以外の要素を取得する") {
    assert(List(1, 2, 3).init == List(1, 2))
    assertThrows[UnsupportedOperationException] {
      List().init
    }
  }

  it("tail ... 最初以外の要素を取得する") {
    assert(List(1, 2, 3).tail == List(2, 3))
    assertThrows[UnsupportedOperationException] {
      List().tail
    }
  }

  it("foldLeft ... 要素の先頭から畳み込みを行う") {
    val itr = List(0, 1, 3).iterator
    val result = List(1, 2, 3).foldLeft(0) { (sum, i) =>
      assert(sum == itr.next())
      sum + i
    }
    assert(result == 6)

    // 省略
    assert((0 /: List(1, 2, 3)){(sum, i) => sum + i} == 6)
  }

  it("foldRight ... 要素の最後から畳み込みを行う") {
    val itr = List(0, 3, 5).iterator
    // foldLeftと引数の順番が違うので気をつける！
    val result = List(1, 2, 3).foldRight(0) { (i, sum) =>
      assert(sum == itr.next())
      sum + i
    }
    assert(result == 6)

    // 省略
    assert((List(1, 2, 3) :\ 0){(sum, i) => sum + i} == 6)
  }

}
