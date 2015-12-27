package reactive.week02

import frp.Var

/**
  * @author mpakhomov
  * @since 12/22/15
  */
class BankAccount {
  val balance = Var(0)

  def deposit(amount: Int): Unit = {
    if (amount > 0) {
      val b = balance()
      balance() = b + amount
    }
  }

  def withdraw(amount: Int): Int = 
    if (0 < amount && amount <= balance()) {
      val b = balance()
      balance() = b - amount
      balance()
    } else throw new Error("Insufficient funds")

}
