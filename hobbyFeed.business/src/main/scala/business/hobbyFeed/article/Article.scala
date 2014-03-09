package business.hobbyFeed.article

import java.util.{Date, Calendar}

case class Article (Title : String, PreviewText : String, Reference : ArticleReference, Updated : Date, Provider : SearchProvider)