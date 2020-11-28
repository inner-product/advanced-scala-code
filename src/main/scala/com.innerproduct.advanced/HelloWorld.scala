package com.innerproduct.advanced

import cats.effect._

object HelloWorld extends IOApp {
  def run(args: List[String]): IO[ExitCode] =
    IO(println("Hello world!")).as(ExitCode.Success)
}