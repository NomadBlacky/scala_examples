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

object Monoid {
  def concatenate[A](as: List[A], m: Monoid[A]): A = as.foldLeft(m.zero)(m.op)

  def foldMap[A, B](as: List[A], m: Monoid[B])(f: A => B): B = as.foldLeft(m.zero)((b, a) => m.op(b, f(a)))
//  def foldMap[A, B](as: List[A], m: Monoid[B])(f: A => B): B = as.foldLeft(m.zero)(m.op(_, f(_)))
//  [error] type mismatch;
//  [error]  found   : A => B
//  [error]  required: B
//  [error] def foldMap[A, B](as: List[A], m: Monoid[B])(f: A => B): B = as.foldLeft(m.zero)(m.op(_, f(_)))
//  [error]                                                                                           ^
}
