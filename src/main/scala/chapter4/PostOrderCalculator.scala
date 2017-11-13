package chapter4

import cats.data.State
import cats.syntax.applicative._

object PostOrderCalculator {

  type CalcState[A] = State[List[Int], A]

  def evalOne(sym: String): CalcState[Int] = sym match {
    case "+" => evalF(_ + _)
    case "*" => evalF(_ * _)
    case "-" => evalF(_ - _)
    case "/" => evalF(_ / _)
    case num => eval(num.toInt)
  }

  def eval(i: Int): CalcState[Int] = State[List[Int], Int] {
    stack => (i :: stack, i)
  }

  def evalF(function: (Int, Int) => Int): CalcState[Int] = State[List[Int], Int] {
    {
      case a :: b :: Nil =>
        val ans = function(a, b)
        (ans :: Nil, ans)
      case _ => sys.error("Fail!")
    }
  }

  def calculate(input: List[String]): CalcState[Int] = {
    input.foldLeft(0.pure[CalcState]) {
      (a, b) => a flatMap (_ => evalOne(b))
    }
  }
}
