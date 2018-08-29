package easy.core

trait Deck {
	def drawCard: Card
	def drawBlackCard: Card
}

object Deck {
	def drawCard(deck: Deck): Card = deck.drawCard
	def drawBlackCard(deck: Deck): Card = deck.drawBlackCard
}
