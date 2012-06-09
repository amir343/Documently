import sbt._

object Dependencies {

  object compile {
    val akkaActor =        "com.typesafe.akka"       %      "akka-actor"         %        "2.0"
    val akkaTestKit =      "com.typesafe.akka"       %      "akka-testkit"       %        "2.0"
/*
    val configrity =       "com.typesafe"            %      "config"             %        "0.4.1"
*/
    val scalaz =           "org.scalaz"             %%      "scalaz-core"        %        "6.0.4"
    val openNLPTools =     "org.apache.opennlp"      %      "opennlp-tools"      %        "1.5.2-incubating"
    val openNLPMaxent =    "org.apache.opennlp"      %      "opennlp-maxent"     %        "3.0.2-incubating"
    val commonsIO =        "commons-io"              %      "commons-io"         %        "2.2"
    val commonsCLI =       "commons-cli"             %      "commons-cli"        %        "1.2"
    val commonsLang =      "commons-lang"            %      "commons-lang"       %        "2.6"
    val slf4jAPI =         "org.slf4j"               %      "slf4j-api"          %        "1.6.4"
    val slf4jSimple =      "org.slf4j"               %      "slf4j-simple"       %        "1.6.4"
    val slf4s =            "com.weiglewilczek.slf4s" %%     "slf4s"              %        "1.0.7"
    val rabbitMQ =         "com.rabbitmq"            %      "amqp-client"        %        "2.8.2"
    val jerkson =          "com.codahale"           %%      "jerkson"            %        "0.5.0"

    val allDependencies = Seq(akkaActor, akkaTestKit, scalaz, openNLPTools, openNLPMaxent, commonsIO, commonsCLI,
    commonsLang, slf4jAPI, slf4jSimple, slf4s, rabbitMQ, jerkson)

  }

  object test {
    val junit =            "junit"                   %      "junit"              %        "4.10"      % "test"
    val scalaTest =        "org.scalatest"          %%      "scalatest"          %        "1.8"       % "test"
  }
}