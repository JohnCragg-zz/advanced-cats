package chapter4

import cats.syntax.flatMap._
import chapter3.{BinaryTreeFunctor, Branch, Leaf, Tree}
import org.scalatest.FunSuite

class TreeMonadTest extends FunSuite {

  val b = BinaryTreeFunctor

  import b._

  val tm = TreeMonad

  import tm._

  test("we can use our monad to flatmap and map") {
    val input: Tree[Int] = branch(leaf(100),
      leaf(200)).flatMap(x => branch(leaf(x - 1),
      leaf(x + 1)))
    val output: Tree[Int] = Branch(Branch(Leaf(99), Leaf(101)), Branch(Leaf(199), Leaf(201)))

    //    input should be output
  }

}
