package easy.core

object GameRules {
	import Outcome._

	def intermediateOutcome(hand: Hand): Outcome = {
		if (isBusted(hand.sum)) Loss else Ongoing
	}

	def finalOutcome(dealer: Hand, player: Hand): Outcome = (dealer, player) match {
		case (d, p) if isBusted(p) || d.sum > p.sum => Loss
		case (d, p) if isBusted(d) || d.sum < p.sum => Win
		case _ => Tie
	}

	private def isBusted(hand: Hand): Boolean = {
		isBusted(hand.sum)
	}

	private def isBusted(sum: Int): Boolean = {
		sum < 1 || sum > 21
	}
}
