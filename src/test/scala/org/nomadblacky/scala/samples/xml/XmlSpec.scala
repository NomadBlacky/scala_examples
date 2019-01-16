package org.nomadblacky.scala.samples.xml

import org.scalatest.FunSpec

import scala.xml.{Elem, XML}

class XmlSpec extends FunSpec {

  override def suiteName: String = "XMLを扱う"

  it("xmlリテラル") {
    val xml = <h1>title</h1>
    assert(xml.getClass == classOf[Elem])
    assert(xml.toString() == "<h1>title</h1>")
  }

  it("値を埋め込む") {
    val a   = 100
    val xml = <h1>{a}</h1>
    assert(xml.toString() == "<h1>100</h1>")
  }

  it("ファイルから読み込む") {
    val xml = XML.load(getClass.getResource("xml_spec_01.html"))
    assert(xml.getClass == classOf[Elem])
    println(xml)
  }

  it("要素を取得する1") {
    val xml  = XML.load(getClass.getResource("xml_spec_01.html"))
    val body = xml \ "body"
    println(body)
  }

  it("要素を取得する2") {
    val xml   = XML.load(getClass.getResource("xml_spec_01.html"))
    val pTags = xml \ "body" \ "div" \ "p"
    assert(pTags.size == 5)
    println(pTags)
  }

  it("要素を取得する3") {
    val xml   = XML.load(getClass.getResource("xml_spec_01.html"))
    val aTags = xml \\ "a"
    assert(aTags.size == 2)
    println(aTags)
  }

  it("属性から要素を取得する") {
    val xml = XML.load(getClass.getResource("xml_spec_01.html"))
    // これだとうまくいかない
    // val p = xml \\ "p" \ "@class"
    val p = xml \\ "p" \\ "@class"
    assert(p.size == 1)
    println(p)
  }
}
