package chapter3

import cats.Functor

sealed trait Tree[+A]

final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

final case class Leaf[A](value: A) extends Tree[A]

object BinaryTreeFunctor {

  implicit val binaryTreeFunctor = new Functor[Tree] {
    def map[A, B](tree: Tree[A])(func: A => B): Tree[B] = tree match {
      case Leaf(v: A) => Leaf(func(v))
      case Branch(left: Tree[A], right: Tree[A]) =>
        Branch(map(left)(func), map(right)(func))
    }
  }

  def branch[A](left: Tree[A], right: Tree[A]): Tree[A] = Branch(left, right)
  def leaf[A](value: A) = Leaf(value)

}
