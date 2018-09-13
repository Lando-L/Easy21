package easy.core.decks

import cats.data.NonEmptyList
import easy.core.cards.{Card, Colour, Value}
import easy.core.decks.FiniteDeck.{EmptyDeck, NonEmptyDeck}
import org.scalatest.{FlatSpec, Matchers}

class FiniteDeckSpec extends FlatSpec with Matchers {
	"draw" should "return no card and an EmptyDeck if the deck is already empty" in {
		val deck = EmptyDeck
		val (optCard, newDeck) = deck.draw

		optCard shouldBe None
		newDeck shouldBe EmptyDeck
	}

	it should "return the last card and an EmptyDeck if the deck had only one card left" in {
		val lastCard = Card(Value(1).get, Colour.Black)
		val deck = NonEmptyDeck(NonEmptyList(lastCard, Nil))
		val (optCard, newDeck) = deck.draw

		optCard shouldEqual Some(lastCard)
		newDeck shouldBe EmptyDeck
	}

	it should "return the next card and a NonEmptyDeck if the deck had more than one card left" in {
		val nextCard = Card(Value(1).get, Colour.Black)
		val cards = NonEmptyList(Card(Value(2).get, Colour.Black), List(Card(Value(3).get, Colour.Red), Card(Value(4).get, Colour.Black)))
		val deck = NonEmptyDeck(NonEmptyList(nextCard, cards.toList))
		val (optCard, newDeck) = deck.draw

		optCard shouldEqual Some(nextCard)
		newDeck shouldEqual NonEmptyDeck(cards)
	}
}
