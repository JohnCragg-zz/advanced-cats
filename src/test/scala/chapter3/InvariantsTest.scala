package chapter3

import org.scalatest.{FunSuite, Matchers}
import cats.syntax.semigroup._

class InvariantsTest extends FunSuite with Matchers{

val i = new Invariants
  import i._

  test("can use our symbol semigroup to add symbols"){
    'a |+| 'few |+| 'words shouldEqual 'afewwords
  }
}
