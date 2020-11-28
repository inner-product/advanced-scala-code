package com.innerproduct.advanced.effects

case class MyIO[A](unsafeRun: () => A) {
  def map[B](f: A => B): MyIO[B] =
    ???

  def flatMap[B](f: A => MyIO[B]): MyIO[B] =
    ???
}

object MyIO {
  def putStr(s: => String): MyIO[Unit] =
    ???
}

object Printing extends App {
  lazy val hello = MyIO.putStr("hello!")

  hello.unsafeRun()
}