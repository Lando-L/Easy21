package easy.core.cards

import org.scalatest.{FlatSpec, Matchers}

class CardSamplerInstancesSpec extends FlatSpec with Matchers {
	"sample" should "sample a Card given a distribution" in {
		val listCardSampler = CardSamplerInstances.listCardSampler

		val distribution = {
			for {
				number <- 1 to 5
				(colour, p) <- Map(Colour.Black -> 0.7, Colour.Red -> 0.3)
				value <- Value(number)
			} yield (Card(value, colour), p / 5)
		}.toList

		for {
			_ <- 1 to 10
			Card(value, _) <- listCardSampler.sample(distribution)
		}{
			value.value should be > 0
			value.value should be < 11
		}
	}
}
