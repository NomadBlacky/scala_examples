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

  val markdownFilePath: Path                    = Paths.get("README.md")
  val succeededTests: ListBuffer[TestSucceeded] = ListBuffer[TestSucceeded]()

  val header = "# Table of Contents\n"

  lazy val relativeFilePathRegex: Regex = {
    val currentDir = Paths.get(".").toAbsolutePath.getParent
    s"$currentDir/(.+)".r
  }

  override def apply(event: Event): Unit = event match {
    case e: TestSucceeded =>
      succeededTests += e
      println(s"${Console.GREEN}${e.testName}${Console.RESET}")
    case e: TestFailed =>
      println(s"${Console.RED}${e.testName}${Console.RESET}")
      e.throwable.foreach(_.printStackTrace())
    case _: RunCompleted => writeTableOfContents()
    case _               =>
  }

  private[reporter] def getLineNumber(test: TestSucceeded, default: Int = 1): Int =
    test.location
      .collect { case l: LineInFile => l.lineNumber }
      .getOrElse(default)

  private def writeTableOfContents(): Unit = {
    val sortedSuites = succeededTests
      .groupBy(_.suiteName)
      .toSeq
      .sortBy(_._1)

    val markdownLines: Seq[String] = sortedSuites
      .flatMap {
        case (suiteName, tests) =>
          val testLines = tests.toList
            .sortBy(getLineNumber(_, Int.MaxValue))
            .map(getTestDetailLine)
          Seq("", s"## $suiteName", "") ++ testLines
      }

    for (pw <- new PrintWriter(markdownFilePath.toFile)) {
      header +: markdownLines foreach pw.println
    }
  }

  private def getTestDetailLine(test: TestSucceeded): String = {
    val githubRelativeUrl = for {
      location         <- test.location.collect { case l: LineInFile => l }
      filePathname     <- location.filePathname
      absolutePathName = Paths.get(filePathname).toAbsolutePath.toString
      matchGroups      <- relativeFilePathRegex.unapplySeq(absolutePathName)
      relativeFilePath <- matchGroups.headOption
    } yield relativeFilePath

    githubRelativeUrl match {
      case Some(url) => s"+ [${test.testName}]($url#L${getLineNumber(test)})"
      case None      => s"+ ${test.testName}"
    }
  }
}
