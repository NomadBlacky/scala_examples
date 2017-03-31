package org.nomadblacky.scala.samples.scala

import org.scalatest.FunSpec

import scala.concurrent._
import ExecutionContext.Implicits.global
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
    assert(f.value == None)

    println("D")

    Thread.sleep(2000)

    assert(f.isCompleted)
    assert(f.value.get.get == "ok")
  }

}
