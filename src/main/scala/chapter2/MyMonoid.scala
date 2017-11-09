package chapter2

trait MyMonoid[A] extends MySemigroup[A] {

  def empty: A

}

object MyMonoid {
  def apply[A](implicit monoid: MyMonoid[A]) = monoid

  implicit def setUnionMonoid[A]: MyMonoid[Set[A]] = {

    new MyMonoid[Set[A]] {
      override def empty: Set[A] = Set.empty[A]

      override def combine(x: Set[A], y: Set[A]): Set[A] = x union y
    }
  }


  implicit val intMonoid: MyMonoid[Int] = {
    new MyMonoid[Int] {
      override def empty: Int = 0

      override def combine(x: Int, y: Int): Int = x + y
    }
  }
}

