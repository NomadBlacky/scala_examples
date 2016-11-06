package org.nomadblacky.scala.samples.scala

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

  it("caseクラスとパターンマッチ") {
    case class Point(x:Int, y:Int)

    def matchTest(value:Any):Int = {
      value match {
        case Point(10, y) => y + 1
        case _ => 0
      }
    }

    val p1 = Point(10, 30)
    assert(matchTest(p1) == 31)

    val p2 = p1.copy(y = 99)
    assert(p2.x == 10)
    assert(matchTest(p2) == 100)
  }

  it("抽象クラス") {
    abstract class Engineer {
      def work():String
      def study():String = "Do study."
    }

    class Programmer(name:String) extends Engineer {
      def work() = "%s is writing a program code.".format(name)
      // 具象メソッドをオーバーライドするときは override キーワードが必須
      override def study() = "%s is studying programming.".format(name)
    }

    val programmer = new Programmer("Tom")
    assert(programmer.work() == "Tom is writing a program code.")
    assert(programmer.study() == "Tom is studying programming.")
  }

  it("メソッドをvalでオーバーライドする") {
    // Scala ではフィールドとメソッドに同じ名前で定義できない。
    // 引数なしのメソッドを val でオーバーライドできるようにするため。

    abstract class Parent {
      def method:String = "Parent."
    }

    class Child extends Parent {
      override val method:String = "Child."
    }

    assert(new Child().method == "Child.")
  }

  it("sealedによる継承制限とパターンマッチ") {
    // クラス定義に sealed を付けられたクラスは、別ファイル内で定義されたクラスから継承できない。
    // (ただし、シールドクラスを継承したクラスは別ファイルから継承可能)

    sealed abstract class TeamMember
    case class Programmer() extends TeamMember
    case class Designer() extends TeamMember
    case class Manager() extends TeamMember

    val member:TeamMember = new Programmer

    // パターンマッチを記述する際に役に立つ
    member match {
      case p:Programmer => println("Programmer.")
      case d:Designer => println("Designer.")
      // Managerに対するcase式または、デフォルト式が足りてないので警告が出る。
    }

    // 警告が煩わしい場合は、 @unchecked で無効にできる
    // FIXME: 空白がない (member:@unchecked) だとエラーなのはなんでだろう
    (member: @unchecked) match {
      case p:Programmer => println("Programmer.")
      case d:Designer => println("Designer.")
    }
  }
}
