package org.nomadblacky.scala.samples.scala

import org.scalatest.FunSpec

/**
 * match式
 * 
 * x match {
 *   case [選択肢1] => [xが選択肢1にmatchした時の処理]
 *   case [選択肢2] => [xが選択肢2にmatchした時の処理]
 *   case _ => [xが選択肢にmatchしなかった時の処理]
 * }
 */
class MatchSpec extends FunSpec {
  
  it("基本的なマッチング") {
     val x = 10
     x match {
       case  1 => println("ng")
       case 10 => println("ok")
       case  _ => println("ng")
     }
  }
  
  it("型のマッチング") {
    val x:Any = "hoge"
    x match {
      case i: Int    => println(i)
      case s: String => println(s)
      case _         => println("other")
    }
  }
  
  it("パターンガード") {
    val x:Any = 50
    x match {
      case i: Int if 100 <= i => println(i)
      case _                  => println("other")
    }
  }

  it("リストのマッチング") {
    val list = List(1,2,3,4,5)
    list match {
      // リストの2番目の要素を変数に束縛して、それ以外を捨てる
      case List(_, i, _*) => assert(i == 2)
      case _ => fail()
    }
  }
}