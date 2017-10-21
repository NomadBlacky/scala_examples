package org.nomadblacky.scala.samples.akka.http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse, StatusCodes}
import akka.stream.ActorMaterializer
import akka.util.ByteString
import org.scalatest.FunSpec

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by blacky on 17/05/29.
  */
class AkkaHttpSpec extends FunSpec {

  override def suiteName: String = "Akka HTTP"

  it("クライアントAPI") {
    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()

    val responseFuture: Future[HttpResponse] =
      Http().singleRequest(HttpRequest(uri = "https://www.google.co.jp/"))

    responseFuture.foreach {
      case HttpResponse(StatusCodes.OK, _, entity, _) =>
        entity.dataBytes.runFold(ByteString(""))(_ ++ _).foreach { body =>
          println(body.utf8String)
        }
      case _ => throw new IllegalStateException("fail")
    }

    Await.ready(responseFuture, Duration.Inf)
  }

}
