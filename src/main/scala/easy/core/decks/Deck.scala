package easy.core.decks

import easy.core.cards.Card

trait Deck {
	def draw: (Option[Card], Deck)
}
