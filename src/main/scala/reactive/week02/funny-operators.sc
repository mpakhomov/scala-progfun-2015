/**
  * @author mpakhomov
  * @since 12/27/15
  */

object funnyoperators {
  val a = List(2, 3, 4)
  a :+ 5
  1 +: a

  a ::: List(5)
  1 :: a
}
