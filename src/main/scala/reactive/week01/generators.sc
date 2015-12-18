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

  def trees: Generator[Tree] = ???
}