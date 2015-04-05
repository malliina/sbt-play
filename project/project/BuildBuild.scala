import sbt.Keys._
import sbt._

object BuildBuild extends Build {

  override lazy val settings = super.settings ++ Seq(
    scalaVersion := "2.10.4"
  ) ++ sbtPlugins

  def sbtPlugins = Seq(
    "com.github.malliina" %% "sbt-utils" % "0.1.0"
  ) map addSbtPlugin

  override lazy val projects = Seq(root)
  lazy val root = Project("plugins", file("."))
}