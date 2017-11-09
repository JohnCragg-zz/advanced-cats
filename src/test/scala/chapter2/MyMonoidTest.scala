package chapter2

import org.scalatest.{FunSuite, Matchers}

class MyMonoidTest extends FunSuite with Matchers {

  test("can combine the int and set union monoids") {
    val intSetUnionMonoid = MyMonoid[Set[Int]]
    intSetUnionMonoid.combine(Set(1, 2), Set(2, 3)) shouldBe Set(1, 2, 3)
  }

  def associativeLaw[A](x: A, y: A, z: A)(implicit m: MyMonoid[A]): Boolean =
    m.combine(x, m.combine(y, z)) == m.combine(m.combine(x, y), z)

  def identityLaw[A](x: A)(implicit m: MyMonoid[A]): Boolean =
    (m.combine(x, m.empty) == x) && (m.combine(m.empty, x) == x)
}
