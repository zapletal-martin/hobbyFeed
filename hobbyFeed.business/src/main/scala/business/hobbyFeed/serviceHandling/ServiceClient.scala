package business.hobbyFeed.serviceHandling

import scala.concurrent.Future
import scala.Predef._

trait ServiceClient {
    def Request(keyWords : Set[String]) : Future [String]
}
