package easy.core.cards

import org.scalatest.{FlatSpec, Matchers}

class CardDistributionInstancesSpec extends FlatSpec with Matchers {
	import CardDistributionInstances._

	"fromColourDistribution" should "create a card distribution from given colors" in {
		val colors = Map[Colour, Double](Colour.Black -> 0.5, Colour.Red -> 0.5)
		val distribution = CardDistribution.fromColourDistribution[List[(Card, Double)]](colors)

		distribution.length shouldEqual 20

		distribution.foreach { case (_, p) =>
			p shouldEqual 0.05
		}
	}
}
