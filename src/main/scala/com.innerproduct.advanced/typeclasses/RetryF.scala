package com.innerproduct.advanced.typeclasses

import cats.effect._

/** Let's steal the code from `Retry.scala` and refactor it into
 * a typeclass-based approach.
 */
object RetryF extends IOApp {

  def run(args: List[String]): IO[ExitCode] =
    program.as(ExitCode.Success)
    
  /** Our program will generate some tasks, manage them,
   * and print their results.
   * 
   * Extra credit: do this repeatedly.
   */
  val program: IO[Unit] =
    for {
      tasks <- IO.pure(taskSource.tasks(20))
      results <- manager.manage(tasks)
      (failures, successes) = results
      // TODO: report on the results
    } yield ()

  lazy val taskSource: TaskSource[IO] = TaskSource[IO]
  lazy val manager: TaskManager[IO] = TaskManager.withRetries(3)
}

trait TaskSource[F[_]] {
  def create: F[Int]

  /** Given a natural number create a list of tasks that may fail. */
  def tasks(n: Int): List[F[Int]] =
    List.fill(n)(create)
}

object TaskSource {
  /** Create a source of tasks for some effect type. The tasks
   * themselves should be created via `sometimesFailing`.
   * 
   * What typeclass instance(s) does this method need?
   */
  def apply[F[_]]: TaskSource[F] = ???

  /** Helper method to produce a task that sometimes fails.
   * 
   * What typeclass instance(s) does it need?
   */
  def sometimesFailing[F[_]]: F[Int] = ???
}

trait Retries[F[_]] {
  /**
   * Given an F[A] construct and an F[A] that, on failure of the original F,
   * will retry it at most count number of times. Count is a natural number.
   */
  def retry[A](io: F[A], count: Int): F[A]
}

object Retries {
  /** Create a task retrier.
   * 
   * What typeclass instance(s) does this method need?
   */
  implicit def apply[F[_]]: Retries[F] = ???
}

trait TaskManager[F[_]] {
  /** 
   * Given tasks and a retry policy, run the tasks
   * and return two lists containing the successful results and the failure respectively.
   * 
   * Tasks is a list of tasks to execute.
   * Retries is the number of retry attempts for each task before it is considered failed.
   */
  def manage[A](tasks: List[F[A]]): F[(List[Throwable], List[A])]
}

object TaskManager {
  /** Create a task manager that will retry task failures.
   * 
   * What typeclass instance(s) does this method need?
   */
  def withRetries[F[_]: Retries](retries: Int): TaskManager[F] = ???

  // TODO: Create another manager instance that runs tasks in parallel.
}