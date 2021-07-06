package org.nomadblacky.scala.samples.functional.programming.in.scala.chapter06

case class SimpleRNG(seed: Long) extends RNG {
  override def nextInt: (Int, RNG) = {
    val newSeed = (seed * 0x5deece66dL + 0xbL) & 0xffffffffffffL
    val nextRNG = SimpleRNG(newSeed)
    val n       = (newSeed >>> 16).toInt
    (n, nextRNG)
  }
}
