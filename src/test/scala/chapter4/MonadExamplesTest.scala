package chapter4

import org.scalatest.{FunSuite, Matchers}
import cats.Monad
import cats.instances.option._
import cats.instances.list._


class MonadExamplesTest extends FunSuite with Matchers {

  val opt1 = Monad[Option].pure(3)
  val opt2 = Monad[Option].flatMap(opt1)(a => Some(a + 2))
  val opt3 = Monad[Option].map(opt2)(a => 100 * a)
  test("cats option monad has a pure method from Applicative") {
    opt1 shouldBe Some(3)
  }

  test("cats option monad has a flatmap method from FlatMap") {
    opt2 shouldBe Some(5)
  }

  test("cats option monad jas a map method from Functor") {
    opt3 shouldBe Some(500)
  }

  val list1 = Monad[List].pure(3)
  val list2 = Monad[List].flatMap(List(1, 2, 3))(x => List(x, x * 10))
  val list3 = Monad[List].map(list2)(_ + 123)

  test("cats list monad has a pure method from Applicative") {
    list1 shouldBe List(3)
  }

  test("cats list monad has a flatmap method from FlatMap") {
    list2 shouldBe List(1, 10, 2, 20, 3, 30)
  }

  test("cats list monad jas a map method from Functor") {
    list3 shouldBe List(124, 133, 125, 143, 126, 153)
  }


}
