import scala.annotation.tailrec

//def sum(f: Int => Int, a: Int, b: Int): Int =
//  if (a > b) 0
//  else f(a) + sum(f, a + 1, b)

def sum(f: Int => Int, a: Int, b: Int): Int = {
  @tailrec
  def loop(a: Int, acc: Int): Int = {
    if (a > b) acc
    else loop(a + 1, acc + f(a))
  }
  loop(a, 0)
}


def id(x: Int): Int = x
def cube(x: Int): Int = x * x *x
def fact(x: Int): Int = if (x == 0) 1 else x * fact(x - 1)

def sumInts(a: Int, b: Int) = sum(id, a, b)
def sumCubes(a: Int, b: Int) = sum(cube, a, b)
def sumFactorials(a: Int, b: Int) = sum(fact, a, b)

sumInts(1, 10)
sumInts(1, 1)
sumCubes(1, 3)
sumFactorials(1, 3)
sum((x: Int) => x * x * x, 1, 3)
sum(x => x, 1, 10)

