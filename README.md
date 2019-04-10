# effectful-decline

This library connects `cats-effect` and `decline` to give you a nice way to define something that's both an `IOApp` and a `CommandApp`, also I didn't really like `CommandApp` much.

## Example

```scala
import cats._, cats.implicits._
import cats.effect._
import com.monovore.decline._

case class Config(a: Boolean, o: Option[String], arg: String)

object Example extends IOCommandApp[Config] {
  val name: String = "example"
  val header: String = "an example program"
  override val version: String = "0.1.0"

  override def commandLine = (
    Opts.flag("a", "an a flag").orFalse,
    Opts.option[String]("o", "an o option").orNone,
    Opts.argument[String]("an argument")
  ).mapN(Config.apply)

  override def main(c: Config): IO[ExitCode] = IO {
    println((c.a, c.o, c.arg))
    ExitCode.Success
  }
}
```
