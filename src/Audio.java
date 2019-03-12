import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class Audio {

    final static int piano = 0;
    final static int tubular = 14;

    public MidiChannel canalPiano;
    public MidiChannel canalTubular;


    public Audio() throws MidiUnavailableException {
        Synthesizer syn = MidiSystem.getSynthesizer();
        syn.open();
        final MidiChannel[] canaux = syn.getChannels();

        this.canalPiano = canaux[0];
        this.canalPiano.programChange(0, piano);

        this.canalTubular = canaux[1];
        this.canalTubular.programChange(0, tubular);
    }


}
