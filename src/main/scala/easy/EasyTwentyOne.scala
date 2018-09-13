package easy

import easy.core.cards.CardSamplerInstances._
import easy.core.cards.CardDistributionInstances._
import easy.core.cards.{Card, CardDistribution}
import easy.core.cards.Colour._
import easy.core.decks.InfiniteDeck
import easy.core.game.{Game, GameRules, Hand, PlayerState}
import easy.core.policies.{DealerPolicy, DealerPolicyInstances, PlayerPolicy, PlayerPolicyInstances}

object EasyTwentyOne extends App {
	def cardDistribution: List[(Card, Double)] = {
		CardDistribution.fromColourDistribution[List[(Card, Double)]](
			Map(Black -> 0.7, Red -> 0.3)
		)
	}

	def blackCardDistribution: List[(Card, Double)] = {
		CardDistribution.fromColourDistribution[List[(Card, Double)]](
			Map(Black -> 1.0)
		)
	}

	def rules: GameRules = {
		hand =>
			val sum = hand.sum
			sum < 1 || 21 < sum
	}

	def playerPolicy: PlayerPolicy = {
		PlayerPolicyInstances.randomPolicy
	}

	def dealerPolicy: DealerPolicy = {
		DealerPolicyInstances.stupidDealer
	}

	def initialState: Option[PlayerState] = {
		val deck = InfiniteDeck[List[(Card, Double)]](cardDistribution)
		val blackCardDeck = InfiniteDeck[List[(Card, Double)]](blackCardDistribution)

		for {
			dealer <- blackCardDeck.draw._1
			player <- blackCardDeck.draw._1
		} yield PlayerState(deck, dealer, Hand(player))
	}

	override def main(args: Array[String]): Unit = {
		println("Starting game...")

		initialState.foreach {
			start =>
				val game = new Game(rules)(dealerPolicy, playerPolicy)(start)
				val reward = game.run.reward
				println(reward)
		}

		println("Finishing game...")
	}
}
