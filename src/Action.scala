import Audio._
import Midi._

/* Type des instructions à passer à un périphérique MIDI */

package object Action {

  sealed trait Switch
  case object On extends Switch
  case object Off extends Switch


  type action = (Instrument, hauteur, Switch)

  val perform_action: Audio => action => Unit = s => a => a match {
    case (i, h, On) => (channel_of_instrument(s)(i)).noteOn(h, 600)
    case (i, h, Off) => (channel_of_instrument(s)(i)).noteOff(h, 600)
  }

}
