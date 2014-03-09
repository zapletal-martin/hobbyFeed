package business.hobbyFeed.feedRequest

import business.hobbyFeed.feed.identity.{WebSocketConnectionIdentity, RequestIdentity}

case class UserWebSocketRequest (userInput : Set [String], identity : WebSocketConnectionIdentity)


