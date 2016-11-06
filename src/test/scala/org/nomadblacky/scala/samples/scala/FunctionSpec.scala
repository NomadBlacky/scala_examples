package org.nomadblacky.scala.samples.scala

import org.scalatest.FunSpec

class FunctionSpec extends FunSpec {
  
  it("関数リテラル") {
    val func = (x:Int, y:Int) => x + y
    assert(func(1, 2) == 3)
  }
  
  it("Functionトレイト") {
    val func:Function2[Int,Int,Int] = (x:Int, y:Int) => x + y
    assert(func(1, 2) == 3)
  }
  
  it("関数を引数に取る関数") {
    val result = calc((x,y) => x * y, 2)
    assert(result == 4)
  }
  def calc(f:(Int,Int) => Int, num:Int): Int = f(num, num)

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
      println(count)
      count += 1
    }
  }
}