package chapter4

import org.scalatest.{FunSuite, Matchers}

class LoginSystemTest extends FunSuite with Matchers {
  val l = LoginSystem

  import l._

  val db = Db(Map(1 -> "john"), Map("john" -> "pass"))
  test("if the username does not exist I cannot log in") {
    checkLogin(2, "pass").run(db) shouldBe false
  }

  test("if the passwords don't match then I cannot log in"){
    checkLogin(1, "wrongPass").run(db) shouldBe false
  }

  test("if the correct details match I can log in"){
    checkLogin(1, "pass").run(db) shouldBe true
  }

}
