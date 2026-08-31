package org.nomadblacky.scala.samples.with_java

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class WithJavaSpec extends AnyFunSpec with Matchers {

  override def suiteName: String = "ScalaとJavaの結合"

  it("ScalaからJavaを使う 01") {
    val java = new JavaClass(1)
    java.f() shouldBe "1"
  }

  it("ScalaからJavaを使う 02") {
    val useScala = new UseScala(new ScalaClass(2))
    useScala.f2(ScalaCaseClass(3, "hoge")) shouldBe """ScalaCaseClass(3,hoge)"""
  }
}

class ScalaClass(val i: Int)

case class ScalaCaseClass(id: Long, name: String)

object ScalaObject {
  def f(i: Int): String = i.toString
}
