package easy.core.decks

import cats.data.NonEmptyList
import easy.core.cards.Card

sealed trait FiniteDeck extends Deck {
	import FiniteDeck._

	override def draw: (Option[Card], Deck) = this match {
		case EmptyDeck => (None, EmptyDeck)
		case NonEmptyDeck(cards) =>
			cards.tail match {
				case Nil => (Some(cards.head), EmptyDeck)
				case c :: cs => (Some(cards.head), NonEmptyDeck(NonEmptyList(c, cs)))
			}
	}

	def isEmpty: Boolean = this match {
		case EmptyDeck => true
		case NonEmptyDeck(_) => false
	}

	def nonEmpty: Boolean = !isEmpty
}

object FiniteDeck {
	case object EmptyDeck extends FiniteDeck
	case class NonEmptyDeck(cards: NonEmptyList[Card]) extends FiniteDeck
}
