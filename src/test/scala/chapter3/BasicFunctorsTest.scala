package chapter3

import org.scalatest.{FunSuite, Matchers}

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import cats.instances.function._
import cats.syntax.functor._

class BasicFunctorsTest extends FunSuite with Matchers {


  test("List is a functor") {
    List(1, 2, 3).map(x => (x % 2) == 0) shouldBe
      List(false, true, false)
  }

  test("map functions can be chained on lists") {
    val test = List(1, 2, 3)
    test.map(_ * 2).map(_ + 4) shouldBe test.map(x => (x * 2) + 4)
  }

  test("maps can be chained on options") {
    val test = Option(123)
    test.map(_ * 2).map(_ + 4) shouldBe test.map(x => (x * 2) + 4)
  }

  test("we can map on futures") {
    val f1 = Future("Hello World")
    val f2 = f1.map(_.length)

    Await.result(f1, 1.second) shouldBe "Hello World"
    Await.result(f2, 1.second) shouldBe 11
  }

  test("mapping is just function composition") {
    val func1: Int => Double = (x: Int) => x.toDouble
    val func2: Double => Double = (y: Double) => y * 2
    val func3: Int => Double = func1.map(func2)
    func3(1) shouldBe func2(func1(1))
  }
}
