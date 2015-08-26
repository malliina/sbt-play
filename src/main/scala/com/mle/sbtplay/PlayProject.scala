package com.mle.sbtplay

import com.mle.sbtutils.SbtProjects
import com.typesafe.sbt.web.Import.Assets
import play.routes.compiler.InjectedRoutesGenerator
import play.sbt.routes.RoutesKeys
import play.sbt.{PlayImport, PlayScala}
import sbt.Keys._
import sbt._

import scala.language.postfixOps

/**
 * @author Michael
 */
object PlayProject {
  def apply(name: String, base: File = file(".")) = SbtProjects.testableProject(name, base).enablePlugins(PlayScala).settings(
    resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases",
    libraryDependencies ++= Seq(
      PlayImport.specs2 % Test
    ),
    RoutesKeys.routesGenerator := InjectedRoutesGenerator,
    mappings in(Compile, packageBin) ++= {
      (unmanagedResourceDirectories in Assets).value flatMap
        (assetDir => (assetDir ***) pair relativeTo(baseDirectory.value))
    }
  )
}
