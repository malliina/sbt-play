import com.mle.sbtutils.{SbtProjects, SbtUtils}
import sbt.Keys._
import sbt._
import xerial.sbt.Sonatype

/**
 * A scala build file template.
 */
object SbtPlayBuild extends Build {
  lazy val template = SbtProjects.mavenPublishProject("sbt-play").settings(projectSettings: _*)

  lazy val projectSettings = Seq(
    SbtUtils.gitUserName := "malliina",
    SbtUtils.developerName := "Michael Skogberg",
    version := "0.1.1",
    sbtPlugin := true,
    scalaVersion := "2.10.4",
    //    crossScalaVersions := Seq("2.11.0", "2.10.4"),
    exportJars := false,
    fork in Test := true
  ) ++ plugins

  def plugins = Seq(
    "com.github.malliina" %% "sbt-utils" % "0.0.5",
    "com.typesafe.play" % "sbt-plugin" % "2.3.4"
  ) map addSbtPlugin
}