import sbt._
import Keys._

object BuildSettings {
  lazy val basicSettings = Seq[Setting[_]](
    name          := "Documently-clusterer",
    version       := "1.0-SNAPSHOT",
    homepage      := Some(new URL("https://github.com/amir343/Documently")),
    organization  := "com.jayway",
    organizationHomepage := Some(new URL("http://jayway.com")),
    description   := "Documently component that is responsible for clustering documents",
    startYear     := Some(2012),
    licenses      := Seq("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt")),
    scalaVersion  := "2.9.1",
    scalacOptions := Seq("-deprecation", "-encoding", "utf8"),
    resolvers     := Seq(
                      "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
                      "OpenNLP" at "http://opennlp.sourceforge.net/maven2",
                      "Codehale" at "http://repo.codahale.com"
                     )
  )

  lazy val noPublishing = Seq(
    publish := (),
    publishLocal := ()
  )

  lazy val exampleSettings = basicSettings ++ noPublishing

}

