package chapter4

import cats.data.State

object StepStates {

  val step1 = State[Int, String] {
    num =>
      val ans = num + 1
      (ans, s"Result of step1: $ans")
  }

  val step2 = State[Int, String] {
    num =>
      val ans = num * 2
      (ans, s"Result of step2: $ans")
  }

  val both = for {
    a <- step1
    b <- step2
  } yield (a, b)

}
