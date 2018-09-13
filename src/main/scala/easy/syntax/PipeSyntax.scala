package easy.syntax

object PipeSyntax {
	implicit class Pipe[A](val a: A) extends AnyVal {
		def |>[B](f: A => B): B = f(a)
	}
}
