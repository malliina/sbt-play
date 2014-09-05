package com.mle.play

import com.mle.sbtutils.SbtProjects
import com.typesafe.sbt.web.Import.Assets
import sbt.Keys._
import sbt._

/**
 * @author Michael
 */
object PlayProjects {
  val mleGroup = "com.github.malliina"

  def plainPlayProject(name: String) = SbtProjects.testableProject(name).enablePlugins(play.PlayScala).settings(
    libraryDependencies ++= Seq(
      "org.scalatestplus" %% "play" % "1.2.0" % "test"
    ),
    mappings in(Compile, packageBin) ++= {
      (unmanagedResourceDirectories in Assets).value flatMap
        (assetDir => (assetDir ***) pair relativeTo(baseDirectory.value))
    }
  )

  def playProject(name: String) = plainPlayProject(name).settings(
    libraryDependencies ++= Seq(
      mleGroup %% "util-play" % "1.5.0",
      mleGroup %% "logback-rx" % "0.1.0")
  )
}
