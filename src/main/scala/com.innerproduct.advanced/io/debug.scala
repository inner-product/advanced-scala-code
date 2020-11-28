package com.innerproduct.advanced.io

import cats.effect._

/** `import com.innerproduct.advanced.effects.debug._` to access
 * the `debug` extension method. */
object debug {
  /** Extension methods for an effect of type `F[A]`. */
  implicit class DebugHelper[A](ioa: IO[A]) {

    /** Print to the console the value of the effect
     * along with the thread it was computed on. */
    def debug: IO[A] =
      for {
        a <- ioa
        tn = Thread.currentThread.getName
        _ = println(s"[${Colorize.reversed(tn)}] $a")
      } yield a
  }
}
