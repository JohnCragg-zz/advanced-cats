package chapter4

import cats.Monad
import org.scalatest.{FunSuite, Matchers}

import scala.concurrent.Await

class IdentityMonadTest extends FunSuite with Matchers {

  import cats.Id
  import IdentityMonad._

  import cats.syntax.flatMap._
  import cats.syntax.functor._


  val p = Monad[Id].pure(3)
  val b = Monad[Id].flatMap(p)(_ + 1)

  test("we can call our sum squares method on monadic and non monadic values using the identity monad") {
    sumSquare(3: Id[Int], 4: Id[Int]) shouldBe 25
  }

  test("Id is a type alias that turns an atomic type into a single parameter type constructor") {

    p shouldBe 3


    b shouldBe 4


    val c = for {
      x <- p
      y <- b
    } yield x + y

    c shouldBe 7
  }

  test("we can use id for generic methods, we can run code asynchronously in production using future and" +
    "synchronously in test using ID") {

    import scala.concurrent._
    import scala.concurrent.duration._
    import scala.concurrent.ExecutionContext.Implicits.global
    import cats.instances.future._
    //production
    Await.result(sumSquare(Future(3), Future(4)), 1.second) shouldBe 25

    // In test
    sumSquare(p, b) shouldBe 25
  }

}
