import scala.annotation.tailrec

//def sum(f: Int => Int, a: Int, b: Int): Int =
//  if (a > b) 0
//  else f(a) + sum(f, a + 1, b)

def sum(f: Int => Int)(a: Int, b: Int): Int =
  mapReduce(f, (x, y) => x + y, 0) (a, b)
//def sum(f: Int => Int)(a: Int, b: Int): Int = {
//  @tailrec
//  def loop(a: Int, acc: Int): Int = {
//    if (a > b) acc
//    else loop(a + 1, acc + f(a))
//  }
//  loop(a, 0)
//}

def product(f: Int => Int)(a: Int, b: Int): Int =
  mapReduce(f, (x, y) => x * y, 1) (a, b)
//def product(f: Int => Int)(a: Int, b: Int): Int = {
//  @tailrec
//  def loop(a: Int, acc: Int): Int = {
//    if (a > b) acc
//    else loop(a + 1, acc * f(a))
//  }
//  loop(a, 1)
//}

def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int) : Int = {
  @tailrec
  def loop(a: Int, acc: Int): Int = {
    if (a > b) acc
    else loop(a + 1, combine(acc, f(a)))
  }
  loop(a, zero)
}


def id(x: Int): Int = x
def cube(x: Int): Int = x * x *x
def fact(x: Int): Int = if (x == 0) 1 else x * fact(x - 1)
def sumInts = sum(x => x)_
def sumCubes = sum(x => x * x * x)_
def sumFactorials = sum(fact)_

def factorial(n: Int): Int = product(x => x)(1, n)

sumInts(1, 10)
sumInts(1, 1)
sumCubes(1, 3)
sumFactorials(1, 3)
sum((x: Int) => x * x * x)(1, 3)
sum(x => x)(1, 10)
product(x => x * x)(3, 4)
factorial(4)
mapReduce(x => x, (a, b) => a + b, 0)(1, 10)
mapReduce(x => x, (a, b) => a * b, 1)(1, 4)
