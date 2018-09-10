package easy.core.cards

case class Value(value: Int) extends AnyVal

object Value {
	def apply(value: Int): Option[Value] = {
		if (1 <= value && value <= 10) Some(new Value(value))
		else None
	}
}
