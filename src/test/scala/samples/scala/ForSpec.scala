package samples.scala

import org.scalatest.FunSpec
import scala.collection.mutable.ListBuffer

/**
 * for (
 *   [ジェネレータ]
 *   [フィルタ]
 * ) 式
 * 
 * ※フィルタは任意
 * ※処理に複数の式を記述したい場合{}で囲む
 */
class ForSpec extends FunSpec {
  
  it("コレクションのイテレート") {
    for (i <- List(1,2,3,4,5)) {
      println(i)
    }
  }
  
  it("要素のフィルタ") {
    val l: ListBuffer[Int] = ListBuffer.empty
    for(i <- List(1,2,3,4,5) if 3 < i) {
      l += i
    }
    assert(l == ListBuffer(4,5))
  }
  
  it("yield ... forの結果を保持する") {
    val l = for(name <- List("Taro", "Jiro")) yield "I am " + name
    assert(l == List("I am Taro", "I am Jiro"))
  }
}