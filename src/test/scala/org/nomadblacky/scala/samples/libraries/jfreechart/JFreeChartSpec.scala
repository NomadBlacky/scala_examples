package org.nomadblacky.scala.samples.libraries.jfreechart

import better.files.File
import org.jfree.chart.{ChartUtilities, JFreeChart}
import org.jfree.chart.plot.PiePlot
import org.jfree.data.general.DefaultPieDataset
import org.scalatest.{BeforeAndAfter, FunSpec}


/**
  * Created by blacky on 17/02/26.
  */
class JFreeChartSpec extends FunSpec with BeforeAndAfter {

  val ResultDir:String = "target/jfreechart/"

  before {
    File(ResultDir).createDirectories()
  }

  it("Part1 ... 円グラフを作成する") {
    val dataSet = new DefaultPieDataset()
    Map("hoge" -> 10.0, "foo" -> 30.0, "bar" -> 60.0).
      foreach { case (s, d) => dataSet.setValue(s, d) }

    val chart: JFreeChart = new JFreeChart("sample", new PiePlot(dataSet))
    val file = File(ResultDir + "part1.png")

    ChartUtilities.saveChartAsJPEG(file.toJava, chart, 300, 300)
  }
}
