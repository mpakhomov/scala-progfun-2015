name := """progfun-2015"""

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.5" % "test" withSources()
//libraryDependencies += "com.lihaoyi" %% "scalarx" % "0.2.8"


fork in run := true