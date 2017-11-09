package chapter4

import chapter4.RepresentingErrors.{LoginError, LoginResult, User, UserNotFound}
import org.scalatest.{FunSuite, Matchers}
import cats.syntax.either._

class RepresentingErrorsTest extends FunSuite with Matchers {

  val result1: LoginResult = User("dave", "passw0rd").asRight
  val result2: LoginResult = UserNotFound("dave").asLeft

  import RepresentingErrors._

  test("can handle results") {
    result1.fold(handleError, println) shouldBe()
    // User(dave,passw0rd)
    result2.fold(handleError, println) shouldBe()
    // User not found: dave
  }

}
