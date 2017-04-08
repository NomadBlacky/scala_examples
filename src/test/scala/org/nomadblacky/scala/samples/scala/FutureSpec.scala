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

  it("onComplete ... コールバック関数を定義する") {
    val f = Future {
      Thread.sleep(1000)
      "ok"
    }
    // onComplete はExecutionContextを必要とする(implicit parameter)
    // これ → import ExecutionContext.Implicits.global
    // メインスレッド、Futureのスレッド、コールバックのスレッドは基本的に別のスレッドで実行される
    f.onComplete {
      case Success(result) => assert(result == "ok")
      case Failure(_) => fail()
    }
    Await.ready(f, Duration.Inf)
  }

  it("map ... Futureの計算結果を処理するFutureを取得する") {
    { // Futureの結果がネストしてきたない
      val f1 = Future {
        "ok"
      }
      f1 onComplete {
        case Success(str) =>
          val f2 = Future {
            if (str == "ok") 1
            else 0
          }
          val result = Await.result(f2, Duration.Inf)
          assert(result == 1)
        case Failure(_) => fail()
      }
    }
    { // mapを使うとよい
      val f1 = Future {
        "ok"
      }
      val f2: Future[Int] = f1 map { str =>
        if (str == "ok") 1
        else fail()
      }
      val result = Await.result(f2, Duration.Inf)
      assert(result == 1)
    }
  }
}
