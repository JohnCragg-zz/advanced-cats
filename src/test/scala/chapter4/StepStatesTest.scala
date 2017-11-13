package chapter4

import org.scalatest.{FunSuite, Matchers}

class StepStatesTest extends FunSuite with Matchers {

  val ss = StepStates

  import ss._

  test("we can thread the state from one instance to another using map and flatmap") {
    val (state, result) = both.run(20).value
    state shouldBe 42
    result shouldBe ("Result of step1: 21", "Result of step2: 42")
  }
}
