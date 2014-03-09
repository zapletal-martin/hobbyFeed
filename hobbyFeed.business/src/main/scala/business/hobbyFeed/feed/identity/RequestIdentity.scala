package business.hobbyFeed.feed.identity

import org.mashupbots.socko.events.{HttpRequestEvent}

case class RequestIdentity (userId : Int, httpRequest : HttpRequestEvent)
