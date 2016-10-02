package samples.collections

import org.scalatest.FunSpec
import scala.collection.immutable.List
import scala.collection.immutable.Nil
import scala.collection.mutable.ListBuffer

/**
 * 要素同士を連結するデータ構造のリスト。
 * 順次呼び出しや再帰アクセスに強い。
 * ランダムアクセスは遅い。
 */
class ListSpec extends FunSpec {
  
  it("Listを生成する") {
    val l: List[Int] = List(1, 2, 3)
  }
  
  it("Listから値を取り出す") {
    val l: List[Int] = List(1, 2, 3)
    assert(l(0) == 1)
    assert(l(1) == 2)
    assert(l(2) == 3)
  }
  
  it("空のListを作成する") {
    val l: List[Int] = Nil
    assert(l.isEmpty == true)
  }
  
  it("ミュータブルなList") {
    val l: ListBuffer[Int] = ListBuffer.empty
    l += 1
    l += 2
    assert(l == ListBuffer(1, 2))
    l(0) = 10
    assert(l == ListBuffer(10, 2))
  }
  
  it("Listに値を追加する") {
    val l: List[String] = List("b")
    // 先頭に追加
    assert(("a" :: l) == List("a", "b"))
    // 末尾に追加
    assert((l :+ "c") == List("b", "c"))
  }
  
}