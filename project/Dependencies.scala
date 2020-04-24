import sbt._


object Dependencies {
  // Library Versions
  val catsVersion       = "2.1.0"
  val catsEffectVersion = "2.0.0"
  val circeVersion      = "0.12.3"

  val http4sVersion     = "0.21.0"

  val logbackVersion    = "1.2.3"
  val janinoVersion     = "3.1.0"

  val miniTestVersion   = "2.7.0"
  val scalaCheckVersion = "1.14.1"


  // Libraries
  val catsEffect = "org.typelevel" %% "cats-effect" % catsEffectVersion
  val catsCore   = "org.typelevel" %% "cats-core"   % catsVersion

  val miniTest     = "io.monix" %% "minitest"      % miniTestVersion % "test"
  val miniTestLaws = "io.monix" %% "minitest-laws" % miniTestVersion % "test"

  val http4sBlazeServer = "org.http4s" %% "http4s-blaze-server" % http4sVersion
  val http4sBlazeClient = "org.http4s" %% "http4s-blaze-client" % http4sVersion
  val http4sCirce       = "org.http4s" %% "http4s-circe"        % http4sVersion
  val http4sDsl         = "org.http4s" %% "http4s-dsl"          % http4sVersion

  val logback = "ch.qos.logback"      % "logback-classic" % logbackVersion
  val janino  = "org.codehaus.janino" % "janino"          % janinoVersion

  val circe = "io.circe" %% "circe-generic" % circeVersion
}
