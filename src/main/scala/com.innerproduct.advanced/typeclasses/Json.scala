package com.innerproduct.advanced.typeclasses

// Define a very simple JSON AST
sealed trait Json
final case class JsObject(get: Map[String, Json]) extends Json
final case class JsString(get: String) extends Json
final case class JsNumber(get: Double) extends Json
final case object JsNull extends Json

// The "serialize to JSON" behaviour is encoded in this trait
trait JsonWriter[A] {
  def write(value: A): Json
}

object JsonWriter {

  /* generic methods */

  def toJson[A](value: A)(implicit w: JsonWriter[A]): Json =
    w.write(value)

  /* typeclass instances */

  implicit val stringWriter: JsonWriter[String] =
    new JsonWriter[String] {
      def write(value: String): Json =
        JsString(value)
    }

  /* derived instances */

  implicit def optionWriter[A](implicit
      writer: JsonWriter[A]
  ): JsonWriter[Option[A]] = ???

  implicit def listWriter[A](implicit
      writer: JsonWriter[A]
  ): JsonWriter[List[A]] = ???

  /* extension methods */

  object syntax {
    implicit class JsonWriterOps[A](value: A) {
      def toJson(implicit w: JsonWriter[A]): Json =
        w.write(value)
    }
  }
}

final case class Person(name: String, email: String)

object Person {
  // n.b. "SAM" (single abstract method) value creation
  implicit val personWriter: JsonWriter[Person] =
    person =>
      JsObject(
        Map(
          "name" -> JsString(person.name),
          "email" -> JsString(person.email)
        )
      )
}

object JsonExamples extends App {
  val dave = Person("Dave", "dave@example.com")

  println(JsonWriter.toJson(dave))

  import JsonWriter.syntax._

  println(dave.toJson)
}
