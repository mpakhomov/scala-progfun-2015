import fundesign.week2.Pouring

val problem = new Pouring(Vector(4, 9))
problem.moves

//problem.pathSets.take(3).toList

val target = 6
val path = problem.solution(target)(0)

path.history mkString "\n"


