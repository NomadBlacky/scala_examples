package dev.nomadblacky.scala_examples.akka_stream

import akka.actor.ActorSystem
import akka.stream.scaladsl.{Concat, Merge, Source}
import akka.stream.testkit.scaladsl.TestSink
import org.scalatest.funspec.AnyFunSpec

class SourceOperatorsSpec extends AnyFunSpec {

  override def suiteName: String = "Source のオペレータ"

  private implicit val system: ActorSystem = ActorSystem("source-operations")

  it("apply - Iterable から Source をつくる") {
    val source = Source(Seq(1, 2, 3))
    source.runWith(TestSink[Int]()).request(3).expectNext(1, 2, 3).expectComplete()
  }

  describe("combine - 複数の Source を結合する") {
    val source1 = Source(1 to 3)
    val source2 = Source(4 to 6)
    val source3 = Source(7 to 9)

    it("Merge - 順不同で結合する") {
      val combined = Source.combine(source1, source2, source3)(Merge(_))
      combined.runWith(TestSink[Int]()).request(9).expectNextUnorderedN(1 to 9).expectComplete()
    }

    it("Concat - Source の順番で結合する") {
      val combined = Source.combine(source1, source2, source3)(Concat(_))
      combined.runWith(TestSink[Int]()).request(9).expectNextN(1 to 9).expectComplete()
    }
  }

  it("cycle - 要素を繰り返す") {
    val source = Source.cycle(() => Seq(1, 2, 3).iterator)
    source.runWith(TestSink[Int]()).request(6).expectNext(1, 2, 3, 1, 2, 3)
  }
}
