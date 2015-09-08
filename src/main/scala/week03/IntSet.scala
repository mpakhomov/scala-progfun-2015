package week03

/**
 * @author mpakhomov
 * @since 9/8/15
 */
abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
}

class Empty extends IntSet {
  def incl(x: Int): IntSet = {
    println("Empty: incl x = " + x)
    new NonEmpty(x, new Empty, new Empty)
  }
  def contains(x: Int): Boolean = false

  override def toString = "."
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {

  def contains(x: Int): Boolean =
    if (x < elem) left.contains(x)
    else if (x > elem) right.contains(x)
    else true

  def incl(x: Int): IntSet =
    if (x < elem) {
      println("NonEmpty: < x = " + x)
      new NonEmpty(elem, left incl x, right)
    } else if (x > elem) {
      println("NonEmpty: > x = " + x)
      new NonEmpty(elem, left, right incl x)
    } else {
      println("NonEmpty: = x = " + x)
      this
    }

  override def toString = "{" + left + elem + right + "}"
}

object IntSet extends App {
//  val z  = new Empty incl 5
//  val y = z incl 4
//  val x = z incl 6
//  y incl 3
  val t1 = new NonEmpty(3, new Empty, new Empty)
  val t2 = t1 incl 4
  println(t2)
}
