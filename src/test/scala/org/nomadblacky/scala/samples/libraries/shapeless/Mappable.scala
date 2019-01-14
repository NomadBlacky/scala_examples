package org.nomadblacky.scala.samples.libraries.shapeless

// https://gist.github.com/calippo/892ce793c9696b330e55772099056b7a
object Mappable {

  implicit class ToMapOps[A](val a: A) extends AnyVal {

    import shapeless._
    import ops.record._

    def toMap[L <: HList](implicit gen: LabelledGeneric.Aux[A, L], tmr: ToMap[L]): Map[String, Any] = {
      val m: Map[tmr.Key, tmr.Value] = tmr(gen.to(a))
      m.map {
        case (k: Symbol, v) => k.name -> v
        case x              => throw new IllegalStateException(x.toString())
      }
    }
  }
}
