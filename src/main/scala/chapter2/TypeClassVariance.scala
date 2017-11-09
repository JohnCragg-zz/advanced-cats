package chapter2

class TypeClassVariance {

  trait Invariant[A] {
    //Given Foo[C] and Foo[B] there is no relationship between B and C
  }

  trait Covariant[+A] {
    // B subtype of C => Foo[C] subtype of Foo[B]
  }

  trait Contravariant[-A] {
    // C supertype of B => Foo[C] subtype of Foo[B]
  }


  sealed trait A

  final case object B extends A

  final case object C extends A


  //////////////////////////////////////////////////////////////////////////
  // Type Class Variance | Invariant | Covariant | Contravariant
  // -------------------------------------------------------------
  // Supertype Instance  | No        | No        | Yes
  // -------------------------------------------------------------
  // More Specific       | Yes       | Yes       | No
  // Type preferred      |           |           |
  //////////////////////////////////////////////////////////////////////////


}
