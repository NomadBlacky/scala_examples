package org.nomadblacky.scala.samples.collections

import org.scalatest.FunSpec

/**
  * Created by blacky on 17/02/06.
  */
class IterableSpec extends FunSpec {

  it("grouped ... 指定サイズのListにまとめたIteratorを返す") {
    val itr:Iterator[List[Int]] = List(1, 2, 3, 4).grouped(2)
    assert(itr.next() == List(1, 2))
    assert(itr.next() == List(3, 4))
    assert(itr.hasNext == false)

    val itr2:Iterator[List[Int]] = List(1, 2, 3, 4).grouped(3)
    assert(itr2.next() == List(1, 2, 3))
    assert(itr2.next() == List(4))
    assert(itr2.hasNext == false)
  }

  it("sliding ... ウィンドウをずらしながら参照するIteratorを返す") {
    val itr:Iterator[List[Int]] = List(1, 2, 3, 4).sliding(2)
    assert(itr.next() == List(1, 2))
    assert(itr.next() == List(2, 3))
    assert(itr.next() == List(3, 4))
    assert(itr.hasNext == false)

    val itr2:Iterator[List[Int]] = List(1, 2, 3, 4).sliding(2, 2)
    assert(itr2.next() == List(1, 2))
    assert(itr2.next() == List(3, 4))
    assert(itr2.hasNext == false)
  }

  it("takeRight ... コレクションの最後のn個の要素を取り出す") {
    assert(List(1, 2, 3, 4, 5).takeRight(3) == List(3, 4, 5))
  }

  it("dropRight ... コレクションの最後のn個の要素を取り除く") {
    assert(List(1, 2, 3, 4, 5).dropRight(3) == List(1, 2))
  }
}
