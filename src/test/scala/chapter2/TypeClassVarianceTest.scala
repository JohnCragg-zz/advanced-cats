package chapter2

import org.scalatest.{FunSuite, Matchers}
import cats.instances.option._
import cats.syntax.option._

class TypeClassVarianceTest extends FunSuite with Matchers {
  test("options use smart constructors") {
    Some(1).getClass shouldBe Some
    None.getClass shouldBe None
    1.some.getClass shouldBe Option
    none[Int] shouldBe None
  }
}
