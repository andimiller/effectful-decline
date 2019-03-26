# effectful-decline

This library connects `cats-effect` and `decline` to give you a nice way to define something that's both an `IOApp` and a `CommandApp`, also I didn't really like `CommandApp` much.

## Example

```scala
import cats._, cats.implicits._
import cats.effect._
import com.monovore.decline._

object Example extends IOCommandApp {
  override val name: String = "example"
  override val header: String = "an example program"
  override val version: String = "0.1.0"
  override val main: Opts[IO[ExitCode]] = (
    Opts.flag("a", "an a flag").orFalse,
    Opts.option[String]("o", "an o option").orNone,
    Opts.argument[String]("an argument")
  ).mapN { case (a, o, arg) =>
      IO {
        println((a, o, arg))
        ExitCode.Success
      }
  }
}
```
