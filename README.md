# sbt-play #

This SBT plugin helps create Play Framework projects. Common dependencies I find useful are added by default, avoiding
repetition in build files. The packageBin task will furthermore deviate from the default and package assets into the 
resulting jar.

## Installation ##

    addSbtPlugin("com.malliina" % "sbt-play" % "0.8.0")

## Usage ##

Define your project as follows in Build.scala:

    lazy val project = com.mle.sbt.play.PlayProjects.playProject("mywebsite")
