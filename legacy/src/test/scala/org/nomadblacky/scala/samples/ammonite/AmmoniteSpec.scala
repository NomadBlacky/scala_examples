package org.nomadblacky.scala.samples.ammonite

import ammonite.ops._
import org.scalatest.{BeforeAndAfterAll, FunSpec, Matchers}

/** http://ammonite.io/#Ammonite-Ops
  */
class AmmoniteSpec extends FunSpec with Matchers with BeforeAndAfterAll {

  override def suiteName: String = "Ammonite-Ops"

  override protected def beforeAll(): Unit = {
    val f = pwd / 'tmp / "hoge.txt"
    if (exists ! f) rm ! f
  }

  it("パスを参照する") {
    pwd  // カレントディレクトリ
    home // ホームディレクトリ
    root // ルートディレクトリ

    // スラッシュで下の階層へ
    // Symbol か String でパスを指定できる
    val directory: FilePath = root / 'home
    val file: FilePath      = root / 'tmp / "sample.scala"

    directory.toString shouldBe "/home"
    file.toString shouldBe "/tmp/sample.scala"
  }

  it("基本的なファイル操作") {
    // いわゆる ls コマンド
    // ちなみに `ls` がobjectで `!` がメソッドである
    // ( `!` メソッド内で `apply` を呼び出している)
    val files: LsSeq = ls ! pwd

    ls.rec ! pwd                // いわゆる find
    mkdir ! pwd / 'tmp / "hoge" // ディレクトリ作成

    cp(pwd / 'tmp / "sample.txt", pwd / 'tmp / "sample2.txt")  // コピー
    mv(pwd / 'tmp / "sample2.txt", pwd / 'tmp / "sample3.txt") // 移動
    rm ! pwd / 'tmp / "sample3.txt"                            // 削除
  }

  it("ファイルの読み書き") {
    // http://ammonite.io/#Operations

    // テキストファイルの読み込み
    read(pwd / 'tmp / "sample.txt") shouldBe "Scala de Scala\n"
    read.bytes ! pwd / 'tmp / "sample.txt" // Array[Byte]
    read.lines ! pwd / 'tmp / "sample.txt" // Vector[String]

    // ファイルの書き込み
    write(pwd / 'tmp / "hoge.txt", "Foo!")
    write.over(pwd / 'tmp / "foo.txt", "Yaa!")   // 上書き
    write.append(pwd / 'tmp / "foo.txt", "Boo!") // 追記
  }

  it("拡張演算子とワンライナー") {
    // http://ammonite.io/#Extensions

    // Traversableの拡張
    val seq = Seq(1, 2, 3)
    seq | (_ * 2) shouldBe seq.map(_ * 2)
    seq || (i => Seq(i, i * 2)) shouldBe seq.flatMap(i => Seq(i, i * 2))
    seq |? (_ % 2 == 0) shouldBe seq.filter(_ % 2 == 0)
    seq |& (_ + _) shouldBe seq.reduce(_ * _)
    seq |! println // foreach

    // Pipeable
    val func = (i: Int) => i * 2
    100 |> func shouldBe 200 // func(100)

    // Callable
    func ! 100 shouldBe 200 // func(100)

    // ワーキングディレクトリ以下の".scala"ファイルを読み込む
    ls.rec ! pwd |? (_.ext == "scala") | read
  }

  it("サブプロセスの実行") {
    // ワーキングディレクトリを implicit parameter として渡す
    import ammonite.ops.ImplicitWd._

    // % %% でコマンドを実行できる
    // 一見、コンパイルが通らなそうな見た目だが、これはDynamic#applyDynamicを使用しているので正しいコードである。
    // http://www.ne.jp/asahi/hishidama/home/tech/scala/dynamic.html
    % ls
    val res = %%('echo, "foo!")
    res.exitCode shouldBe 0
    res.out.string shouldBe "foo!\n"
  }
}
