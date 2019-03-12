import Action._
import Midi._
import Partition.{Noire, _}
import Sequence._
import ancien.Musique.{Croche, Duree, Noire, sortie}

object Musique {
  val sortie = new Audio();





  /**
    * Transforme une partition en sequence
    *
    */
  val sequence_of_partition: Instrument => date => partition => sequence = i => d => p =>
    p match {
      case Nil => Nil
      case Silence :: r => sequence_of_partition(i)(d + 200)(r)
      case Note(Noire, h) :: r => ((i, h, On), d) :: ((i, h, Off), d + 400) :: sequence_of_partition(i)(d + 400)(r)
      case Note(Croche, h) :: r => ((i, h, On), d) :: ((i, h, Off), d + 200) :: sequence_of_partition(i)(d + 200)(r)
    }

  def main(args: Array[String]): Unit = {
    val mapartition: partition = Note(Croche, 70) :: Silence :: Note(Croche, 70) :: Silence :: Note(Croche, 77) :: Silence :: Note(Croche, 77) :: Silence :: Note(Croche, 79) :: Silence :: Note(Croche, 79) :: Silence :: Note(Noire, 77) :: Nil
    val masequence = sequence_of_partition(Tubular)(0)(mapartition)
    play_relatif(sortie)(masequence)
  }
}

