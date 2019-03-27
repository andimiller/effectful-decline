organization := "net.andimiller"

name := "effectful-decline"

version := "0.1"

scalaVersion := "2.12.8"

scalacOptions += "-Ypartial-unification"

libraryDependencies ++= Seq(
  "com.monovore" %% "decline" % "0.5.0",
   "org.typelevel" %% "cats-effect" % "1.2.0"
)