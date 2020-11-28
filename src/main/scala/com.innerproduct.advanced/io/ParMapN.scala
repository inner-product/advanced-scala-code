package com.innerproduct.advanced.io

import cats.effect._
import cats.syntax.all._
import debug._

object ParMapN extends IOApp {
  def run(args: List[String]): IO[ExitCode] =
    par.as(ExitCode.Success)

  val hello = IO("hello").debug
  val world = IO("world").debug

  val par =
    (hello, world)
      .parMapN((h, w) => s"$h $w")
      .debug
}
