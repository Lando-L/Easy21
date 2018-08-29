package easy.core

trait PlayerPolicy {
	def nextAction: (Card, Hand) => Action
}
