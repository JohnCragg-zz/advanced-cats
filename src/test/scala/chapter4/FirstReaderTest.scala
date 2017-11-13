package chapter4

import org.scalatest.{FunSuite, Matchers}

class FirstReaderTest extends FunSuite with Matchers {

  val r = FirstReader

  import r._

  val g = Dog("Garfield", "lasagne")
  val h = Dog("Heathcliff", "junk food")

  test("we can extract the function again using the readers run method and call it using apply as usual") {
    dogName.run(g) shouldBe "Garfield"
  }

  test("the map method extends a computation by passing through its result as a function"){
    sayHello.run(h) shouldBe "Hello Heathcliff"
  }

  test("flat map allows us to combine multiple readers that depend on the same input type"){
    sayHelloAndFeed(g) shouldBe "Hello Garfield Have a nice bowl of lasagne"
    sayHelloAndFeed(h) shouldBe "Hello Heathcliff Have a nice bowl of junk food"
  }

}
