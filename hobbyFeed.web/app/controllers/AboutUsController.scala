package controllers

import play.api._
import play.api.mvc._

object AboutUsController extends Controller {
	def index = Action {
	    Ok(views.html.aboutus.index())
	}
}