package org.nomadblacky.scala.samples.functional.programming.in.scala.chapter06

trait RNG {
  def nextInt: (Int, RNG)
}
