package easy.core.cards

trait CardDistribution[A] {
	def fromColourDistribution(colours: Map[Suite, Double]): A
}

object CardDistribution {
	def fromColourDistribution[A](colours: Map[Suite, Double])(implicit c: CardDistribution[A]): A =
		c.fromColourDistribution(colours)
}
