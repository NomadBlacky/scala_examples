package samples.libraries.scopt

import org.scalatest.FunSpec

/**
  * Created by blacky on 16/10/14.
  */
class ScoptSpec extends FunSpec {

  it("コマンドライン引数を解析する") {
    val parser = new scopt.OptionParser[Config]("ScoptSpec") {
      head("ScoptSpec")

      opt[String]('a', "aaa").action( (s, c) => {
        c.copy(aaa = s)
      }).text("set string to aaa")

      opt[Int]('b', "bbb").action((x, c) => {
        c.copy(bbb = Option(x))
      }).text("set int to bbb")

      opt[Unit]('c', "ccc").action( (b, c) => {
        c.copy(ccc = true)
      }).text("set true to ccc")
    }
    parser.parse(Seq("-a", "hoge", "--bbb", "10", "-c"), Config()) match {
      case Some(config: Config) =>
        println(config)
      case None =>
        throw new IllegalArgumentException("arguments are bad.")
    }
  }
}

case class Config(
                 aaa: String = "",
                 bbb: Option[Int] = None,
                 ccc: Boolean = false
                 )