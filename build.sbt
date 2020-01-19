ThisBuild / pluginCrossBuild / sbtVersion := "1.2.8"

val sbtplay = Project("sbt-play", file("."))
  .enablePlugins(BintrayReleasePlugin)
  .settings(
    organization := "com.malliina",
    scalaVersion := "2.12.10",
    resolvers ++= Seq(
      Resolver.url("malliina bintray sbt", url("https://dl.bintray.com/malliina/sbt-plugins/"))(
        Resolver.ivyStylePatterns
      ),
      Resolver.bintrayRepo("malliina", "maven")
    ),
    releaseProcess := tagReleaseProcess.value,
    Seq(
      "com.typesafe.play" % "sbt-plugin" % "2.8.0",
      "com.malliina" %% "sbt-packager" % "2.8.3",
      "com.eed3si9n" % "sbt-buildinfo" % "0.9.0",
      "com.github.gseitz" % "sbt-release" % "1.0.13"
    ) map addSbtPlugin
  )
