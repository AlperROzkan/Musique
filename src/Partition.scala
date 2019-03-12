/* Partitions de haut-niveau : solfège */

import Midi.hauteur

package object Partition {

  sealed trait Duree
  case object Noire extends Duree
  case object Croche extends Duree
  
  
  sealed trait Donnee
  case object Silence extends Donnee
  final case class Note(d:Duree, h:hauteur) extends Donnee
  
  type partition = List[Donnee]
  
    
  /* exemple de partition */
  val mapartition : partition = (Note (Croche, 70)) :: Silence :: Note (Croche, 70) :: Silence:: Note (Croche, 77) :: Silence :: Note (Croche, 77) :: Silence :: Note (Croche, 79) :: Silence :: Note (Croche, 79) :: Silence :: Note (Noire, 77) :: Nil
  
}
