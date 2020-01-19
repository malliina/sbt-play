[![Build Status](https://travis-ci.org/malliina/sbt-play.svg?branch=master)](https://travis-ci.org/malliina/sbt-play)

# sbt-play

This SBT plugin helps create Play Framework projects. Common dependencies I 
find useful are added by default, avoiding repetition in build files. The 
packageBin task will furthermore deviate from the default and package assets 
into the resulting jar.

## Features

- Custom release process
- Predefined buildinfo settings
- Support for running the app as an OS service (via sbt-native-packager)

## Installation

    addSbtPlugin("com.malliina" % "sbt-play" % "1.7.1")

## Usage

Enable one of three plugins:

- PlayServerPlugin
- PlayLinuxPlugin
- PlayDefaultPlugin

Define your project as follows:

    val website = Project("mywebsite", file("."))
      .enablePlugins(PlayServerPlugin)

Alternatively, for Linux deployment targets:
      
    val linuxWebsite = Project("mywebsite", file("."))
      .enablePlugins(PlayLinuxPlugin)
      
Alternatively, for libraries:
      
    val playLibrary = Project("mywebsite", file("."))
      .enablePlugins(PlayDefaultPlugin)
