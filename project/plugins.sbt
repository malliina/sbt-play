scalaVersion := "2.12.10"
resolvers += Resolver.url(
  "malliina bintray sbt",
  url("https://dl.bintray.com/malliina/sbt-plugins/")
)(Resolver.ivyStylePatterns)

Seq(
  "com.malliina" %% "sbt-utils-maven" % "0.15.2"
) map addSbtPlugin
