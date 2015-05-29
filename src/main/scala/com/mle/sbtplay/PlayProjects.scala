package com.mle.sbtplay

import com.mle.sbtutils.SbtProjects
import com.typesafe.sbt.web.Import.Assets
import sbt.Keys._
import sbt._

import scala.language.postfixOps

/**
 * @author Michael
 */
object PlayProjects {
  val mleGroup = "com.github.malliina"

  def plainPlayProject(name: String) = SbtProjects.testableProject(name).enablePlugins(play.sbt.PlayScala).settings(
    libraryDependencies += "org.scalatestplus" %% "play" % "1.2.0" % "test",
    mappings in(Compile, packageBin) ++= {
      (unmanagedResourceDirectories in Assets).value flatMap
        (assetDir => (assetDir ***) pair relativeTo(baseDirectory.value))
    }
  )
}
