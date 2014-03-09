package api.hobbyFeed.routing

import akka.actor.{Props, Actor}
import business.hobbyFeed.feedRequest.{UserWebSocketRequest}
import business.hobbyFeed.feed.consolidated.{FeedConsolidator}
import scala.language.postfixOps
import com.google.inject.{Inject, Provider}
import api.hobbyFeed.util.ActorNameGenerator

class RoutingActor @Inject() (val actorNameGenerator : ActorNameGenerator, val feedConsolidatorProvider : Provider[FeedConsolidator]) extends Actor {

    def receive = {
       case message : UserWebSocketRequest => {
           context.actorOf(Props(feedConsolidatorProvider.get()), actorNameGenerator.GenerateActorName(message.identity.userId)) ! message
       }

       case _ => {
           print ("Routing actor not matched")
       }
    }
}
