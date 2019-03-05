object MusiqueTraits {

  // On définit un type Duree n'ayant que deux valeurs possibles, Noire et Croche. Filtrables comme Nil
  sealed trait Duree
  case object Noire extends Duree
  case object Croche extends Duree

  sealed trait Donnee
  case object Silence extends Donnee
  final case class Note(duree: Duree, hauteur: Int) extends Donnee

  /* Creation d'une sorite MIDI*/
  val sortie = new Audio()

  val playdonnee = (d: Donnee) =>
    d match {
      case Silence => sortie.silence(200)
      case Note(Noire, n) => sortie.playNote(n, 400, sortie.canalPiano)
      case Note(Croche, n) => sortie.playNote(n, 200, sortie.canalPiano)
    }

  type partition = List[Donnee]


  val iter: (Donnee => Unit) => partition => Unit = f => s =>
    s match {
      case Nil => ()
      case e :: r => {
        f(e)
        iter(f)(r)
      }
    }

  val play_partition = iter(playdonnee)

  /**
    * Ajoute n en hauteur
    */
  val transpose: Int => partition => partition = n => s =>
    s match {
      case Nil => Nil
      case Note(a, h) :: r => Note(a, h + n) :: transpose(n)(r)
      case Silence :: r => Silence :: transpose(n)(r)
    }

  /**
    * Ajoute 12 en hauteur
    */
  val octave = transpose(12)

  /**
    * Remplace les silences par des croches de la hauteur de la note precedente
    */
  val remove_silence : partition => partition = p =>
    p match {
      case Nil => Nil
      case Note(d,h) :: Silence :: r => Note(d,h) :: Note (Croche,h) :: remove_silence(r)
      case c::r => c::remove_silence(r)
    }


  /**
    * Remplacer noire suivie d'une autre note par croche, silence ,note
    */
  val detache : partition => partition = p =>
    p match {
      case Nil => Nil
      case Note(Noire,h) :: Note(a,h2) :: r => Note(Croche,h) :: Silence :: Note(a,h2) :: detache(r)
      case e::r => e :: detache(r)
    }


  val reverse_aux : partition => partition => partition = s => accu =>
    s match {
      case Nil => accu
      case e::r => reverse_aux(r)(e::accu)
    }

  /**
    * Joue une partition à l'envers
    * A besoin de reverse_aux
    */
  val reverse: partition => partition = (s : partition) => reverse_aux(s)(Nil)

  /**
    * Remplace les croches par des noires
    */
  

  val mapartition: partition = Note(Croche, 70) :: Silence :: Note(Croche, 70) :: Silence :: Note(Croche, 77) :: Silence :: Note(Croche, 77) :: Silence :: Note(Croche, 79) :: Silence :: Note(Croche, 79) :: Silence :: Note(Croche, 77) :: Nil
  val pi: partition = Note(Croche, 31) :: Note(Croche, 41) :: Note(Croche, 59) :: Note(Croche, 26) :: Note(Croche, 53) :: Silence :: Note(Croche, 58) :: Note(Croche, 97) :: Note(Croche, 93) :: Note(Croche, 23) :: Note(Croche, 84) :: Note(Croche, 62) :: Note(Croche, 64) :: Note(Croche, 33) :: Note(Croche, 83) :: Note(Croche, 27) :: Note(Croche, 95) :: Note(Croche, 2) :: Note(Croche, 88) :: Note(Croche, 41) :: Note(Croche, 97) :: Note(Croche, 16) :: Note(Croche, 93) :: Note(Croche, 99) :: Note(Croche, 37) ::Nil
  val maPartitionPourDetache : partition = Note(Noire, 70) :: Note(Croche, 100) :: Nil


  def main(args: Array[String]): Unit = {

    play_partition(pi)
    play_partition(reverse(pi))
    play_partition(octave(pi))
    play_partition(transpose(21)(pi))


    /*
    play_partition(maPartitionPourDetache)

    Thread.sleep(1000)
    play_partition(detache(maPartitionPourDetache))


    Thread.sleep(100)
    play_partition(mapartition)

    Thread.sleep(100)
    play_partition(remove_silence(mapartition))

    Thread.sleep(100)
    play_partition(transpose(12)(mapartition))

    Thread.sleep(100)
    play_partition(octave(mapartition))
    */
  }
}
