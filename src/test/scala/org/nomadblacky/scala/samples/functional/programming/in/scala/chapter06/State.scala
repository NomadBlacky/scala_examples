package org.nomadblacky.scala.samples.functional.programming.in.scala.chapter06

/**
  * クラスとして独立させ、関数を追加する。
  * この型を使い、ステートフルなプログラムの共通パターンを表現する関数を記述すればよい。
  *
  * [EXERCISE 6.10]
  */
case class State[S, +A](run: S => (A, S)) {
  def map[B](f: A => B): State[S, B] =
    flatMap(a => State.unit(f(a)))

  def map2[B, C](sb: State[S, B])(f: (A, B) => C): State[S, C] =
    flatMap(a => sb.map(b => f(a, b)))

  def flatMap[B](f: A => State[S, B]): State[S, B] =
    State { (s: S) =>
      val (a, s2) = run(s)
      f(a).run(s2)
    }
}

/**
  * [EXERCISE 6.10]
  */
object State {
  def unit[S, A](a: A): State[S, A] =
    State(s => (a, s))

  def sequence[S, A](sas: List[State[S, A]]): State[S, List[A]] =
    sas.foldRight(unit[S, List[A]](List.empty))((st, acc) => st.map2(acc)(_ :: _))
}
