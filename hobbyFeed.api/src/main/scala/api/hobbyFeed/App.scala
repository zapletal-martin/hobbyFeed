package api.hobbyFeed.core

import akka.actor.{Props, ActorSystem}
import org.mashupbots.socko.webserver.{WebServerConfig, WebServer}
import api.hobbyFeed.util
import api.hobbyFeed.util.{FizzBuzz, SetExercise}

object App extends App {

  override def main(args: Array[String]) = {
      /* val core = new Core ()
       core.Boot()*/


      val a = FizzBuzz.fizzBuzz2();
      println(FizzBuzz.fibs.take(5).toList)

  }
}
