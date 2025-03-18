import org.jfugue.player.Player;
import org.jfugue.pattern.Pattern;
import java.util.List;

public class AudioPlayer {
    private Song song;
    private Instrument instrument;
    private int currentMeasureIndex;
    private double playbackSpeed = 1.0;
    private int bpm = 120;
    private boolean isPlaying;
    private Player player;

    //Constructor
    public AudioPlayer(Song song, Instrument instrument) {
        this.song = song;
        this.instrument = instrument;
        this.currentMeasureIndex = 0;
        this.player = new Player();
        this.isPlaying = false;
    }

    //Starts the playback speed of the song
    public void play() {
        if(song == null) {
            System.out.println("No song loaded.");
            return;
        }

        isPlaying = true;
        Pattern musicPattern = generatePattern();
        player.play(musicPattern);
    }

    // Pause playback (JFugue does not have a built in pause method, so we stop instead. This instead need to be hard-coded.)
    public void pause() {
        if(isPlaying) {
            player.getManagedPlayer().finish();
            isPlaying = false;
        }
    }

    // Stop playback entirely
    public void stop() {
        player.getManagedPlayer().finish();
        isPlaying = false;
        currentMeasureIndex = 0;
    }

    // Move to the next measure
    public void nextMeasure() {
        if (currentMeasureIndex < song.getMeasures().size() -1) {
            currentMeasureIndex++;
            playCurrentMeasure();
        }
    }

    // Move to the previous measure
    public void previousMeasure() {
        if (currentMeasureIndex > 0) {
            currentMeasureIndex--;
            playCurrentMeasure();
        }
    }

    // Set playback speed
    public void setPlaybackSpeed(double speed) {
        if (speed > 0) {
            this.playbackSpeed = speed;
        }
    }

    // Set BPM (beats per minute in case you didn't know that already) 
    public void setBPM(int bpm) {
        if (bpm > 0) {
            this.bpm = bpm;
        }
    }

    // Play the current measure only
    private void playCurrentMeasure() {
        Pattern measurePattern = generateMeasurePattern(song.getMeasures().get(currentMeasureIndex)) {
            player.play(measurePattern);
        }

        // Generate a full song pattern for JFugue
        private Pattern generatePattern() {
            Pattern pattern = new Pattern();
            pattern.add("T" + bpm); // Sets the tempo

            for (Measure measure : song.getMeasures()) {
                pattern.add(generateMeasurePattern(measure));
            }

            return pattern;
        }

        // Generate a pattern for a single measure
        private Pattern generateMeasurePattern(Measure measure) {
            Pattern measurePattern  = new Pattern();
            measurePattern.add("T" + bpm); // Sets the tempo

            for (Part part : measure.getParts()) {
                measurePattern.add(generatePartPattern(part));
            }

            return measurePattern;
        }

        // Generate a pattern for an individual part within a measure
        private Pattern generatePrtPattern(Part part) {
            Pattern partPattern = new Pattern();

            for (Note note : part.getMusic()) {
                partPattern.add(note.toString() + " "); // Note objects should have a proper 'toString()'
            }

            return partPattern;
        }
    }
}