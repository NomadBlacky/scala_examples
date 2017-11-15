package org.nomadblacky.scala.samples.best_practice

import java.awt.Point

import org.scalatest.{FunSpec, Matchers}

class PimpMyLibraryPatternSpec extends FunSpec with Matchers {

  override def suiteName: String = "[BestPractice] Pimp My Library パターン"

  it("`implicit class` で既存クラスにメソッドを追加する") {
    implicit class RichPoint(val point: Point) {
      def +(other: Point): Point = new Point(point.x + other.x, point.y + other.y)
      def -(other: Point): Point = new Point(point.x - other.x, point.y - other.y)
    }

    val p1 = new Point(1, 4)
    val p2 = new Point(2, 3)

    p1 + p2 shouldBe new Point(3, 7)
    p1 - p2 shouldBe new Point(-1, 1)
  }
}
