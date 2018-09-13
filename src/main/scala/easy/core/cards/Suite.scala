package easy.core.cards

sealed trait Suite

object Suite {
	final case object Red extends Suite
	final case object Black extends Suite
}
