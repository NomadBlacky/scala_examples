package dev.nomadblacky.scala_examples.scala3

class Scala3Test extends munit.FunSuite {
  test("新しい制御構文") {
    // https://docs.scala-lang.org/scala3/reference/other-new-features/control-syntax.html

    // if - then で括弧を省略
    val x = 1
    if x < 0 then
      "negative"
    else if x == 0 then
      "zero"
    else
      "positive"

    if x < 0 then -x else x

    // while - do で括弧を省略
    var i = 0
    while i < 3 do i += 1

    // for - yield で括弧を省略
    for x <- (1 to 3) if x > 1 yield x * x

    // for - do で括弧を省略
    for
      x <- (1 to 3)
      y <- (1 to 3)
    do
      println(x + y)

    // try-catch - 例外がひとつの場合ワンライナーで書ける
    try sys.error("error!") catch case ex: Exception => println(ex)
  }
}
