package chapter4

import org.scalatest.{FunSuite, Matchers}

class ListzTest extends FunSuite with Matchers {

  import Listz._

  test("list has a flatmap method") {
    val expected = List((1, 4), (1, 5), (2, 4), (2, 5), (3, 4), (3, 5))
    val actual = for {
      x <- numbersBetween(1, 3)
      y <- numbersBetween(4, 5)
    } yield (x, y)
    actual shouldBe expected
  }

}
