package org.nomadblacky.scala.samples.s99

import org.scalatest.{FunSpec, Matchers}

import scala.annotation.tailrec

/** Weeble ゆるふわScala勉強会にて回答されたコードです。 https://weeyble-scala.connpass.com/
  */
class S99Spec extends FunSpec with Matchers {

  override def suiteName: String = "Ninety-Nine Scala Problems"

  it("P01 (*) Find the last element of a list.") {
    // List#last は最後の要素を返す
    def last[T](list: List[T]): T = list.last
    last(List(1, 1, 2, 3, 5, 8)) shouldBe 8
  }

  it("P02 (*) Find the last but one element of a list.") {
    val list   = List(1, 1, 2, 3, 5, 8)
    val expect = 5

    // A01
    def penultimate[T](list: List[T]): T = list(list.size - 2)
    penultimate(list) shouldBe expect

    // A02
    // List#init は最後の要素を除いたListを返す
    def penultimate2[T](list: List[T]): T = list.init.last
    penultimate2(list) shouldBe expect

    // A03 - 例外安全なパターン
    // List#lastOption は要素が含まれればSome(最後の値)を、
    // 空リストならNoneを返す
    def penultimate3[T](list: List[T]): Option[T] = list.init.lastOption
    penultimate3(list) shouldBe Some(expect)
    penultimate3(List(1)) shouldBe None
  }

  it("P03 (*) Find the Kth element of a list.") {
    val list   = List(1, 1, 2, 3, 5, 8)
    val index  = 2
    val expect = 2

    // A01
    // インデックスに基づき要素を返す
    def nth[T](i: Int, list: List[T]): T = list(i)
    nth(2, list) shouldBe expect

    // A02
    // 上のコードと同義。applyは省略できる。
    def nth2[T](n: Int, list: List[T]): T = list.apply(n)
    nth2(2, list) shouldBe expect

    // A03 - 例外安全なパターン
    @tailrec def nth3[T](n: Int, l: List[T]): Option[T] = {
      if (n == 0)
        l.headOption
      else if (l.isEmpty)
        None
      else
        nth3(n - 1, l.tail)
    }
    nth3(2, list) shouldBe Some(expect)
    nth3(9, list) shouldBe None

    // A04 - 例外安全なパターン2
    // List#lift は指定されたインデックスに要素が含まれればSome(要素)を、
    // 含まれていなければNoneを返す
    def nth4[T](i: Int, list: List[T]): Option[T] = list.lift(i)
    nth4(2, list) shouldBe Some(expect)
    nth4(9, list) shouldBe None
  }
}
