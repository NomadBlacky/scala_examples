name := "samples"

version := "1.0"

scalaVersion := "2.12.1"

val scalazVersion = "7.2.10"

libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % "3.0.0",
  "org.scalatest" %% "scalatest" % "3.0.0" % "test",
  "com.github.scopt" %% "scopt" % "3.5.0",
  "org.pegdown" % "pegdown" % "1.6.0",
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "org.jfree" % "jfreechart" % "1.0.19",
  "com.github.pathikrit" %% "better-files" % "2.17.1",
  "org.scalaz" %% "scalaz-core" % scalazVersion
)

testOptions in Test ++= Seq(
  Tests.Argument(TestFrameworks.ScalaTest, "-C", "org.nomadblacky.scala.reporter.TableOfContentsReporter")
)
