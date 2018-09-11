package easy

import easy.core.cards.CardSamplerInstances._
import easy.core.cards.CardDistributionInstances._
import easy.core.cards.{Card, CardDistribution}
import easy.core.cards.Colour._
import easy.core.decks.InfiniteDeck
import easy.core.game.{Game, GameRules, Hand, PlayerState}
import easy.core.policies.{DealerPolicy, DealerPolicyInstances, PlayerPolicy, PlayerPolicyInstances}

import scala.util.Random

object EasyTwentyOne extends App {
	implicit val random: Random = new Random()

	val cardDistribution: List[(Card, Double)] = {
		CardDistribution.fromColourDistribution(Map(Black -> 0.7, Red -> 0.3))(listCardDistribution)
	}

	val blackCardDistribution: List[(Card, Double)] = {
		CardDistribution.fromColourDistribution(Map(Black -> 1.0))(listCardDistribution)
	}

	val rules: GameRules = {
		hand =>
			val sum = hand.sum
			sum < 1 || 21 < sum
	}

	val playerPolicy: PlayerPolicy = {
		PlayerPolicyInstances.randomPolicy
	}

	val dealerPolicy: DealerPolicy = {
		DealerPolicyInstances.stupidDealer
	}

	def initialState: Option[PlayerState] = {
		val deck = InfiniteDeck(cardDistribution)
		val blackCardDeck = InfiniteDeck(blackCardDistribution)

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
