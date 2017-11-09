package chapter1

import cats.Eq
import cats.instances.int._
import cats.instances.string._
import cats.syntax.eq._

final case class Cat(name: String, age: Int, colour: String) {

  implicit val catEqual: Eq[Cat] = Eq.instance[Cat] {
    (cat1, cat2) =>
      (cat1.name === cat2.name) && (cat1.age === cat2.age) && (cat1.colour === cat2.colour)
  }

}