package org.nomadblacky.scala.reporter

import java.io.PrintWriter
import java.nio.file.{Path, Paths}

import org.scalatest.Reporter
import org.scalatest.events._

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.matching.Regex

/**
  * Created by blacky on 16/11/06.
  */
class TableOfContentsReporter() extends Reporter {

  val markdownFilePath: Path = Paths.get("README.md")
  val stringBuilder: scala.StringBuilder = new StringBuilder()
  val succeededTests: ListBuffer[TestSucceeded] = ListBuffer[TestSucceeded]()

  lazy val filePathRegex: Regex = "%s(.+)".format(Paths.get(".").toAbsolutePath.toString.diff(".")).r("more")

  class MMultiMap[K,V] extends mutable.LinkedHashMap[K,mutable.Set[V]] with mutable.MultiMap[K,V]


  override def apply(event: Event): Unit = event match {
    case e:TestSucceeded => succeededTests += e
    case _:RunCompleted  => writeTableOfContents()
    case _ =>
  }

  // Refer to ... http://ym.hatenadiary.jp/entry/2015/04/02/163557
  implicit class Using[T <: AutoCloseable](resource: T) {
    def foreach[R](op: T => R): R = {
      try op(resource)
      catch { case e: Exception => throw e }
      finally resource.close()
    }
  }

  def getLineNumber(test: TestSucceeded, default: Int = 1): Int = test
    .location
    .flatMap {
      case l:LineInFile => Some(l.lineNumber)
      case _ => None
    }
    .getOrElse(default)

  private def writeTableOfContents(): Unit = {
    for (pw <- new PrintWriter(markdownFilePath.toFile)) {
      pw.println("# Table of Contents\n")

      val sortedSuites = succeededTests
        .foldLeft(new MMultiMap[String, TestSucceeded]) { (map, e) => map.addBinding(e.suiteName, e) }
        .toSeq
        .sortBy(_._1)

      sortedSuites.foreach { case (suiteName, tests) =>
        pw.println("\n## %s\n".format(suiteName))
        tests
          .toList
          .sortBy(getLineNumber(_, Int.MaxValue))
          .foreach(eachTest(pw, _))
      }
    }
  }

  private def eachTest(pw: PrintWriter, test: TestSucceeded): Unit = {
    val githubRelativeUrl: Option[String] = test
      .location
      .flatMap {
        case l:LineInFile => l.filePathname.map(Paths.get(_).toString)
        case _ => None
      }
      .flatMap {
        case filePathRegex(more) => Some(more)
        case _ => None
      }

    val line = githubRelativeUrl match {
      case Some(url) => s"+ [${test.testName}]($url#L${getLineNumber(test)})"
      case None => s"+ ${test.testName}"
    }
    pw.println(line)
  }
}
