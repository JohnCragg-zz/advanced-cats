package chapter2

trait MySemigroup[A] {
  def combine(x: A, y: A): A
}

object MySemigroup {
  def apply[A](implicit s: MySemigroup[A]) = s
}