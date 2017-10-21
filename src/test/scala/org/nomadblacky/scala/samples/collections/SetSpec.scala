package org.nomadblacky.scala.samples.collections

import org.scalatest.FunSpec

/**
  * 集合
  * http://docs.scala-lang.org/ja/overviews/collections/sets
  *
  * Created by blacky on 17/03/18.
  */
class SetSpec extends FunSpec {

  override def suiteName: String = "Set - 重複する要素を含まないコレクション"

  val s1 = Set(1, 2, 3)
  val s2 = Set(3, 4, 5)

  it("apply, contains ... 要素が含まれるかを調べる") {
    assert(s1.contains(1) == true)
    assert(s1.contains(9) == false)
    assert(s2(3) == true)
    assert(s2(9) == false)
  }

  it("subsetOf ... 部分集合であるか調べる") {
    assert(Set(1, 2).subsetOf(s1) == true)
    assert(Set(3, 4).subsetOf(s1) == false)
    assert(Set(8, 9).subsetOf(s1) == false)
  }

  it("+ ... 渡された要素を追加した集合を返す") {
    assert(s1 + 4 == Set(1, 2, 3, 4))
    assert(s1 + 1 == Set(1, 2, 3))
    assert(s1 + (3, 4, 5) == Set(1, 2, 3, 4, 5))
  }

  it("++ ... 渡された集合を追加した集合を返す") {
    assert(s1 ++ s2 == Set(1, 2, 3, 4, 5))
  }

  it("- ... 渡された要素を除いた集合を返す") {
    assert(s1 - 1 == Set(2, 3))
    assert(s1 - 4 == Set(1, 2, 3))
    assert(s1 - (2, 3, 4) == Set(1))
  }

  it("-- ... 渡された集合のすべての要素を除いた集合を返す") {
    assert(s1 -- Set(1, 2) == Set(3))
    assert(s1 -- s2 == Set(1, 2))
  }

  it("empty ... 集合と同じクラスの空集合を返す") {
    assert(Set(1, 2, 3).empty == Set[Int]())
    assert(Set("a", "b", "c").empty == Set[String]())
  }

  it("&, intersect ... 積集合") {
    assert((s1 & s2) == Set(3))
    assert((s1 intersect s2) == Set(3))
  }

  it("|, union ... 和集合") {
    assert((s1 | s2) == Set(1, 2, 3, 4, 5))
    assert((s1 union s2) == Set(1, 2, 3, 4, 5))
  }

  it("&~, diff ... 差集合") {
    assert((s1 &~ s2) == Set(1, 2))
    assert((s1 diff s2) == Set(1, 2))
  }
}
