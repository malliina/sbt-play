import com.malliina.sbtutils.SbtProjects

lazy val sbtplay = SbtProjects.sbtPlugin("sbt-play")

organization := "com.malliina"
scalaVersion := "2.12.6"
resolvers ++= Seq(
  Resolver.url("malliina bintray sbt", url("https://dl.bintray.com/malliina/sbt-plugins/"))(Resolver.ivyStylePatterns),
  Resolver.bintrayRepo("malliina", "maven")
)

Seq(
  "com.typesafe.play" % "sbt-plugin" % "2.6.18",
  "com.malliina" %% "sbt-packager" % "2.4.1",
  "com.eed3si9n" % "sbt-buildinfo" % "0.9.0",
  "com.github.gseitz" % "sbt-release" % "1.0.9"
) map addSbtPlugin

dependencyOverrides ++= Seq(
  "org.webjars" % "webjars-locator-core" % "0.33",
  "org.codehaus.plexus" % "plexus-utils" % "3.0.17",
  "com.google.guava" % "guava" % "23.0"
)
