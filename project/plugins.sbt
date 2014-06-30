// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository 
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

// Use the Play sbt plugin for Play projects
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.2.3")

libraryDependencies += "mysql" % "mysql-connector-java" % "[5.1,)"

addSbtPlugin("org.scalikejdbc" %% "scalikejdbc-mapper-generator" % "[2.0,)")
