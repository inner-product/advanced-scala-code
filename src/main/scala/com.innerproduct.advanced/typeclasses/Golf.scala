package typeclasses

import cats.effect._
// TODO: uncomment for cats extension methods:
// import cats.syntax.all._

// Each method below can be written more concisely using some type classes
// operations. Try to find the shortest replacement you can. We're not
// suggesting that you should do this in production code, but these exercises
// might help sharpen your intuition for when a type class can be used.
object Golf {
  def calculatePrice(
      basePrice: Option[Double],
      salesTax: Option[Double]
  ): Either[String, Double] = {
    val price: Option[Double] =
      for {
        bP <- basePrice
        sT <- salesTax
      } yield (bP + (sT * bP))

    price.toRight("Could not calculate the price.")
  }

  def sendMetrics(
      latency: IO[Double],
      requestsPerSecond: IO[Double]
  ): IO[Unit] =
    // We use `println` as a fake metric collection service
    for {
      l <- latency
      r <- requestsPerSecond
    } yield println(s"${l} latency, ${r} requests per second")

  def totalLatency(latencies: List[Option[Double]]): Double =
    latencies.foldLeft(0.0) { (accum, elt) =>
      elt match {
        case None    => accum
        case Some(l) => accum + l
      }
    }

  def jobsPerServer(
      running: Map[String, Int],
      scheduled: Map[String, Int]
  ): Map[String, Int] = {
    val total =
      running.map { case (server, jobs) =>
        scheduled.get(server) match {
          case Some(value) => server -> (jobs + value)
          case None        => server -> jobs
        }
      }

    scheduled.foldLeft(total) { (accum, elt) =>
      val (server, jobs) = elt
      accum.get(server) match {
        case Some(value) =>
          // We've already handled this
          accum
        case None =>
          accum + elt
      }
    }
  }

  def makeUser(
      name: Either[String, String],
      age: Either[String, Int]
  ): Either[String, (String, Int)] = {
    (name, age) match {
      case (Right(n), Right(a)) => Right((n, a))
      case (Left(e1), Right(_)) => Left(e1)
      case (Right(_), Left(e2)) => Left(e2)
      case (Left(e1), Left(e2)) => Left(e1 ++ e2)
    }
  }

  def configValue(setting: Option[Int], default: Option[Int]): Option[Int] =
    setting match {
      case Some(value) => Some(value)
      case None        => default
    }
}
