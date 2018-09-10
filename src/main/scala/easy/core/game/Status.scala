package easy.core.game

sealed trait Status

object Status {
	case object Running extends Status
	case class Over(outcome: Outcome) extends Status
}
