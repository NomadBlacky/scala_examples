package org.nomadblacky.scala.samples.functional.programming.in.scala.chapter11

/**
  * Created by blacky on 17/04/25.
  *
  * Functorは全体を写せる(map over)ものの型クラス。
  * List, Optionなどの型コンストラクタなど。
  * Functor[F]インスタンスは、Fが実際にファンクタであることの裏付けとなる。
  */
trait Functor[F[_]] {
  // map を実装する
  def map[A,B](fa: F[A])(f: A => B): F[B]

  // mapのみで定義できる演算の一例
  def distribute[A,B](fab: F[(A, B)]): (F[A], F[B]) = {
    (map(fab)(_._1), map(fab)(_._2))
  }

  def codistribute[A,B](e: Either[F[A], F[B]]): F[Either[A,B]] = {
    e match {
      case Left(fa) => map(fa)(Left(_))
      case Right(fb) => map(fb)(Right(_))
    }
  }
}