package org.nomadblacky.scala.samples.scala

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
      def coding: String = "I'm writing a program code."
    }

    // クラスが明示的に継承をしない場合、 extends キーワードでミックスインする
    class Person(val name:String) extends Programmer

    assert(new Person("Tom").coding == "I'm writing a program code.")
  }

  it("複数のトレイトをミックスインする") {
    trait Programmer {
      def coding:String = "I'm writing a program code."
    }
    trait Designer {
      def design:String = "I'm making a design."
    }

    // with キーワードでミックスインする
    class Person(val name:String) extends Programmer with Designer
    val p = new Person("Alex")

    assert(p.coding == "I'm writing a program code.")
    assert(p.design == "I'm making a design.")
  }

  it("インスタンス化のタイミングでミックスインする") {
    trait Programmer {
      def coding:String = "I'm writing a program code."
    }
    class Person(val name:String)

    val p = new Person("Tom") with Programmer

    assert(p.coding == "I'm writing a program code.")
  }

  it("同じシグネチャのメソッドを複数ミックスインした場合") {
    trait Programmer {
      def write:String = "I'm writing a program code."
    }
    trait Writer {
      def write:String = "I'm writing a blog."
    }

    class Person extends Programmer with Writer {
      // メソッドを必ずオーバーライドしなくてはならない。(エラーになる。)
      override def write:String = "I'm writing a document."
    }
  }

  it("superで呼び出すトレイトのメソッドを指定する") {
    trait Programmer {
      def write:String = "I'm writing a program code."
    }
    trait Writer {
      def write:String = "I'm writing a blog."
    }
    class Person

    val p1 = new Person with Programmer with Writer {
      override def write:String = super[Programmer].write
    }
    val p2 = new Person with Programmer with Writer {
      override def write:String = super[Writer].write
    }

    assert(p1.write == "I'm writing a program code.")
    assert(p2.write == "I'm writing a blog.")
  }

  it("トレイトを単体で利用する") {
    trait T1 {
      def method1:String = "You are calling method1."
    }
    trait T2 {
      def method2:String = "You are calling method2."
    }

    // {} はトレイトをミックスインした無名クラスを作成することを意味している。
    val instance1 = new T1 {}
    assert(instance1.method1 == "You are calling method1.")

    // 複数のトレイトをミックスインした無名クラスを作成できる。
    val instance2 = new T1 with T2 // この場合の {} は不要

    assert(instance2.method1 == "You are calling method1.")
    assert(instance2.method2 == "You are calling method2.")
  }
}
