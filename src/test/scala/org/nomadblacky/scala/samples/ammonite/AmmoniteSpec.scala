package org.nomadblacky.scala.samples.ammonite

import ammonite.ops._
import org.scalatest.{FunSpec, Matchers}

/**
  * http://ammonite.io/#Ammonite-Ops
  */
class AmmoniteSpec extends FunSpec with Matchers {

  override def suiteName: String = "Ammonite-Ops"

  it("パスを参照する") {
    pwd  // カレントディレクトリ
    home // ホームディレクトリ
    root // ルートディレクトリ

    // スラッシュで下の階層へ
    // Symbol か String でパスを指定できる
    val directory: FilePath = root/'home
    val file: FilePath = root/'tmp/"sample.scala"

    directory.toString shouldBe "/home"
    file.toString shouldBe "/tmp/sample.scala"
  }

  it("基本的なファイル操作") {
    // いわゆる ls コマンド
    // ちなみに `ls` がobjectで `!` がメソッドである
    // ( `!` メソッド内で `apply` を呼び出している)
    val files: LsSeq = ls! pwd

    ls.rec! pwd            // いわゆる find
    mkdir! pwd/'tmp/"hoge" // ディレクトリ作成

    cp(pwd/'tmp/"sample.txt", pwd/'tmp/"sample2.txt")  // コピー
    mv(pwd/'tmp/"sample2.txt", pwd/'tmp/"sample3.txt") // 移動
    rm! pwd/'tmp/"sample3.txt" // 削除
  }

  it("ファイルの読み書き") {
    // http://ammonite.io/#Operations

    // テキストファイルの読み込み
    read(pwd/'tmp/"sample.txt") shouldBe "Scala de Scala\n"
    read.bytes! pwd/'tmp/"sample.txt" // Array[Byte]
    read.lines! pwd/'tmp/"sample.txt" // Vector[String]

    // ファイルの書き込み
    write(pwd/'tmp/"hoge.txt", "Foo!")
    write.over(pwd/'tmp/"foo.txt", "Yaa!") // 上書き
    write.append(pwd/'tmp/"foo.txt", "Boo!") // 追記
  }

}
