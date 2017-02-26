name := "samples"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"
libraryDependencies += "com.github.scopt" %% "scopt" % "3.5.0"
libraryDependencies += "org.pegdown" % "pegdown" % "1.6.0"
libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value
libraryDependencies += "org.jfree" % "jfreechart" % "1.0.19"
libraryDependencies += "com.github.pathikrit" %% "better-files" % "2.17.1"

testOptions in Test ++= Seq(
  Tests.Argument(TestFrameworks.ScalaTest, "-C", "org.nomadblacky.scala.reporter.TableOfContentsReporter")
)
