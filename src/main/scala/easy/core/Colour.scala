package easy.core

sealed trait Colour

object Colour {
	final case object Red extends Colour
	final case object Black extends Colour
}
