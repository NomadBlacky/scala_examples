package org.nomadblacky.scala.samples.parsercombinator

import org.scalatest.FunSpec

import scala.util.parsing.combinator.RegexParsers

/**
  * 参考資料
  *  * Scalaスケーラブルプログラミング 第33章 パーサー・コンビネーター
  *  * 面倒くさいパーサの実装もDSLで書くだけ！そう、Scalaならね - Qiita
  *     http://qiita.com/suin/items/35bc4afe618cb77f80f6
  *
  * Created by blacky on 17/04/20.
  */
class ParserCombinatorSpec extends FunSpec {

  it("電話番号のパース") {
    // 正規表現パーサを継承する
    object PhoneNumberParser extends RegexParsers {
      // 正規表現(~ 逐次合成)
      def code = """\d{3}""".r ~ "-" ~ """\d{4}""".r ~ "-" ~ """\d{4}""".r
      // applyを定義しておくとクライアントコードが軽くなる
      def apply(source: String) = parseAll(code, source)
    }
    assert(PhoneNumberParser("012-7890-3456").get.toString() == "((((012~-)~7890)~-)~3456)")
  }

  it("パースのエラーハンドリング") {
    object PhoneNumberParser extends RegexParsers {
      def code = """\d{3}""".r ~ "-" ~ """\d{4}""".r ~ "-" ~ """\d{4}""".r
      // パース結果はParseResultで返る
      def apply(source: String) = parseAll(code, source) match {
        case Success(parsed, _) => Right(parsed)
        case NoSuccess(errorMessage, _) => Left(errorMessage)
      }
    }
    PhoneNumberParser("012-7890-3456") match {
      case Right(parsed) =>
        assert(parsed.toString() == "((((012~-)~7890)~-)~3456)")
      case _ => fail()
    }
    PhoneNumberParser("01278903456") match {
      case Left(errorMsg) =>
        assert(errorMsg == "`-' expected but `7' found")
      case _ => fail()
    }
  }

  it("詳細なエラー内容を取得する") {
    object PhoneNumberParser extends RegexParsers {
      def code = """\d{3}""".r ~ "-" ~ """\d{4}""".r ~ "-" ~ """\d{4}""".r
      def apply(source: String) = parseAll(code, source) match {
        case Success(parsed, _) => Right(parsed)
        // next パースされていない地点を表す
        case NoSuccess(errorMessage, next) => Left(s"$errorMessage on line ${next.pos.line} on column ${next.pos.column}")
      }
    }
    PhoneNumberParser("01278903456") match {
      case Left(errorMsg) =>
        assert(errorMsg == "`-' expected but `7' found on line 1 on column 4")
      case _ => fail()
    }
  }

  it("パース内容を変換する") {
    // 電話番号を表す case class
    case class PhoneNumber(area: String, city: String, subscriber: String)

    object PhoneNumberParser extends RegexParsers {
      // P ^^ f はパース結果Pを関数fで変換する
      def code = """\d{3}""".r ~ "-" ~ """\d{4}""".r ~ "-" ~ """\d{4}""".r ^^
        { case (area~"-"~city~"-"~subscriber) => PhoneNumber(area, city, subscriber) }
      def apply(source: String) = parseAll(code, source) match {
        case Success(parsed, _) => Right(parsed)
        case NoSuccess(errorMessage, next) => Left(s"$errorMessage on line ${next.pos.line} on column ${next.pos.column}")
      }
    }
    PhoneNumberParser("012-7890-3456") match {
      case Right(pn) =>
        assert(pn == PhoneNumber("012", "7890", "3456"))
      case _ => fail()
    }
  }
}
