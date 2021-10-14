package dev.nomadblacky.scala_examples.akka_stream

import akka.actor.ActorSystem
import akka.stream.scaladsl.Source
import akka.stream.testkit.scaladsl.TestSink
import org.scalatest.funspec.AnyFunSpec

class SourceOperatorsSpec extends AnyFunSpec {

  override def suiteName: String = "Source のオペレータ"

  private implicit val system: ActorSystem = ActorSystem("source-operations")

  it("apply - Iterable から Source をつくる") {
    val source = Source(Seq(1, 2, 3))
    source.runWith(TestSink[Int]()).request(3).expectNext(1, 2, 3).expectComplete()
  }
}
