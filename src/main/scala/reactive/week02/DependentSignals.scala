package reactive.week02

import frp.{Signal, Var}

/**
  * @author mpakhomov
  * @since 12/27/15
  */
object DependentSignals extends App {
  val num = Var(2)
  val derived = Signal(num() * 2)
  println(s"derived: ${derived()}") // should be 4

  // all right, now lt's increase num to 4
  num() = 4
  println(s"derived: ${derived()}") // should be 8
}
