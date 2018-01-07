package org.nomadblacky.scala.samples.scala

import org.scalatest.{FunSpec, Matchers}

class JVMSpec extends FunSpec with Matchers {

  override def suiteName: String = "JVM関連のあれこれ"

  it("クラスパスの一覧を取得する") {
    // Thanks! => https://gist.github.com/jessitron/8376139
    def urlses(cl: ClassLoader): Array[java.net.URL] = cl match {
      case null => Array()
      case u: java.net.URLClassLoader => u.getURLs ++ urlses(cl.getParent)
      case _ => urlses(cl.getParent)
    }

    val  urls = urlses(getClass.getClassLoader)
    println(urls.filterNot(_.toString.contains("ivy")).mkString("\n"))
  }
}
