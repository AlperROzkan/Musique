import Partition._

/**
  * Une collection de segments et partitions.
  */
package object MesPartitions {
  val monsegment : segment = Note (Croche, 70, Fort) :: Silence :: Note (Croche, 70, Fort) :: Silence:: Note (Croche, 77,Fort) :: Silence :: Note (Croche, 77, Fort) :: Silence :: Note (Croche, 79, Fort) :: Silence :: Note (Croche, 79, Fort) :: Silence :: Note (Noire, 77,Fort) :: Silence :: Silence :: Nil
  val monsegment2 : segment = Note(Croche,77, Fort) :: Silence :: Nil
  val monsegment3 : segment = Note(Croche,77, Fort) :: Nil

  val monsegmentFaible : segment = Note (Croche, 70, Faible) :: Silence :: Note (Croche, 70, Faible) :: Silence:: Note (Croche, 77,Faible) :: Silence :: Note (Croche, 77, Faible) :: Silence :: Note (Croche, 79, Faible) :: Silence :: Note (Croche, 79, Faible) :: Silence :: Note (Noire, 77,Faible) :: Silence :: Silence :: Nil
  val mapartition : partition = (Nb(2), monsegment) :: (Nb(2),monsegmentFaible) :: Nil

}