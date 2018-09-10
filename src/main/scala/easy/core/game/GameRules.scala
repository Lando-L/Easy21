package easy.core.game

trait GameRules {
	import Outcome._
	import Status._

	def status(hand: Hand): Status = {
		if (isBusted(hand)) Over(Loss) else Running
	}

	def outcome(dealer: Hand, player: Hand): Outcome = (dealer, player) match {
		case (d, p) if isBusted(p) || d.sum > p.sum => Loss
		case (d, p) if isBusted(d) || d.sum < p.sum => Win
		case _ => Tie
	}

	def isBusted(hand: Hand): Boolean
}

object GameRules {
	def status(hand: Hand)(implicit gameRules: GameRules): Status =
		gameRules.status(hand)

	def outcome(dealer: Hand, player: Hand)(implicit gameRules: GameRules): Outcome =
		gameRules.outcome(dealer, player)
}
