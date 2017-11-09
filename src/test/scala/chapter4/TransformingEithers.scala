package chapter4

import org.scalatest.{FunSuite, Matchers}

class TransformingEithers extends FunSuite with Matchers {

  import cats.syntax.either._

  test("we can use orElse and getOrElse to extract values from the right side") {
    "Error".asLeft[Int].getOrElse(0) shouldBe 0
    "Error".asLeft[Int].orElse(2.asRight[String]) shouldBe Right(2)
  }

  test("we can use the ensure method to check whether a wrapped value satisfies a predicate") {
    (-1).asRight[String].ensure("Must be non-negative")(_ > 0) shouldBe Left("Must be non-negative")
  }

  test("recover and recover with methods provide similar error handling to their namesakes on future") {
    "error".asLeft[String] recover {
      case str: String =>
        "Recovered from " + str
    } shouldBe Right("Recovered from error")

    "error".asLeft[String] recoverWith {
      case str: String => Right("Recovered from " + str)
    } shouldBe Right("Recovered from error")
  }

  test("leftMap and biMap methods are used to comlement map") {
    "foo".asLeft[Int].leftMap(_.reverse) shouldBe Left("oof")
    6.asRight[String].bimap(_.reverse, _ * 7) shouldBe Right(42)
    "bar".asLeft[Int].bimap(_.reverse, _ * 7) shouldBe Left("rab")
  }

  test("swap method allows us to exchange right for left") {
    val x =123.asRight[String]
    x shouldBe Right(123)
    x.swap shouldBe Left(123)
  }

}
