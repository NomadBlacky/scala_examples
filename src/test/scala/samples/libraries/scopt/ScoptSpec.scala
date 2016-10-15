package samples.libraries.scopt

import org.scalatest.FunSpec

/**
  * Created by blacky on 16/10/14.
  */
class ScoptSpec extends FunSpec {

  it("parse") {
    val parser = new scopt.OptionParser[Config]("ScoptSpec") {
      head("ScoptSpec")
      opt[Unit]("verbose").action((_, c) => {
        c.copy(verbose = true)
      }).text("verbose!")
    }
    parser.parse("--help" :: Nil, Config())
  }
}

case class Config(
                 aaa: String = "",
                 bbb: Option[Int] = None,
                 verbose: Boolean = false
                 )