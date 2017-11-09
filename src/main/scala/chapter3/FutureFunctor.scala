package chapter3

import cats.Functor

import scala.concurrent.{ExecutionContext, Future}

class FutureFunctor {

  implicit def futureFunctor(implicit ec: ExecutionContext) = new Functor[Future] {
    def map[A, B](value: Future[A])(func: A => B): Future[B] = value.map(func)
  }

}
