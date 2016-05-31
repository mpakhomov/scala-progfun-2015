package fundesign.week3

/**
  * @author mpakhomov
  * @since 5/31/16
  */
class DiscreteEventSimulation {

  type Action = () => Unit

  trait Simulation {
    def currentTime = ???
    def afterDelay(delay: Int)(block: Unit): Unit = ???
    def run(): Unit = ???
  }

}
