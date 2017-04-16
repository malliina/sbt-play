package com.malliina.sbtplay

import com.malliina.sbt.unix.LinuxKeys.ciBuild
import com.malliina.sbt.unix.LinuxPlugin
import com.typesafe.sbt.SbtNativePackager.Debian
import com.typesafe.sbt.packager.Keys.serverLoading
import com.typesafe.sbt.packager.archetypes.{JavaServerAppPackaging, ServerLoader}
import com.typesafe.sbt.web.Import.Assets
import play.routes.compiler.InjectedRoutesGenerator
import play.sbt.routes.RoutesKeys
import play.sbt.{PlayImport, PlayScala}
import sbt.Keys._
import sbt._
import sbtbuildinfo.BuildInfoKeys.buildInfoKeys
import sbtbuildinfo.{BuildInfoKey, BuildInfoPlugin}
import sbtrelease.ReleasePlugin.autoImport._
import sbtrelease.ReleaseStateTransformations._

import scala.language.postfixOps

object PlayProject {
  def default(name: String, base: File = file(".")) = Project(name, base)
    .enablePlugins(PlayScala)
    .settings(defaultSettings: _*)

  def server(name: String, base: File = file(".")) = Project(name, base)
    .enablePlugins(PlayScala, JavaServerAppPackaging, BuildInfoPlugin)
    .settings(serverSettings: _*)

  def noDeps(name: String, base: File = file(".")) = Project(name, base)
    .enablePlugins(PlayScala)
    .settings(routesSettings)

  def library(name: String, base: File = file(".")) = Project(name, base)
    .settings(libSettings: _*)

  def serverSettings = defaultSettings ++ LinuxPlugin.playSettings ++ Seq(
    serverLoading in Debian := ServerLoader.Systemd,
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
      "hash" -> Process("git rev-parse --short HEAD").lines.head
    )
  )

  def defaultSettings = routesSettings ++ libSettings

  def routesSettings = Seq(
    RoutesKeys.routesGenerator := InjectedRoutesGenerator
  )

  def libSettings = Seq(
    libraryDependencies ++= defaultDeps
  )

  def defaultDeps = Seq(
    "com.lihaoyi" %% "scalatags" % "0.6.3",
    "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
    PlayImport.specs2 % Test
  )

  def assetSettings = Seq(
    mappings in(Compile, packageBin) ++= {
      (unmanagedResourceDirectories in Assets).value flatMap
        (assetDir => (assetDir ***) pair relativeTo(baseDirectory.value))
    }
  )
}
