/* Partitions de haut niveau : solfège*/

import Midi.hauteur

package object Partition {

  /* Duree d'une note */
  sealed trait Duree
  case object Noire extends Duree
  case object Croche extends Duree

  /* Puissance d'une note */
  sealed trait Puissance
  case object Fort extends Puissance
  case object Moyen extends Puissance
  case object Faible extends Puissance

  /* Types de notes */
  sealed trait Donnee
  case object Silence                         extends Donnee
  final case class Note(d: Duree, h: hauteur, p: Puissance) extends Donnee

  final case class Tempo(i : Int)

  /* Segment de partition */
  type segment = List[Donnee]

  /* Types de répétitions */
  sealed trait Repetition
  case object AdLib           extends Repetition
  final case class Nb(i: Int) extends Repetition

  /* Partition */
  type partition = List[(Repetition, segment)]

}
