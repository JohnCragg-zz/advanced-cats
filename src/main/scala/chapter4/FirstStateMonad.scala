package chapter4

import cats.data.State
import cats.data.State._

object FirstStateMonad {

  val b = State[Int, String] {
    state => (state, s"The state is $state")
  }

  val program: State[Int, (Int, Int, Int)] = for {
    a <- get[Int]
    _ <- set[Int](a + 1)
    b <- get[Int]
    _ <- modify[Int](_ + 1)
    c <- inspect[Int, Int](_ * 1000)
  } yield (a, b, c)

}
