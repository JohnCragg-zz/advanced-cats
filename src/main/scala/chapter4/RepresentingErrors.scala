package chapter4

object RepresentingErrors {

  type Result[A] = Either[Throwable, A]

  type LoginResult = Either[LoginError, User]

  sealed trait LoginError extends Product with Serializable

  final case class UserNotFound(username: String) extends LoginError

  final case class PasswordIncorrect(username: String) extends LoginError

  case object UnexpectedError extends LoginError

  case class User(username: String, password: String)

  def handleError(error: LoginError): Unit = error match {
    case UserNotFound(u) => println(s"User not found: $u")
    case PasswordIncorrect(u) => println(s"Password incorrect: $u")
    case UnexpectedError => println("Unexpected error")
  }
}
