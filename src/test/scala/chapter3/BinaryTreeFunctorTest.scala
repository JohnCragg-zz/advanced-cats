package chapter3

import org.scalatest.{FunSuite, Matchers}

class BinaryTreeFunctorTest extends FunSuite with Matchers {

  val binaryTreeFunctor = BinaryTreeFunctor

  val func = (x: Int) => x * 2

  test("binary tree functor works on a leaf") {
    val tree = Leaf(1)
    binaryTreeFunctor.binaryTreeFunctor.map(tree)(func) shouldBe Leaf(2)
  }

  test("binary tree functor works on a branch with two leaves"){
    val tree = Branch(Leaf(1), Leaf(2))
    binaryTreeFunctor.binaryTreeFunctor.map(tree)(func) shouldBe Branch(Leaf(2), Leaf(4))
  }

  test("binary tree functor works on a branch with four leaves"){
    val tree = Branch(Branch(Leaf(1), Leaf(2)), Branch(Leaf(3), Leaf(4)))
    binaryTreeFunctor.binaryTreeFunctor.map(tree)(func) shouldBe
      Branch(Branch(Leaf(2), Leaf(4)), Branch(Leaf(6), Leaf(8)))
  }
}
