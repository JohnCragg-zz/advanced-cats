package chapter3

import chapter1.Printable
import org.scalatest.{FunSuite, Matchers}

class PrintablesTest extends FunSuite with Matchers {

  import chapter1.PrintableInstances._

  test("can use contramap to format a box"){
  Printable.format(Box("hello")) shouldBe "hello"
  Printable.format(Box(true)) shouldBe "yes"
  }

}
