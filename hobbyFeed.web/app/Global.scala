import app.models.CookieValues
import controllers.routes
import javax.inject._
import com.google.inject.Guice
import play.api.GlobalSettings
import injectionTest._
import container.DevelopmentModule
import play.api._
import play.api.mvc.Request
import mvc._
import play.mvc.Results.Redirect
import controllers.routes
import scalax.io._
import authentication._

/**
 * application.global=Global 
 * global settings selection in application.conf 
 */

object Global extends GlobalSettings  {
 
  lazy val injector = Guice.createInjector(new DevelopmentModule())

  /** ugly hack - this can't be injected because it's object
   *  function composition would not help, because all controllers would have to
   *  extend some authcontroller. And therefore all of them would need authservice
   *  injected. Possible to call other controller action before all actions?
   */
  val _authenticationService : IAuthenticationService = new FakeAuthenticationService()
  /**
   * Controllers must be resolved through the application context.
   * There is a special method of GlobalSettings
   * that we can override to resolve a given controller. 
   *  This resolution is required by the Play router.
   */
  override def getControllerInstance[A](controllerClass: Class[A]): A = { 
	  injector.getInstance(controllerClass)
  }
  
  /**
   * Intercepts call to every action
   * To intercept one particular action, use Action composition
   */
  override def onRouteRequest(request: RequestHeader): Option[Handler] = {
     val emailCookie = request.cookies.get(CookieValues.email.toString()).getOrElse(null)
     val email = if (emailCookie != null) { emailCookie.value } else { "" }   
     
     val sessionCookie = request.cookies.get(CookieValues.session.toString()).getOrElse(null)
     val sessionId = if (sessionCookie != null) { sessionCookie.value } else { "" }
     
     if(!_authenticationService.AuthenticateBySession(email, sessionId) 
         && !request.path.equals(routes.LoginController.login.toString())
         && !request.path.equals(routes.LoginController.authenticate.toString()))
     {
    	 Some(LoginRedirector.redirect(request))
     }
     else
     {
         super.onRouteRequest(request)
     }
  }
}

object LoginRedirector extends Controller {
  def redirect(request: RequestHeader) = Action {
    Redirect(routes.LoginController.login)
  }
}
