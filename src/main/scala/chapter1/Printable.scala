package chapter1

import chapter3.Box

trait Printable[A] {
  def format(value: A): String

  def contramap[B](func: B => A) : Printable[B] = {
    val self = this
    new Printable[B] {
      def format(value: B): String = self.format(func(value))
    }
  }
}

object PrintableInstances {

  implicit val stringPrintable = new Printable[String] {
    def format(value: String): String = value
  }

  implicit val intPrintable = new Printable[Int] {
    def format(value: Int): String = value.toString
  }

  implicit val catPrintable = new Printable[Cat] {
    def format(cat: Cat) = {
      val name = Printable.format(cat.name)
      val age = Printable.format(cat.age)
      val color = Printable.format(cat.colour)
      s"$name is a $age year-old $color cat"
    }
  }

  implicit val booleanPrintable =
    new Printable[Boolean] {
      def format(value: Boolean): String =
        if(value) "yes" else "no"
    }

  implicit def boxPrintable[A](implicit p: Printable[A]) = p.contramap[Box[A]](_.value)

}

object Printable {
  def format[A](value: A)(implicit printable: Printable[A]): String = printable.format(value)

  def print[A](value: A)(implicit printable: Printable[A]): Unit = println(format(value))
}

object PrintableSyntax {

  implicit class PrintOps[A](value : A) {
    def format(implicit p: Printable[A]): String = p.format(value)

    def print(implicit p: Printable[A]): Unit = println(format)
  }

}
