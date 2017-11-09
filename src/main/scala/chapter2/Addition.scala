package chapter2

import cats.Monoid
import cats.syntax.semigroup._

object Addition {

  //  def add(items: List[Int]): Int = items.foldLeft(0)(_ + _)


  def add[A](items: List[A])(implicit monoid: Monoid[A]): A =
    items.foldLeft(Monoid[A].empty)(_ |+| _)

}

case class Order(totalCost: Double, quantity: Double)

case object Order{

  implicit val orderMonoid: Monoid[Order] = new Monoid[Order] {
    override def empty = Order(0L, 0L)

    override def combine(x: Order, y: Order) =
      Order(
        x.totalCost + y.totalCost,
        x.quantity + y.quantity
      )

  }
}


