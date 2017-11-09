package chapter3

import scala.language.higherKinds
import cats.functor

trait Invariantz[F[_]] {
  def imap[A, B](fa: F[A])(f: A => B)(g: B => A): F[B]
}

trait Contravariantz[F[_]] extends Invariantz[F] {
  def contramap[A, B](fa: F[A])(f: B => A): F[B]

  def imap[A, B](fa: F[A])(fi: B => A): F[B] = contramap(fa)(fi)
}

trait Functorzz[F[_]] extends Invariantz[F] {
  def map[A, B](fa: F[A])(f: A => B): F[B]

  def imap[A, B](fa: F[A])(f: A => B)(fi: B => A): F[B] = map(fa)(f)
}