/**
  * @author mpakhomov
  * @since 12/28/15
  */
trait MyTrait {
  val foo = 42
  def bar: Int
  var foobar = 1
}

class Subclass extends AnyRef with MyTrait {
  override def bar: Int = foo * 2
}

object MyTraitTest extends App {
  val subclass = new Subclass
  subclass.foobar = 7
  println(s"foo: ${subclass.foo}")
  println(s"bar: ${subclass.bar}")
  println(s"foobar: ${subclass.foobar}")
}