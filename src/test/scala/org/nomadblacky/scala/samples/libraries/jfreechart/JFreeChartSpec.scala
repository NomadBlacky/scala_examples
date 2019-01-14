package org.nomadblacky.scala.samples.libraries.jfreechart

import better.files.File
import org.jfree.chart.{ChartFactory, ChartUtilities, JFreeChart}
import org.jfree.chart.plot.{PiePlot, PlotOrientation}
import org.jfree.data.general.DefaultPieDataset
import org.jfree.data.statistics.HistogramDataset
import org.scalatest.{BeforeAndAfter, FunSpec}

import scala.util.Random

/**
  * Created by blacky on 17/02/26.
  */
class JFreeChartSpec extends FunSpec with BeforeAndAfter {

  override def suiteName: String = "JFreeChart - グラフを描画するJavaライブラリ"

  val ResultDir: String = "target/jfreechart/"

  before {
    File(ResultDir).createDirectories()
  }

  it("Part1 ... 円グラフを作成する") {
    val dataSet = new DefaultPieDataset()
    Map("hoge" -> 10.0, "foo" -> 30.0, "bar" -> 60.0).foreach { case (s, d) => dataSet.setValue(s, d) }

    val chart: JFreeChart = new JFreeChart("sample", new PiePlot(dataSet))
    val file              = File(ResultDir + "part1.png")

    ChartUtilities.saveChartAsJPEG(file.toJava, chart, 300, 300)
  }

  it("Part2 ... ヒストグラム") {
    val dataSet = new HistogramDataset

    dataSet.addSeries(
      "data",
      (1 to 1000)
        .map(_ => {
          val i = 50 - (if (Random.nextBoolean()) -1 else 1) * Random.nextInt(30)
          i.toDouble
        })
        .toArray,
      10
    )
    val chart = ChartFactory.createHistogram(
      "sample",
      "x",
      "y",
      dataSet,
      PlotOrientation.VERTICAL,
      false,
      false,
      false
    )
    val file = File(ResultDir + "part2.png")

    ChartUtilities.saveChartAsJPEG(file.toJava, chart, 300, 300)
  }
}
