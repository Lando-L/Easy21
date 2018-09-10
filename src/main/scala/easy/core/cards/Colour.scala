package easy.core.cards

sealed trait Colour

object Colour {
	final case object Red extends Colour
	final case object Black extends Colour
}
