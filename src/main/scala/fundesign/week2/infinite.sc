import scala.annotation.tailrec

def from(n: Int): Stream[Int] = n #:: from(n + 1)

from(1) take 10 toList

val nats = from(0)

val m4s = nats.map(_ * 4)

m4s.take(10).toList


def sieve(s: Stream[Int]): Stream[Int] =
  s.head #:: sieve(s.tail filter (_ % s.head != 0))

sieve(from(2)).take(15).toList