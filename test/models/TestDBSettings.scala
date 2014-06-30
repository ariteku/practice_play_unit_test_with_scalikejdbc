package models

import scalikejdbc._
import java.sql.DriverManager

trait TestDBSettings {
  def loadJDBCSettings() {
    DriverManager.registerDriver(new com.mysql.jdbc.Driver())
    val url = "jdbc:mysql://localhost/test_scalikejdbc?useUnicode=true&characterEncoding=UTF-8"
    val user = "root"
    val password = ""
    ConnectionPool.singleton(url, user, password)
  }

  loadJDBCSettings()
}

