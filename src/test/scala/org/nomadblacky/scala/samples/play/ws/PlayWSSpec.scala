package org.nomadblacky.scala.samples.play.ws

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import org.scalatest.{FunSpec, Matchers}
import play.api.libs.ws.StandaloneWSRequest
import play.api.libs.ws.ahc.StandaloneAhcWSClient

import scala.concurrent.Future


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

}
