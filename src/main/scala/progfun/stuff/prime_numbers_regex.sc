import scala.util.matching.Regex

def from(n: Int): Stream[Int] = n#:: from(n + 1)

def sieve(s: Stream[Int]): Stream[Int] =
  s.head #:: sieve(s.tail filter (_ % s.head != 0))

val primes = sieve(from(2)).take(10).mkString(", ")

val pattern = new Regex(".?|(..+?)\\1+")//("/^1?$|^(11+?)\\1+$/")

var str = ""
for (i <- 1 to 14) {
  str = str + "1"
}

pattern.findFirstIn(str)
val pattern1 = "([a-cA-C])".r
pattern1.findAllIn("car").mkString(", ")