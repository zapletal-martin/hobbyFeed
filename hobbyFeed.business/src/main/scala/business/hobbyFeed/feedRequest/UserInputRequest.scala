package business.hobbyFeed.feedRequest


import business.hobbyFeed.article.Article
import business.hobbyFeed.feed.identity.RequestIdentity

case class UserInputRequest (userInput : Set [String], Caller : RequestIdentity)

case class UserInputResponse (response : Article, Caller : RequestIdentity)
