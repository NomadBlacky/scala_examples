package org.nomadblacky.scala.reporter

import java.io.PrintWriter
import java.nio.file.{Path, Paths}

import org.scalatest.Reporter
import org.scalatest.events._

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * Created by blacky on 16/11/06.
  */
class TableOfContentsReporter() extends Reporter {

  val markdownFilePath = Paths.get("README.md")
  val stringBuilder = new StringBuilder()

  val succeededTests = ListBuffer[TestSucceeded]()

  override def apply(event: Event): Unit = {
    event match {
      case e:TestSucceeded => succeededTests += e
      case _:RunCompleted  => writeTableOfContents()

      //      case _: RecordableEvent =>
      //      case _: ExceptionalEvent =>
      //      case _: NotificationEvent =>
      //      case TestStarting(ordinal, suiteName, suiteId, suiteClassName, testName, testText, formatter, location, rerunner, payload, threadName, timeStamp) =>
      //      case TestSucceeded(ordinal, suiteName, suiteId, suiteClassName, testName, testText, recordedEvents, duration, formatter, location, rerunner, payload, threadName, timeStamp) =>
      //      case TestFailed(ordinal, message, suiteName, suiteId, suiteClassName, testName, testText, recordedEvents, throwable, duration, formatter, location, rerunner, payload, threadName, timeStamp) =>
      //      case TestIgnored(ordinal, suiteName, suiteId, suiteClassName, testName, testText, formatter, location, payload, threadName, timeStamp) =>
      //      case TestPending(ordinal, suiteName, suiteId, suiteClassName, testName, testText, recordedEvents, duration, formatter, location, payload, threadName, timeStamp) =>
      //      case TestCanceled(ordinal, message, suiteName, suiteId, suiteClassName, testName, testText, recordedEvents, throwable, duration, formatter, location, rerunner, payload, threadName, timeStamp) =>
      //      case SuiteStarting(ordinal, suiteName, suiteId, suiteClassName, formatter, location, rerunner, payload, threadName, timeStamp) =>
      //      case SuiteCompleted(ordinal, suiteName, suiteId, suiteClassName, duration, formatter, location, rerunner, payload, threadName, timeStamp) =>
      //      case SuiteAborted(ordinal, message, suiteName, suiteId, suiteClassName, throwable, duration, formatter, location, rerunner, payload, threadName, timeStamp) =>
      //      case RunStarting(ordinal, testCount, configMap, formatter, location, payload, threadName, timeStamp) =>
      //      case RunCompleted(ordinal, duration, summary, formatter, location, payload, threadName, timeStamp) =>
      //      case RunStopped(ordinal, duration, summary, formatter, location, payload, threadName, timeStamp) =>
      //      case RunAborted(ordinal, message, throwable, duration, summary, formatter, location, payload, threadName, timeStamp) =>
      //      case InfoProvided(ordinal, message, nameInfo, throwable, formatter, location, payload, threadName, timeStamp) =>
      //      case AlertProvided(ordinal, message, nameInfo, throwable, formatter, location, payload, threadName, timeStamp) =>
      //      case NoteProvided(ordinal, message, nameInfo, throwable, formatter, location, payload, threadName, timeStamp) =>
      //      case MarkupProvided(ordinal, text, nameInfo, formatter, location, payload, threadName, timeStamp) =>
      //      case ScopeOpened(ordinal, message, nameInfo, formatter, location, payload, threadName, timeStamp) =>
      //      case ScopeClosed(ordinal, message, nameInfo, formatter, location, payload, threadName, timeStamp) =>
      //      case ScopePending(ordinal, message, nameInfo, formatter, location, payload, threadName, timeStamp) =>
      //      case DiscoveryStarting(ordinal, configMap, threadName, timeStamp) =>
      //      case DiscoveryCompleted(ordinal, duration, threadName, timeStamp) =>
      case _ => // Noting to do.
    }
  }

  // Refer to ... http://ym.hatenadiary.jp/entry/2015/04/02/163557
  implicit class Using[T <: AutoCloseable](resource: T) {
    def foreach[R](op: T => R): R = {
      try op(resource)
      catch { case e: Exception => throw e}
      finally resource.close()
    }
  }

  private def writeTableOfContents(): Unit = {
    for { pw <- new PrintWriter(markdownFilePath.toFile) } {
      pw.println("# Table of Contents\n")
      val map = new mutable.LinkedHashMap[String, mutable.Set[TestSucceeded]] with mutable.MultiMap[String, TestSucceeded]
      succeededTests.foldLeft(map) { (map, e) =>
        map.addBinding(e.suiteName, e)
      }.toSeq.sortBy(_._1).foreach { case (key, set) =>
        pw.println("\n## %s\n".format(key))
        set.toList.sortBy { test =>
          test.location match {
            case Some(location) => location match {
              case l:LineInFile => l.lineNumber
              case _ => Integer.MAX_VALUE
            }
            case _ => Integer.MAX_VALUE
          }
        }.foreach { test =>
          val regex = "%s(.+)".format(Paths.get(".").toAbsolutePath.toString.diff(".")).r("more")
          val path = test.location match {
            case Some(l:LineInFile) => l.filePathname match {
              case Some(p) => Some(Paths.get(p))
              case None => None
            }
            case _ => None
          }
          val githubRelativeUrl = path match {
            case Some(p) => p.toString match {
              case regex(more) => more
              case _ => None
            }
            case None => None
          }
          val lineNumber = test.location match {
            case Some(l:LineInFile) => l.lineNumber
            case _ => 1
          }
          pw.println("+ [%s](%s#L%d)".format(test.testName, githubRelativeUrl, lineNumber))
        }
      }
    }
  }
}
