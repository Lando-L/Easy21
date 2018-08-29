package easy.core

case class Hand(cards: List[Card] = Nil) {
	def sum: Int = cards.map(_.value.value).sum
}
