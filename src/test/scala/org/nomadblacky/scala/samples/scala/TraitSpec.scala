package org.nomadblacky.scala.samples.scala

import org.scalatest.FunSpec

/** Created by blacky on 16/11/05.
  *
  * Javaでいう interface の機能。
  * トレイト自体に実装を持つことができる。
  *
  * trait ... (人・ものの) 特性、特色、特徴
  */
class TraitSpec extends FunSpec {

  override def suiteName: String = "トレイトの使い方"

  it("トレイトの基本") {
    trait Programmer {
      def coding: String = "I'm writing a program code."
    }

    // クラスが明示的に継承をしない場合、 extends キーワードでミックスインする
    class Person(val name: String) extends Programmer

    assert(new Person("Tom").coding == "I'm writing a program code.")
  }

  it("複数のトレイトをミックスインする") {
    trait Programmer {
      def coding: String = "I'm writing a program code."
    }
    trait Designer {
      def design: String = "I'm making a design."
    }

    // with キーワードでミックスインする
    class Person(val name: String) extends Programmer with Designer
    val p = new Person("Alex")

    assert(p.coding == "I'm writing a program code.")
    assert(p.design == "I'm making a design.")
  }

  it("インスタンス化のタイミングでミックスインする") {
    trait Programmer {
      def coding: String = "I'm writing a program code."
    }
    class Person(val name: String)

    val p = new Person("Tom") with Programmer

    assert(p.coding == "I'm writing a program code.")
  }

  it("同じシグネチャのメソッドを複数ミックスインした場合") {
    trait Programmer {
      def write: String = "I'm writing a program code."
    }
    trait Writer {
      def write: String = "I'm writing a blog."
    }

    class Person extends Programmer with Writer {
      // メソッドを必ずオーバーライドしなくてはならない。(エラーになる。)
      override def write: String = "I'm writing a document."
    }
  }

  it("superで呼び出すトレイトのメソッドを指定する") {
    trait Programmer {
      def write: String = "I'm writing a program code."
    }
    trait Writer {
      def write: String = "I'm writing a blog."
    }
    class Person

    val p1 = new Person with Programmer with Writer {
      override def write: String = super[Programmer].write
    }
    val p2 = new Person with Programmer with Writer {
      override def write: String = super[Writer].write
    }

    assert(p1.write == "I'm writing a program code.")
    assert(p2.write == "I'm writing a blog.")
  }

  it("トレイトを単体で利用する") {
    trait T1 {
      def method1: String = "You are calling method1."
    }
    trait T2 {
      def method2: String = "You are calling method2."
    }

    // {} はトレイトをミックスインした無名クラスを作成することを意味している。
    val instance1 = new T1 {}
    assert(instance1.method1 == "You are calling method1.")

    // 複数のトレイトをミックスインした無名クラスを作成できる。
    val instance2 = new T1 with T2 // この場合の {} は不要

    assert(instance2.method1 == "You are calling method1.")
    assert(instance2.method2 == "You are calling method2.")
  }

  it("abstract override で既存のメソッドに新しい処理を追加する") {
    abstract class Engineer {
      def work(time: Int): String
    }
    class Person extends Engineer {
      def work(time: Int): String = {
        "Finish work in %d minutes.".format(time)
      }
    }
    trait Programmer extends Engineer {
      abstract override def work(time: Int): String = {
        // trait内でsuperを使う場合、
        // 具象実装をあとでミックスインする必要がある。
        // この場合、メソッドに abstract override が必要。
        super.work(time - 15)
      }
    }

    val p = new Person with Programmer

    // Programmer -> Person の順で実行される。
    assert(p.work(60) == "Finish work in 45 minutes.")
  }

  it("トレイトの指定順序") {
    abstract class Engineer {
      def work(time: Int): String
    }
    class Person extends Engineer {
      def work(time: Int): String = {
        "Finish work in %d minutes.".format(time)
      }
    }
    trait Programmer extends Engineer {
      abstract override def work(time: Int): String = {
        super.work(time - 15)
      }
    }
    trait Agiler extends Engineer {
      abstract override def work(time: Int): String = {
        super.work(time / 2)
      }
    }

    val p1 = new Person with Programmer with Agiler
    val p2 = new Person with Agiler with Programmer

    // Agiler -> Programmer
    assert(p1.work(60) == "Finish work in 15 minutes.")
    // Programmer -> Agiler
    assert(p2.work(60) == "Finish work in 22 minutes.")

    // 重なったトレイトはおおよそ右から実行される。
  }

  it("自分型アノテーション") {
    // class/object には自分型と言われる型が存在する。
    // (this の型のこと)
    // 自分型は全てのスーパークラスに適合しなければならない。
    // 適合する ... A <: B という関係(代入関係)が成り立つこと。

    trait MyService {
      def findAll(): String
    }
    trait MyServiceImpl extends MyService {
      override def findAll(): String = "MyServiceImpl#findAll"
    }
    class MyController {
      // self: Type => と書くと、自分型はTypeを継承したと認識される。
      self: MyService =>
      def execute = {
        findAll()
      }
    }

    // 自分型の条件を満たせないインスタンスを作成しようとするとコンパイルエラーになる。
//    val c = new MyController

    val c = new MyController with MyServiceImpl
    assert(c.execute == "MyServiceImpl#findAll")
  }
}
