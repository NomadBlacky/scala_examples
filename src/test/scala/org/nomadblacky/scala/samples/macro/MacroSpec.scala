package org.nomadblacky.scala.samples.`macro`

import scala.language.experimental.macros
import scala.reflect.macros.blackbox.Context
import org.scalatest.FunSpec

/**
  * Created by blacky on 17/02/17.
  */
object Macros {
  def impl(c: Context) = {
    import c.universe._
    c.Expr[Unit](q"""println("macro!")""")
  }

  def hoge: Unit = macro impl
}

class MacroSpec extends FunSpec {

  it("macro") {
    Macros.hoge
  }
}
