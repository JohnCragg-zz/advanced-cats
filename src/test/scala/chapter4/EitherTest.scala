package chapter4

import org.scalatest.{FunSuite, Matchers}
import cats.syntax.either._

class EitherTest extends FunSuite with Matchers {

  val e1: Either[String, Int] = Right(123)
  val e2: Either[String, Int] = Right(312)

  test("Eithers needs a left or right projection to be able to use flatmap") {
    e1.right.flatMap(x => Right(x * 2)) shouldBe Right(246)
    e2.left.flatMap(x => Left(x + "!!!")) shouldBe Right(321)
  }

  test("in scala 2.11 to use eithers in a for comprehension we needed to cast") {
    val t = for {
      a <- e1.right
      b <- e2.right
    } yield a + b
    t shouldBe Right(444)
  }

  test("we can create instances of left and right directly using cats either") {
    val b = 4.asRight[String]
    val a = 3.asRight[String]
    for {
      x <- a
      y <- b
    } yield x * x + y * y

  }

  test("we can use the asRight method to force the compiler to chose the correct type for the accumulator") {
    countPositives(List(1, 2, 3)) shouldBe Right(3)
    countPositives(List(1, -2, 3)) shouldBe Left("Negative. Stopping!")
  }

  private def countPositives(nums: List[Int]) = nums.foldLeft(0.asRight[String]) {
    (acc, num) => if (num > 0) acc.map(_ + 1) else Left("Negative. Stopping!")
  }

//  test("we can turn data types into eithers") {
//    Either.catchOnly[NumberFormatException]("foo".toInt)
//    Either.catchNonFatal(sys.error("Badness"))
//    Either.fromTry(scala.util.Try("foo".toInt)) shouldBe Left(new NumberFormatException)
//    Either.fromOption[String, Int](None, "Badness") shouldBe None
//  }

}
