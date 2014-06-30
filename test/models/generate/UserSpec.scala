package models.generate

import models._
import scalikejdbc.specs2.mutable.AutoRollback
import org.specs2.mutable._
import org.joda.time._
import scalikejdbc._

class UserSpec extends Specification with TestDBSettings {

  "User" should {

    val u = User.syntax("u")

    "find by primary keys" in new AutoRollback {
      val maybeFound = User.find("user1")
      maybeFound.isDefined should beTrue
    }
    "find all records" in new AutoRollback {
      val allResults = User.findAll()
      allResults.size should be_>(0)
    }
    "count all records" in new AutoRollback {
      val count = User.countAll()
      count should be_>(0L)
    }
    "find by where clauses" in new AutoRollback {
      val results = User.findAllBy(sqls.eq(u.userId, "user1"))
      results.size should be_>(0)
    }
    "count by where clauses" in new AutoRollback {
      val count = User.countBy(sqls.eq(u.userId, "user1"))
      count should be_>(0L)
    }
    "create new record" in new AutoRollback {
      val created = User.create(userId = "user3", updDatetime = DateTime.now)
      created should not beNull
    }
    "save a record" in new AutoRollback {
      val entity = User.findAll().head
      //modify something
      val modified = entity.copy(name = Some("mock_user"))
      val updated = User.save(modified)
      updated should not equalTo(entity)
    }
    "destroy a record" in new AutoRollback {
      val entity = User.findAll().head
      User.destroy(entity)
      val shouldBeNone = User.find("user1")
      shouldBeNone.isDefined should beFalse
    }
  }

}

