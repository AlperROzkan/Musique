import Midi._
import Audio._

/* Instructions pour piloter le peripherique MIDI */
package object Action {

  /* Activation/desactivation d'une note */
  sealed trait Switch
  case object On extends Switch
  case object Off extends Switch

  /* Instructions : evenement ou attente */
  sealed trait Instruction
  final case class Event(i: Instrument, h: hauteur, s: Switch) extends Instruction
  final case class Wait(d: date)                               extends Instruction

  /* Executer une instruction */
  val perform_action: Audio => Instruction => Unit = s => a => a match {
    case Wait(d) => Thread.sleep(d)
    case Event(i, h, On)  => (channel_of_instrument(s)(i)).noteOn(h, 600)
    case Event(i, h, Off) => (channel_of_instrument(s)(i)).noteOff(h, 600)
  }

}
