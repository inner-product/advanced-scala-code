# Advanced Scala Code

This repository contains exercises and solutions for
[Inner Product's Advanced Scala][course] training course.

If you want to discuss the content or exercises with the authors,
drop us an [email][email].


## Using the Source Code

This repository contains two branches: one for `exercises` and one for `solutions`.
The directory structure is the same in each branch.

You will need to have Git, the JDK, and an internet connection to run the exercises.
All other dependencies are either included with the repo
or downloaded on demand during compilation.


### Notes on Editors and IDEs

If you don't have a particular preference for a Scala editor or IDE,
we recommend you do the exercises for this course using
the [Visual Studio Code][vscode] editor with the [Metals][metals] and [Scala syntax][scala-syntax] extensions.

If you want to use IntelliJ we suggest installing it via the [Scala Bundle][intellij-scala-bundle],
which includes all the plugins you need for Scala development.


### Getting Started on Linux or OS X

To get started:

1. To run these exercises, you need a Java 8 compatible JDK on your machine.
   If you don't have this already, you can download and install one from 
   [OpenJDK][openjdk].

2. Clone this repository to a directory on your hard drive,
   e.g. `~/advanced-scala-code`:

   ~~~
   bash$ git clone https://github.com/inner-product/advanced-scala-code.git
   ~~~

3. Change to the root directory in the repository:

   ~~~
   bash$ cd advanced-scala-code
   ~~~

4. Run `sbt`.
   You may have to wait while SBT downloads various dependencies:

   ~~~
   bash$ sbt
   # Lots of output here...
   # The first run will take a while...

   >
   ~~~

5. Type `runMain com.innerproduct.advanced.HelloWorld` at the SBT prompt.
   You may have to wait while SBT downloads various dependencies.

5. If you see the message `"Hello world!"`, you're ready to go!

*If you have any problems getting started, get in touch via [email][email].*


[course]: https://www.inner-product.com/services/training/advanced-scala/
[email]: email:hello@inner-product.com
[vscode]: https://code.visualstudio.com/
[metals]: https://marketplace.visualstudio.com/items?itemName=scalameta.metals
[scala-syntax]: https://marketplace.visualstudio.com/items?itemName=scala-lang.scala
[intellij-scala-bundle]: https://github.com/JetBrains/intellij-scala-bundle
[openjdk]: https://jdk.java.net/
