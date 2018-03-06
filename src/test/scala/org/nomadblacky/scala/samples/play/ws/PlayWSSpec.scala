package org.nomadblacky.scala.samples.play.ws

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Keep, Sink}
import akka.util.ByteString
import org.scalatest.{FunSpec, Matchers}
import play.api.libs.ws.StandaloneWSRequest
import play.api.libs.ws.ahc.StandaloneAhcWSClient

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}


class PlayWSSpec extends FunSpec with Matchers {
  import scala.concurrent.ExecutionContext.Implicits.global

  override def suiteName: String = "Play WS ... Play製のHTTPクライアント"

  // Akkaを使っているので必要
  implicit val actorSystem: ActorSystem = ActorSystem()
  implicit val actorMaterializer: ActorMaterializer = ActorMaterializer()

  it("基本的なHTTPアクセス") {
    val client = StandaloneAhcWSClient()
    val future: Future[StandaloneWSRequest#Response] = client.url("https://google.co.jp").get()

    for (response <- future) {
      response.body should contain("<title>Google</title>")
    }
  }

  it("akka-streamsのSourceとして受け取る") {
    val client = StandaloneAhcWSClient()
    val flow = Flow[ByteString].filter(bs => bs.utf8String contains "G")
    val sink = Sink.fold[Int, ByteString](0)((acc, bs) => acc + bs.length)
    val future = client.url("https://google.co.jp").stream().flatMap { res =>
      res.bodyAsSource.via(flow).toMat(sink)(Keep.right).run()
    }
    val result = Await.result(future, Duration.Inf)
    println(result)
  }
}
