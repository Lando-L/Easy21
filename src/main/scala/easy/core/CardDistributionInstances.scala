package easy.core

import scala.util.Random

object CardDistributionInstances {
	implicit val mapCardDistribution: CardDistribution[Map[Card, Double]] = new CardDistribution[Map[Card, Double]] {
		override def sample(random: Random)(distribution: Map[Card, Double]): Card = {
			distribution.head._1
		}
	}
}
