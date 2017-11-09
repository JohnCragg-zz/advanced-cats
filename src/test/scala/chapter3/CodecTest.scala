package chapter3

import org.scalatest.{FunSuite, Matchers}

class CodecTest extends FunSuite with Matchers {

  import Codec._

  test("can parse and serialize ints"){
    encode(1) shouldBe "1"
    decode[Int]("1") shouldBe Option(1)
  }

  test("can parse and serialize boxes"){
    encode(Box(123)) shouldBe "123"
    decode[Box[Int]]("123") shouldBe Option(Box(123))
  }


}
