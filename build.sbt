val versions = new {
  val scalikejdbc = "3.3.4"
}

lazy val TableOfContents = config("tableOfContents").extend(Test)

lazy val commonSettings = Seq(
  scalaVersion := "2.12.8",
  scalacOptions ++= Seq(
    "-Ywarn-unused:imports"
  )
)

lazy val reporter = (project in file("reporter"))
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.0.5"
    )
  )

lazy val root = (project in file("."))
  .dependsOn(reporter)
  .settings(commonSettings)
  .configs(TableOfContents)
  .settings(inConfig(TableOfContents)(Defaults.testTasks): _*)
  .settings(
    name := "scala_samples",
    version := "1.0",
    TableOfContents / testOptions ++= Seq(
      Tests.Argument(
        TestFrameworks.ScalaTest,
        "-C",
        "org.nomadblacky.scala.reporter.TableOfContentsReporter"
      )
    ),
    libraryDependencies ++= Seq(
      "org.scalactic"        %% "scalactic"              % "3.0.7",
      "org.scalatest"        %% "scalatest"              % "3.0.7"              % "test",
      "org.scalacheck"       %% "scalacheck"             % "1.14.0"             % "test",
      "com.github.scopt"     %% "scopt"                  % "3.7.1",
      "org.pegdown"           % "pegdown"                % "1.6.0",
      "org.scala-lang"        % "scala-reflect"          % scalaVersion.value,
      "org.jfree"             % "jfreechart"             % "1.5.0",
      "com.github.pathikrit" %% "better-files"           % "3.9.1",
      "org.scalaz"           %% "scalaz-core"            % "7.3.4",
      "com.typesafe.akka"    %% "akka-http-core"         % "10.1.14",
      "com.typesafe.akka"    %% "akka-stream"            % "2.5.22",
      "com.chuusai"          %% "shapeless"              % "2.3.3",
      "org.typelevel"        %% "cats-core"              % "1.6.0",
      "com.lihaoyi"          %% "ammonite-ops"           % "1.6.6",
      "com.typesafe.play"    %% "play-ahc-ws-standalone" % "2.0.3",
      "org.scalikejdbc"      %% "scalikejdbc"            % versions.scalikejdbc,
      "org.scalikejdbc"      %% "scalikejdbc-config"     % versions.scalikejdbc,
      "org.scalikejdbc"      %% "scalikejdbc-test"       % versions.scalikejdbc % "test",
      "org.skinny-framework" %% "skinny-orm"             % "3.0.2",
      "com.h2database"        % "h2"                     % "1.4.199",
      "ch.qos.logback"        % "logback-classic"        % "1.2.3"
    )
  )

// FIXME: Cannot apply this...
// https://github.com/scalatest/scalatest/commit/10b38d73e804546aaf3690e6496b65d984f2459f#diff-a2caa30f41e1c2f5fac0195d465701cf
// ThisBuild / envVars += "SCALACTIC_FILL_FILE_PATHNAMES" -> "yes"
