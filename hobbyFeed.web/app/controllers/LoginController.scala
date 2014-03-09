package controllers

import app.models.login.LoginDetails
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import javax.inject.{Singleton, Inject}
import authentication._
import app.models.CookieValues

class LoginController @Inject() (_authenticationService : IAuthenticationService) extends Controller {     
  
	val signupForm = Form (
		mapping(
			"email" -> email,
			"password" -> text 
		)(LoginDetails.apply)(LoginDetails.unapply)
	)
  
	def login = Action {
		Ok(views.html.login.index(signupForm))
	}
	
	def authenticate = Action { implicit request =>
	   signupForm.bindFromRequest.fold(
	       formWithErrors => Ok(views.html.login.index(formWithErrors)),
	       loginDetails => {
               if(_authenticationService.AuthenticateByPassword(loginDetails.email, loginDetails.password))
               {
                    val sessionId = _authenticationService.BuildSessionCookie(loginDetails.email)

                   _authenticationService.LogUserActivity(sessionId)

                   Redirect(routes.LandingController.index).withCookies(new Cookie (CookieValues.email.toString(), loginDetails.email), new Cookie (CookieValues.session.toString(), sessionId))
                   Ok(views.html.login.index(signupForm))
               }
               else
               {
                   Ok(views.html.login.index(signupForm))
               }
	       }
	   )
	}
}