import scala.annotation.tailrec

//import progfun.week02.Rational

//import progfun.week02.Rational
//object rationals {
val x = new Rational(1,3)
val y = new Rational(5,7)
val z = new Rational(3,2)
x - y - z
y + y
x < y
x max y
val q = new Rational(5)
//val strange = new Rational(1, 0)
//}
class Rational(x: Int, y: Int) {
  require(y != 0, "denominator must be non-zero")

  def this(x: Int) = this(x, 1)

  def numer = x /// g
  def denom = y /// g


  @tailrec
  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)
  private val g = gcd(x, y)

  def +(that: Rational) : Rational =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom)

  def unary_- : Rational = new Rational(-numer, denom)

  def - (that: Rational) : Rational = this + -that

  def < (that: Rational): Boolean =
    numer * that.denom < that.numer * denom

  def max(that: Rational): Rational =
    if (this < that) that else this

  override def toString = (numer / g) + "/" + (denom / g)
}


