package chapter4

import cats.Monad
import chapter3.{Branch, Leaf, Tree}

import scala.annotation.tailrec

object TreeMonad {

  implicit val treeMonad = new Monad[Tree] {

    def flatMap[A, B](fa: Tree[A])(f: A => Tree[B]) = fa match {
      case Branch(l: Tree[A], r: Tree[A]) =>
        Branch(flatMap(l)(f): Tree[B], flatMap(r)(f): Tree[B])
      case Leaf(x) => f(x)
    }

    @tailrec
    def tailRecM[A, B](arg: A)(func: A => Tree[Either[A, B]]): Tree[B] = func(arg) match {
      case Branch(l, r) =>
        Branch(
          flatMap(l) {
            case Left(l)
            => tailRecM(l)(func)
            case Right(l) => pure(l)
          },
          flatMap(r) {
            case Left(r)
            => tailRecM(r)(func)
            case Right(r) => pure(r)
          }
        )
      case Leaf(Left(value)) =>
        tailRecM(value)(func)
      case Leaf(Right(value)) =>
        Leaf(value)
    }

    def pure[A](x: A) = Leaf(x)
  }

}

