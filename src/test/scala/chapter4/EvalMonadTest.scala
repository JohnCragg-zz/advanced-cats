package chapter4

import cats.{Always, Later, Now}
import org.scalatest.{FunSuite, Matchers}

class EvalMonadTest extends FunSuite with Matchers {

  import cats.Eval

  test("Eval types") {
    val now = Eval.now(1 + 2)
    now.value shouldBe 3

    val later = Eval.later(3 + 4)
    later.value shouldBe 7

    val always = Eval.always(5 + 6)
    always.value shouldBe 11
  }

  test("scala vals are eager definitions and are memoized") {
    val x = {
      println("Computing X")
      1 + 1
    }
    //Prints Computing x

    x shouldBe 2

    x shouldBe 2
  }

  test("scala defs are lazy and not memoized") {
    def y = {
      println("Computing Y")
      1 + 1
    }

    y shouldBe 2
    //Prints Computing Y
    y shouldBe 2
    //Prints Computing Y
  }

  test("scala lazy vals are lazy and memoized") {
    lazy val z = {
      println("Computing Z")
      1 + 1
    }

    z shouldBe 2
    //Prints Computing z
    z shouldBe 2

  }
}
