package easy.core.game

sealed trait Action

object Action {
	final case object Stick extends Action
	final case object Hit extends Action
}
