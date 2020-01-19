package com.malliina.sbtplay

import com.malliina.sbt.unix.LinuxPlugin
import com.typesafe.sbt.packager.archetypes.JavaServerAppPackaging
import com.typesafe.sbt.packager.archetypes.systemloader.SystemdPlugin
import play.sbt.PlayScala
import sbt._
import sbtbuildinfo.BuildInfoPlugin

object PlayDefaultPlugin extends AutoPlugin {
  override def requires = PlayScala && JavaServerAppPackaging && SystemdPlugin && BuildInfoPlugin

  override def projectSettings = PlayProject.libSettings
}

object PlayServerPlugin extends AutoPlugin {
  override def requires = PlayScala && JavaServerAppPackaging && SystemdPlugin && BuildInfoPlugin

  override def projectSettings = PlayProject.serverSettings
}

object PlayLinuxPlugin extends AutoPlugin {
  override def requires =
    PlayScala && JavaServerAppPackaging && LinuxPlugin && SystemdPlugin && BuildInfoPlugin

  override def projectSettings = PlayProject.linuxSettings
}
