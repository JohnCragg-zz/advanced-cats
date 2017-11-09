package chapter4

object IdentityMonad {

  import scala.language.higherKinds
  import cats.Monad
  import cats.syntax.functor._
  import cats.syntax.flatMap._
  import cats.Id

  def sumSquare[M[_]: Monad](a : M[Int], b : M[Int]) : M[Int] =  for {
    x <-a
    y <- b
  } yield x*x + y*y


    def pure[A](value : A): Id[A] = value
    def map[A, B](fa : Id[A])(f : A => B) : Id[B] = f(fa)
    def flatMap[A, B](fa : Id[A])(f : A => Id[B]) = f(fa)


}
