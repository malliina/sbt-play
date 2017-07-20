scalaVersion := "2.10.6"
resolvers += Resolver.url("malliina bintray sbt", url("https://dl.bintray.com/malliina/sbt-plugins/"))(Resolver.ivyStylePatterns)

Seq(
  "com.malliina" %% "sbt-utils" % "0.6.3",
  "me.lessis" % "bintray-sbt" % "0.2.1"
) map addSbtPlugin
