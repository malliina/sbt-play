# sbt-play #

This SBT plugin helps create Play Framework projects. Common dependencies I find useful are added by default, avoiding
repetition in build files. The packageBin task will furthermore deviate from the default and package assets into the 
resulting jar.

## Installation ##

    addSbtPlugin("com.github.malliina" % "sbt-play" % "0.0.1")

## Usage ##

Define your project as follows in Build.scala:

    lazy val project = com.mle.play.PlayProjects.playProject("mywebsite")
