import Audio._

/*Definitions communes*/

package object Midi {

  type hauteur = Int
  type date = Long

  sealed trait Instrument
  case object Piano extends Instrument
  case object Tubular extends Instrument

  val channel_of_instrument = (s: Audio) => (i: Instrument) => i match {
    case Piano => s.canalPiano
    case Tubular => s.canalTubular
  }

}
