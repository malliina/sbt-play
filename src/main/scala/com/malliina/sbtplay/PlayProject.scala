package com.malliina.sbtplay

import com.malliina.sbt.unix.LinuxKeys.ciBuild
import play.sbt.PlayImport
import sbt.Keys._
import sbt._
import sbtbuildinfo.BuildInfoKey
import sbtbuildinfo.BuildInfoKeys.buildInfoKeys
import sbtrelease.ReleasePlugin.autoImport._
import sbtrelease.ReleaseStateTransformations._

import scala.language.postfixOps
import scala.sys.process.Process

object PlayProject {
  def library(name: String, base: File = file(".")) =
    Project(name, base)
      .settings(libSettings: _*)

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
    libraryDependencies ++= defaultDeps
  )

  def defaultDeps = Seq(
    "com.lihaoyi" %% "scalatags" % "0.8.4",
    "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
    PlayImport.specs2 % Test
  )
}
