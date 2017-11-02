package org.nomadblacky.scala.samples.scala

import org.scalatest.FunSpec

class LazySpec extends FunSpec {

  override def suiteName: String = "遅延評価"
  
  it("lazy ... 変数を遅延評価する") {
    val x = 1
    lazy val lazyX = { println("initialize!"); x + 1 }
    println(lazyX)
    println(lazyX)
  }
  
}