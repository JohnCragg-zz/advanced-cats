package chapter4

import cats.Monad
import org.scalatest.{FunSuite, Matchers}

import scala.concurrent._
import scala.concurrent.duration._

class MoreMonadExamplesTest extends FunSuite with Matchers {

  test("Cats provides instances for option") {
    import cats.instances.option._
    Monad[Option].flatMap(Option(1))(x => Option(x * 2)) shouldBe Some(2)
  }

  test("Cats provides instances for list") {
    import cats.instances.list._
    Monad[List].flatMap(List(1, 2, 3))(x => List(x, x * 10)) shouldBe List(1, 10, 2, 20, 3, 30)
  }

  test("Cats provides instances for vectors") {
    import cats.instances.vector._
    Monad[Vector].flatMap(Vector(1, 2, 3))(x => Vector(x, x * 10)) shouldBe Vector(1, 10, 2, 20, 3, 30)
  }

  test("Cats requires us to have an ExecutionContext in scope when we summon monad for future"){
    import scala.concurrent.ExecutionContext.Implicits.global
    import cats.instances.future._

    val fm = Monad[Future]

    //The monad instances uses the captured ExecutionCntect for subsequent calls to pure and flatmap

    Await.result(
      fm.flatMap(fm.pure(1)){
        x => fm.pure(x+2)
      },
      1.second
    ) shouldBe 3
  }

}
