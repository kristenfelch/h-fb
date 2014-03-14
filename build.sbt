name := "h-fb"

version := "1.0"

scalaVersion := "2.10.2"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-contrib" % "2.2.1",
  "com.typesafe.akka" %% "akka-testkit" % "2.2.1",
  "ch.qos.logback" % "logback-classic" % "1.0.9",
  "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test")

resolvers += Resolver.url("sbt-plugin-releases",
  new URL("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/"))(
    Resolver.ivyStylePatterns)

resolvers += "spray" at "http://repo.spray.io/"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0.M6-SNAP24"

libraryDependencies += "org.seleniumhq.selenium" % "selenium-server" % "2.30.0"

libraryDependencies += "org.seleniumhq.selenium" % "selenium-java" % "2.30.0"

libraryDependencies += "com.github.wookietreiber" %% "scala-chart" % "latest.integration"

libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "0.2.0"

libraryDependencies += "com.google.code.findbugs" % "jsr305" % "2.0.1"

libraryDependencies += "io.spray" %%  "spray-json" % "1.2.5"

libraryDependencies += "org.apache.commons" % "commons-email" % "1.3.1"

libraryDependencies += "org.pegdown" % "pegdown" % "1.3.0"

libraryDependencies += "org.jsoup" % "jsoup" % "1.7.2"

libraryDependencies += "org.mongodb" %% "casbah" % "2.6.1"