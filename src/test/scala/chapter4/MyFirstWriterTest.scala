package chapter4

import cats.Id
import cats.data.{Writer, WriterT}
import cats.instances.vector._
import cats.syntax.applicative._
import cats.syntax.writer._
import org.scalatest.{FunSuite, Matchers} // this gives use the 'pure' method

class MyFirstWriterTest extends FunSuite with Matchers {
  test("A writer carries two values, a log of type W and a result of type A") {

    val myVector = Vector("It was the best of times", "It was the worst of times")

    //    Writer(myVector, 123) shouldBe new WriterT((myVector, 123))
    //    The type of the writer reported on the console is actually WriterT[Id, Vector[String], Int]
    //    WriterT is an example of a new concept called a monad transformer
  }

  type Writer[W, A] = WriterT[Id, W, A]
  type Logged[A] = Writer[Vector[String], A]

  test("Cats provides a way of creating writers specifying only the log or the result") {

    //    123.pure[Logged] shouldBe WriterT(new Id[Int], (Vector.empty[String], 123))
  }

  test("If we have a log and no result we can create a Writer[Unit] using the tell syntax from cats writer") {
    //    Vector("msg1", "msg2", "msg3").tell shouldBe new WriterT((Vector("msg1", "msg2", "msg3"), ()))
  }


  test("If we have both a result and a log, in addition to using WRiter.apply as we did above we can use" +
    "the writer syntax from cats.syntax.writer") {
    import cats.syntax.writer._
    val vec = Vector("msg1", "msg2", "msg3")
    val a = Writer(vec, 123)
    val b = 123.writer(vec)
    a.value shouldBe 123
    a.written shouldBe vec
    val (log, result) = b.run
    log shouldBe vec
    result shouldBe 123
  }

  val writer1 = for {
    a <- 10.pure[Logged]
    _ <- Vector("a", "b", "c").tell
    b <- 32.writer(Vector("x", "y", "z"))
  } yield a + b

  test("the log in a writer is preserved when we map or flatmap over it and" +
    "the flatmap appends the logs from the source writer and then the results" +
    "of the users sequencing function") {
    writer1.run shouldBe(Vector("a", "b", "c", "x", "y", "z"), 42)
  }

  val writer2 = writer1.mapWritten(_.map(_.toUpperCase))
  test("In addition to transforming the result with map and flatmap we can trasnform the log in a " +
    "writer with mapWritten method") {
    writer2.run shouldBe(Vector("A", "B", "C", "X", "Y", "Z"), 42)
  }

  val writer3 = writer1.bimap(
    log => log.map(_.toUpperCase),
    result => result * 100
  )

  test("we can transform the log and the result simultaneously using the bimap") {
    writer3.run shouldBe(Vector("A", "B", "C", "X", "Y", "Z"), 4200)
  }

  test("we can clear the log with the reset method") {
    val writer5 = writer1.reset
    writer5.run shouldBe(Vector(), 42)
  }


  test("we can swap logs with the swap method") {
    val writer6 = writer1.swap
    writer6.run shouldBe (42, Vector("a", "b", "c", "x" , "y", "z"))
  }


}
