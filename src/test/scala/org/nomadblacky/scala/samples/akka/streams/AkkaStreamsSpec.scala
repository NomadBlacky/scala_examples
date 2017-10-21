package org.nomadblacky.scala.samples.akka.streams

import akka.actor.ActorSystem
import org.scalatest.{FunSpec, Matchers}

/**
  * Akka Streams
  * 非同期ストリーム処理のライブラリ
  *
  * 特徴
  * + バックプレッシャー … サブスクライバが処理できる量をパブリッシャに送ることで、無駄なく処理ができる。
  * + リアクティブストリーム … 異なるストリーム処理ツール間でもバックプレッシャを実現できる。
  * + 非同期
  * + 直感的なAPI
  * + DSLによるグラフ処理
  */
class AkkaStreamsSpec extends FunSpec with Matchers {

  override def suiteName: String = "Akka Streams"

  import akka.stream._
  import akka.stream.scaladsl._

  it("ことはじめ") {
    // Actorを使っているため、ActorSystemが必要
    implicit val actorSystem: ActorSystem = ActorSystem()
    // 設定・ロジックなど
    implicit val materializer: ActorMaterializer = ActorMaterializer()

    val source = Source(1 to 10) // Publisher
    val sink = Sink.foreach(println) // Subscriber

    source
      .map(_ * 2) // Stage (Actorが立ち上がる)
      .runWith(sink) // Sinkで結果を受け取る
  }
}
