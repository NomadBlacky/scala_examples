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

  it("[EXERCISE 6.3] ランダムな値のタプルを生成する関数") {
    def intDouble(rng: RNG): ((Int, Double), RNG) = {
      val (i, rng2) = nonNegativeInt(rng)
      val (d, rng3) = double(rng2)
      ((i, d), rng3)
    }
    def doubleInt(rng: RNG): ((Double, Int), RNG) = {
      val (d, rng2) = double(rng)
      val (i, rng3) = nonNegativeInt(rng2)
      ((d, i), rng3)
    }
    def double3(rng: RNG): ((Double, Double, Double), RNG) = {
      val (d1, rng2) = double(rng)
      val (d2, rng3) = double(rng2)
      val (d3, rng4) = double(rng3)
      ((d1, d2, d3), rng4)
    }

    intDouble(SimpleRNG(10)) shouldBe ((3847489, 0.6213264381513), SimpleRNG(87443922374356L))
    doubleInt(SimpleRNG(10)) shouldBe ((0.0017916266806423664, 1334288366), SimpleRNG(87443922374356L))
    double3(SimpleRNG(10)) shouldBe ((0.0017916266806423664, 0.6213264381513, 0.6923740776255727), SimpleRNG(97442988689487L))
  }

  it("[EXERCISE 6.4] ランダムな整数のリストを作成する関数") {
    def ints(count: Int)(rng: RNG): (List[Int], RNG) = {
      (1 to count).foldLeft((List.empty[Int], rng)) { case ((list, r), _) =>
        val (i, r2) = nonNegativeInt(r)
        (list :+ i, r2)
      }
    }

    val expect = (List(3847489, 1334288366, 1486862010, 711662464, 1453296530), SimpleRNG(186231735346465L))

    ints(5)(SimpleRNG(10)) shouldBe expect
  }
}
