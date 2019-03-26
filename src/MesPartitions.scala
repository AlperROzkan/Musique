import Partition._

/**
  * Une collection de segments et partitions.
  */
package object MesPartitions {
  val monsegment : segment = Note (Croche, 70) :: Silence :: Note (Croche, 70) :: Silence:: Note (Croche, 77) :: Silence :: Note (Croche, 77) :: Silence :: Note (Croche, 79) :: Silence :: Note (Croche, 79) :: Silence :: Note (Noire, 77) :: Silence :: Silence :: Nil
  val monsegment2 : segment = Note(Croche,77) :: Silence :: Nil
  val monsegment3 : segment = Note(Croche,77) :: Nil
  val mapartition : partition = (Nb(2), monsegment) :: (Nb(8),monsegment2) :: (AdLib,monsegment) ::  Nil

  val monsegmentNoire : segment = Note (Noire, 20) :: Silence :: Note (Noire, 100) :: Silence:: Note (Noire, 77) :: Silence :: Note (Noire, 77) :: Silence :: Note (Noire, 79) :: Silence :: Note (Noire, 79) :: Silence :: Note (Noire, 77) :: Silence :: Silence :: Nil
  val partitionRepeatFast : partition = (Nb(2), monsegment) :: (AdLib, monsegmentNoire) :: Nil
}
