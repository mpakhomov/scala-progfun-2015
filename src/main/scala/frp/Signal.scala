package frp

class Signal[T](expr: => T) {
  import Signal._
  def apply(): T = {
    observers += caller.value
    assert(!caller.value.observers.contains(this),
      "Cyclic signal definition")
    myValue
  }
  private var myExpr: () => T = _
  private var myValue: T = _
  private var observers: Set[Signal[_]] = Set()
  update(expr)

  protected def update(expr: => T): Unit = {
    myExpr = () => expr
    computeValue()
  }

  protected def computeValue(): Unit = {
    val newValue: T = caller.withValue(this)(myExpr())
    if (myValue != newValue) {
      myValue = newValue
      val obs = observers
      observers = Set()
      obs.foreach(_.computeValue())
    }
  }

}

object NoSignal extends Signal[Nothing](???) {
  override def computeValue() = ()  
}

object Signal {
  //private val caller = new DynamicVariable[Signal[_]](NoSignal)
  private var caller = new StackableVariable[Signal[_]](NoSignal)
  def apply[T](expr: => T) = new Signal(expr)
}
