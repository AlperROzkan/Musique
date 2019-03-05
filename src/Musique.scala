object Musique {
  // On définit un type Duree n'ayant que deux valeurs possibles, Noire et Croche. Filtrables comme Nil
  sealed trait Duree
  case object Noire extends Duree
  case object Croche extends Duree

  type partition = List[(Int,Duree)]

  /* Creation d'une sorite MIDI*/
  val sortie = new Audio()

  // Jouer une note
  val playnote = (n:Int, v:Duree) =>
    //sortie.playNote(n,300,sortie.canalPiano)
    v match {
      case Noire => sortie.playNote(n,300,sortie.canalPiano)
      case Croche => sortie.playNote(n, 150, sortie.canalPiano)
    }

  // Jouer une partition
  val playlist : partition => Unit = (s) =>
    s match {
      case Nil => ()
      case (n,d) :: r => playnote(n,d) ; playlist(r)
    }

  /**
    * @param n : Increment sur les notes
    * @param s : Liste de partitions
    */
  val transpose : (Int, partition) => partition = (n,s) =>
    s match {
      case Nil => Nil
      case (e,duree)::r => ((e+n),duree)::transpose(n,r)
    }

  /**
    * Ajoute d'une octave (en utilisant transpose
    */
  val octave : partition => partition = l =>
    transpose(12,l)

/*
  def discoPar(l: List[Int]): List[Int] = {
    l match {
      case Nil => Nil
      case (n, d)::t => (n, d)::(n+12, d)::disco(t)
    }
  }

  def minorisePar(l: partition): partition = {
    l match {
      case Nil => Nil
      case (n1, d1)::(n2, d2)::(n3, d3)::t if n1 == n2-4 && n1 == n3-7
      => (n1, d1)::(n2-1, d2)::(n3, d3)::minorise(t)
      case h::t => h::minorise(t)
    }
  }
  */

  /*
  val repete3 = partition => partition = s  =>
    s match {
      case a::b::c::Nil => a::b::c::a::b::c::Nil
      case a::r => a::repete3(r)
      case Nil => Nil
    }
    */
  def main(args: Array[String]): Unit = {
    /*val maliste = (60,Croche) :: (64, Croche) :: (67,Croche) :: (72,Croche) :: (76,Croche) :: Nil
    playlist(maliste) // Normale

    Thread.sleep(1000)
    playlist(dedouble(maliste)) // octave

    Thread.sleep(1000)
    playlist(octave(maliste)) // octave

    Thread.sleep(1000)
    playlist(transpose(12, maliste)) // transpose*/
  }
}
