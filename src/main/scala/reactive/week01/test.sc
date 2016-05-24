val f: PartialFunction[String, String] = {case "Ping" => "Pong"}

f.isDefinedAt("Ping")
f.isDefinedAt("Pong")

val g: PartialFunction[List[Int], String] = {
  case Nil => "one"
  case x :: rest =>
    // it will not be checked by isDefined. isDefined checks only
    // the outermost case
    rest match {
      case Nil => "two"
    }
}

// it's defined
g.isDefinedAt(List(1, 2, 3))
// but throws an exception at runtime, because isDefined checks only
// the outermost case (x :: rest) and skip inner cases (rest match ...)
g(List(1, 2, 3))