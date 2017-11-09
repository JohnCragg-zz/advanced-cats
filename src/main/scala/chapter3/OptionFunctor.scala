package chapter3

import cats.Functor

class OptionFunctor {

  implicit val optionFunctor = new Functor[Option]{
    override def map[A, B](fa: Option[A])(f: (A) => B) =
      fa.map(f)
  }

}
