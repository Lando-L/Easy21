package easy.core.cards

trait CardDistribution[A] {
	def fromColourDistribution(colours: Map[Colour, Double]): A
}

object CardDistribution {
	def fromColourDistribution[A](colours: Map[Colour, Double])(implicit c: CardDistribution[A]): A =
		c.fromColourDistribution(colours)
}
