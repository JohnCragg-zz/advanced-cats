package chapter1

import cats.syntax.show._
import org.scalatest.{FunSuite, Matchers}

class CatShowTest extends FunSuite with Matchers {

  test("can show a cat type") {
    import chapter1.CatShow.catShow
    val cat = Cat("John", 23, "blue")
    cat.show shouldBe "John is a 23 year-old blue cat"
  }

}
