package org.nomadblacky.scala.samples.libraries.scalaz

import org.scalatest.FunSpec

import scalaz._
import Scalaz._

/** Created by blacky on 17/04/14.
  *
  * \/
  * Disjunction, Either
  * 数学の論理和の記号が由来 ∨
  *
  * -\/ ... Left
  * \/- ... Right
  */
class DisjunctionSpec extends FunSpec {

  override def suiteName: String = "[Scalaz] Disjunction - 強化版Either"

  it("Left,Rightの生成") {
    // Leftの生成
    val a: \/[Int, String] = -\/(1)
    val b: \/[Int, String] = \/.left(1) // leftメソッドで
    val c: Int \/ String   = -\/(1)     // 中置記法で
    a assert_=== b
    b assert_=== c

    // Rightの生成
    val d: \/[Int, String] = \/-("a")
    val e: \/[Int, String] = \/.right("a") // rightメソッドで
    val f: Int \/ String   = \/-("a")      // 中置記法で
    d assert_=== e
    e assert_=== f
  }
}
