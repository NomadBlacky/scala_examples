package org.nomadblacky.scala.samples.functional.programming.in.scala.chapter10

/**
  * Created by blacky on 17/03/08.
  */
trait Monoid[A] {
  // op(op(x,y),z) == op(x, op(y,z)) を満たす
  def op(a1: A, a2: A): A

  // op(zero, x) == x と op(x, zero) == x を満たす
  def zero: A
}
