/*object ScalaApp {
  def somme(s: List[Int]): Int = {
    s match {
      case Nil => 0
      case e :: r => e + somme(r)
    }
  }

  // On passe une fonction en tant que parametre
  def compte(p: Int => Boolean, s: List[Int]): Int = {
    s match {
      case Nil => 0
      case e :: r =>
        if (p(e)) {
          1 + compte(p, r)
        }
        else
          compte(p, r)
    }
  }

  def superieurADix(cmp: Int): Boolean = {
    if (cmp > 10) {
      return true
    }
    else {
      return false
    }
  }

  def main(args: Array[String]): Unit = {
    val sortie = new Audio()

    val playnote = (n:Int) => sortie.playNote(n,300,sortie.canalPiano)

    playnote (60)

    val playList : List[Int]=> Unit = (s:List[Int]) =>

      s match {
        case Nil => ()
        case e::r => playnote(e) ; playList(r)
      }

    playList(60 :: 64 :: 67 :: Nil)
  }
}*/