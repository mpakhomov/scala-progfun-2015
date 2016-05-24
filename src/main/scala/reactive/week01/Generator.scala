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
  val integers: Generator[Int] = new Generator[Int] {
    val rand = new java.util.Random
    def generate = rand.nextInt()
  }

  val booleans: Generator[Boolean] = for (x <- integers) yield x > 0

//  def pairs[T, U](t: Generator[T], u: Generator[U]): Generator[(T, U)] =
//    for {
//      x <- t
//      y <- t
//    } yield (x, y)
  def pairs[T, U](t: Generator[T], u: Generator[U]): Generator[(T, U)] = t flatMap {
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

  def leafs: Generator[Leaf] = for { // the same as integers map { i => Leaf(i) }
    i <- integers
  } yield Leaf(i)

  def inners: Generator[Inner] = for {
    l <- trees
    r <- trees
  } yield Inner(l, r)

  def trees: Generator[Tree] = for {
    isLeaf <- booleans
    tree <- if (isLeaf) leafs else inners
  } yield tree

  def test[T](r: Generator[T], noTimes: Int = 100)(test: T => Boolean) {
    for (_ <- 0 until noTimes) {
        val value = r.generate
        assert(test(value), s"Test failed for: $value")
    }
    println(s"Test passed $noTimes times")
  }

}

object GeneratorTest extends App {
  val intGenerator: Generator[Int]  = Generator.integers
  val booleanGenerator: Generator[Boolean] = Generator.booleans
  val pairsGenerator: Generator[(Int, Int)] = Generator.pairs[Int, Int](intGenerator, intGenerator)
  val listGenerator = Generator.lists

  println(intGenerator.generate)
  println(booleanGenerator.generate)
  println(pairsGenerator.generate)

  for (_ <- 1 to 10)  { println(Generator.oneOf("red", "green", "blue").generate) }
  1 to 5 foreach { _ => println(listGenerator.generate) }
// for (i <- 1 to 5) println(listGenerator.generate)
// (1 to 5) map { i  => println(listGenerator.generate) }
}

