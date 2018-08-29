package easy.core

import scala.util.Random

object DeckInstances {
	def infiniteDeck[A](distribution: A)(implicit cardDistribution: CardDistribution[A]): Deck = new Deck {
		def random: Random = new Random()

		override def drawCard: Card = {
			CardDistribution.sample(random)(distribution)
		}

		override def drawBlackCard: Card = {
			CardDistribution.sample(random)(distribution)
		}
	}
}
