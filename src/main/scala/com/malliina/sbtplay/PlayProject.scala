package com.malliina.sbtplay

import com.typesafe.sbt.web.Import.Assets
import play.routes.compiler.InjectedRoutesGenerator
import play.sbt.routes.RoutesKeys
import play.sbt.{PlayImport, PlayScala}
import sbt.Keys._
import sbt._

import scala.language.postfixOps

object PlayProject {
  def noDeps(name: String, base: File = file(".")) = Project(name, base)
    .enablePlugins(PlayScala)
    .settings(routesSettings)

  def default(name: String, base: File = file(".")) = Project(name, base)
    .enablePlugins(PlayScala)
    .settings(defaultSettings: _*)

  def library(name: String, base: File = file(".")) = Project(name, base)
    .settings(libSettings: _*)

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
