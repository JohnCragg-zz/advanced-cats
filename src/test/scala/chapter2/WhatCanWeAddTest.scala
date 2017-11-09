package chapter2

import org.scalatest.{FunSuite, Matchers}
import cats.Monoid
import cats.instances.all._
import cats.syntax.semigroup._


class WhatCanWeAddTest extends FunSuite with Matchers {

  test("we can add options") {
    Option(1) |+| Option(2) shouldBe Some(3)
  }

  test("we can add maps") {
    Map("a" -> 1, "b" -> 2) |+| Map("b" -> 3, "d" -> 4) shouldBe
      Map("a" -> 1, "b" -> 5, "d" -> 4)
  }

  test("we can add tuples"){
    ("hello",123) |+| ("world", 321) shouldBe ("helloworld", 444)
  }

}
