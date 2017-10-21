scalaVersion := "2.12.4"
resolvers += Resolver.url("malliina bintray sbt", url("https://dl.bintray.com/malliina/sbt-plugins/"))(Resolver.ivyStylePatterns)

Seq(
  "com.malliina" %% "sbt-utils" % "0.7.0",
  "org.foundweekends" % "sbt-bintray" % "0.5.1"
) map addSbtPlugin
