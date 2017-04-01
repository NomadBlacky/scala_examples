package org.nomadblacky.scala.samples.scala

import org.scalatest.FunSpec

import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success}

/**
  * Created by blacky on 17/03/31.
  */
class FutureSpec extends FunSpec {

  it("Futureの基本") {
    // Future#apply で Futureインスタンスを生成
    val f = Future {
      // インスタンス生成と同時に別スレッドで実行される
      println("A")
      Thread.sleep(1000)
      println("B")
      "ok"
    }
    println("C")

    // Future#isComplete ... Futureの処理が完了したか
    assert(!f.isCompleted)

    // Future#value ... 現在時点のの値をOption[Try[T]]で取得する
    // 未完了 → None  完了 → Some[Try[T]]
    assert(f.value.isEmpty)

    println("D")

    Thread.sleep(2000)

    assert(f.isCompleted)
    assert(f.value.get.get == "ok")
  }

  it("Await ... スレッドの終了を待機する") {
    val f = Future {
      Thread.sleep(1000)
      "ok"
    }
    // Await.result でスレッドの終了を待機して結果を受け取る
    val result = Await.result(f, Duration.Inf)
    assert(result == "ok")

    val f2: Future[String] = Future {
      Thread.sleep(1000)
      throw new RuntimeException()
    }
    // Futureの例外を受け取りたい場合は Await.ready で処理を終えてからFutureのメソッドで結果を受け取る
    Await.ready(f2, Duration.Inf)
    f2.value.get match {
      case Success(_) => fail()
      case Failure(ex) => assert(ex.isInstanceOf[RuntimeException])
    }
  }
}
