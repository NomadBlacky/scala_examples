package dev.nomadblacky.scala_examples.basics

import org.scalatest.funspec.AnyFunSpec

class LazySpec extends AnyFunSpec {

  override def suiteName: String = "遅延評価"

  it("lazy ... 変数を遅延評価する") {
    val x          = 1
    lazy val lazyX = { println("initialize!"); x + 1 }
    println(lazyX)
    println(lazyX)
  }

}
