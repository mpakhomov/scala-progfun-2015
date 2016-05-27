val xs = List(1, 2, 3, 4)
val ys = List(5, 6, 7, 8)

xs.foldLeft(0)((acc, x) => acc + x)

val words = List("the", "quick", "brown", "fox")
("" /: words)(_ + " " + _)
(words.head /: words.tail)(_ + " " + _)

val words2 = List("slow", "green", "turtle")

words.foldRight(words2)(_ :: _)

(xs foldRight ys)(_ :: _)
xs.foldRight(ys)(_ :: _)
xs.foldLeft(ys)(_ :: _)
