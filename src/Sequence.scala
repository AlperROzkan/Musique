/* Partitions de bas niveau : ce sont des instructions pour le periphérique MIDI*/

import Midi._
import Action._

package object Sequence {

  type sequence = Stream[Instruction]

  val play: Audio => sequence => Unit = out => p =>
    p match {
      case Stream.Empty => ()
      case e #:: r => {
        perform_action(out)(e)
        play(out)(r)
      }
    }

  /*
  type sequence_finie = List[Instruction]

  val play_sequence_finie : Audio => sequence_finie => Unit = out => p =>
    p match {

      case Nil => ()

      case e :: r => {
        perform_action (out) (e)
        play_sequence_finie (out) (r)
      }
    }

  val play_debut = (nb_events:Int) => (out:Audio) => (s:sequence) =>
    play_sequence_finie (out)  ((s.take (nb_events)).toList)
    */
}
