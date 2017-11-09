package chapter3Some

import cats.Show
import cats.functor.Contravariant
import cats.instances.string._

class Contravariants {

  val showString = Show[String]
  val showSymbol = Contravariant[Show].contramap(showString)((sym: Symbol) => s"'${sym.name}")


  val div2 : Int => Double = _ / 2.0
  val add1  : Int => Int = _ + 1


}
