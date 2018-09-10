package easy.core.game

import easy.core.cards.Card
import easy.core.decks.Deck

case class PlayerState(deck: Deck, dealer: Card, hand: Hand) {
	def draw: PlayerState = {
		val (newCard, newDeck) = deck.draw
		val newPlayerHand = Hand.draw(newCard)(hand)
		PlayerState(newDeck, dealer, newPlayerHand)
	}
}
