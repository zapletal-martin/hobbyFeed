package api.hobbyFeed.routing

import org.mashupbots.socko.events.HttpResponseStatus
import org.mashupbots.socko.routes._
import org.mashupbots.socko.infrastructure.Logger
import akka.actor.{Actor, ActorSystem}
import business.hobbyFeed.feedRequest.{UserWebSocketRequest, UserWebSocketConnectionRequest, UserInputRequest}
import business.hobbyFeed.feed.identity.{RequestIdentity, WebSocketConnectionIdentity}
import business.hobbyFeed.util.StringToIntConverter
import javax.inject.{Singleton, Inject}

class Router @Inject()(actorSystem: ActorSystem, stringToInt : StringToIntConverter) extends Logger {

    val routes = Routes {
        case HttpRequest(request) => request match {

            case GET(PathSegments("feed" :: userId :: Nil)) & QueryString(hobby : String) => {
                stringToInt.Parse(userId) match {
                    case Some(userIdInt) => actorSystem.actorSelection("/user/RoutingActor") ! UserInputRequest(Set(hobby), new RequestIdentity(userIdInt, request))
                    case None => //reply that invalid id
                }
            }

            case route => {
                print("httprequest not matched " + route.toString)
            }

        }

        case WebSocketHandshake(wsHandshake) => wsHandshake match {
            case PathSegments("feed" :: userId :: Nil) => {
                PerformActionWithUserId(userId, id => {
                    wsHandshake.authorize()
                    //actorSystem.actorSelection("/user/RoutingActor") ! UserWebSocketConnectionRequest(new WebSocketConnectionIdentity(id, wsHandshake.channel))
                })
            }

            case route =>  {
                print("websocket not matched " + route.toString)
            }
        }

        case WebSocketFrame(frame) => frame match {
            case PathSegments("feed" :: userId :: Nil) => {
                PerformActionWithUserId(userId,
                    id => actorSystem.actorSelection("/user/RoutingActor") ! UserWebSocketRequest(Set(frame.readText()), new WebSocketConnectionIdentity(id, frame.channel)))
            }
        }

        case route => {
            print("route not matched " + route.toString)
        }
    }

    private def PerformActionWithUserId (userId : String, action: Int => Unit) {
        stringToInt.Parse(userId) match {
            case Some(userIdInt) => {
                action(userIdInt)
            }
            case None => //reply that invalid id
        }
    }
}
