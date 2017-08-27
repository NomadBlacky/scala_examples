package org.nomadblacky.scala.samples.libraries.scalaz

import org.scalatest.FunSpec

import scalaz._
import Scalaz._

/**
  * Created by blacky on 17/04/13.
  *
  * NonEmptyList ... 要素がひとつ以上含まれることが保証されているList
  */
class NonEmptyListSpec extends FunSpec {

  val list = NonEmptyList(1, 2, 3)

  it("<:: ．．． 先頭に要素を追加する") {
    9 <:: list assert_=== NonEmptyList(9, 1, 2, 3)
  }

  it("head ... 先頭の要素を取り出す") {
    list.head assert_=== 1
  }

  it("size ... 要素の数を取得する") {
    list.size assert_=== 3
  }

  it("reverse ... リストを反転する") {
    list.reverse assert_=== NonEmptyList(3, 2, 1)
  }

  it("map ... 要素に関数を適用する") {
    list.map(_ * 2) assert_=== NonEmptyList(2, 4, 6)
  }

  it("flatmap ... 要素に関数を適用し、flattenする") {
    list.flatMap(x => NonEmptyList(x, x * 10)) assert_=== NonEmptyList(1, 10, 2, 20, 3, 30)
  }
}
