import javax.sound.midi.* ;

public class Audio {
    
    final static int piano = 0 ;
    final static int tubular = 14 ;
    
    public MidiChannel canalPiano ;
    public MidiChannel canalTubular ;


    public Audio() throws MidiUnavailableException {
	Synthesizer syn = MidiSystem.getSynthesizer();
	syn.open();
	final MidiChannel[] canaux = syn.getChannels();
	
	this.canalPiano = canaux[0] ;
	this.canalPiano.programChange(0,piano);
	
	this.canalTubular = canaux[1] ;
	this.canalTubular.programChange(0,tubular);
    }


    public void silence(int duree) throws InterruptedException {
	Thread.sleep(duree);
    }

    /** hauteur entre 0 et 127 */
    public void playNote (int hauteur, int duree, MidiChannel chan) throws InterruptedException {
	chan.noteOn(hauteur,600);
	Thread.sleep(duree);
	chan.noteOff(hauteur,600);
    }
    

/*    public static void main(String[] args){

	try {
	    Audio out = new Audio();
	    out.playNote(60, 300, out.canalPiano) ;
	    out.playNote(64, 300, out.canalPiano);
	    out.playNote(67, 4000, out.canalTubular);
	}
	catch (Exception e ){ System.exit(-1); }
	
    }*/
}
