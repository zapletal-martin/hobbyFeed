package business.hobbyFeed.feed.consolidated

import scala.concurrent.Future
import business.hobbyFeed.article.{SearchProvider, Article}
import scala.Predef._
import business.hobbyFeed.article.Article


trait Feed {
    def Retrieve (keyWords: Set[String]): Future[String]
    def ResponseParser() : String => Traversable[Future[String]]
    def ArticleParser (response : String)(responseParser: (String) => Traversable[Future[String]]): Traversable[Future[Article]]
}
