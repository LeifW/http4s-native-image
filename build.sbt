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
enablePlugins(GraalVMNativeImagePlugin, sbtdocker.DockerPlugin)
import sbt._
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

graalVMNativeImageOptions ++= Seq(
  "--static",
  "-H:UseMuslC=" + baseDirectory.value / "bundle"
)

dockerfile in docker := {
  val artifact: File = (packageBin in GraalVMNativeImage).value
  new Dockerfile {
    from("scratch")
    add(artifact, artifact.name)
    entryPoint("/" + artifact.name)
  }
}
