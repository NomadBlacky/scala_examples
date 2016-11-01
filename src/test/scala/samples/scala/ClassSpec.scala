package samples.scala

import org.scalatest.FunSpec

/**
  * Created by blacky on 16/10/20.
  */
class ClassSpec extends FunSpec{

  it("require ... 引数を検証する") {
    class Programmer(_language:String) {
      require(_language != null && !_language.isEmpty)
      val language = _language
    }

    new Programmer("Scala")

    intercept[IllegalArgumentException] {
      new Programmer("")
    }
  }

  it("unapplyを使用したパターンマッチ") {
    class Designer
    object Designer {
      def unapply(arg: Any): Boolean = {
        if (arg.isInstanceOf[Designer]) true else false
      }
    }
    class Programmer(val language: String)
    object Programmer {
      def apply(language: String): Programmer = new Programmer(language)
      def unapply(arg: Programmer): Option[String] = Some(arg.language)
    }

    def matchTest(value: Any):String = {
      value match {
        case Designer => "designer"
        case Programmer("scala") => "scala programmer"
        case _ => "other"
      }
    }

    assert(matchTest(Designer) == "designer")
    assert(matchTest(Programmer("scala")) == "scala programmer")
    assert(matchTest(Programmer("java")) == "other")
  }
}
