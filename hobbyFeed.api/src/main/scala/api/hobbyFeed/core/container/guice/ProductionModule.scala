package api.hobbyFeed.core.container.guice

import com.google.inject.{Provides, AbstractModule}
import api.hobbyFeed.routing.{RoutingActor, Router}
import akka.actor.ActorSystem
import business.hobbyFeed.util.StringToIntConverter
import business.hobbyFeed.feed.consolidated.{Feed, FeedConsolidator}
import business.hobbyFeed.feed.google.GoogleFeed
import business.hobbyFeed.feed.faroo.{FarooServiceClient, FarooFeed}
import business.hobbyFeed.serviceHandling.ServiceClient
import com.google.inject.name.Names
import business.hobbyFeed.article.SearchProvider
import api.hobbyFeed.util.ActorNameGenerator

class ProductionModule extends AbstractModule{
    def configure(): Unit = {
        bind(classOf[StringToIntConverter])
        bind(classOf[Router])
        bind(classOf[RoutingActor])
        bind(classOf[FeedConsolidator])
       /*bind(classOf[ActorSystem]).toInstance(initActorSystem())*/
        bind(classOf[ServiceClient]).annotatedWith(Names.named("Faroo")).to(classOf[FarooServiceClient])
        bind(classOf[SearchProvider]).annotatedWith(Names.named("Faroo")).toInstance(new SearchProvider("Faroo"))
        bind(classOf[FarooFeed])
        bind(classOf[GoogleFeed])
        bind(classOf[ActorNameGenerator]).toInstance(new ActorNameGenerator("feedConsolidator"))
    }

    @Provides
    @com.google.inject.Singleton
    def provideActorSystem() = {
        ActorSystem("hobbyFeed")
    }

    @Provides
    def getFeeds(google : GoogleFeed, faroo : FarooFeed) : Traversable[Feed] = {
        Set(google, faroo)
    }
}
