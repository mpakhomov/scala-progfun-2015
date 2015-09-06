import scala.annotation.tailrec

// greatest common divisor

object gcd {
  @tailrec
  def gcd(a: Int, b: Int): Int =
    if (b == 0) 0 else gcd(b, a % b)

  println(14 % 21)

  def factorial(n: Int): Int = {
    @tailrec
    def factorialTailRec(n: Int, acc: Int): Int =
      if (n == 1) acc else factorialTailRec(n - 1, acc * n)
    factorialTailRec(n, 1)
  }

  factorial(4)
}