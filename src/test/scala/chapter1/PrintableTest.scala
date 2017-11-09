package chapter1

import org.scalatest.{FunSuite, Matchers}

class PrintableTest extends FunSuite with Matchers {

  import chapter1.PrintableInstances._
  import chapter1.PrintableSyntax._

  val cat = Cat("John", 23, "blue")
  val expected = "John is a 23 year-old blue cat"
  test("cat printable works") {

    Printable.format(cat) shouldBe expected
  }

  test("can use printable syntax to print out a cat class") {
    cat.format shouldBe expected
  }

}
