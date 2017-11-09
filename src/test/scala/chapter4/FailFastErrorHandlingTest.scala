package chapter4

import org.scalatest.{FunSuite, Matchers}
import cats.syntax.either._

class FailFastErrorHandlingTest extends FunSuite with Matchers {

  test("we use either to fail fast") {
    val willFail = for {
      a <- 1.asRight[String]
      b <- 0.asRight[String]
      c <- if (b == 0) "DIV0".asLeft[Int] else (a / b).asRight[String]
    } yield c * 100
    willFail shouldBe Left("DIV0")
  }
}
