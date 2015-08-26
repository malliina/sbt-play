import bintray.Plugin.bintraySettings
import bintray.Keys.{bintray, bintrayOrganization, repository}
import com.mle.sbtutils.SbtProjects
import sbt.Keys._
import sbt._

/**
 * A scala build file template.
 */
object SbtPlayBuild extends Build {
  lazy val template = SbtProjects.testableProject("sbt-play").settings(projectSettings: _*)

  lazy val projectSettings = bintraySettings ++ Seq(
    organization := "com.github.malliina",
    version := "0.4.2",
    sbtPlugin := true,
    scalaVersion := "2.10.4",
    exportJars := false,
    fork in Test := true,
    resolvers += Resolver.url("malliina bintray sbt", url("https://dl.bintray.com/malliina/sbt-plugins/"))(Resolver.ivyStylePatterns),
    bintrayOrganization in bintray := None,
    repository in bintray := "sbt-plugins",
    publishMavenStyle := false,
    licenses +=("MIT", url("http://opensource.org/licenses/MIT"))
  ) ++ plugins

  def plugins = Seq(
    "com.github.malliina" %% "sbt-utils" % "0.2.1",
    "com.typesafe.play" % "sbt-plugin" % "2.4.2"
  ) map addSbtPlugin
}
