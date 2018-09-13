package easy.core.game

import easy.core.decks.Deck
import easy.syntax.PipeSyntax._

case class DealerState(deck: Deck, hand: Hand) {
	def draw: DealerState = {
		deck.draw |> { case (newCard, newDeck) =>
			val newHand = Hand.draw(newCard)(hand)
			DealerState(newDeck, newHand)
		}
	}
}
