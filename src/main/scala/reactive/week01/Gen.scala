package reactive.week01

/**
  * make generator class a type class
  *
  * @author mpakhomov
  * @since 12/18/15
  */
trait Gen[+T] {
  self =>   // an alias for "this"

  def generate: T

  def map[S](f: T => S): Gen[S] = new Gen[S] {
    def generate = f(self.generate)
  }

  def flatMap[S](f: T => Gen[S]): Gen[S] = new Gen[S] {
    def generate = f(self.generate).generate
  }
}

object Gen {

  implicit object IntGen extends Gen[Int] {
    val rand = new java.util.Random
    override def generate: Int = rand.nextInt()
  }

  val integers = implicitly[Gen[Int]]

//  val integers: Gen[Int] = new Gen[Int] {
//    val rand = new java.util.Random
//    def generate = rand.nextInt()
//  }

  val booleans: Gen[Boolean] = for (x <- integers) yield x > 0

  //  def pairs[T, U](t: Gen[T], u: Gen[U]): Gen[(T, U)] =
  //    for {
  //      x <- t
  //      y <- t
  //    } yield (x, y)
  def pairs[T, U](t: Gen[T], u: Gen[U]): Gen[(T, U)] = t flatMap {
    (x: T) => u map { (y: U) => (x, y) }
  }

  def single[T](x: T): Gen[T] = new Gen[T] {
    def generate = x
  }

  def choose(lo: Int, hi: Int): Gen[Int] =
    for (x <- integers) yield lo + Math.abs(x) % (hi - lo)

  def oneOf[T](xs: T*): Gen[T] =
    for (idx <- choose(0, xs.length)) yield xs(idx)

  def lists: Gen[List[Int]] = for {
    isEmpty <- booleans
    list <- if (isEmpty) emptyLists else nonEmptyLists
  } yield list

  def emptyLists = single(Nil)

  def nonEmptyLists = for {
    head <- integers
    tail <- lists
  } yield head :: tail

  def leafs: Gen[Leaf] = for { // the same as integers map { i => Leaf(i) }
    i <- integers
  } yield Leaf(i)

  def inners: Gen[Inner] = for {
    l <- trees
    r <- trees
  } yield Inner(l, r)

  def trees: Gen[Tree] = for {
    isLeaf <- booleans
    tree <- if (isLeaf) leafs else inners
  } yield tree

  def test[T](r: Gen[T], noTimes: Int = 100)(test: T => Boolean) {
    for (_ <- 0 until noTimes) {
      val value = r.generate
      assert(test(value), s"Test failed for: $value")
    }
    println(s"Test passed $noTimes times")
  }

}

object GenTest extends App {
  val intGen: Gen[Int]  = implicitly[Gen[Int]]//Gen.integers
  val booleanGen: Gen[Boolean] = Gen.booleans
  val pairsGen: Gen[(Int, Int)] = Gen.pairs[Int, Int](intGen, intGen)
  val listGen = Gen.lists

  println(intGen.generate)
  println(booleanGen.generate)
  println(pairsGen.generate)

//  for (_ <- 1 to 10)  { println(Gen.choose(-10, 10).generate) }
//  1 to 5 foreach { _ => println(listGen.generate) }
  // for (i <- 1 to 5) println(listGen.generate)
  // (1 to 5) map { i  => println(listGen.generate) }
  1 to 5 map { _ => println(Generator.trees.generate) }
}

