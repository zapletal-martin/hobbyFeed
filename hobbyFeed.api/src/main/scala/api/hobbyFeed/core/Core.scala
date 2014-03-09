package api.hobbyFeed.core

import org.mashupbots.socko.webserver.{WebServerConfig, WebServer}
import akka.actor.{Props, ActorSystem}
import api.hobbyFeed.routing.{RoutingActor, Router}
import business.hobbyFeed.feed.consolidated.FeedConsolidator
import com.google.inject.Guice
import api.hobbyFeed.core.container.guice.ProductionModule

class Core {
  def Boot () = {
      val injector = Guice.createInjector(new ProductionModule())
      val router = injector.getInstance(classOf[Router])
      val system = injector.getInstance(classOf[ActorSystem])

      system.actorOf(Props(injector.getInstance(classOf[RoutingActor])), "RoutingActor")

      val webServer = new WebServer(WebServerConfig(), router.routes, system)

      Runtime.getRuntime.addShutdownHook(new Thread {
        override def run { webServer.stop() }
      })

      webServer.start()
   }
}
