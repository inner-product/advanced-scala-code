package com.innerproduct.advanced.typeclasses

import cats._
import cats.effect._
import cats.syntax.all._
import com.innerproduct.advanced.io.debug._

object MyTraverse extends IOApp {
  implicit val list: Traverse[List] =
    new Traverse[List] {
      def foldLeft[A, B](fa: List[A], b: B)(f: (B, A) => B): B =
        fa.foldLeft(b)(f)

      def foldRight[A, B](fa: List[A], lb: Eval[B])(f: (A, Eval[B]) => Eval[B]): Eval[B] =
        fa.foldRight(lb)(f)

      def traverse[G[_]: Applicative, A, B](fa: List[A])(f: A => G[B]): G[List[B]] =
        ???
    }

  def run(args: List[String]): IO[ExitCode] =
    List(1, 2, 3)
      .traverse(i => IO(i).debug)
      .debug
      .as(ExitCode.Success)
}