package com.innerproduct.advanced.contexts

import cats.effect._
import com.innerproduct.advanced.io.debug._

object Blocking extends IOApp {

  def run(args: List[String]): IO[ExitCode] =
    withBlocker(???).as(ExitCode.Success) // <1>

  def withBlocker(blocker: Blocker): IO[Unit] =
    for {
      _ <- IO("on default").debug
      _ <- IO("on blocker").debug // <2>
    } yield ()
}
