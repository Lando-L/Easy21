package easy.core.cards

object CardDistributionInstances {
	implicit val listCardDistribution: CardDistribution[List[(Card, Double)]] = new CardDistribution[List[(Card, Double)]] {
		override def fromColourDistribution(colours: Map[Suite, Double]): List[(Card, Double)] = {
			for {
				(colour, p) <- colours
				number <- 1 to 10
				value <- Value(number)
			} yield (Card(value, colour), p / 10)
		}.toList
	}
}
