package easy.core.policies

import easy.core.cards.Card
import easy.core.game.{Action, Hand}

import scala.util.Random

object PlayerPolicyInstances {
	import Action._

	def randomPolicy(implicit random: Random): PlayerPolicy = new PlayerPolicy {
		override def nextAction: (Card, Hand) => Action = {
			(_, _) =>
				val p = random.nextDouble()
				if (p < 0.5) Hit else Stick
		}
	}
}
