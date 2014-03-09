package api.hobbyFeed.util

object SetExercise {
    type Set = (Int) => (Boolean)
    val data : scala.collection.immutable.Set [Int] = scala.collection.immutable.Set (1, 3, 5, 7, 9, 11)

    def Set (a : Int) : Boolean = {
        data.contains(a)
    }

    def ForAll (predicate : (Int) => (Boolean), set : Set) : Boolean = {
        val From = -1000
        val To = 1000

        def CheckOne (i : Integer) : Boolean = {
            if(i > To) true else (SatisfiesPredicate(i) && CheckOne(i + 1))
        }

        def SatisfiesPredicate (i : Integer) : Boolean = {
            !(set(i) && !predicate(i))
        }

        CheckOne(From)
    }

    def Test () : Unit = {
        ForAll(_ % 2 == 1, Set)
    }
}
