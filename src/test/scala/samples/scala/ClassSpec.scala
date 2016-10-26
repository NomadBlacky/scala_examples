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
}
