package reactive.week02

import frpodersky.Signal

/**
  * @author mpakhomov
  * @since 12/27/15
  */
object Consolidated extends App {
  def consolidated(accts: List[BankAccount]): Signal[Int] =
    Signal(accts.map(_.balance()).sum)
  val a = new BankAccount()
  val b = new BankAccount()
  val c = consolidated(List(a, b))
  println(s"c()=${c()}")
  a deposit 20
  c()
  println(s"c()=${c()}")
  b deposit 30
  c()
  println(s"c()=${c()}")
  val xchange = Signal(246.0)
  val inDollar = Signal(c() * xchange())
  inDollar()
  println(s"inDollar()=${inDollar()}")
  b withdraw 10
  println(s"inDollar()=${inDollar()}")
}
