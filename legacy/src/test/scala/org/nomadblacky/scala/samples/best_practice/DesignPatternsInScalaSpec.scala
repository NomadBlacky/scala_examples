package org.nomadblacky.scala.samples.best_practice

import java.awt.Point
import java.io.File
import java.util.Scanner


import scala.annotation.tailrec
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class DesignPatternsInScalaSpec extends AnyFunSpec with Matchers {

  override def suiteName: String = "[BestPractice] Scalaでのデザインパターン"

  it("Pimp My Library パターンで既存クラスにメソッドを追加する") {
    implicit class RichPoint(val point: Point) {
      def +(other: Point): Point = new Point(point.x + other.x, point.y + other.y)
      def -(other: Point): Point = new Point(point.x - other.x, point.y - other.y)
    }

    val p1 = new Point(1, 4)
    val p2 = new Point(2, 3)

    p1 + p2 shouldBe new Point(3, 7)
    p1 - p2 shouldBe new Point(-1, 1)
  }

  it("LoanパターンでAutoClosingを実装する") {
    def using[Resource <: AutoCloseable, A](r: Resource)(f: Resource => A): A =
      try {
        f(r)
      } finally {
        r.close()
      }

    def linesCount(scanner: Scanner): Int = {
      @tailrec def loop(scanner: Scanner, i: Int): Int =
        if (scanner.hasNextLine) {
          scanner.nextLine()
          loop(scanner, i + 1)
        } else {
          i
        }
      loop(scanner, 0)
    }

    val scanner = new Scanner(new File("data/hightemp.txt"), "UTF-8")

    val result = using(scanner)(linesCount)
    result shouldBe 24

    val caught =
      intercept[IllegalStateException](scanner.nextLine())
    caught.getMessage shouldBe "Scanner closed"
  }
}
