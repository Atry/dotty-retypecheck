ThisBuild / scalaVersion := "0.26.0-RC1"

libraryDependencies += "ch.epfl.lamp" %% "dotty-compiler" % scalaVersion.value

Test / scalacOptions += raw"""-Xplugin:${(Compile / packageBin).value}"""

Test / scalacOptions += "-Xprint:all"