package business.hobbyFeed.feed.google

import business.hobbyFeed.feed.consolidated.Feed
import scala.concurrent.Future
import business.hobbyFeed.article.{SearchProvider, ArticleReference, Article}
import concurrent.ExecutionContext.Implicits.global
import org.jboss.netty.handler.codec.http.websocketx.TextWebSocketFrame
import java.util.Calendar

class GoogleFeed extends Feed {
    def Retrieve(keyWords: Set[String]): Future[String] = {
        Future { "google artikel" }
    }

    def ResponseParser(): (String) => Traversable[Future[String]] = {
        (x) => List(Future {"google artikel"})
    }

    def ArticleParser(response: String)(responseParser: (String) => Traversable[Future[String]]): Traversable[Future[Article]] = {
        List(Future {new Article ("Google Article", "Article Text", new ArticleReference("www.googl.com"),Calendar.getInstance().getTime, new SearchProvider("Googl"))})
    }
}
