package fundesign.week3

/**
  * @author mpakhomov
  * @since 5/31/16
  */
class Inverter(input: Wire, output: Wire): Unit = {
  def invertAction(): Unit = {
    var inputSig = input.getSignal
    afterDelay(InverterDelay) {
      output setSignal !inputSig
    }
  }
  input addAction invertAction
}
