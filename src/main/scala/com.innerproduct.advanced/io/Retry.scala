package com.innerproduct.advanced.io

import cats.effect._

object Retry {
  /**
   * Given an IO[A] construct an IO[A] that, on failure of the original IO,
   * will retry it at most count number of times. Count is a natural number.
   * 
   * Note: you *must not* call any unsafe methods within this method.
   */
  def retry[A](io: IO[A], count: Int): IO[A] =
    ???

  val sometimesFailing: IO[Int] =
    IO.delay(scala.util.Random.nextDouble() < 0.5).flatMap(choice =>
      if(choice) IO.pure(1)
      else IO.raiseError(new Exception("Oh noes!!!"))
    )

  /** Given a natural number create a list of IOs (the tasks) that may fail. */
  def tasks(n: Int): List[IO[Int]] =
    List.fill(n)(sometimesFailing)

  /** 
   * Given tasks and a retry policy, run the tasks (retrying on failure)
   * and return two lists containing the successful results and the failure respectively.
   * 
   * Bonus points for running tasks in parallel where possible.
   * 
   * Tasks is a list of tasks to execute.
   * Retries is the number of retry attempts for each task before it is considered failed.
   */
  def taskManager1[A](tasks: List[IO[A]], retries: Int): (List[A], List[Throwable]) =
    ???

  /** As above but notice the difference in result type and adapt accordingly. */
  def taskManager2[A](tasks: List[IO[A]], retries: Int): IO[(List[A], List[Throwable])] =
    ???
}