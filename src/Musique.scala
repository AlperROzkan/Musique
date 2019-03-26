import Action._
import MesPartitions._
import Midi._
import Partition._
import Sequence._

object Musique {
  val sortie = new Audio()

  /**
    * Transforme les segments en sequences.
    */
  val seg_to_seq : Instrument => Int => segment => sequence = inst => tempo => seg => {
    seg match {
      case Nil                  => Stream.Empty
      case Silence::t           => Wait(tempo) #:: seg_to_seq(inst)(tempo)(t)
      case Note(Croche, h, p):: t  => Event(inst, h, On(p)) #:: Wait(tempo) #:: Event(inst, h, Off) #:: seg_to_seq(inst)(tempo)(t)
      case Note(Noire, h, p)::t    => Event(inst, h, On(p)) #:: Wait(tempo * 2) #:: Event(inst, h, Off) #:: seg_to_seq(inst)(tempo)(t)

    }
  }

  /**
    * Transforme les partitions en sequences
    */
  val part_to_seq: Instrument => Int => partition => sequence = inst => tempo => part => {
    part match {
      case Nil             => Stream.Empty
      case (Nb(1), seg)::t => seg_to_seq(inst)(tempo)(seg) #::: part_to_seq(inst)(tempo)(t)
      case (Nb(n), seg)::t => seg_to_seq(inst)(tempo)(seg) #::: part_to_seq(inst)(tempo)((Nb(n-1), seg)::t)
      case (AdLib, seg)::t => seg_to_seq(inst)(tempo)(seg) #::: part_to_seq(inst)(tempo)((AdLib, seg)::t)

    }
  }

  /**
    * Combine deux sequences en une seule.
    * s1 :
    * s2 :
    */
  val combine_seq: sequence => sequence => sequence = s1 => s2 => {
    (s1,s2) match {
      case (Wait(n1)#::t1, Wait(n2)#::t2) if n1 > n2 => Wait(n2) #:: combine_seq(Wait(n1 - n2)#::t1)(t2)
      case (Wait(n1)#::t1, Wait(n2)#::t2) if n1 < n2 => Wait(n1) #:: combine_seq(t1)(Wait(n2 - n1)#::t2)
      case (Wait(n1)#::t1, Wait(n2)#::t2)            => Wait(n1) #:: combine_seq(t1)(t2)
      case (Event(i, h, s)#::t1, _#::t2)             => Event(i, h, s) #:: combine_seq(t1)(s2)
      case (_#::t1, Event(i, h, s)#::t2)             => Event(i, h, s) #:: combine_seq(s1)(t2)
      case (_, Stream.Empty)                         => s1
      case (Stream.Empty, _)                         => s2
    }
  }

  def main(args: Array[String]): Unit = {
    play(sortie)(part_to_seq(Piano)(150)(mapartition))
  }
}
