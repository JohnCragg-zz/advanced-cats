package chapter3

import cats.Functor
import cats.instances.list._
import cats.instances.option._
import org.scalatest.{FunSuite, Matchers}

class FunctorzTest extends FunSuite with Matchers {


  test("we can obtain instances using the standard Functor.apply") {

    val list1 = List(1, 2, 3)
    val list2 = Functor[List].map(list1)(_ * 2)
    list2 shouldBe List(2, 4, 6)

    val option1 = Option(123)
    val option2 = Functor[Option].map(option1)(_.toString)
    option2 shouldBe Option("123")

  }

  test("we can lift functions") {
    val func = (x: Int) => x + 1
    val lifted = Functor[Option].lift(func)
    lifted(Option(1)) shouldBe Some(2)
  }
}
