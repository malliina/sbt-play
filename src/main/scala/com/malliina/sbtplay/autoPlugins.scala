package com.malliina.sbtplay

import com.malliina.sbt.unix.LinuxPlugin
import com.typesafe.sbt.packager.archetypes.JavaServerAppPackaging
import com.typesafe.sbt.packager.archetypes.systemloader.SystemdPlugin
import play.sbt.PlayScala
import sbt.Keys._
import sbt._
import sbtbuildinfo.BuildInfoPlugin

import scala.sys.process.{Process, ProcessLogger}

object PlayDefaultPlugin extends AutoPlugin {
  override def requires = PlayScala && JavaServerAppPackaging && SystemdPlugin && BuildInfoPlugin

  override def projectSettings = PlayProject.libSettings
}

object PlayServerPlugin extends AutoPlugin {
  override def requires = PlayScala && JavaServerAppPackaging && SystemdPlugin && BuildInfoPlugin

  override def projectSettings = PlayProject.serverSettings
}

object PlayLinuxPlugin extends AutoPlugin {
  override def requires = PlayScala && JavaServerAppPackaging && LinuxPlugin && SystemdPlugin && BuildInfoPlugin

  override def projectSettings = PlayProject.linuxSettings
}

object NodeCheckPlugin extends AutoPlugin {
  object autoImport {
    val checkNode = taskKey[Unit]("Make sure the user uses the correct version of node.js")
  }
  import autoImport.checkNode

  override val globalSettings: Seq[Def.Setting[_]] = Seq(
    checkNode := runNodeCheck(streams.value.log),
    onLoad in Global := (onLoad in Global).value andThen { state =>
      "checkNode" :: state
    }
  )

  def runNodeCheck(log: ProcessLogger) = {
    val nodeVersion = Process("node --version")
      .lineStream(log)
      .toList
      .headOption
      .getOrElse(sys.error(s"Unable to resolve node version."))
    val validPrefixes = Seq("v8")
    if (validPrefixes.exists(p => nodeVersion.startsWith(p))) {
      log.out(s"Using node $nodeVersion")
    } else {
      log.out(s"Node $nodeVersion is unlikely to work. Trying to change version using nvm...")
      Process("nvm use 8").run(log).exitValue()
    }
  }
}
