package authentication

trait IAuthenticationService {
	def AuthenticateByPassword (username : String, password : String) : Boolean
	def AuthenticateBySession (username : String, sessionId : String) : Boolean
	def BuildSessionCookie (username : String) : String
	def LogUserActivity (sessionId : String)
}