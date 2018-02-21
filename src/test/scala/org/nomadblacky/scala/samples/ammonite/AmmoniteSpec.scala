package org.nomadblacky.scala.samples.ammonite

import ammonite.ops._
import org.scalatest.{FunSpec, Matchers}

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
}
