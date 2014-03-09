package business.hobbyFeed.util

class StringToIntConverter {
   def Parse (string : String) : Option [Int] = {

       try
       {
           val userIdInt = Integer.parseInt(string)
           Some(userIdInt)
       }
       catch
       {
           case _ : Throwable => None
       }
   }
}
