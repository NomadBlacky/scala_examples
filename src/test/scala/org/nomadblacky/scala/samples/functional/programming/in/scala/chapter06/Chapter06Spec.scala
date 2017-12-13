package org.nomadblacky.scala.samples.functional.programming.in.scala.chapter06

import org.scalactic.{Equality, TolerantNumerics}
import org.scalatest.{FunSpec, Matchers}

class Chapter06Spec extends FunSpec with Matchers {

  implicit val doubleEquality: Equality[Double] = TolerantNumerics.tolerantDoubleEquality(0.0001)

  /**
    * [EXERCISE 6.1]
    */
  def nonNegativeInt(rng: RNG): (Int, RNG) = {
    val (n, rng2) = rng.nextInt
    val n2 = if (n == Int.MinValue) Int.MaxValue else Math.abs(n)
    (n2, rng2)
  }

  /**
    * [EXERCISE 6.2]
    */
  def double(rng: RNG): (Double, RNG) = {
    val (n, rng2) = nonNegativeInt(rng)
    (n / (Int.MaxValue.toDouble + 1), rng2)
  }


  it("[EXERCISE 6.1] ランダムな 0~Int.MaxValue のIntを生成する関数") {
    nonNegativeInt(SimpleRNG(10)) shouldBe (3847489, SimpleRNG(252149039181L))
  }

  it("[EXERCISE 6.2] ランダムな 0~1未満 のDoubleを生成する関数") {
    double(SimpleRNG(10)) shouldBe (0.0017916266806423664, SimpleRNG(252149039181L))
  }
}
