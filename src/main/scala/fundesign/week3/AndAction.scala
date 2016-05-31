package fundesign.week3

/**
  * @author mpakhomov
  * @since 5/31/16
  */
def andGate(in1: Wire, in2: Wire, output: Wire): Unit = {
  def andAction(): Unit = {
    val in1Sig = in1.getSignal
    val in2Sig = in2.getSignal
    afterDelay(AndGateDelay) {
      output setSignal (in1Sig & in2Sig)
    }
  }
  in1 addAction andAction
  in2 addAction andAction
}