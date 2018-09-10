package easy.core.decks

import easy.core.cards.{Card, CardSampler}

case class InfiniteDeck[A](cardDistribution: A)(implicit c: CardSampler[A]) extends Deck {
	override def draw: (Option[Card], Deck) = (c.sample(cardDistribution), InfiniteDeck(cardDistribution))
}
