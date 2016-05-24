import scala.annotation.tailrec

def map[T, U](xs: List[T], f: T => U): List[U] = {
  @tailrec
  def loop(xs: List[T], acc: List[U]): List[U] = xs match {
    case y :: ys => loop(ys,  f(y) :: acc)
    case Nil => acc
  }
  loop(xs, List())
}

val xs = List(1, 2, 3)
map[Int, Int](xs, x => x + 1)
