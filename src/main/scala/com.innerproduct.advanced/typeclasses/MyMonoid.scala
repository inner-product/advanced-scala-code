package com.innerproduct.advanced.typeclasses

import scala.concurrent.ExecutionContext

trait MyMonoid[A] {
  def empty: A
  def combine(l: A, r: A): A
}

object MyMonoid {
  def empty[A](implicit monoid: MyMonoid[A]): A = monoid.empty
  def combine[A](l: A, r: A)(implicit monoid: MyMonoid[A]): A =
    monoid.combine(l, r)

  implicit val intAdditive: MyMonoid[Int] =
    new MyMonoid[Int] {
      def empty: Int = ???
      def combine(l: Int, r: Int): Int = ???
    }
  val intMultiplicative: MyMonoid[Int] = ???

  val booleanDisjunction: MyMonoid[Int] = ???
  val booleanConjunction: MyMonoid[Int] = ???

  implicit val string: MyMonoid[String] = ???

  implicit def list[A]: MyMonoid[List[A]] = ???
}

object MyMonoidExamples extends App {
  println(MyMonoid.combine(12, 1))
  println(MyMonoid.combine(12, 1)(MyMonoid.intMultiplicative))
}

object FoldMap extends App {
  def foldMap[A, B](as: List[A], f: A => B)(implicit monoid: MyMonoid[B]): B =
    ???

  println(foldMap(List.range(0, 100), (_: Int) + 1))
}

object ParFoldMap extends App {
  def parFoldMap[A, B](as: List[A], f: A => B)(implicit monoid: MyMonoid[B], ec: ExecutionContext): B =
    ???

  implicit val ec = ExecutionContext.global
  
  println(parFoldMap(List.range(0, 100), (_: Int) + 1))
}
