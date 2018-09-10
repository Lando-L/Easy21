package easy.core.cards

trait CardSampler[A] {
	def sample: A => Option[Card]
}

object CardSampler {
	def sample[A](distribution: A)(implicit c: CardSampler[A]): Option[Card] =
		c.sample(distribution)
}
