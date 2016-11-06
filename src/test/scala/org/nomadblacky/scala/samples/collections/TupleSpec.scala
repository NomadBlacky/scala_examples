package org.nomadblacky.scala.samples.collections

import org.scalatest.FunSpec

class TupleSpec extends FunSpec {
  
  it("タプルを生成する") {
    val t = (1, "hoge", 1.0)
  }
  
  it("タプルから値を取り出す") {
    val t = (1, "hoge", 1.0)
    assert(t._1 == 1)
    assert(t._2 == "hoge")
    assert(t._3 == 1.0)
  }
}