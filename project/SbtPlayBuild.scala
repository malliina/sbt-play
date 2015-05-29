import com.mle.sbtutils.{SbtProjects, SbtUtils}
import sbt.Keys._
import sbt._
import xerial.sbt.Sonatype
import bintray.Plugin.bintraySettings
import bintray.Keys.{bintrayOrganization, repository, bintray}

/**
 * A scala build file template.
 */
object SbtPlayBuild extends Build {
  lazy val template = SbtProjects.testableProject("sbt-play").settings(projectSettings: _*)

  lazy val projectSettings = bintraySettings ++ Seq(
    organization := "com.github.malliina",
    version := "0.3.0",
    sbtPlugin := true,
    scalaVersion := "2.10.4",
    //    crossScalaVersions := Seq("2.11.0", "2.10.4"),
    exportJars := false,
    fork in Test := true,
    resolvers += Resolver.url("malliina bintray sbt", url("https://dl.bintray.com/malliina/sbt-plugins/"))(Resolver.ivyStylePatterns),
    bintrayOrganization in bintray := None,
    repository in bintray := "sbt-plugins",
    publishMavenStyle := false,
    licenses += ("MIT", url("http://opensource.org/licenses/MIT"))
  ) ++ plugins

  def plugins = Seq(
    "com.github.malliina" %% "sbt-utils" % "0.2.0",
    "com.typesafe.play" % "sbt-plugin" % "2.4.0"
  ) map addSbtPlugin
}
