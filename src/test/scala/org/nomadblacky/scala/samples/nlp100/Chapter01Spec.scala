package org.nomadblacky.scala.samples.nlp100

import org.scalatest.{FunSpec, Matchers}

import scala.annotation.tailrec
import scala.collection.immutable.ListMap
import scala.collection.mutable
import scala.util.matching.Regex

class Chapter01Spec extends FunSpec with Matchers {

  override def suiteName: String = "言語処理100本ノック 第1章: 準備運動"

  it("00. 文字列の逆順") {
    // 文字列"stressed"の文字を逆に（末尾から先頭に向かって）並べた文字列を得よ．
    "stressed".reverse shouldBe "desserts"
  }

  it("01. 「パタトクカシーー」") {
    // 「パタトクカシーー」という文字列の1,3,5,7文字目を取り出して連結した文字列を得よ．
    val text = "パタトクカシーー"

    // A01
    val result = text
      .zipWithIndex
      .collect {
        case (c, i) if i % 2 == 0 => c
      }
      .mkString
    result shouldBe "パトカー"

    // A02
    val result2 =
      for {
        (c,i) <- text.zipWithIndex if i % 2 == 0
      } yield c
    result2.mkString shouldBe "パトカー"

    // A03
    val result3 =
      text.foldLeft(("", true)) { case ((acc, b), c) =>
        val str = if (b) acc + c else acc
        (str, !b)
      }._1
    result3 shouldBe "パトカー"
  }

  it("02. 「パトカー」＋「タクシー」＝「パタトクカシーー」") {
    // 「パトカー」＋「タクシー」の文字を先頭から交互に連結して文字列「パタトクカシーー」を得よ．
    val str1 = "パトカー"
    val str2 = "タクシー"
    val expect = "パタトクカシーー"

    // A01
    val result = (str1 zip str2)
      .flatMap { case (c1,c2) => Seq(c1, c2) }
      .mkString
    result shouldBe expect

    // A02
    def fn(s1: String, s2: String): String = {
      @tailrec def tmp(c1: List[Char], c2: List[Char], s: String): String = {
        if (c1.isEmpty)
          s + c2.mkString
        else if (c2.isEmpty)
          s + c1.mkString
        else
          tmp(c1.tail, c2.tail, s + c1.head + c2.head)
      }
      tmp(s1.toList, s2.toList, "")
    }
    val result2 = fn(str1, str2)
    result2 shouldBe expect

    // A03
    val result3 = "パトカー".zipWithIndex.foldLeft("") { (acc, t) =>
      val y = "タクシー".charAt(t._2)
      acc + t._1 + y
    }.mkString
    result3 shouldBe expect

    // A04
    val result4 = (str1 zip str2).foldLeft("") {
      case (acc, (c1, c2)) => acc + c1 + c2
    }
    result4 shouldBe expect

    // A05
    val result5 = "パトカー".foldLeft[(String, String)]("", "タクシー") {
      case ((acc, rest), c) =>
        (acc + c + rest.head, rest.tail)
    }._1
    result5 shouldBe expect

    // A06
    def fn2(s1: String, s2: String): String = {
      @tailrec
      def tmp(c1: List[Char], c2: List[Char], s: String): String = {
        (c1.isEmpty, c2.isEmpty) match {
          case (true, _) => s + c2.mkString
          case (_, true) => s + c1.mkString
          case _ => tmp(c1.tail, c2.tail, s + c1.head + c2.head)
        }

      }
      tmp(s1.toList, s2.toList, "")
    }
    val result6 = fn2(str1, str2)
    result6 shouldBe expect
  }

  it("03. 円周率") {
    // "Now I need a drink, alcoholic of course, after the heavy lectures involving quantum mechanics."
    // という文を単語に分解し，各単語の（アルファベットの）文字数を先頭から出現順に並べたリストを作成せよ．
    val text = "Now I need a drink, alcoholic of course, after the heavy lectures involving quantum mechanics."
    val expect = Seq(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 8, 9, 7, 9)

    // A01
    val result = text
      .split("""\W+""")
      .map(_.length)
      .toList
    result shouldBe expect

    // A02
    val expected2 = "314159265358979"
    val string = "Now I need a drink, alcoholic of course, after the heavy lectures involving quantum mechanics."

    val answer = (for {
      xs <- string.split(" ") // String <- Array[String]
    } yield {
      xs.foldLeft(0) {
        case (acc, c) if c.isLetter => acc + 1 // (Int, Char)
        case (acc, _) => acc
      }
    }).mkString
    answer shouldBe expected2

    // A03
    var str = "Now I need a drink, alcoholic of course, after the heavy lectures involving quantum mechanics."
    val buffer = scala.collection.mutable.ListBuffer[Int]()
    str.replaceAll(""",|\.""", "").split(" ").foreach{
      word => buffer += word.length()
    }
    buffer shouldBe expect

    // A04
    val pi :List[Int] = str
      .replaceAll(",","")
      .replaceAll("\\.","")
      .split(" ")
      .map(_.length)
      .toList
    pi shouldBe expect
  }

  it("04. 元素記号") {
    /**
      * "Hi He Lied Because Boron Could Not Oxidize Fluorine. New Nations Might Also Sign Peace Security Clause. Arthur King Can."
      * という文を単語に分解し，1, 5, 6, 7, 8, 9, 15, 16, 19番目の単語は先頭の1文字
     * それ以外の単語は先頭に2文字を取り出し
      * 取り出した文字列から単語の位置（先頭から何番目の単語か）への連想配列（辞書型もしくはマップ型）を作成せよ．
      */
    val text = "Hi He Lied Because Boron Could Not Oxidize Fluorine. New Nations Might Also Sign Peace Security Clause. Arthur King Can."
    val expect = Map(
      1 -> "H", 2 -> "He", 3 -> "Li", 4 -> "Be", 5 -> "B",
      6 -> "C", 7 -> "N", 8 -> "O", 9 -> "F", 10 -> "Ne",
      11 -> "Na", 12 -> "Mi", 13 -> "Al", 14 -> "Si", 15 -> "P",
      16 -> "S", 17 -> "Cl", 18 -> "Ar", 19 -> "K", 20 -> "Ca"
    )

    // A01
    val pic = Set(1, 5, 6, 7, 8, 9, 15, 16, 19)
    val result = text
      .split("""\W+""")
      .zipWithIndex
      .map { case (s, i) =>
        val realIndex = i + 1
        val count = if (pic.contains(realIndex)) 1 else 2
        (realIndex, s.take(count))
      }
      .toMap
    result shouldBe expect

    // A02
    val ones = Seq(1, 5, 6, 7, 8, 9, 15, 16, 19)
    val (_, answer) = text.split("""\W+""")
      .foldLeft((1, Map.empty[Int, String])) {
        case ((current, map), word) =>
          (current + 1, map.updated(current, word.take(if (ones.contains(current)) 1 else 2)))
      }
    answer shouldBe expect

    // A03
    var n: Int = 0
    val poss = "^1|5|6|7|8|9|15|16|19$"
    val map = mutable.Map.empty[Int, String]

    text.split("""\s""").foreach { word =>
      n += 1
      val hoge = if (n.toString.matches(poss)) 1 else 2
      map.put(n, word.take(hoge))
    }
    map shouldBe expect
  }
}
