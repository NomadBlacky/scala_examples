val scalazVersion = "7.2.10"
val scalikejdbcVersion = "3.2.2"

lazy val TableOfContents = config("tableOfContents").extend(Test)

lazy val commonSettings = Seq(
  name := "scala_samples",
  version := "1.0",
  scalaVersion := "2.12.5",
  libraryDependencies ++= Seq(
    "org.scalactic" %% "scalactic" % "3.0.0",
    "org.scalatest" %% "scalatest" % "3.0.0" % "test",
    "org.scalacheck" %% "scalacheck" % "1.13.4" % "test",
    "com.github.scopt" %% "scopt" % "3.5.0",
    "org.pegdown" % "pegdown" % "1.6.0",
    "org.scala-lang" % "scala-reflect" % scalaVersion.value,
    "org.jfree" % "jfreechart" % "1.0.19",
    "com.github.pathikrit" %% "better-files" % "2.17.1",
    "org.scalaz" %% "scalaz-core" % scalazVersion,
    "com.typesafe.akka" %% "akka-http-core" % "10.0.7",
    "com.typesafe.akka" % "akka-stream_2.12" % "2.5.6",
    "com.chuusai" %% "shapeless" % "2.3.2",
    "org.typelevel" %% "cats-core" % "1.0.0-MF",
    "com.lihaoyi" %% "ammonite-ops" % "1.0.3",
    "com.typesafe.play" %% "play-ahc-ws-standalone" % "1.1.6",
    "org.scalikejdbc" %% "scalikejdbc" % scalikejdbcVersion,
    "org.scalikejdbc" %% "scalikejdbc-config" % scalikejdbcVersion,
    "org.scalikejdbc" %% "scalikejdbc-test" % scalikejdbcVersion % "test",
    "com.h2database" % "h2" % "1.4.197",
    "ch.qos.logback" % "logback-classic" % "1.2.3"
  )
)

lazy val root = (project in file("."))
  .settings(commonSettings)
  .configs(TableOfContents)
  .settings(inConfig(TableOfContents)(Defaults.testTasks): _*)
  .settings(
    testOptions in TableOfContents ++= Seq(
      Tests.Argument(TestFrameworks.ScalaTest, "-C", "org.nomadblacky.scala.reporter.TableOfContentsReporter")
    )
  )
