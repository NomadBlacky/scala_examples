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
}