package easy.core

object DealerPolicyInstances {
	import Action._

	val stupidDealer: DealerPolicy = new DealerPolicy {
		override def nextAction: Hand => Action = {
			case hand if hand.sum < 17 => Hit
			case _ => Stick
		}
	}
}
