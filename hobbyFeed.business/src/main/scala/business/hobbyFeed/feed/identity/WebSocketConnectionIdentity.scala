package business.hobbyFeed.feed.identity
import org.jboss.netty.channel.Channel

case class WebSocketConnectionIdentity (userId : Int, webSocketChannel: Channel)
