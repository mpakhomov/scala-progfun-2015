import reactive.week01._
object generators {
  val intGenerator: Generator[Int]  = Generator.integers
  val booleanGenerator: Generator[Boolean] = Generator.booleans
  val pairsGenerator: Generator[(Int, Int)] = Generator.pairs[Int, Int](intGenerator, intGenerator)
  val listGenerator = Generator.lists
  println(intGenerator.generate)
  println(booleanGenerator.generate)
  println(pairsGenerator.generate)
  1 to 5 foreach { _ => println(listGenerator.generate) }
  val integers = Generator.integers
  val booleans1 = for (x <- integers) yield x > 0
  val booleans2 = integers map (x => x > 0)
  val booleans3 = new Generator[Boolean] {
    def generate = ((x: Int) => x > 0)(integers.generate)
  }
  booleans1.generate
  booleans2.generate
  booleans3.generate
  def pairs1[T, U](t: Generator[T], u: Generator[U]): Generator[(T, U)] =
    for (x <- t; y <- u) yield (x, y)
  def pairs2[T, U](t: Generator[T], u: Generator[U]): Generator[(T, U)] =
    t flatMap ((x: T) => u map ((y: U) => (x, y))) 
  def pairs3[T, U](t: Generator[T], u: Generator[U]): Generator[(T, U)] =
    t flatMap { (x: T) =>
        new Generator[(T, U)] {
            def generate = ((y : U) => (x, y))(u.generate)
        }
    }

  def pairs4[T, U](t: Generator[T], u: Generator[U]): Generator[(T, U)] =
    t flatMap { (x: T) =>
        new Generator[(T, U)] {
            def generate = (x, u.generate)
        }
    }
  
  def pairs5[T, U](t: Generator[T], u: Generator[U]): Generator[(T, U)] =
    new Generator[(T, U)] {
        def generate = (new Generator[(T, U)] {
            def generate = (t.generate, u.generate)
        }).generate
    }

  def pairs6[T, U](t: Generator[T], u: Generator[U]): Generator[(T, U)] =
    new Generator[(T, U)] {
      def generate = (t.generate, u.generate)
    }

  pairs1(integers, integers).generate
  pairs2(integers, integers).generate
  pairs3(integers, integers).generate
  pairs4(integers, integers).generate
  pairs5(integers, integers).generate
  pairs6(integers, integers).generate

  val treeGenerator: Generator[Tree] = Generator.trees
  treeGenerator.generate
  treeGenerator.generate
  treeGenerator.generate
  treeGenerator.generate

  Generator.test(Generator.pairs(listGenerator, listGenerator)) {
    case (xs, ys) => (xs ++ ys).length > xs.length
  }
}

