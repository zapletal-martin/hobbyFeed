package business.hobbyFeed.feed.faroo

import business.hobbyFeed.feed.consolidated.Feed
import business.hobbyFeed.article.{SearchProvider, ArticleReference, Article}
import business.hobbyFeed.serviceHandling.ServiceClient
import scala.concurrent.Future
import com.google.inject.{Inject}
import javax.inject.Named
import java.util.Calendar
import concurrent.ExecutionContext.Implicits.global
import net.liftweb.json.JsonAST.{JArray, JField, JString}
import net.liftweb.json._
import net.liftweb.json.JsonParser._

class FarooFeed @Inject() (@Named("Faroo") service : ServiceClient) extends Feed {
    implicit val formats = DefaultFormats

    def Retrieve(keyWords: Set[String]): Future[String] = {
        service.Request(keyWords)
    }

    def ResponseParser(): (String) => Traversable[Future[String]] = {
        response => {
            parseBasicFarooResponse(response)
        }
    }

    def ArticleParser (response : String)(responseParser: (String) => Traversable[Future[String]]): Traversable[Future[Article]] = {
        val articles = responseParser(response)

        articles.map(article => {
            article.flatMap(data => {
                Future { new Article("Article from Faroo", data, new ArticleReference("www.faroo.com/artikl"), Calendar.getInstance().getTime, new SearchProvider("Faroo"))}
            } )
        } )
    }

    private def parseBasicFarooResponse (response : String) : Traversable[Future[String]]  = {
        val json = JsonParser.parse(response)

        for {
            x <- 1 to 5
            JArray(allArticles) <- json \\ "results"
            JObject(article) <- allArticles
        } yield  { Future  { Thread.sleep(x * 1000); article.toString() } }
    }
}

