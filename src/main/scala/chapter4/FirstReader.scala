package chapter4

object FirstReader {

  import cats.data.Reader

  case class Dog(name: String, favouriteFood: String)

  val dogName: Reader[Dog, String] = Reader(dog => dog.name)

  val sayHello: Reader[Dog, String] = dogName.map(name => s"Hello ${name}")

  val feedDog: Reader[Dog, String] = Reader(dog => s"Have a nice bowl of ${dog.favouriteFood}")

  val sayHelloAndFeed: Reader[Dog, String] = for {
    msg1 <- sayHello
    msg2 <- feedDog
  } yield s"$msg1 $msg2"


}
