package business.hobbyFeed.feed.consolidated

import akka.actor.Actor
import business.hobbyFeed.feedRequest.UserWebSocketRequest
import org.jboss.netty.handler.codec.http.websocketx.TextWebSocketFrame
import concurrent.ExecutionContext.Implicits.global
import com.google.inject.Inject
import scala.util.{Failure, Success}

class FeedConsolidator @Inject() (feeds : Traversable [Feed]) extends Actor {

    def receive: Actor.Receive = {
        case message : UserWebSocketRequest => {
            val userRequest = message
            feeds.foreach (feed => { parseFeedResponse(feed, userRequest) })
        }
    }

    private def parseFeedResponse(feed : Feed, message : UserWebSocketRequest) = {
        val response = feed.Retrieve(message.userInput)

        response.onComplete ( result =>
            result match {
                case Success(x) => {
                    val parsedResults = feed.ArticleParser(x)(feed.ResponseParser())

                    parsedResults.foreach(
                        article =>  {
                            article.onComplete(result => {
                                message.identity.webSocketChannel.write(new TextWebSocketFrame(result.toString))
                            })
                        }
                    )
                }

                case Failure(x) => message.identity.webSocketChannel.write(new TextWebSocketFrame(x.getStackTraceString))
            }
        )
    }
}
