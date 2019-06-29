package com.malliina.sbtplay

import com.malliina.sbt.unix.LinuxKeys.ciBuild
import com.malliina.sbt.unix.LinuxPlugin
import com.typesafe.sbt.packager.archetypes.JavaServerAppPackaging
import com.typesafe.sbt.packager.archetypes.systemloader.SystemdPlugin
import play.sbt.{PlayImport, PlayScala}
import sbt.Keys._
import sbt._
import sbtbuildinfo.BuildInfoKeys.buildInfoKeys
import sbtbuildinfo.{BuildInfoKey, BuildInfoPlugin}
import sbtrelease.ReleasePlugin.autoImport._
import sbtrelease.ReleaseStateTransformations._

import scala.language.postfixOps
import scala.sys.process.Process

object PlayProject {
  def default(name: String, base: File = file(".")) =
    Project(name, base)
      .enablePlugins(PlayScala)
      .settings(libSettings)

  def server(name: String, base: File = file(".")) =
    Project(name, base)
      .enablePlugins(PlayScala, JavaServerAppPackaging, SystemdPlugin, BuildInfoPlugin)
      .settings(serverSettings: _*)

  def linux(name: String, base: File = file(".")) =
    Project(name, base)
      .enablePlugins(PlayScala, JavaServerAppPackaging, LinuxPlugin, SystemdPlugin, BuildInfoPlugin)
      .settings(linuxSettings: _*)

  def noDeps(name: String, base: File = file(".")) =
    Project(name, base)
      .enablePlugins(PlayScala)
      .settings(libSettings)

  def library(name: String, base: File = file(".")) =
    Project(name, base)
      .settings(libSettings: _*)

  def serverSettings = linuxSettings ++ LinuxPlugin.playSettings

  def linuxSettings = libSettings ++ Seq(
    // https://github.com/sbt/sbt-release
    releaseProcess := Seq[ReleaseStep](
      releaseStepTask(clean in Compile),
      checkSnapshotDependencies,
      runTest,
      releaseStepTask(ciBuild)
    ),
    buildInfoKeys := Seq[BuildInfoKey](
      name,
      version,
      "hash" -> Process("git rev-parse --short HEAD").lineStream.head
    )
  )

  def libSettings = Seq(
    resolvers += "Sonatype releases" at "https://oss.sonatype.org/content/repositories/releases/",
    libraryDependencies ++= defaultDeps
  )

  def defaultDeps = Seq(
    "com.lihaoyi" %% "scalatags" % "0.7.0",
    "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % Test,
    PlayImport.specs2 % Test
  )
}
