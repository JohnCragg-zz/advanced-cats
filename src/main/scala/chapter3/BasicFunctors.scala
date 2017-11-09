package chapter3

import scala.language.higherKinds

class BasicFunctors {


  //A functor is anything with a defined map method eg option future list etc
  //
  //
  // Formally a functor is a type F[A] with an operation map with type (A => B) => F[B]
  //
  //
  //  FUNCTOR LAWS
  //
  //  1. Identity
  //      fa.map(a=>a) ==fa
  //  2. Composition
  //      fa.map(g(f(_)) == fa.map(f).map(g)

  trait Functor[F[_]] {
    def map[A, B](fa: F[A])(f: A => B): F[B]
  }

}
