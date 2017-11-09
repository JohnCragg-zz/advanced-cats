package chapter1

import cats.Eq
import cats.instances.int._
import cats.syntax.option._
import org.scalatest.{FunSuite, Matchers}


class EqualTest extends FunSuite with Matchers {
  val eqInt = Eq[Int]
  test("cats equals works") {
    eqInt.eqv(123, 123) shouldBe true
    eqInt.eqv(234, 123) shouldBe false
  }

  test("cats equals syntax works") {
    123 === 123 shouldBe true
    import cats.syntax.eq._
    123 =!= 234 shouldBe true
  }

  test("cats equals works for options") {
    1.some === None shouldBe false
  }

  test("cats equal method") {
    val cat1 = Cat("Garfield", 35, "orange and black")
    val cat2 = Cat("Heathcliff", 30, "orange and black")
    cat1 === cat2 shouldBe false
  }
}
