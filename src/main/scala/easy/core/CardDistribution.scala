package easy.core

import scala.util.Random

trait CardDistribution[A] {
	def sample(random: Random)(distribution: A): Card
}

object CardDistribution {
	def sample[A](random: Random)(distribution: A)(implicit c: CardDistribution[A]): Card =
		c.sample(random)(distribution)
}
