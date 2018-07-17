package org.nomadblacky.scala.samples.scala

import org.scalatest.{FunSpec, Matchers}

import scala.concurrent._
import scala.concurrent.duration._
import scala.util.{Failure, Success}

/**
  * Created by blacky on 17/03/31.
  */
class FutureSpec extends FunSpec with Matchers {

  override def suiteName: String = "Futureの使い方"

  /**
    * ExecutionContextとは?
    * ・Runnableのインスタンスを渡すと、よしなに非同期実行してくれる仕組み
    * ・実行タイミング・スレッドへの配分などは実装により異なる
    * ・ExecutionContext.Implicits.globalは通常、最大でCPUの論理プロセッサ数ぶんのスレッドを立ち上げ、処理する。
    *   ※オプションで「論理プロセッサ数 x N倍」に設定できる。
    */
  import ExecutionContext.Implicits.global

  it("Futureの基本") {
    // Future#apply で Futureインスタンスを生成
    // ここで、ExcutionContextが入ってくる
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

  it("sequence ... Seq[Future] を Future[Seq] に変換する") {
    val futures1: Seq[Future[Int]] = Seq(Future.successful(1), Future.successful(2), Future.successful(3))
    val resultF1: Future[Seq[Int]] = Future.sequence(futures1)
    Await.result(resultF1, 1.seconds) shouldBe Seq(1, 2, 3)

    // Failureが含まれる場合は失敗に寄せられる
    val futures2: Seq[Future[Int]] = Seq(Future.successful(1), Future.failed(new RuntimeException), Future.successful(3))
    val resultF2: Future[Seq[Int]] = Future.sequence(futures2)
    a [RuntimeException] shouldBe thrownBy(Await.result(resultF2, 3.seconds))
  }
}
