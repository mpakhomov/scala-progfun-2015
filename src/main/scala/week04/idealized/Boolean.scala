package week04.idealized

/**
 * @author mpakhomov
 * @since 9/15/15
 */
abstract class Boolean {

  def ifThenElse[T](t: T, e: T): T

  def && (x: Boolean): Boolean = ifThenElse[Boolean](x, _false)
  def || (x: Boolean): Boolean = ifThenElse[Boolean](_true, x)
  def unary_! : Boolean = ifThenElse(_false, _true)

  def == (x: Boolean): Boolean = ifThenElse(x, !x)
  def != (x: Boolean): Boolean = ifThenElse(!x, x)
  def < (x: Boolean): Boolean = ifThenElse(_false, x)
}

object _true extends Boolean {
  def ifThenElse[T](t: T, e: T): T = t
}

object _false extends Boolean {
  def ifThenElse[T](t: T, e: T): T = e
}
