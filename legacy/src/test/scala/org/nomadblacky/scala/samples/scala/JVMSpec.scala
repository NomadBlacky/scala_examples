package org.nomadblacky.scala.samples.scala

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class JVMSpec extends AnyFunSpec with Matchers {

  override def suiteName: String = "JVM関連のあれこれ"

  it("クラスパスの一覧を取得する") {
    // Thanks! => https://gist.github.com/jessitron/8376139
    def urlses(cl: ClassLoader): Array[java.net.URL] = cl match {
      case null                       => Array()
      case u: java.net.URLClassLoader => u.getURLs ++ urlses(cl.getParent)
      case _                          => urlses(cl.getParent)
    }

    val urls = urlses(ClassLoader.getSystemClassLoader)
    println(urls.mkString("\n"))
  }
}
