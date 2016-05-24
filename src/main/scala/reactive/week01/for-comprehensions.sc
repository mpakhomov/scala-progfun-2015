val n = 13
def from(n: Int): Stream[Int] = n#:: from(n + 1)
def sieve(s: Stream[Int]): Stream[Int] =
  s.head #:: sieve(s.tail filter (_ % s.head != 0))
val primes = sieve(from(2))
def isPrime(x: Int) = primes.take(n).contains(x)

for {
  i <- 1 until n
  j <- 1 until i
  if (isPrime(i + j))
} yield (i, j)

for {
  i <- 1 until n
  j <- (1 until i).withFilter(j => isPrime(i + j))
} yield (i, j)

(1 until n).flatMap( i =>
  for {
    j <- (1 until i).withFilter(j => isPrime(i + j))
  } yield (i, j)
)

(1 until n).flatMap( i =>
  (1 until i)
    .withFilter(j => isPrime(i + j))
    .map(j => (i, j))
)

val list = List(1, 2, 3, 4)
val list2 = List(5, 6, 7, 8)

for (e <- list) yield e + 1
list map { x => x + 1 }
list map ( x => x + 1 )

for (e <- list if e % 2 ==0) yield e + 1
for (e <- list.withFilter(e => e % 2 == 0)) yield e + 1
list withFilter(e => e % 2  == 0) map (e => e + 1)

for {
  e1 <- list if e1 % 2 == 0
  e2 <- list2 if e2 % 2 != 0
} yield (e1 + 1, e2 * 2)

for {
  e1 <- list.withFilter(e => e % 2 == 0)
  e2 <- list2 if e2 % 2 != 0
} yield (e1 + 1, e2 * 2)

list.withFilter(e => e % 2 == 0).flatMap( e1 =>
  for (e2 <- list2 if e2 % 2 != 0) yield (e1 + 1, e2 * 2)
)

list.withFilter(e => e % 2 == 0).flatMap( e1 =>
  list2.withFilter(e => e % 2 != 0).map( e2 =>
    (e1 + 1, e2 * 2)
  )
)

list.withFilter (e => e % 2 == 0) flatMap { e1 =>
  list2.withFilter(e => e % 2 != 0) map { e2 =>
    (e1 + 1, e2 * 2)
  }
}