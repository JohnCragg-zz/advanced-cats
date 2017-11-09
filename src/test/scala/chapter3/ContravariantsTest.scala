package chapter3

import org.scalatest.{FunSuite, Matchers}
import cats.instances.function._
import cats.syntax.contravariant._
import chapter3Some.Contravariants

class ContravariantsTest extends FunSuite with Matchers {

  val obj = new Contravariants

  import obj._

  test("we can summon instances of contravariant using the apply method") {
    showSymbol.show('dave) shouldBe "dave"
  }
  test("we can use contramap extension methods") {
    div2.contramap(add1) shouldBe 1.5
  }
}
