package chapter1

import org.scalatest.{FunSuite, Matchers}

class JsonTest extends FunSuite with Matchers {

  import chapter1.JsonSyntax._
  import chapter1.JsonWriterInstances._

  val dave = Person("Dave", "dave@example.com")
  val daveToJson = JsObject(
    Map(
      "name" -> JsString("Dave"),
      "email" -> JsString("dave@example.com")
    )
  )


  test("we can import type class instances") {
    Json.toJson(dave) shouldBe daveToJson
  }

  test("we can use extension methods to extend existing types with interface methods") {
    dave.toJson shouldBe daveToJson
  }

}
