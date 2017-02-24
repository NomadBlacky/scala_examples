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
      case _:RunCompleted  => writeTableOfContents2(succeededTests)
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

  private def writeTableOfContents2(tests: Seq[TestSucceeded]): Unit = {
    val header = "# Table of Contents\n"
    val text:StringBuilder =
      tests.groupBy(_.suiteName).
      foldLeft(new StringBuilder(header)) { (sb, pair) =>
        val (suite, tests) = pair
        sb.append(s"\n$suite\n")

        tests.sortBy(_.location.map(l => l match {
          case line:LineInFile => line.lineNumber
          case _ => Int.MaxValue
        }).getOrElse(Int.MaxValue)).foreach(t => {
          sb.append("")
        })

        sb
      }

    for (pw <- new PrintWriter(markdownFilePath.toFile)) {
      pw.println(text)
    }
  }

}
