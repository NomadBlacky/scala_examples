package org.nomadblacky.scala.samples.event.understanding_scala

import org.scalatest.FunSpec

/**
  * 6/10 Understanding Scala ~Scalaを理解しよう~
  * https://connpass.com/event/55308/
  *
  * Scalaの型システムを学ぶ
  * http://kmizu.github.io/understanding_scala/type_system/#/14
  */
class UnderstandingScalaTypeSpec extends FunSpec {

  override def suiteName: String = "Understanding Scala - Scalaの型システムを学ぶ"

  it("Any あらゆる型のスーパータイプ") {
    /**
      * 最低限のメソッドのみを提供
      */
    val a: Any = 10
    assert(a == 10)
    assert(a != 20)
    assert(a.toString == "10")
    assert(a.hashCode == 10)
    assert(a.equals(10))
  }

  it("AnyVal: あらゆる値型のスーパータイプ") {
    /**
      * Javaでいうプリミティブ型をまとめたもの
      * AnyValにnullは代入できない
      * 便宜上存在しているが、AnyValに意味があることは少ない
      */
    val int: AnyVal = 10
    val double: AnyVal = 1.0
    val char: AnyVal = 'a'
    val bool: AnyVal = true
    val unit: AnyVal = {}
    // だめ
    // val nul: AnyVal = null
  }

  it("AnyRef: あらゆる参照型のスーパータイプ") {
    /**
      * Javaでいうjava.lang.Object
      * 参照型のすべての値はAnyRefに代入できる
      */
    // だめ
    // val int: AnyRef = 10
    val string: AnyRef = "hoge"
    // eq ... 参照の等価性を判定する
    assert(string eq string)
  }

  it("Nothing: あらゆる型のサブタイプ") {
    /**
      * Javaでは相当する型が存在しない
      * あらゆる型のサブタイプ
      * → あらゆる型の変数に代入可能
      * → Nothing型の値は存在しない
      * 存在意義
      * → 必ず例外を投げて正常にreturnしないメソッドの型になる
      * → ジェネリクスと組み合わせて使う
      */
    def method(): Nothing = { throw new Exception() }
  }

  it("Null") {
    /**
      * あらゆる参照型のサブタイプ
      * 値はnullのみ
      * nullはあらゆる参照型に代入可能
      */
    val string: String = null
    // だめ
    // val int: Int = null
  }

  it("共変") {

  }

  it("反変") {

  }

  it("構造的部分型") {

  }

  it("高階多相") {

  }

}
