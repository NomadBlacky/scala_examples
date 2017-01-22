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

  it("reduceLeft ... 最初の要素を初期値として畳み込みを行う") {
    val itr = List(1, 3).iterator
    val result = List(1, 2, 3).reduceLeft { (sum, i) =>
      assert(sum == itr.next())
      sum + i
    }
    assert(result == 6)
  }

  it("reduceRight ... 最後の要素を初期値として畳み込みを行う") {
    val itr = List(3, 5).iterator
    // reduceLeftと引数の順番が違うので気をつける！
    val result = List(1, 2, 3).reduceRight { (i, sum) =>
      assert(sum == itr.next())
      sum + i
    }
    assert(result == 6)
  }

  it("foreach ... 戻り値なしで全ての要素を処理する") {
    val itr = List(1, 2, 3).iterator

    List(1, 2, 3).foreach { i =>
      assert(i == itr.next())
    }
  }

  it("filter ... 条件に一致する要素のみを抜き出す") {
    val result = List(1, 2, 3).filter{ 2 <= _ }
    assert(result == List(2, 3))
  }

  it("filter ... 条件に一致しない要素のみを抜き出す") {
    val result = List(1, 2, 3).filterNot{ 2 <= _ }
    assert(result == List(1))
  }

  it("drop ... 指定した数の要素を先頭から取り除く") {
    assert(List(1, 2, 3).drop(0) == List(1, 2, 3))
    assert(List(1, 2, 3).drop(1) == List(2, 3))
    assert(List(1, 2, 3).drop(2) == List(3))
    assert(List(1, 2, 3).drop(3) == List())
    assert(List(1, 2, 3).drop(4) == List())
  }

  it("dropWhile ... 条件がfalseになるまで要素を取り除く") {
    assert(List(1, 2, 3).dropWhile{ _ <= 2 } == List(3))
    assert(List(1, 2, 3).dropWhile{ i => i == 1 || i == 3 } == List(2, 3))
  }

  it("take ... 指定した数の要素を先頭から取り出す") {
    assert(List(1, 2, 3).take(0) == List())
    assert(List(1, 2, 3).take(1) == List(1))
    assert(List(1, 2, 3).take(2) == List(1, 2))
    assert(List(1, 2, 3).take(3) == List(1, 2, 3))
    assert(List(1, 2, 3).take(4) == List(1, 2, 3))
  }

  it("takeWhile ... 条件がfalseになるまで要素を取り出す") {
    assert(List(1, 2, 3).takeWhile{ _ <= 2 } == List(1, 2))
    assert(List(1, 2, 3).takeWhile{ i => i == 1 || i == 3 } == List(1))
  }

  it("map ... 要素に関数を適用して新しいコレクションを返す") {
    assert(List(1, 2, 3).map(_ * 2) == List(2, 4, 6))
  }

  it("flatMap ... 要素に関数を適用して新しいコレクションを返しflattenする") {
    assert(List(1, 2, 3).flatMap(i => List(i, i * 2)) == List(1, 2, 2, 4, 3, 6))
  }
}
