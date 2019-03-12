/* Partitions de bas niveau : instructions pour le périphérique MIDI */


import Midi._
import Action._

package object Sequence {

  /* Une séquence est une liste d'actions associées à une date. */

  type evenement = (action, date)

  type sequence = List[evenement]

  val translate: date => sequence => sequence = delta => s =>
    s match {
      case Nil => Nil
      case (e, d) :: r => (e, d + delta) :: (translate(delta)(r))
    }

  /* Améliorer translate en utilisant un map. */


  val get_date = () => System.currentTimeMillis()

  val play_absolu: Audio => sequence => Unit = out => p =>
    p match {
      case Nil => ()
      case (e, d) :: r =>
        if (d < get_date()) {
          perform_action(out)(e)
          play_absolu(out)(r)
        }
        else {
          Thread.sleep(10)
          play_absolu(out)(p)
        }
    }


  val play_relatif: Audio => sequence => Unit = out => p =>
    play_absolu(out)(translate(get_date())(p))

}
