package org.nomadblacky.scala.samples.functional.programming.in.scala.chapter03

import org.scalatest.FunSpec

/**
  * Created by blacky on 17/02/13.
  *
  * Scala関数型デザイン&プログラミング―Scalazコントリビューターによる関数型徹底ガイド
  * https://www.amazon.co.jp/dp/B00WM54V5Q/ref=dp-kindle-redirect?_encoding=UTF8&btkr=1
  */
class Chapter03Spec extends FunSpec {

  override def suiteName: String = "第3章 関数型プログラミングのデータ構造"

  it("[EXERCISE 3.1] match式") {
    val v = MyList(1, 2, 3, 4, 5) match {
      case Cons(x, Cons(2, Cons(4, _))) => x
      case MyNil => 42
      case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
      case Cons(h, t) => h + MyList.sum(t)
      case _ => 101
    }

    assert(v == 3)
  }

  it("[EXERCISE 3.2] tailの実装") {
    assert(MyList.tail(MyList(1, 2, 3)) == MyList(2, 3))
    assert(MyList.tail(MyList(1, 2   )) == MyList(2))
    assert(MyList.tail(MyList(1      )) == MyNil)
    assert(MyList.tail(MyNil)           == MyNil)
  }

  it("[EXERCISE 3.3] setHeadの実装") {
    assert(MyList.setHead(MyList(1, 2, 3), 9) == MyList(9, 2, 3))
    assert(MyList.setHead(MyList(1, 2   ), 9) == MyList(9, 2))
    assert(MyList.setHead(MyList(1      ), 9) == MyList(9))
    assert(MyList.setHead(MyNil          , 9) == MyNil)
  }

  it("[EXERCISE 3.4] dropの実装") {
    assert(MyList.drop(MyList(1, 2, 3), 0) == MyList(1, 2, 3))
    assert(MyList.drop(MyList(1, 2, 3), 1) == MyList(2, 3)   )
    assert(MyList.drop(MyList(1, 2, 3), 2) == MyList(3)      )
    assert(MyList.drop(MyList(1, 2, 3), 3) == MyNil          )
    assert(MyList.drop(MyNil          , 3) == MyNil          )
  }

}
