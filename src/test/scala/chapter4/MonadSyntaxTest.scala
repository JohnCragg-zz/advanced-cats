package chapter4

import cats.Monad
import cats.implicits._
import org.scalatest.{FunSuite, Matchers}

import scala.language.higherKinds

class MonadSyntaxTest extends FunSuite with Matchers {

  test("we can use the pure method to create instances of a monad") {
    import cats.instances.list._
    import cats.instances.option._
    import cats.syntax.applicative._

    1.pure[Option] shouldBe Some(1)
    1.pure[List] shouldBe List(1)
  }


  //  def sumSquare[M[_] : Monad](a: M[Int], b: M[Int]): M[Int] = a.flatMap(x => b.map(y => x * x + y * y))
  //
  test("can abstract the sum squares method over various monadic representations") {
    sumSquares(Option(3), Option(4)) shouldBe Some(25)
    sumSquares(List(1, 2, 3), List(4, 5)) shouldBe List(17, 26, 20, 29, 25, 34)
  }

  //
  //
  def sumSquares[M[_] : Monad](a: M[Int], b: M[Int]): M[Int] = for {
    x <- a
    y <- b
  } yield x * x + y * y

}
