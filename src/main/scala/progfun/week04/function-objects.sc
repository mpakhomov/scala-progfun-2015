import progfun.week04._

def succ(n: Int): Int = n + 1
val f1 = new Function[Int, Int] {
  def apply(x: Int): Int = x + 1
}
assert(f1.apply(3) == succ(3))



