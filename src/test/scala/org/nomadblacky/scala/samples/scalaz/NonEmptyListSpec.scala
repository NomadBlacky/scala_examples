package org.nomadblacky.scala.samples.scalaz

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
}
