package progfun.week03

import scala.annotation.tailrec

/**
 * @author mpakhomov
 * @since 9/9/15
 */
class Rational(x: Int, y: Int) {
  require(y != 0, "denominator must be non-zero")

  def this(x: Int) = this(x, 1)

  def numer = x /// g
  def denom = y /// g


  @tailrec
  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)
  private val g = gcd(x, y)

  def add(that: Rational) : Rational =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom)

  def neg: Rational = new Rational(-numer, denom)

  def sub(that: Rational) : Rational = add(that.neg)

  def less(that: Rational): Boolean =
    numer * that.denom < that.numer * denom

  def max(that: Rational): Rational =
    if (this.less(that)) that else this

  override def toString = (numer / g) + "/" + (denom / g)
}
