package controllers

import app.models.landing.HobbyInput
import play.api.mvc._
import javax.inject.{Singleton, Inject}
import injectionTest._
import play.api.data.Forms._
import play.api.data._

@Singleton
class LandingController @Inject() (shout : IShouting) extends Controller {

    val inputForm = Form [HobbyInput] (
        mapping(
            "keyword" -> text
        )(HobbyInput.apply)(HobbyInput.unapply)
    )

	def index = Action { implicit request =>
        inputForm.bindFromRequest.fold(
            formWithErrors => Ok(views.html.landing.index(formWithErrors)),
            input => Ok(views.html.landing.index(inputForm))
        )
    }
}