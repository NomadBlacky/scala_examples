package org.nomadblacky.scala.samples.scala

import org.scalatest.{FunSpec, Matchers}

/**
  * scala.Dynamic
  * 特殊な構文を提供するトレイト
  */
class DynamicSpec extends FunSpec with Matchers {

  override def suiteName: String = "scala.Dynamic ... 動的言語のような構文をサポートする"

  // Dynamicの構文を利用するのに必要
  import scala.language.dynamics

  it("applyDynamic") {
    case class MyMap[V](m: Map[String, V]) extends Dynamic {
      def applyDynamic(key: String)(default: V): V = m.getOrElse(key, default)
    }
    val mymap = MyMap(Map("aaa" -> 10))

    // フィールドに対してapplyしているような構文を書ける
    mymap.aaa(-1) shouldBe 10
    mymap.bbb(-1) shouldBe -1
  }

  it("applyDynamicNamed") {
    case class MyMap[V](m: Map[String, V]) extends Dynamic {
      def applyDynamicNamed[U](key: String)(args: (String, V => U)*): Map[String, U] = {
        val kvs = for {
          v  <- m.lift(key).toSeq
          (k, f) <- args
        } yield (k, f(v))
        kvs.toMap
      }
    }
    val mymap = MyMap(Map("aaa" -> 10))

    val f = (i: Int) => i * 2
    val g = (i: Int) => i * 10
    mymap.aaa(x = f, y = g) shouldBe Map("x" -> 20, "y" -> 100)
    mymap.bbb(x = f, y = g) shouldBe Map()
  }
}
