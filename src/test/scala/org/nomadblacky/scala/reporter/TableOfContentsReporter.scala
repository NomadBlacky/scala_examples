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
  val succeededTests: ListBuffer[TestSucceeded] = ListBuffer[TestSucceeded]()

  val header = "# Table of Contents\n"

  lazy val filePathRegex: Regex = {
    val currentDir = Paths.get(".").toAbsolutePath.getParent
    s"$currentDir/(.+)".r("more")
  }

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
    .collect { case l:LineInFile => l.lineNumber }
    .getOrElse(default)

  private def writeTableOfContents(): Unit = {
    val sortedSuites = succeededTests
      .foldLeft(new MMultiMap[String, TestSucceeded]) { (map, e) => map.addBinding(e.suiteName, e) }
      .toSeq
      .sortBy(_._1)

    val markdownLines: Seq[String] = sortedSuites
        .flatMap { case (suiteName, tests) =>
          val testLines = tests
            .toList
            .sortBy(getLineNumber(_, Int.MaxValue))
            .map(getTestDetailLine)
          Seq("", s"## $suiteName", "") ++ testLines
        }

    for (pw <- new PrintWriter(markdownFilePath.toFile)) {
      header +: markdownLines foreach pw.println
    }
  }

  private def getTestDetailLine(test: TestSucceeded): String = {
    val githubRelativeUrl: Option[String] = test
      .location
      .collect { case l:LineInFile => l.filePathname }
      .flatMap(_.map(Paths.get(_).toString))
      .collect { case filePathRegex(more) => more }

    githubRelativeUrl match {
      case Some(url) => s"+ [${test.testName}]($url#L${getLineNumber(test)})"
      case None => s"+ ${test.testName}"
    }
  }
}
