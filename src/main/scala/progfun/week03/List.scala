package progfun.week03

/**
 * @author mpakhomov
 * @since 9/9/15
 */
trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]

}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty: Boolean = false
}

class Nil[T] extends List[T] {
  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}

