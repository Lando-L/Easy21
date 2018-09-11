package easy.core.cards

import scala.util.Random

object CardSamplerInstances {
	implicit def listCardSampler(implicit random: Random): CardSampler[List[(Card, Double)]] =
		new CardSampler[List[(Card, Double)]] {
			override def sample: List[(Card, Double)] => Option[Card] = {
				val p = random.nextDouble()

				def sampleRec(optCard: Option[Card], lowerBound: Double): List[(Card, Double)] => Option[Card] = {
					case Nil => optCard
					case (c, l) :: cs =>
						val upperBound = lowerBound + l
						optCard match {
							case Some(card) => Some(card)
							case None if p <= upperBound => Some(c)
							case _ => sampleRec(None, upperBound)(cs)
						}
				}

				sampleRec(None, 0.0)
			}
	}
}
