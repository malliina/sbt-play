lazy val sbtplay = Project("sbt-play", file("."))
  .enablePlugins(BintrayReleasePlugin)

organization := "com.malliina"
scalaVersion := "2.12.8"
resolvers ++= Seq(
  Resolver.url("malliina bintray sbt", url("https://dl.bintray.com/malliina/sbt-plugins/"))(Resolver.ivyStylePatterns),
  Resolver.bintrayRepo("malliina", "maven")
)

Seq(
  "com.typesafe.play" % "sbt-plugin" % "2.7.0",
  "com.malliina" %% "sbt-packager" % "2.7.0",
  "com.eed3si9n" % "sbt-buildinfo" % "0.9.0",
  "com.github.gseitz" % "sbt-release" % "1.0.11"
) map addSbtPlugin
