package org.nomadblacky.scala.samples.libraries.scopt

import java.io.File
import java.util.Calendar

import org.scalatest.FunSpec

/**
  * Created by blacky on 16/10/14.
  */
class ScoptSpec extends FunSpec {

  override def suiteName: String = "Scopt - コマンドライン引数を解析するライブラリ"

  val parser = new scopt.OptionParser[Config]("ScoptSpec") {
    head("ScoptSpec")

    opt[String]('a', "aaa")
      .action((s, c) => {
        c.copy(aaa = s)
      })
      .text("set string to aaa")

    opt[Int]('b', "bbb")
      .action((x, c) => {
        c.copy(bbb = Some(x))
      })
      .text("set int to bbb")

    opt[Unit]('c', "ccc")
      .action((b, c) => {
        c.copy(ccc = true)
      })
      .text("set true to ccc")

    opt[Calendar]("calendar").action((x, c) => {
      c.copy(calendar = Some(x))
    })

    opt[File]("file").action((x, c) => {
      c.copy(file = Some(x))
    })
  }

  it("コマンドライン引数を解析する") {
    parser.parse(List("-a", "hoge", "--bbb", "10", "-c"), Config()) match {
      case Some(config: Config) =>
        println(config)
      case None =>
        throw new IllegalArgumentException("arguments are bad.")
    }
  }

  it("引数から日付を取得") {
    parser.parse(List("--calendar", "2016-1-1"), Config()) match {
      case Some(config: Config) =>
        //FIXME: Calendarの比較
        println(config.calendar)
      case None =>
        throw new IllegalArgumentException("arguments are bad.")
    }
  }

  it("引数からファイルを取得") {
    parser.parse(List("--file", "build.sbt"), Config()) match {
      case Some(config: Config) =>
        assert(
          config.file
            .getOrElse(throw new IllegalArgumentException())
            .getAbsolutePath == new File("build.sbt").getAbsolutePath
        )
      case None =>
        throw new IllegalArgumentException("arguments are bad.")
    }
  }
}

case class Config(
    aaa: String = "",
    bbb: Option[Int] = None,
    ccc: Boolean = false,
    calendar: Option[Calendar] = None,
    file: Option[File] = None
)
