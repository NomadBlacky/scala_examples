package org.nomadblacky.scala.samples.scala

import org.scalatest.FunSpec

/**
  * Created by blacky on 16/11/19.
  */
class TypeParameterSpec extends FunSpec {

  it("Scalaにおける型の検査") {
    val v1:Any = "hoge"
    val v2:Any = 123

    // Any#instanceOf で型の検査ができる
    assert(v1.isInstanceOf[String] == true)
    assert(v1.isInstanceOf[Int] == false)
    assert(v2.isInstanceOf[String] == false)
    assert(v2.isInstanceOf[Int] == true)

    // が、パターンマッチを使うほうが一般的
    v1 match {
      case v:String =>
      case v:Int => fail()
    }
    v2 match {
      case v:String => fail()
      case v:Int =>
    }
  }

}
