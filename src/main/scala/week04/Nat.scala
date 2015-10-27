package week04

/**
 * @author mpakhomov
 * @since 9/16/15
 */
abstract class Nat {
  def isZero: Boolean
  def predecessor: Nat
  def successor: Nat
  def + (that: Nat): Nat
  def - (that: Nat): Nat
}

object Zero extends Nat {
  override def isZero: Boolean = true

  override def successor: Nat = Zero

  override def +(that: Nat): Nat = that

  override def -(that: Nat): Nat = that

  override def predecessor: Nat = Zero
}

class Succ(n: Int) extends Nat {
  override def isZero: Boolean = false

  override def successor: Nat = new Succ(n + 1)

  override def +(that: Nat): Nat = new Succ(n - 1)

  override def -(that: Nat): Nat = ???

  override def predecessor: Nat = new Succ(n - 1)
}
