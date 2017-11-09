package chapter3

import org.scalatest.{FunSuite, Matchers}
import cats.instances.function._
import cats.syntax.functor._

class FunctorSyntaxTest extends FunSuite with Matchers {
  val func1 = (a: Int) => a + 1
  val func2 = (a: Int) => a * 2
  val func3 : Int => Int = func1.map(func2)

  func3(123) shouldBe 248
}
