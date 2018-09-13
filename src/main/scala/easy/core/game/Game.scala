package easy.core.game

import cats.data.State
import easy.core.cards.Card
import easy.core.policies.{DealerPolicy, PlayerPolicy}
import easy.syntax.PipeSyntax._

case class Game(gameRules: GameRules)(dealerPolicy: DealerPolicy, playerPolicy: PlayerPolicy)(initialState: PlayerState) {
	import Action._
	import Status._

	type VisibleCards = (Card, Hand)
	type GameState[A] = State[PlayerState, A]

	def run: Outcome = {
		def simulateGame: GameState[Outcome] = for {
			action <- State.inspect[PlayerState, Action] { case PlayerState(_, dealer, player) => playerPolicy.nextAction(dealer, player) }
			status <- step(action)
			outcome <- status match {
				case Over(outcome) => State.pure[PlayerState, Outcome](outcome)
				case Running => simulateGame
			}
		} yield outcome

		simulateGame.runA(initialState).value
	}

	private def step: Action => GameState[Status] = {
		case Hit => hit
		case Stick => stick
	}

	private def hit: GameState[Status] = for {
		_ <- State.modify[PlayerState](_.draw)
		status <- State.inspect[PlayerState, Status] { case PlayerState(_, _, player) => gameRules.status(player) }
	} yield status

	private def stick: GameState[Status] = {
		def simulateDealer: PlayerState => Outcome = {
			case PlayerState(deck, dealer, playerHand) =>
				def simulateDealerRec: DealerState => DealerState = {
					dealerState =>
						gameRules.status(dealerState.hand) match {
							case Over(_) => dealerState
							case Running =>
								dealerPolicy.nextAction(dealerState.hand) match {
									case Stick => dealerState
									case Hit => simulateDealerRec(dealerState.draw)
								}
						}
				}

				simulateDealerRec(DealerState(deck, Hand(dealer))) |> {
					case DealerState(_, hand) =>
						gameRules.outcome(hand, playerHand)
				}
		}

		for {
			outcome <- State.inspect[PlayerState, Outcome](simulateDealer)
		} yield Over(outcome)
	}
}
