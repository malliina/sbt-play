package com.malliina.sbtplay

import com.malliina.sbtutils.SbtProjects
import com.typesafe.sbt.web.Import.Assets
import play.routes.compiler.InjectedRoutesGenerator
import play.sbt.routes.RoutesKeys
import play.sbt.{PlayImport, PlayScala}
import sbt.Keys._
import sbt._

import scala.language.postfixOps

object PlayProject {
  def apply(name: String, base: File = file(".")) = SbtProjects.baseProject(name, base)
    .enablePlugins(PlayScala)
    .settings(defaultSettings: _*)

  def defaultSettings = Seq(
    resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases",
    libraryDependencies ++= Seq(
      PlayImport.specs2 % Test
    ),
    RoutesKeys.routesGenerator := InjectedRoutesGenerator
  )

  def assetSettings = Seq(
    mappings in(Compile, packageBin) ++= {
      (unmanagedResourceDirectories in Assets).value flatMap
        (assetDir => (assetDir ***) pair relativeTo(baseDirectory.value))
    }
  )
}
