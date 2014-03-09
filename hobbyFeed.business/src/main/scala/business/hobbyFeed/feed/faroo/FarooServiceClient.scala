package business.hobbyFeed.feed.faroo

import business.hobbyFeed.serviceHandling.ServiceClient
import scala.concurrent.Future
import concurrent.ExecutionContext.Implicits.global

class FarooServiceClient extends ServiceClient {
    def Request(keyWords : Set[String]): Future[String] = {
        Future  {
            val url = "http://www.faroo.com/api" + "?" + keyWords.toString() + "&key=O7XUvtCQBIQZeMT@0U36AlJD0SQ_"
            scala.io.Source.fromURL(url).mkString
        }
    }
}

