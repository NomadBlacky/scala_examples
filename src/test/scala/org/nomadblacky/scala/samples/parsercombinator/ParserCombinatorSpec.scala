package org.nomadblacky.scala.samples.parsercombinator

import org.scalatest.FunSpec

import scala.util.parsing.combinator.RegexParsers

/**
  * Created by blacky on 17/04/20.
  */
class ParserCombinatorSpec extends FunSpec {

  case class PhoneNumber(area: String, city: String, subscriber: String)

  it("電話番号のパース") {
    object PhoneNumberParser extends RegexParsers {
      def code = """\d{3}""".r ~ "-" ~ """\d{4}""".r ~ "-" ~ """\d{4}""".r
      def apply(source: String) = parseAll(code, source)
    }
    println(PhoneNumberParser("012-7890-3456").get)
  }
}
