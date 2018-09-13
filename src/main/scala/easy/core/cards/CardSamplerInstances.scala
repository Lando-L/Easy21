package easy.core.cards

import scala.util.Random

object CardSamplerInstances {

	implicit def listCardSampler: CardSampler[List[(Card, Double)]] =
		new CardSampler[List[(Card, Double)]] {
			override def sample: List[(Card, Double)] => Option[Card] = {
				val p = Random.nextDouble()

				def sampleRec(optCard: Option[Card], lowerBound: Double): List[(Card, Double)] => Option[Card] = {
					case Nil => optCard
					case (c, l) :: cs =>
						val upperBound = lowerBound + l
						optCard match {
							case Some(card) => Some(card)
							case None if p <= upperBound => Some(c)
							case None => sampleRec(None, upperBound)(cs)
						}
				}

				sampleRec(None, 0.0)
			}
	}
}
