import bintray.Plugin.bintraySettings
import bintray.Keys.{bintray, bintrayOrganization, repository}
import sbt.Keys._
import sbt._

object SbtPlayBuild {
  lazy val template = Project("sbt-play", file("."))
    .settings(projectSettings: _*)

  lazy val projectSettings = bintraySettings ++ Seq(
    organization := "com.malliina",
    version := "0.9.1",
    sbtPlugin := true,
    scalaVersion := "2.10.6",
    exportJars := false,
    fork in Test := true,
    resolvers += Resolver.url("malliina bintray sbt", url("https://dl.bintray.com/malliina/sbt-plugins/"))(Resolver.ivyStylePatterns),
    bintrayOrganization in bintray := None,
    repository in bintray := "sbt-plugins",
    publishMavenStyle := false,
    licenses +=("MIT", url("http://opensource.org/licenses/MIT"))
  ) ++ plugins

  def plugins = Seq(
    "com.typesafe.play" % "sbt-plugin" % "2.5.10"
  ) map addSbtPlugin
}
