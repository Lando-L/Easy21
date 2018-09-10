package easy.core.game

import easy.core.decks.Deck

case class DealerState(deck: Deck, hand: Hand) {
	def draw: DealerState = {
		val (newCard, newDeck) = deck.draw
		val newHand = Hand.draw(newCard)(hand)
		DealerState(newDeck, newHand)
	}
}
