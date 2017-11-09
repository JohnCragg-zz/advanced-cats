package chapter4

import scala.language.higherKinds

trait Monadz[F[_]] {
  def pure[A](value: A): F[A]

  def flatMap[A, B](value: F[A])(func: A => F[B]): F[B]

  def map[A, B](value: F[A])(func: A => B): F[B] =
    flatMap(value)(a => pure(func(a)))
}

/////////////////////////////////////////////
// MONADIC LAWS
/////////////////////////////////////////////
//
// 1. Left Identity
//    pure(a).flatMap(f) == f(a)
//
// 2. Right Identity
//    m.flatMap(pure) == m
//
// 3. Associativity
//    m.flatMap(f).flatMap(g) == m.flatMap(x => f(x).flatMap(g))
//
//////////////////////////////////////////////