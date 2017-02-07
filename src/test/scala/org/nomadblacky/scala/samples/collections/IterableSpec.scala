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

  it("zip ... 2つのコレクションから対応する要素をペアにする") {
    assert(List(1, 2, 3).zip(List(10, 20, 30)) == List((1, 10), (2, 20), (3, 30)))
    assert(List(1, 2   ).zip(List(10, 20, 30)) == List((1, 10), (2, 20)         ))
    assert(List(1, 2, 3).zip(List(10, 20    )) == List((1, 10), (2, 20)         ))
  }

  it("zipAll ... 2つのコレクションから対応する要素をペアにする") {
    assert(List(1, 2, 3).zipAll(List(10, 20, 30), 9, 99) == List((1, 10), (2, 20), (3, 30)))
    assert(List(1, 2   ).zipAll(List(10, 20, 30), 9, 99) == List((1, 10), (2, 20), (9, 30)))
    assert(List(1, 2, 3).zipAll(List(10, 20    ), 9, 99) == List((1, 10), (2, 20), (3, 99)))
  }

  it("zipWithIndex ... コレクションの要素と添字をペアにしたIterableを返す") {
    assert(List(1, 2, 3).zipWithIndex == List((1, 0), (2, 1), (3, 2)))
  }

  it("sameElements ... 2つのコレクションが同じ要素を同じ順序で格納しているかを返す") {
    assert(List(1, 2, 3).sameElements(List(1, 2, 3)) == true)
    assert(List(1, 2, 3).sameElements(List(3, 2, 1)) == false)
  }
}
