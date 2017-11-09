package chapter1

import cats.Show
import cats.instances.int._
import cats.instances.string._
import cats.syntax.show._

object CatShow {
  implicit val catShow: Show[Cat] = Show.show[Cat](
    cat => {
      val name = cat.name.show
      val age = cat.age.show
      val color = cat.colour.show
      s"$name is a $age year-old $color cat"
    }
  )
}
