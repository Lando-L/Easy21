package easy.core.game

import easy.core.cards.Card
import easy.core.decks.Deck
import easy.syntax.PipeSyntax._

case class PlayerState(deck: Deck, dealer: Card, hand: Hand) {
	def draw: PlayerState = {
		deck.draw |> { case (newCard, newDeck) =>
			val newHand = Hand.draw(newCard)(hand)
			PlayerState(newDeck, dealer, newHand)
		}
	}
}
