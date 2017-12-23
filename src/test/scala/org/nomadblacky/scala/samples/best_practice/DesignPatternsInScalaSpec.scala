package org.nomadblacky.scala.samples.best_practice

import java.awt.Point
import java.io.File
import java.nio.charset.{Charset, StandardCharsets}

import org.apache.commons.io.FileUtils
import org.scalatest.{FunSpec, Matchers}

import scala.util.Try

class DesignPatternsInScalaSpec extends FunSpec with Matchers {

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
    def file2iterator[X](file: File, charset: Charset = StandardCharsets.UTF_8)(body: Iterator[String] => X): Try[X] = {
      import scala.collection.JavaConverters._
      def using[Resource <: {def close()}, A](r: Resource)(f: Resource => A): A = try {
        f(r)
      } finally {
        r.close()
      }
      Try(using(FileUtils.lineIterator(file, charset.toString))(lineItr => body(lineItr.asScala)))
    }

  }
}
