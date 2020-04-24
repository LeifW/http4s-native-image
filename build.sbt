/*
 * Copyright 2020 Noel Welsh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import sbt._
import scala.sys.process._
import java.io.File
import Dependencies._

organization := "co.innerproduct"
name := "ping"
scalaVersion := "2.13.2"

libraryDependencies ++= Seq(
  catsCore,
  catsEffect,
  miniTest,
  miniTestLaws,
  http4sBlazeServer,
  http4sBlazeClient,
  http4sCirce,
  http4sDsl,
  logback,
  janino
)

testFrameworks += new TestFramework("minitest.runner.Framework")

lazy val nativeImageLocal =
  taskKey[File]("Build a standalone executable on this machine using GraalVM Native Image")

nativeImageLocal := {
  import sbt.Keys.streams
  val assemblyFatJar = assembly.value
  val assemblyFatJarPath = assemblyFatJar.getAbsolutePath()
  val outputName = "ping-server.local"
  val outputPath = (baseDirectory.value / "out" / outputName).getAbsolutePath()

  val cmd = s"""native-image
     | -jar ${assemblyFatJarPath}
     | ${outputPath}""".stripMargin.filter(_ != '\n')

  val log = streams.value.log
  log.info(s"Building local native image from ${assemblyFatJarPath}")
  log.debug(cmd)
  val result = (cmd.!(log))

  if (result == 0) file(s"${outputPath}")
  else {
    log.error(s"Local native image command failed:\n ${cmd}")
    throw new Exception("Local native image command failed")
  }
}


lazy val nativeImage =
  taskKey[File]("Build a standalone Linux executable using GraalVM Native Image")

nativeImage := {
  import sbt.Keys.streams
  val assemblyFatJar = assembly.value
  val assemblyFatJarPath = assemblyFatJar.getParent()
  val assemblyFatJarName = assemblyFatJar.getName()
  val outputPath = (baseDirectory.value / "out").getAbsolutePath()
  val outputName = "ping-server"
  val nativeImageDocker = "inner-product/graalvm-native-image"

  val cmd = s"""docker run
     | --volume ${assemblyFatJarPath}:/opt/assembly
     | --volume ${outputPath}:/opt/native-image
     | ${nativeImageDocker}
     | --static
     | -jar /opt/assembly/${assemblyFatJarName}
     | ${outputName}""".stripMargin.filter(_ != '\n')

  val log = streams.value.log
  log.info(s"Building native image from ${assemblyFatJarName}")
  log.debug(cmd)
  val result = (cmd.!(log))

  if (result == 0) file(s"${outputPath}/${outputName}")
  else {
    log.error(s"Native image command failed:\n ${cmd}")
    throw new Exception("Native image command failed")
  }
}
