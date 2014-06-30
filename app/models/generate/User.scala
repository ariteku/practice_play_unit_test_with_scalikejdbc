package models.generate

import scalikejdbc._
import org.joda.time.{DateTime}

case class User(
  userId: String, 
  name: Option[String] = None, 
  password: Option[String] = None, 
  loginDatetime: Option[DateTime] = None, 
  insDatetime: Option[DateTime] = None, 
  updDatetime: DateTime) {

  def save()(implicit session: DBSession = User.autoSession): User = User.save(this)(session)

  def destroy()(implicit session: DBSession = User.autoSession): Unit = User.destroy(this)(session)

}
      

object User extends SQLSyntaxSupport[User] {

  override val tableName = "user"

  override val columns = Seq("user_id", "name", "password", "login_datetime", "ins_datetime", "upd_datetime")

  def apply(u: SyntaxProvider[User])(rs: WrappedResultSet): User = apply(u.resultName)(rs)
  def apply(u: ResultName[User])(rs: WrappedResultSet): User = new User(
    userId = rs.get(u.userId),
    name = rs.get(u.name),
    password = rs.get(u.password),
    loginDatetime = rs.get(u.loginDatetime),
    insDatetime = rs.get(u.insDatetime),
    updDatetime = rs.get(u.updDatetime)
  )
      
  val u = User.syntax("u")

  override val autoSession = AutoSession

  def find(userId: String)(implicit session: DBSession = autoSession): Option[User] = {
    withSQL {
      select.from(User as u).where.eq(u.userId, userId)
    }.map(User(u.resultName)).single.apply()
  }
          
  def findAll()(implicit session: DBSession = autoSession): List[User] = {
    withSQL(select.from(User as u)).map(User(u.resultName)).list.apply()
  }
          
  def countAll()(implicit session: DBSession = autoSession): Long = {
    withSQL(select(sqls"count(1)").from(User as u)).map(rs => rs.long(1)).single.apply().get
  }
          
  def findAllBy(where: SQLSyntax)(implicit session: DBSession = autoSession): List[User] = {
    withSQL { 
      select.from(User as u).where.append(sqls"${where}")
    }.map(User(u.resultName)).list.apply()
  }
      
  def countBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Long = {
    withSQL { 
      select(sqls"count(1)").from(User as u).where.append(sqls"${where}")
    }.map(_.long(1)).single.apply().get
  }
      
  def create(
    userId: String,
    name: Option[String] = None,
    password: Option[String] = None,
    loginDatetime: Option[DateTime] = None,
    insDatetime: Option[DateTime] = None,
    updDatetime: DateTime)(implicit session: DBSession = autoSession): User = {
    withSQL {
      insert.into(User).columns(
        column.userId,
        column.name,
        column.password,
        column.loginDatetime,
        column.insDatetime,
        column.updDatetime
      ).values(
        userId,
        name,
        password,
        loginDatetime,
        insDatetime,
        updDatetime
      )
    }.update.apply()

    User(
      userId = userId,
      name = name,
      password = password,
      loginDatetime = loginDatetime,
      insDatetime = insDatetime,
      updDatetime = updDatetime)
  }

  def save(entity: User)(implicit session: DBSession = autoSession): User = {
    withSQL {
      update(User).set(
        column.userId -> entity.userId,
        column.name -> entity.name,
        column.password -> entity.password,
        column.loginDatetime -> entity.loginDatetime,
        column.insDatetime -> entity.insDatetime,
        column.updDatetime -> entity.updDatetime
      ).where.eq(column.userId, entity.userId)
    }.update.apply()
    entity
  }
        
  def destroy(entity: User)(implicit session: DBSession = autoSession): Unit = {
    withSQL { delete.from(User).where.eq(column.userId, entity.userId) }.update.apply()
  }
        
}
