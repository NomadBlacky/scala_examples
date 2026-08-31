package org.nomadblacky.scala.samples.event.understanding_scala


import scala.concurrent.Future
import org.scalatest.funspec.AnyFunSpec

/** 6/10 Understanding Scala ~Scalaを理解しよう~ https://connpass.com/event/55308/
  *
  * Scalaの謎・落とし穴を学ぶ http://kmizu.github.io/understanding_scala/pitfall/#/7
  */
class UnderstandingScalaTrapSpec extends AnyFunSpec {

  override def suiteName: String = "[勉強会] Understanding Scala - Scalaの落とし穴を学ぶ"

  it("Unitを返す関数を意図せず書いてしまう") {
    // Javaのノリで書くとUnitが返る関数ができる
    def double(x: Int) {
      x * 2
    }
    assert(double(1).isInstanceOf[Unit])

    // 解決策: 戻り値の型を明示的に書く
    def double2(x: Int): Int = {
      x * 2
    }
    assert(double2(1) == 2)
  }

  it("変更可能コレクションの変更しない操作を呼び出してしまう") {
    val buffer = scala.collection.mutable.Buffer(1, 2, 3)
    assert(buffer.drop(1) == scala.collection.mutable.Buffer(2, 3))
    assert(buffer == scala.collection.mutable.Buffer(1, 2, 3))
  }

  it("配列同士の==") {
    val x = Array(1, 2, 3)
    val y = Array(1, 2, 3)
    // 配列はJVMの配列が使われるので、参照が一致しているかが比較される
    assert(!(x == y))
    // Arrays.deepEqualsを使う
    // TODO: Fix the compile error ↓
    // assert(util.Arrays.deepEquals(x, y))
  }

  it("誤ったFutureの使い方") {
    import scala.concurrent.ExecutionContext.Implicits.global

    // 以下のコードでは、処理が平行に実行されない
    for {
      f1 <- Future(1 + 2)
      f2 <- Future(3 + 4)
    } yield f1 + f2
    // 先にFutureを格納する変数を用意する
    val f1 = Future(1 + 2)
    val f2 = Future(3 + 4)
    for {
      a <- f1
      b <- f2
    } yield a + b
  }

  it("意図しない結果のパターンマッチ") {
    // TODO: Add sample code.
  }

  it("誤った正規表現のパターンマッチ") {
    // TODO: Add sample code.
  }

  it("既存の型同士のimplicit conversionは使わない") {
    {
      // だめ!
      implicit def int2boolean(n: Int): Boolean = n != 0

      if (1) {
        // doSomething
      }
    }
  }

  it("改行とブロックの組み合わせに注意") {
    // TODO: Add sample code.
  }

  it("Javaのメソッドの返り値に注意") {
    // Javaのメソッドはnullが返って来る
    val m = new java.util.HashMap[Int, String]
    assertThrows[NullPointerException](m.get(1).toLowerCase)
  }

  it("Set#mapの罠") {
    // Scalaのコレクションは可能な限り自分と同じ型を返そうとする
    assert(Set(1, 2, 3).map(_ => 2) == Set(2))
    // 対策: toListなどで他のコレクションに変換する
    assert(Set(1, 2, 3).toList.map(_ => 2) == List(2, 2, 2))
  }

  it("インナークラスのインスタンス") {
    class Outer {
      class Inner
      val I = new Inner
    }
    val outer              = new Outer
    val inner: Outer#Inner = outer.I
  }

  it("アンダースコア七変化") {
    // ワイルドカードインポート
    {}

    // 特定クラスを除外してインポート
    {
      import java.util.{List => _}
    }

    // ワイルドカードパターン
    1 match {
      case _ => // 必ずマッチする
    }

    // 仮引数を使わない
    List(1, 2, 3).map(_ => 4)

    // 可変長引数にコレクションを分解して渡す
    // _単体では意味はなく、:_*の組み合わせで意味を持つ
    printf("%d", List(1): _*)

    // メソッドを関数の型に変換する
    // メソッドはファーストクラスの型ではない
    val f: () => List[Int] = List(1, 2, 3).reverse _

    // プレースホルダ構文
    // 構文解析に作用する点で特異
    List(1, 2, 3).map(_ * 2)
  }

}
