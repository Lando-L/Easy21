package easy.core.policies

import easy.core.cards.Card
import easy.core.game.{Action, Hand}
import easy.syntax.PipeSyntax._

import scala.util.Random

object PlayerPolicyInstances {
	import Action._

	val randomPolicy: PlayerPolicy = new PlayerPolicy {
		override def nextAction: (Card, Hand) => Action = {
			(_, _) =>
				Random.nextDouble() |> {
					case p if p < 0.5 => Hit
					case _ => Stick
				}
		}
	}

	val stupidPolicy: PlayerPolicy = new PlayerPolicy {
		override def nextAction: (Card, Hand) => Action = {
			case (_, hand) if hand.sum < 17 => Hit
			case _ => Stick
		}
	}
}
