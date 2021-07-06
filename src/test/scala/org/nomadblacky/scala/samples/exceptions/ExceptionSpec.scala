package org.nomadblacky.scala.samples.exceptions

import org.scalatest.FunSpec

/** try-catch-finally
  *
  * try {
  *   式
  * } catch {
  *   case 変数:例外クラスの型 => 例外処理の式
  *   ...
  * } finally {
  *   式
  * }
  */
class ExceptionSpec extends FunSpec {

  override def suiteName: String = "例外処理"

  it("基本的なtry-catch-finally") {
    val result =
      try {
        "a".toInt
      } catch {
        case e: NumberFormatException => {
          println("exception!")
          -1
        }
      } finally {
        println("finally!")
      }
  }
}
