import progfun.week03._
import scala.annotation.tailrec

object nth {
  @tailrec
  def nth[T](n: Int, xs: List[T]): T =
    if (xs.isEmpty) throw new IndexOutOfBoundsException
    else
      if (n == 0) xs.head
      else nth(n - 1, xs.tail)
//  def nth[T](n: Int, xs: List[T]): T = {
//    if (n < 0)  throw new IllegalArgumentException("n < 0")
//    @tailrec
//    def loop(i: Int, xs: List[T]): T =
//      if (xs.isEmpty) throw new IndexOutOfBoundsException("i: " + i + ", n: " + n)
//      else
//        if (i < n) loop(i + 1, xs.tail)
//        else xs.head
//    loop(0, xs)
//  }
//  val x = new Cons[Int](3, new Nil[Int])
//  val y = new Cons[Int](2, x)
//  val z = new Cons[Int](1, y)
  val z = new Cons[Int](1, new Cons[Int](2, new Cons[Int](3, new Nil[Int])))
  nth(2, z)
//  nth(3, z)
  nth(-1, z)
}


