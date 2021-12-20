inThisBuild(
  List(
    semanticdbEnabled := true,
    semanticdbVersion := scalafixSemanticdb.revision
  )
)

val Scala2_12 = "2.12.14"
val Scala2_13 = "2.13.6"
val Scala3    = "3.0.1"

val versions = new {
  val scalikejdbc = "3.3.5"
  val silencer    = "1.7.7"
}

lazy val TableOfContents = config("tableOfContents").extend(Test)

lazy val legacyCommonSettings = Seq(
  scalacOptions ++= Seq(
    "-Yrangepos",
    "-Ywarn-unused:imports"
  )
)

def createProject(path: String, scalaVer: String): sbt.Project = {
  val isScala3 = scalaVer.startsWith("3")
  Project(path, file(path))
    .settings(
      scalaVersion := scalaVer,
      scalacOptions ++= mkScalacOptions(isScala3),
      libraryDependencies ++= mkLibraryDependencies(isScala3)
    )
}

def mkScalacOptions(isScala3: Boolean): Seq[String] = {
  Seq(
    "-deprecation",
    "-feature",
    "-unchecked",
    "-Xfatal-warnings"
  ) ++ {
    if (isScala3) {
      Seq()
    } else {
      Seq(
        "-Xlint",
        "-P:silencer:checkUnused"
      )
    }
  }
}

def mkLibraryDependencies(isScala3: Boolean): Seq[ModuleID] = {
  Seq(
    "org.scalatest" %% "scalatest-funspec"        % "3.2.9" % Test,
    "org.scalatest" %% "scalatest-shouldmatchers" % "3.2.9" % Test
  ) ++ {
    if (isScala3) {
      Seq()
    } else {
      Seq(
        compilerPlugin("com.github.ghik" % "silencer-plugin" % versions.silencer cross CrossVersion.full),
        "com.github.ghik" % "silencer-lib" % versions.silencer % Provided cross CrossVersion.full
      )
    }
  }
}

lazy val reporter = (project in file("reporter"))
  .settings(legacyCommonSettings)
  .settings(
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.0.5"
    )
  )

lazy val legacy = (project in file("legacy"))
  .dependsOn(reporter)
  .settings(legacyCommonSettings)
  .configs(TableOfContents)
  .settings(inConfig(TableOfContents)(Defaults.testTasks): _*)
  .settings(
    name         := "scala_samples",
    scalaVersion := Scala2_12,
    version      := "1.0",
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
      "org.scalaz"           %% "scalaz-core"            % "7.3.5",
      "com.typesafe.akka"    %% "akka-http-core"         % "10.2.7",
      "com.typesafe.akka"    %% "akka-stream"            % "2.6.18",
      "org.typelevel"        %% "cats-core"              % "2.7.0",
      "com.lihaoyi"          %% "ammonite-ops"           % "2.4.1",
      "com.typesafe.play"    %% "play-ahc-ws-standalone" % "2.1.3",
      "org.scalikejdbc"      %% "scalikejdbc"            % versions.scalikejdbc,
      "org.scalikejdbc"      %% "scalikejdbc-config"     % versions.scalikejdbc,
      "org.scalikejdbc"      %% "scalikejdbc-test"       % versions.scalikejdbc % "test",
      "org.skinny-framework" %% "skinny-orm"             % "3.1.0",
      "com.h2database"        % "h2"                     % "2.0.202",
      "ch.qos.logback"        % "logback-classic"        % "1.2.9"
    )
  )

lazy val akkaStream = createProject("akka-stream", Scala2_13)
  .settings(
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-stream"         % "2.6.18",
      "com.typesafe.akka" %% "akka-stream-testkit" % "2.6.18" % Test
    )
  )

lazy val basics = createProject("basics", Scala2_13)

lazy val collections = createProject("collections", Scala2_13)

lazy val scala3 = createProject("scala3", Scala3)
  .settings(
    libraryDependencies ++= Seq(
      "org.scalameta" %% "munit" % "0.7.27" % Test
    )
  )

lazy val shapeless = createProject("shapeless", Scala2_13)
  .settings(
    libraryDependencies ++= Seq(
      "com.chuusai" %% "shapeless" % "2.3.7"
    )
  )
