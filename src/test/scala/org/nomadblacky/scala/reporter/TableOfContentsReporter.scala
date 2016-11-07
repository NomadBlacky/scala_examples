package org.nomadblacky.scala.reporter

import java.io.PrintWriter
import java.nio.file.{Path, Paths}

import org.scalatest.Reporter
import org.scalatest.events._

/**
  * Created by blacky on 16/11/06.
  */
class TableOfContentsReporter() extends Reporter {

  val markdownFilePath: Path = Paths.get("README.md")
  val stringBuilder: StringBuilder = new StringBuilder()

  override def apply(event: Event): Unit = {
    event match {
      case RunStarting(ordinal, testCount, configMap, formatter, location, payload, threadName, timeStamp) =>
        stringBuilder.append("# Table of Contents\n\n")

      case SuiteStarting(ordinal, suiteName, suiteId, suiteClassName, formatter, location, rerunner, payload, threadName, timeStamp) =>
        stringBuilder.append("## " + suiteName + "\n\n")

      case SuiteCompleted(ordinal, suiteName, suiteId, suiteClassName, duration, formatter, location, rerunner, payload, threadName, timeStamp) =>
        stringBuilder.append("\n")

      case TestSucceeded(ordinal, suiteName, suiteId, suiteClassName, testName, testText, recordedEvents, duration, formatter, location, rerunner, payload, threadName, timeStamp) =>
        stringBuilder.append("+ " + testName + "\n")

      case TestFailed(ordinal, message, suiteName, suiteId, suiteClassName, testName, testText, recordedEvents, throwable, duration, formatter, location, rerunner, payload, threadName, timeStamp) =>
        stringBuilder.append("+ " + testName + " (Failed)\n")

      case TestIgnored(ordinal, suiteName, suiteId, suiteClassName, testName, testText, formatter, location, payload, threadName, timeStamp) =>
        stringBuilder.append("+ " + testName + " (Ignored)\n")

      case TestPending(ordinal, suiteName, suiteId, suiteClassName, testName, testText, recordedEvents, duration, formatter, location, payload, threadName, timeStamp) =>
        stringBuilder.append("+ " + testName + " (Pending)\n")

      case e: RunCompleted =>
        val pw = new PrintWriter(markdownFilePath.toFile)
        pw.println(stringBuilder.toString())
        pw.close()

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
}
