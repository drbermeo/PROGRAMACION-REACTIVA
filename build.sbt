ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.3"

lazy val root = (project in file("."))
  .settings(
    name := "reactiveprogram",
    libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.6.20"

  )
