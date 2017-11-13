package chapter4

import org.scalatest.{FunSuite, Matchers}

class PostOrderCalculatorTest extends FunSuite with Matchers {

  val calculator = PostOrderCalculator

  test("we can add two numbers together") {
    val input = List("1", "2", "+")
    calculator.calculate(input).run(Nil).value shouldBe (List(3), 3)
  }

  test("we can chain a list of operations") {
    val input = List("1", "2", "+", "3", "*")
    calculator.calculate(input).run(Nil).value shouldBe (List(9), 9)
  }

}
