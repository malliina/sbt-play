[![Build Status](https://travis-ci.org/malliina/sbt-play.svg?branch=master)](https://travis-ci.org/malliina/sbt-play)

# sbt-play

This SBT plugin helps create Play Framework projects. Common dependencies I 
find useful are added by default, avoiding repetition in build files. The 
packageBin task will furthermore deviate from the default and package assets 
into the resulting jar.

## Installation

    addSbtPlugin("com.malliina" % "sbt-play" % "1.6.0")

## Usage

Define your project as follows:

    val website = Project("mywebsite", file("."))
      .enablePlugins(PlayServerPlugin)
