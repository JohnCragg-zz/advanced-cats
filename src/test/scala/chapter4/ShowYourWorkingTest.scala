package chapter4

import org.scalatest.{FunSuite, Matchers}

import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

class ShowYourWorkingTest extends FunSuite with Matchers {
  val x = ShowYourWorking

  import x._

  test("factorial function prints out the correct answer") {
    // prints out intermediate workings
    badFactorial(5) shouldBe 120
  }

  test("If we start several factorials in parallel, the log messages can be come mixed on standard out") {
    Await.result(
      Future.sequence(
        Vector(
          Future(badFactorial(3)),
          Future(badFactorial(3))
        )
      ), 5.second
    ) shouldBe Vector(6, 6)
  }

  test("factorial can be written so it capture the log messages in a Writer") {
    val Vector((logA, ansA), (logB, ansB)) =
      Await.result(Future.sequence(Vector(
        Future(factorial(5).run),
        Future(factorial(5).run)
      )), 5.seconds)
    logA shouldBe Vector("fact 0 1", "fact 1 1", "fact 2 2", "fact 3 6", "fact 4 24", "fact 5 120")
    ansA shouldBe 120
    logB shouldBe Vector("fact 0 1", "fact 1 1", "fact 2 2", "fact 3 6", "fact 4 24", "fact 5 120")
    ansB shouldBe 120
  }
}
