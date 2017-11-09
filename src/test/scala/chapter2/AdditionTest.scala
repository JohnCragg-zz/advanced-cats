package chapter2

import org.scalatest.{FunSuite, Matchers}
import cats.instances.int._
import cats.instances.option._
import Order._

class AdditionTest extends FunSuite with Matchers {
  val adder: Addition.type = Addition
  test("add can sum a list of ints") {
    adder.add(List(1, 2, 3)) shouldBe 6
  }

  test("add can sum a list of options of ints") {
    adder.add(List(Option(1), Option(2), Option(3))) shouldBe Option(6)
  }

  test("add can sum a list containing Nones") {
    adder.add(List(Option(1), None, Option(2), Option(3))) shouldBe Option(6)
  }

  test("add can sum a list of Orders") {
    adder.add(List(Order(1, 1), Order(2, 2), Order(3, 3))) shouldBe Order(6, 6)
  }
}
