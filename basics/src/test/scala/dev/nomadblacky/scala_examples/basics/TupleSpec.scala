package dev.nomadblacky.scala_examples.basics

import org.scalatest.funspec.AnyFunSpec

class TupleSpec extends AnyFunSpec {

  override def suiteName: String = "Tuple - 任意の複数の値を保持するクラス"

  it("タプルを生成する") {
    (1, "hoge", 1.0)
  }

  it("タプルから値を取り出す") {
    val t = (1, "hoge", 1.0)
    assert(t._1 == 1)
    assert(t._2 == "hoge")
    assert(t._3 == 1.0)
  }

  it("タプルの要素に意味付けをする") {
    val t = ("hoge", 20)

    // tuple._1 などとした場合、要素についての情報を何も伝えられないので、
    assert(t._1 == "hoge")
    assert(t._2 == 20)

    // このようにするとよい
    val (name, age) = t
    assert(name == "hoge")
    assert(age == 20)
  }
}
