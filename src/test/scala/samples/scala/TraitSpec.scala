package samples.scala

import org.scalatest.FunSpec

/**
  * Created by blacky on 16/11/05.
  *
  * Javaでいう interface の機能。
  * トレイト自体に実装を持つことができる。
  *
  * trait ... (人・ものの) 特性、特色、特徴
  */
class TraitSpec extends FunSpec{

  it("トレイトの基本") {
    trait Programmer {
      def coding: String = "I'm writing program code."
    }

    // クラスが明示的に継承をしない場合、 extends キーワードでミックスインする
    class Person(val name:String) extends Programmer

    assert(new Person("Tom").coding == "I'm writing program code.")
  }

  it("複数のトレイトをミックスインする") {
    trait Programmer {
      def coding:String = "I'm writing program code."
    }
    trait Designer {
      def design:String = "I'm making a design."
    }

    // with キーワードでミックスインする
    class Person(val name:String) extends Programmer with Designer
    val p = new Person("Alex")

    assert(p.coding == "I'm writing program code.")
    assert(p.design == "I'm making a design.")
  }

  it("インスタンス化のタイミングでミックスインする") {
    trait Programmer {
      def coding:String = "I'm writing program code."
    }
    class Person(val name:String)

    val p = new Person("Tom") with Programmer

    assert(p.coding == "I'm writing program code.")
  }
}
