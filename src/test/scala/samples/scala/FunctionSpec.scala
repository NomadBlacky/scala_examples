package samples.scala

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
}