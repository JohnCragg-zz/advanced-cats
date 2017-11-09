package chapter3

import cats.Semigroup
import cats.instances.string._
import cats.syntax.invariant._

class Invariants {

  implicit val symbolSemiGroup: Semigroup[Symbol] =
    Semigroup[String].imap(Symbol.apply)(_.name)
}
