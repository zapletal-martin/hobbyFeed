package api.hobbyFeed.util

object FizzBuzz {

    val fibs: Stream[BigInt] = 0 #:: fibs.scanLeft(BigInt(1))(_ + _)

    def fizzBuzz () = {
        for (i <- 1 to 100) {
            if(i % 3 == 0) {
                print("fizz")
            } else if(i % 5 == 0) {
                print("buzz")
            } else {
                print(i)
            }
        }
    }

    def fizzBuzz2() : Seq[String] = {
        (1 to 100).map(x => if (x % 3 == 0) "fizz" else x.toString)
    }

    /*def fizzBuzz3() : Stream[String] = {

    }*/
}
