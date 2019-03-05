/*object AncienMusique {
  /* Creation d'une sorite MIDI*/
  val sortie = new Audio()

  // Jouer une note
  val playnote: Int => Unit = (n:Int) =>
    sortie.playNote(n,300,sortie.canalPiano)

  // Jouer une partition de notes
  val playList : List[Int]=> Unit = {
    case Nil => ()
    case e :: r => playnote(e); playList(r)
  }

  val transpose : (Int, List[Int]) => List[Int] = (n,s) =>
    s match {
      case Nil  => Nil
      case e::r => (e+n):: transpose(n,r)
    }

  val octave : List[Int] => List[Int] = l =>
    transpose(12,l)

  val dedouble : List[Int] => List[Int] = {
    case Nil => Nil
    case e :: r => e :: (e - 12) :: dedouble(r)
  }

  val minorise : List[Int] => List[Int] = {
    case Nil => Nil
    case e1 :: e2 :: e3 :: r if e2 == e1 + 4 && e3 == e1 + 7 => e1 :: (e2 - 1) :: e3 :: (minorise(r))
    case e :: r => (minorise(r))
  }

  def main(args: Array[String]): Unit = {
    //playnote (60)
    //playList(60 :: 64 :: 67 :: Nil)

    val mapartition = 70::74::77::Nil
    val mapartition2 = transpose(20,mapartition)

    playList(mapartition)
    Thread.sleep(1000) // wait for 1000 millisecond
    playList(mapartition2)
  }
}
*/