import bintray.Plugin.bintraySettings
import bintray.Keys.{bintray, bintrayOrganization, repository}
import com.malliina.sbtutils.SbtProjects
import sbt.Keys._
import sbt._

/** A scala build file template.
  */
object SbtPlayBuild extends Build {
  lazy val template = SbtProjects.testableProject("sbt-play").settings(projectSettings: _*)

  lazy val projectSettings = bintraySettings ++ Seq(
    organization := "com.malliina",
    version := "0.7.1",
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
    "com.malliina" %% "sbt-utils" % "0.3.0",
    "com.typesafe.play" % "sbt-plugin" % "2.5.1"
  ) map addSbtPlugin
}
