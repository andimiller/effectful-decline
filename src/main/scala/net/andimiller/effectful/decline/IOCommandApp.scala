package net.andimiller.effectful.decline

import cats.effect.{ExitCode, IO, IOApp}
import com.monovore.decline.{Command, Opts, PlatformApp, Visibility}

trait IOCommandApp extends IOApp {
  val name: String
  val header: String
  val main: Opts[IO[ExitCode]]
  val helpFlag: Boolean = true
  val version: String = ""

  def command: Command[IO[ExitCode]] = {
      val showVersion =
        if (version.isEmpty) Opts.never
        else
          Opts.flag("version", "Print the version number and exit.", visibility = Visibility.Partial)
            .map { _ => IO { System.err.println(version); ExitCode.Success }}
      Command(name, header, helpFlag)(showVersion orElse main)
  }

  override def run(args: List[String]): IO[ExitCode] = command.parse(PlatformApp.ambientArgs getOrElse args, sys.env) match {
      case Left(help) => IO { System.err.println(help); ExitCode.Error }
      case Right(r) => r
    }
}
