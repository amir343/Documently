import sbt._
import Keys._


object DocumentlyBuild extends Build {

  import Dependencies._
  import BuildSettings._

  override lazy val settings = super.settings :+ {
    shellPrompt := { s => Project.extract(s).currentProject.id + " > " }
  }

  lazy val documentlyClusterer = Project("documently-clusterer", file("."))
    .settings(basicSettings: _*)
    .settings(libraryDependencies ++= compile.allDependencies)
    .settings(libraryDependencies ++= Seq(test.junit, test.scalaTest))
    .settings(com.github.retronym.SbtOneJar.oneJarSettings: _*)

}