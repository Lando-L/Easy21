package easy.core

import cats.data.State

class Game(deck: Deck)(dealerPolicy: DealerPolicy, playerPolicy: PlayerPolicy) {
	import Action._
	import Outcome._

	type Cards = (Card, Hand)
	type GameState[A] = State[Cards, A]

	def run: Outcome = {
		def simulateGame: GameState[Outcome] = for {
			action <- State.inspect[Cards, Action](playerPolicy.nextAction.tupled)
			_ <- step(action)
			outcome <- State.inspect[Cards, Outcome]{ case (card, hand) =>
				GameRules.finalOutcome(Hand(card :: Nil), hand)
			}
		} yield outcome

		simulateGame.runA(start).value
	}

	private def start: Cards = (deck.drawBlackCard, Hand(deck.drawCard :: Nil))

	private def step: Action => GameState[Unit] = {
		case Hit => hit
		case Stick => stick
	}

	private def hit: GameState[Unit] = for {
		_ <- State.modify[Cards] { case (card, hand) =>  (card, Hand(deck.drawCard :: hand.cards)) }
		//outcome <- State.inspect[Cards, Outcome] { case (_, hand) => GameRules.intermediateOutcome(hand) }
	} yield ()

	private def stick: GameState[Unit] = for {
		_ <- State.inspect[Cards, Unit]((simulateDealer _).tupled)
	} yield ()

	private def simulateDealer(card: Card, hand: Hand): Unit = {
		def simulate(dealer: Hand): Hand = {
			GameRules.intermediateOutcome(hand) match {
				case Loss => dealer
				case _ =>
					dealerPolicy.nextAction(dealer) match {
						case Stick => dealer
						case Hit => simulate(Hand(deck.drawCard :: dealer.cards))
					}
			}
		}

		simulate(Hand(card :: Nil))
	}
}
