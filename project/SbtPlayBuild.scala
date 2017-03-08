import com.malliina.sbtutils.SbtProjects
import sbt.Keys._
import sbt._

object SbtPlayBuild {
  lazy val sbtPlay = SbtProjects.sbtPlugin("sbt-play")
    .settings(projectSettings: _*)

  lazy val projectSettings = Seq(
    organization := "com.malliina",
    scalaVersion := "2.10.6",
    resolvers ++= Seq(
      Resolver.url("malliina bintray sbt", url("https://dl.bintray.com/malliina/sbt-plugins/"))(Resolver.ivyStylePatterns),
      Resolver.bintrayRepo("malliina", "maven")
    )
  ) ++ plugins

  def plugins = Seq(
    "com.typesafe.play" % "sbt-plugin" % "2.5.12",
    "com.malliina" %% "sbt-packager" % "2.2.0",
    "com.eed3si9n" % "sbt-buildinfo" % "0.4.0"
  ) map addSbtPlugin
}
