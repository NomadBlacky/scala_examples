package org.nomadblacky.scala.samples.nlp100

import org.scalatest.{FunSpec, Matchers}

import scala.io.Source

/**
  * 言語処理100本ノック 第2章: UNIXコマンド
  *
  * Weeble Scalaもくもく勉強会にて回答されたコードです。
  * https://weeyble-scala.connpass.com/
  */
class Chapter02Spec extends FunSpec with Matchers {

  it("テキストファイルの読み込み") {
    val strings: Iterator[String] = Source.fromFile("data/hightemp.txt").getLines()
    println(strings.mkString("\n"))
  }

  it("10. 行数のカウント") {
    /**
      * 行数をカウントせよ．確認にはwcコマンドを用いよ．
      */

    // A01
    val lines01 = Source.fromFile("data/hightemp.txt").getLines()
    val count01 = lines01.size
    count01 shouldBe 24

    // A02
    val lines02 = scala.io.Source.fromFile("data/hightemp.txt").getLines()
    val count02 = lines02.length
    count02 shouldBe 24
  }
}
