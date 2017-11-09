package chapter4

import org.scalatest.{FunSuite, Matchers}

class OurFirstMonadOptionTest extends FunSuite with Matchers {

  val ourFirstMonadOption = new OurFirstMonadOption

  import ourFirstMonadOption._

  test("flat map on Option fails fast") {
    stringDivideBy1("6", "2") shouldBe Some(3)
    stringDivideBy1("6", "0") shouldBe None
    stringDivideBy1("6", "foo") shouldBe None
    stringDivideBy1("bar", "2") shouldBe None
  }

  test("flat map on Option fails fast") {
    stringDivideBy("6", "2") shouldBe Some(3)
    stringDivideBy("6", "0") shouldBe None
    stringDivideBy("6", "foo") shouldBe None
    stringDivideBy("bar", "2") shouldBe None
  }

}
