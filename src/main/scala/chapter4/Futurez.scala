package chapter4

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object Futurez {

  def getTrafficFromHost(hostname : String) : Future[Int] = ???

  //Flatmap method generates the traffic in sequence
  def getTrafficFromAllHosts : Future[Int] = for {
    traffic1 <- getTrafficFromHost("host1")
    traffic2 <- getTrafficFromHost("host2")
    traffic3 <- getTrafficFromHost("host3")
  } yield traffic1 + traffic2 + traffic3


}
