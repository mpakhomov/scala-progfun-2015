package fundesign.week3

import scala.annotation.tailrec

/**
  * @author mpakhomov
  * @since 5/31/2016
  */
abstract class Simulation {

  type Action = () => Unit

  case class Event(time: Int, action: Action)

  private var curtime = 0
  def currentTime: Int = curtime

  private type Agenda = List[Event]
  // we keep the list sorted by item.time in ascending order
  private var agenda: Agenda = List()

  // TODO: may I write block: () = > instead ?
  def afterDelay(delay: Int)(block: => Unit): Unit = {
    val item = Event(curtime + delay, () => block)
    agenda = insert(agenda, item)
  }

  private def insert(agenda: Agenda, item: Event): Agenda = agenda match {
    case first :: rest if first.time <= item.time => first :: insert(rest, item)
    case _ => item :: agenda
  }

  @tailrec
  private def loop(): Unit = agenda match {
    case first :: rest =>
      agenda = rest
      curtime = first.time
      first.action()
      loop()
    case Nil =>
  }

  def run(): Unit = {
    afterDelay(0) {
      println(s"*** simulation started, time = ${currentTime} ***")
    }
    loop
  }



}