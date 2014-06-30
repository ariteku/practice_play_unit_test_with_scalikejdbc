package controllers

import play.api._
import play.api.mvc._
import models.generate._ 

object Application extends Controller {

  def index = Action {
    val html:String = User.findAll().map ( user => s"${user.toString} <br />" ).mkString
    Ok(s"+ user list + <br />$html").as(HTML)
  }

}
