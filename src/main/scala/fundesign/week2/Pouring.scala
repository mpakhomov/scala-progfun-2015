package fundesign.week2

/**
  * @author mpakhomov
  * @since 5/26/2016
  */
class Pouring(capacity: Vector[Int]) {

  // States
  type State = Vector[Int]
  val initialState = capacity map (_ => 0)

  trait Move {
    def change(state: State): State
  }
  case class Empty(glass: Int) extends Move {
    override def change(state: State): State = state updated (glass, 0)
  }
  case class Fill(glass: Int) extends Move {
    override def change(state: State): State = state updated (glass, capacity(glass))
  }
  case class Pour(from: Int, to: Int) extends Move {
    override def change(state: State): State = {
      val amount = state(from) min (capacity(to) - state(to))
      state updated (from, state(from) - amount) updated (to, state(to) + amount)
    }
  }

  val glasses: Range = 0 until capacity.length

  val moves =
    (for (g <- glasses) yield Empty(g)) ++
    (for (g <- glasses) yield  Fill(g)) ++
    (for (from <- glasses; to <- glasses if from != to) yield Pour(from, to))

  // the last move comes first in the history (LIFO / stack order)
  class Path(val history: List[Move], val endState: State) {
//    def endState: State = history.foldRight(initialState)(_ change _)
//    private def trackState(moves: List[Move]): State = moves match {
//      case Nil => initialState
//      case move :: xs => move change trackState(xs)
//    }
    def extend(move: Move): Path = new Path(move :: history, move change endState)
    override def toString = (history.reverse.mkString(" ")) + " --> " + endState
  }

  val initialPath: Path = new Path(Nil, initialState)

  def from(paths: Set[Path], explored: Set[State]): Stream[Set[Path]] =
    if (paths.isEmpty) Stream.empty
    else {
      val more = for {
        path <- paths
        next <- moves.map(path.extend(_))
        if ! explored.contains(next.endState)
      } yield next
      paths #:: from(more, explored ++ more.map(_.endState))
    }

  val pathSets: Stream[Set[Path]] = from(Set(initialPath), Set(initialState))

  def solution(target: Int): Stream[Path] =
    for {
      pathSet <- pathSets
      path <- pathSet
      if path.endState contains target
    } yield path
}
