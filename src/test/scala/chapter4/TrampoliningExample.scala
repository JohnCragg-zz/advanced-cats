package chapter4

import cats.Eval
import org.scalatest.{FunSuite, Matchers}

class TrampoliningExample extends FunSuite with Matchers {

  def stackOverFlowFactorial(n: BigInt): BigInt = if (n == 1) n else n * stackOverFlowFactorial(n - 1)

  def sOFFactorialWithEval(n: BigInt): Eval[BigInt] = if (n == 1) Eval.now(n) else sOFFactorialWithEval(n - 1).map(_ * n)

  def factorial(n: BigInt): Eval[BigInt] =
    if (n == 1) Eval.now(n) else Eval.defer(factorial(n - 1).map(_ * n))

  test("we can avoid stack overflow by using trampolining") {
    // stackOverFlowFactorial(50000) would generate stack overflow
    // sOFFactorialWithEval(50000) would generate stack overflow
// val expected = BigInt(33473205095971448369154760940714864779127732238104548077301003219901680221)
    factorial(50000).value > 10 shouldBe true
  }

}
