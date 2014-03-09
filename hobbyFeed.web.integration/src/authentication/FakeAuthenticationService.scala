package authentication

class FakeAuthenticationService extends IAuthenticationService {
   override def AuthenticateByPassword (username : String, password : String) : Boolean = {
      true
   }
   
   override def AuthenticateBySession (username : String, password : String) : Boolean = {
      true
   }
   
   override def LogUserActivity (sessionId : String) = {
     
   }
   
   override def BuildSessionCookie (username : String) : String = {
       "ac0a-02ut-wbnl-o86l-vhk6"
   }
   
}