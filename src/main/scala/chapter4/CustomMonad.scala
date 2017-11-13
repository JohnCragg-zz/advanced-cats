package chapter4

import cats.Monad

import scala.annotation.tailrec

object CustomMonad {

  val optionMonad = new Monad[Option] {
    def flatMap[A, B](oa: Option[A])(f: A => Option[B]): Option[B] = oa flatMap f

    @tailrec
    def tailRecM[A, B](a: A)(f: A => Option[Either[A, B]]): Option[B] = f(a) match {
      case None => None
      case Some(Left(a1)) => tailRecM(a1)(f)
      case Some(Right(b)) => Some(b)
    }

    def pure[A](x: A): Option[A] = Some(x)
  }

}
