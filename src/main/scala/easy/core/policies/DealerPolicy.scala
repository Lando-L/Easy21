package easy.core.policies

import easy.core.game.{Action, Hand}

trait DealerPolicy {
	def nextAction: Hand => Action
}

object DealerPolicy {
	def nextAction(hand: Hand)(implicit d: DealerPolicy): Action =
		d.nextAction(hand)
}
