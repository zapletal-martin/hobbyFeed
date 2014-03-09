package api.hobbyFeed.util

class ActorNameGenerator (val actorName : String) {
    val counter = scala.collection.mutable.Map [Int, Int] ()
    final val ConventionActorNameDelimiter = "$"

    def GenerateActorName (userId : Int) : String = {
        counter(userId) = if (counter.contains(userId)) counter(userId) + 1 else 1
        actorName + ConventionActorNameDelimiter + userId.toString + ConventionActorNameDelimiter  + counter(userId)
    }
}
