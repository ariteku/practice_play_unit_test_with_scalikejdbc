name := "scalikejdbc2-test-practice"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  "mysql" % "mysql-connector-java" % "5.1.24",
  "org.scalikejdbc" %% "scalikejdbc"              % "2.0.0",
  "org.scalikejdbc" %% "scalikejdbc-config"       % "2.0.0",
  "org.scalikejdbc" %% "scalikejdbc-play-plugin"  % "2.2.0",
  "org.scalikejdbc" %% "scalikejdbc-test"         % "2.0.4"    % "test",
  "org.slf4j" % "slf4j-nop" % "1.6.4"
)     

play.Project.playScalaSettings

scalikejdbcSettings

