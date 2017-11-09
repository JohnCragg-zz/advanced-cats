package chapter4

object EvalMonad {

  // Allows us to abstract over different models of evaluation
  // cats.Eval allows us to categorise evaluation into three models depending on
  // whether they are memoized or unmemoized

  // DEF : Memoizing is an optimization technique used to speed up a computer program by storing the results of
  //       of an expensive function and returning the cached result when the same inputs occur again

  ///////////////////////////////////////////////
  //
  //  Evaluation Models
  //
  ///////////////////////////////////////////////
  //
  //  1. Now - evaluated once immediately -> val
  //
  //  2. Later - evaluated once when the value is needed -> lazy val
  //
  //  3. Always - evaluated every time the value is needed -> def
  //
  ///////////////////////////////////////////////

  ///////////////////////////////////////////////
  //          //            //                 //
  //          //   Eager    //    Lazy         //
  //          //            //                 //
  ///////////////////////////////////////////////
  //          //            //                 //
  // Memoized //  val, now  // lazy val, later //
  //          //            //                 //
  ///////////////////////////////////////////////
  //          //            //                 //
  // Not      //            //                 //
  // Memoized //  N/A       // def, always     //
  //          //            //                 //
  ///////////////////////////////////////////////
}
