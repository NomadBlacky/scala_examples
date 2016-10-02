package samples.collections

import org.scalatest.FunSpec
import scala.collection.immutable.Set

class MapSpec extends FunSpec {
  
  it("Map() ... Mapを生成する") {
    val m: Map[Int, String] = Map[Int, String](1 -> "a", 2 -> "b", 3 -> "c")
  }
  
  it("Map.empty ... 空のMapを生成する") {
    val m: Map[Int, String] = Map.empty
    assert(m.isEmpty == true)
  }
  
  it("() ... Mapから値を取り出す") {
    val m = Map[Int, String](1 -> "a", 2 -> "b", 3 -> "c")
    assert(m(1) == "a")
    assert(m(2) == "b")
    assert(m(3) == "c")
  }
  
  it("+ ... 指定したキーと値のペアを追加したMapを返す") {
    val m = Map[Int, String](1 -> "a", 2 -> "b", 3 -> "c")
    assert((m + (4 -> "d")) == Map[Int,String](1 -> "a", 2 -> "b", 3 -> "c", 4 -> "d"))
  }
  
  it("- ... 指定したキーの要素を抜いたMapを返す") {
    val m = Map[Int, String](1 -> "a", 2 -> "b", 3 -> "c")
    assert((m - 3) == Map[Int, String](1 -> "a", 2 -> "b"))
  }

  it("ミュータブルなMap") {
    val m: scala.collection.mutable.Map[Int, String] = scala.collection.mutable.Map[Int, String](1 -> "a", 2 -> "b", 3 -> "c")
    m += (4 -> "d")
    assert(m(4) == "d")
  }
  
  it("keySet ... キーのSetを返す") {
    val m = Map[Int, String](1 -> "a", 2 -> "b", 3 -> "c")
    assert(m.keySet == Set(1, 2, 3))
  }
  
  it("values ... 値をIterableで返す") {
    val m = Map[Int, String](1 -> "a", 2 -> "b", 3 -> "c")
    // ↓だとだめ！ (http://www.ne.jp/asahi/hishidama/home/tech/scala/expression.html#h_notice)
    // assert(m.values.toArray == Array("a", "b", "c"))
    assert(m.values.toArray.sameElements(Array("a", "b", "c")))
  }
  
}