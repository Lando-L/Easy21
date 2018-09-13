package easy.core.policies

import easy.core.cards.Card
import easy.core.game.{Action, Hand}

import scala.util.Random

object PlayerPolicyInstances {
	import Action._

	val randomPolicy: PlayerPolicy = new PlayerPolicy {
		override def nextAction: (Card, Hand) => Action = {
			(_, _) =>
				val p = Random.nextDouble()
				if (p < 0.5) Hit else Stick
		}
	}
}
