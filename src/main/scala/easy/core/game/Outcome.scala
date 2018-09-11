package easy.core.game

sealed abstract class Outcome(val reward: Int)

object Outcome {
	final case object Tie extends Outcome(reward = 0)
	final case object Win extends Outcome(reward = 1)
	final case object Loss extends Outcome(reward = -1)
}
