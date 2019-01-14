package org.nomadblacky.scala.samples.scala

import org.scalatest.FunSpec

class FunctionSpec extends FunSpec {

  override def suiteName: String = "Scalaの関数"
  
  it("関数リテラル") {
    val func = (x:Int, y:Int) => x + y
    assert(func(1, 2) == 3)
  }
  
  it("Functionトレイト") {
    val func:Function2[Int,Int,Int] = (x:Int, y:Int) => x + y
    assert(func(1, 2) == 3)
  }
  
  it("関数を引数に取る関数") {
    def calc(f:(Int,Int) => Int, num:Int): Int = f(num, num)
    val result = calc((x,y) => x * y, 2)
    assert(result == 4)
  }

  it("プレースホルダ構文") {
    // プレースホルダ…
    // 実際の内容をあとから挿入するために、仮に確保した場所のこと。

    // 関数の引数の記述を _ で省略できる。
    val func1:(Int, Int) => Int = (x:Int, y:Int) => x + y
    val func2:(Int, Int) => Int = _ + _
    val func3 = (_:Int) + (_:Int)
    assert(func1(1, 2) == func2(1, 2))
    assert(func2(1, 2) == func3(1, 2))
    assert(func1(1, 2) == func3(1, 2))
  }

  it("プレースホルダ構文2") {
    val func1:(String) => String = _.replaceAll("hoge", "foo")
    val func2 = (_:String).replaceAll(_:String, _:String)
    assert(func1("aaahogebbb") == func2("aaahogebbb", "hoge", "foo"))
  }

  it("関数の部分適用") {
    // 引数の一部を確定させた、新しい関数を返すこと。

    def add(x:Int, y:Int):Int = x + y
    // 関数の後ろに _ をつけると、関数オブジェクトに変換できる。
    val addFunc = add _
    val func = addFunc(_:Int, 5)

    assert(func(3) == add(3, 5))
  }

  it("関数のカリー化") {
    // 複数の引数を持つ関数を、ひとつの引数をもつ関数のチェーンに変換すること。
    def sumCurry(a:Int) = (b:Int) => (c:Int) => a + b + c
    def func1 = sumCurry(1)
    def func2 = func1(2)
    def value = func2(3)

    assert(sumCurry(1)(2)(3) == 6)
    assert(value == 6)
  }

  it("関数のカリー化2") {
    def sum(a:Int, b:Int, c:Int) = a + b + c
    def currySum = (sum _).curried

    assert(currySum(1)(2)(3) == 6)
  }

  it("関数の引数を遅延評価する") {
    def myWhile(conditional: => Boolean)(f: => Unit) {
      if(conditional) {
        f
        myWhile(conditional)(f)
      }
    }

    var count = 0
    myWhile(count < 5) {
      count += 1
    }
    assert(count == 5)
  }

  it("scratch01") {
    def f(a: Int)(b: Int): Int = a + b
    def g(c: Int): Int = c + 10

    // これをやりたい
    val k = (x: Int, y: Int) => g(f(x)(y))
    assert(k(10, 20) == 40)

    // これはだめ
    //val l = f(_).compose(g)

    // method と FunctionN は異なる
    // method は第一級ではない (下記をいずれも満たさない)
    // 第一級...
    //    * リテラルがある
    //    * 実行時に生成できる
    //    * 手続きや関数の結果として返すことができる
    //    * 変数に入れて使える
    //    * 手続きや関数に引数として与えることができる
    // Reference: http://tkawachi.github.io/blog/2014/11/26/1/

    // _ を使って明示的に変換する必要がある
    val l = f _ compose g
    assert(l(10)(20) == 40)

    // 翻訳
    val ll1: (Int) => (Int) => Int = (f _)
    val ll2: (Int) => Int = g
    val ll3 = ll1 compose ll2
    assert(ll3(10)(20) == 40)
  }

  it("scratch01 by Mr.aiya000") {
    def comp[A,B,C](g: B => C)(f: A => B)(x: A): C = g(f(x))
    def f(a: Int)(b: Int): Int = a + b
    def g(c: Int): Int = c + 10

    val k = comp(f)(g)(_)
    assert(k(10)(20) == 40)
  }
}