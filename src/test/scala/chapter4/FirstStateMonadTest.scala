package chapter4

import cats.data.State
import org.scalatest.{FunSuite, Matchers}

class FirstStateMonadTest extends FunSuite with Matchers {

  val s = FirstStateMonad

  import s._

  test("we can run our monads by supplying an inital state using the run method") {
    val (state, result) = b.run(10).value
    state shouldBe 10
    result shouldBe "The state is 10"
  }


  test("we can run our monads by supplying an inital state using the runS method") {
    val state = b.runS(10).value
    state shouldBe 10
  }


  test("we can run our monads by supplying an inital state using the runA method") {
    val result = b.runA(10).value
    result shouldBe "The state is 10"
  }
  //Cats provides several convenience constructors for creating primitive steps:
  test("get extracts the state of the result") {
    val getDemo = State.get[Int]
    getDemo.run(10).value shouldBe(10, 10)
  }

  test("set updates the state and returns unit as the result") {
    val setDemo = State.set[Int](30)
    setDemo.run(10).value shouldBe(30, ())
  }

  test("pure ignores the state and returns a supplied result") {
    val pureDemo = State.pure[Int, String]("result")
    pureDemo.run(10).value shouldBe(10, "result")
  }

  test("inspect extracts the state via a transformation function") {
    val inspectDemo = State.inspect[Int, String](_ + "!")
    inspectDemo.run(10).value shouldBe(10, "10!")
  }

  test("modify updates the state using an update function") {
    val modifyDemo = State.modify[Int](_ + 1)
    modifyDemo.run(10).value shouldBe(11, ())
  }

  test("we can assemble building blocks into useful computations" +
    "and often ignore the results of intermediate stages when they only" +
    "represent transformations of state") {
    val (state, result) = program.run(1).value
    state shouldBe 3
    result shouldBe(1, 2, 3000)
  }
}
