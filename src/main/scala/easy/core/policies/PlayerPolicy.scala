package easy.core.policies

import easy.core.cards.Card
import easy.core.game.{Action, Hand}

trait PlayerPolicy {
	def nextAction: (Card, Hand) => Action
}
