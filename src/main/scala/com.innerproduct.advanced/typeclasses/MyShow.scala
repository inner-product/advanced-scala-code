package com.innerproduct.advanced.typeclasses

trait MyShow[A] {
  def show(a: A): String
}

object MyShow {
  def show[A](a: A)(implicit show: MyShow[A]): String = 
    show.show(a)

  def by[A, B](f: A => B)(implicit show: MyShow[B]): MyShow[A] =
    ???

  implicit val string: MyShow[String] = _.toString

  implicit val int: MyShow[Int] = ???

  implicit def list[A](implicit show: MyShow[A]): MyShow[List[A]] =
    ???
}

object MyShowExamples extends App {
  println(MyShow.show("Hello world!"))
  println(MyShow.show(1812))
  println(MyShow.show(List(1, 2, 3)))

  case class Album(name: AlbumName, releaseYear: Year)
  object Album {
    implicit val show: MyShow[Album] = ???
  }
  case class AlbumName(value: String) extends AnyVal
  case class Year(value: Int) extends AnyVal
  

  val kindOfBlue = Album(AlbumName("Kind of Blue"), Year(1959))

  println(MyShow.show(kindOfBlue))
}