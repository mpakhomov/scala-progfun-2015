def sqrtStream(x: Double): Stream[Double] = {
  def improve(guess: Double): Double = (guess + x / guess) / 2
  lazy val guesses: Stream[Double] = 1 #:: (guesses map improve)
  guesses
}

//sqrtStream(4).take(10).toList

def isGoodEnough(guess: Double, x: Double): Boolean =
  Math.abs((guess * guess - x) / x) < 0.0001

sqrtStream(4) filter (isGoodEnough(_, 4))

val approximations: Stream[Double] = sqrtStream(4) filter (isGoodEnough(_, 4))

approximations.take(10).toList