package easy.core.decks

import easy.core.cards.{Card, CardDistribution, Colour}
import easy.core.cards.CardDistributionInstances._
import easy.core.cards.CardSamplerInstances._
import org.scalatest.{FlatSpec, Matchers}

class InfiniteDeckSpec extends FlatSpec with Matchers {
	"draw" should "return a sampled Card and an identical InfiniteDeck instance" in {
		val distribution = CardDistribution.fromColourDistribution[List[(Card, Double)]](Map(Colour.Black -> 1.0))
		val deck = InfiniteDeck[List[(Card, Double)]](distribution)

		for {
			_ <- 1 to 10
			(optCard, newDeck) = deck.draw
		}{
			optCard.nonEmpty shouldBe true
			optCard.get.suite shouldBe Colour.Black
			newDeck shouldEqual deck
		}
	}
}
