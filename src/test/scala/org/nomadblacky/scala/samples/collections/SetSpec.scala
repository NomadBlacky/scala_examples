package org.nomadblacky.scala.samples.collections

import org.scalatest.FunSpec

/**
  * 集合
  * http://docs.scala-lang.org/ja/overviews/collections/sets
  *
  * Created by blacky on 17/03/18.
  */
class SetSpec extends FunSpec {

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


}
