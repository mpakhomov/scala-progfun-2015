package reactive.week01

/**
  * @author mpakhomov
  * @since 12/18/15
  */
trait Generator[+T] {
  self =>   // an alias for "this"

  def generate: T  

  def map[S](f: T => S): Generator[S] = new Generator[S] {
    def generate = f(self.generate)
  }

  def flatMap[S](f: T => Generator[S]): Generator[S] = new Generator[S] {
    def generate = f(self.generate).generate
  }
}

trait Tree
case class Inner(left: Tree, right: Tree) extends Tree
case class Leaf(x: Int) extends Tree

object Generator {
  val integers = new Generator[Int] {
    val rand = new java.util.Random
    def generate = rand.nextInt()
  }

  val booleans = for (x <- integers) yield x > 0

//  def pairs[T, U](t: Generator[T], u: Generator[U]) =
//    for {
//      x <- t
//      y <- t
//    } yield (x, y)
  def pairs[T, U](t: Generator[T], u: Generator[U]) = t flatMap {
    (x: T) => u map { (y: U) => (x, y) }
  }

  def single[T](x: T): Generator[T] = new Generator[T] {
    def generate = x
  }

  def choose(lo: Int, hi: Int): Generator[Int] =
    for (x <- integers) yield lo + x % (hi - lo)

  def oneOf[T](xs: T*): Generator[T] =
    for (idx <- choose(0, xs.length)) yield xs(idx)

  def lists: Generator[List[Int]] = for {
    isEmpty <- booleans
    list <- if (isEmpty) emptyLists else nonEmptyLists
  } yield list

  def emptyLists = single(Nil)

  def nonEmptyLists = for {
    head <- integers
    tail <- lists
  } yield head :: tail

}

object GeneratorTest extends App {
  val intGenerator: Generator[Int]  = Generator.integers
  val booleanGenerator: Generator[Boolean] = Generator.booleans
  val pairsGenerator: Generator[(Int, Int)] = Generator.pairs[Int, Int](intGenerator, intGenerator)
  val listGenerator = Generator.lists

  println(intGenerator.generate)
  println(booleanGenerator.generate)
  println(pairsGenerator.generate)

  1 to 5 foreach { _ => println(listGenerator.generate) }
// for (i <- 1 to 5) println(listGenerator.generate)
//   (1 to 5) map { i  => println(listGenerator.generate) }

}

//val booleans = new Generator[Boolean] {
//  def generate = integers.generate > 0
//}

//val pair = new Generator[(Int, Int)] {
//  def generate = (integers.generate, integers.generate)
//}
