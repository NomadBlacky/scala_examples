package org.nomadblacky.scala.samples.collections

import org.scalatest.FunSpec

import scala.collection.mutable.ArrayBuffer

/** Created by blacky on 17/01/16.
  *
  * Traversable ... 操作可能
  */
class TraversableSpec extends FunSpec {

  override def suiteName: String = "Traversable - コレクションの最上位に位置するトレイト"

  it("++ ... Traversableを連結する") {
    assert((List(1, 2) ++ List(3, 4)) == List(1, 2, 3, 4))
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
    val itr    = List(0, 1, 3).iterator
    val result = List(1, 2, 3).foldLeft(0) { (sum, i) =>
      assert(sum == itr.next())
      sum + i
    }
    assert(result == 6)

    // 省略
    assert((0 /: List(1, 2, 3)) { (sum, i) =>
      sum + i
    } == 6)
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
    assert((List(1, 2, 3) :\ 0) { (sum, i) =>
      sum + i
    } == 6)
  }

  it("reduceLeft ... 最初の要素を初期値として畳み込みを行う") {
    val itr    = List(1, 3).iterator
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
    val result = List(1, 2, 3).filter { 2 <= _ }
    assert(result == List(2, 3))
  }

  it("filter ... 条件に一致しない要素のみを抜き出す") {
    val result = List(1, 2, 3).filterNot { 2 <= _ }
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
    assert(List(1, 2, 3).dropWhile { _ <= 2 } == List(3))
    assert(List(1, 2, 3).dropWhile { i =>
      i == 1 || i == 3
    } == List(2, 3))
  }

  it("take ... 指定した数の要素を先頭から取り出す") {
    assert(List(1, 2, 3).take(0) == List())
    assert(List(1, 2, 3).take(1) == List(1))
    assert(List(1, 2, 3).take(2) == List(1, 2))
    assert(List(1, 2, 3).take(3) == List(1, 2, 3))
    assert(List(1, 2, 3).take(4) == List(1, 2, 3))
  }

  it("takeWhile ... 条件がfalseになるまで要素を取り出す") {
    assert(List(1, 2, 3).takeWhile { _ <= 2 } == List(1, 2))
    assert(List(1, 2, 3).takeWhile { i =>
      i == 1 || i == 3
    } == List(1))
  }

  it("map ... 要素に関数を適用して新しいコレクションを返す") {
    assert(List(1, 2, 3).map(_ * 2) == List(2, 4, 6))
  }

  it("flatMap ... 要素に関数を適用して新しいコレクションを返しflattenする") {
    assert(List(1, 2, 3).flatMap(i => List(i, i * 2)) == List(1, 2, 2, 4, 3, 6))
  }

  it("flatten ... 入れ子になったコレクションを1次元にする") {
    assert(List(List(1, 2), List(3, 4)).flatten == List(1, 2, 3, 4))
  }

  it("splitAt ... コレクションを分割する") {
    assert(List(1, 2, 3, 4).splitAt(2) == (List(1, 2), List(3, 4)))
  }

  it("slice ... コレクションの一部を抜き出す") {
    assert(List(1, 2, 3, 4, 5).slice(1, 3) == List(2, 3))
  }

  it("partition ... 条件を満たす要素とそうでない要素に分割する") {
    assert(List(1, 2, 3, 4, 5).partition(_ < 3) == (List(1, 2), List(3, 4, 5)))
    assert(List(1, 2, 3, 4, 5).partition(_ > 3) == (List(4, 5), List(1, 2, 3)))
  }

  it("span ... 条件がfalseとなった要素を堺にコレクションを分割する") {
    assert(List(1, 2, 3, 4, 5).span(_ < 3) == (List(1, 2), List(3, 4, 5)))
    assert(List(1, 2, 3, 4, 5).span(_ > 3) == (List(), List(1, 2, 3, 4, 5)))
  }

  it("groupBy ... 関数の結果をキーとして要素をMapにして返す") {
    assert(List("hoge", "foo", "bar").groupBy(_.length) == Map(3 -> List("foo", "bar"), 4 -> List("hoge")))
  }

  it("grouped ... N件ごとにコレクションを分割する") {
    val list = List(1, 2, 3, 4, 5)
    assert(list.grouped(2).toList == List(List(1, 2), List(3, 4), List(5)))
    assert(list.grouped(3).toList == List(List(1, 2, 3), List(4, 5)))
    assertThrows[IllegalArgumentException](list.grouped(0))
  }

  it("unzip ... 要素を2つのコレクションに分割する") {
    assert(List((1, "one"), (2, "two"), (3, "three")).unzip == (List(1, 2, 3), List("one", "two", "three")))
    assert(Map(1 -> "one", 2 -> "two", 3 -> "three").unzip == (List(1, 2, 3), List("one", "two", "three")))
  }

  it("unzip3 ... 要素を3つのコレクションに分割する") {
    assert(
      List((1, "one", "hoge"), (2, "two", "foo"), (3, "three", "bar")).unzip3 == (
        List(1, 2, 3),
        List(
          "one",
          "two",
          "three"
        ),
        List("hoge", "foo", "bar")
      )
    )
  }

  it("find ... 最初に条件を満たした要素をOptionで返す") {
    assert(List(1, 2, 3).find(1 < _) == Some(2))
    assert(List(1, 2, 3).find(2 < _) == Some(3))
    assert(List(1, 2, 3).find(3 < _) == None)
  }

  it("exists ... 条件を満たす要素があるか判定する") {
    assert(List(1, 2, 3).exists(_ == 1) == true)
    assert(List(1, 2, 3).exists(_ == 5) == false)
  }

  it("forall ... 全ての要素が条件を満たすか判定する") {
    assert(List(1, 2, 3).forall(0 < _) == true)
    assert(List(1, 2, 3).forall(0 > _) == false)
  }

  it("count ... 条件を満たす要素の個数を返す") {
    assert(List(1, 2, 3).count(1 < _) == 2)
    assert(List(1, 2, 3).count(3 < _) == 0)
  }

  it("size/length ... 要素の個数を返す") {
    assert(List(1, 2, 3).size == 3)
    assert(List(1, 2, 3).length == 3)
  }

  it("min ... 最小値を返す") {
    assert(List(1, 2, 3).min == 1)
    assert(List("a", "b", "c").min == "a")
  }

  it("max ... 最大値を返す") {
    assert(List(1, 2, 3).max == 3)
    assert(List("a", "b", "c").max == "c")
  }

  it("sum ... 合計値を返す") {
    assert(List(1, 2, 3).sum == 6)
    // error
    // assert(List("a", "b", "c").sum == "")
  }

  it("mkString ... コレクションから文字列を作る") {
    assert(List(1, 2, 3).mkString == "123")
    assert(List(1, 2, 3).mkString("-") == "1-2-3")
    assert(List(1, 2, 3).mkString("[", "-", "]") == "[1-2-3]")
  }

  it("toArray ... コレクションをArrayに変換する") {
    // ScalaのArrayはJava配列で実装されているので == の比較はポインタの比較になる？
    // assert(List(1, 2, 3).toArray == Array(1, 2, 3))
    assert(List(1, 2, 3).toArray.deep == Array(1, 2, 3).deep)
  }

  it("toBuffer ... コレクションをBufferに変換する") {
    assert(List(1, 2, 3).toBuffer == ArrayBuffer(1, 2, 3))
  }

  it("toIndexedSeq ... コレクションをIndexedSeqに変換する") {
    assert(List(1, 2, 3).toIndexedSeq == IndexedSeq(1, 2, 3))
  }

  it("toList ... コレクションをListに変換する") {
    assert(Array(1, 2, 3).toList == List(1, 2, 3))
  }

  it("toStream ... コレクションをStreamに変換する") {
    assert(List(1, 2, 3).toStream == Stream(1, 2, 3))
  }

  it("par ... 並列コレクション(ParIterable)に変換する") {
    Range(1, 100).par.foreach(println(_))
  }

  it("view/force ... コレクションの操作を遅延評価させる(中間データを作らない)") {
    assert(List(1, 2, 3).view.map(_ * 2).force == List(2, 4, 6))
  }

  it("collect ... PartialFunctionを適用して要素を変換する") {
    val list   = List(1, 2, 3, 4, 5)
    val result = list.collect {
      case 1 => "one"
      case 2 => "two"
      case 3 => "three"
      case _ => "-"
    }
    assert(result == List("one", "two", "three", "-", "-"))
  }

  it("collectFirst ... PartialFunctionに最初に一致した値を取得する") {
    val list   = List(9, 7, 4, 2, 1, 3, 9)
    val result = list.collectFirst {
      case 1 => "one"
      case 2 => "two"
      case 3 => "three"
    }
    assert(result == Some("two"))
  }

}
