package org.nomadblacky.scala

package object reporter {

  // Refer to ... http://ym.hatenadiary.jp/entry/2015/04/02/163557
  implicit class Using[T <: AutoCloseable](resource: T) {
    def foreach[R](op: T => R): R = {
      try op(resource)
      catch { case e: Exception => throw e }
      finally resource.close()
    }
  }

}
