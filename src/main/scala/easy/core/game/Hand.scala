package easy.core.game

import easy.core.cards.Card

case class Hand(cards: List[Card]) {
	def sum: Int = cards.map(_.value.value).sum
}

object Hand {
	def apply(cards: List[Card] = Nil): Hand = new Hand(cards)
	def apply(card: Card): Hand = new Hand(card :: Nil)

	def draw(card: Card)(hand: Hand): Hand = Hand(card :: hand.cards)
	def draw(optCard: Option[Card])(hand: Hand): Hand = optCard.fold(hand)(card => Hand(card :: hand.cards))
}
