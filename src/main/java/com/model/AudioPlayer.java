package com.model;

import java.util.ArrayList;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

/**
 * Class that uses JFugue and combination with the logic of other classes to
 * create sound. Uses singleton logic
 * 
 * @author James Morris
 */
public class AudioPlayer {
    private static AudioPlayer instance;
    private Song currentSong;
    private Instrument currentInstrument;
    private MeasureGroup currentMeasureGroup;
    private Note currentNote;
    private Player player;

    /**
     * Private constructor that creates the singleton instance of the AudioPlayer
     * class
     */
    private AudioPlayer() {
        this.player = new Player();
    }

    /**
     * Static method that accessses the private constructor
     * 
     * @return The singleton instance of the AudioPlayer
     */
    public static AudioPlayer getInstance() {
        if (instance == null) {
            instance = new AudioPlayer();
        }
        return instance;
    }

    /**
     * Method that runs the audio for a given song (using the Player JFugue object)
     */
    public void play() {
        stop();
        player = new Player();
        player.getManagedPlayer().reset();
        Pattern pattern = generatePattern();
        player.delayPlay(0, pattern);
    }

    /*
     * Method that ends the Player object
     */
    public void stop() {
        if(player.getManagedPlayer().isPlaying())
            player.getManagedPlayer().pause();
        player.getManagedPlayer().finish();
    }

    // --- EDITING/SELECTING ---//

    /**
     * Opens a song which will be played
     * 
     * @param song The Song object that will be played
     * @return The instrumentList that is in a song (which is an ArrayList of type
     *         Instrument)
     */
    public ArrayList<Instrument> openSong(Song song) {
        this.currentSong = song;
        this.currentInstrument = song.getInstruments().get(0);
        this.currentMeasureGroup = song.getMeasureGroups().get(0);
        this.currentNote = currentMeasureGroup.getMeasure(currentInstrument).getNotes().get(0);
        return song.getInstruments();
    }

    /**
     * Selects the current Instrument
     * 
     * @param instrument Instrument selected
     * @return The list of Measures for this instrument
     */
    public ArrayList<Measure> selectInstrument(Instrument instrument) {
        this.currentInstrument = instrument;
        this.currentMeasureGroup = currentSong.getMeasureGroups().get(0);
        this.currentNote = currentMeasureGroup.getMeasure(instrument).getNotes().get(0);
        return currentSong.getMeasures(instrument);
    }

    /**
     * Selects a Measure
     * 
     * @param measure Measure to select
     */
    public void selectMeasure(Measure measure) {
        this.currentMeasureGroup = currentSong.getMeasureGroup(measure);
        this.currentNote = measure.getNotes().get(0);
    }

    /**
     * Selects a Note
     * 
     * @param note Note to select
     */
    public void selectNote(Note note) {
        this.currentNote = note;
    }

    /**
     * Accessor for the instrument ArrayList (of type Instrument) of the currentSong
     * Song object
     * 
     * @return The current Song's instrument ArrayList
     */
    public ArrayList<Instrument> getInstruments() {
        return this.currentSong.getInstruments();
    }

    /**
     * Adds an Instrument to the currentSong Song object
     * 
     * @param instrument Instrument to be added
     */
    public void addInstrument(Instrument instrument) {
        currentSong.addInstrument(instrument);
    }

    /**
     * Removes an Instrument from the currentSong Song object
     * 
     * @param instrument Instrument to be removed
     * @throws Exception When currentSong has only one instrument (which cannot be
     *                   removed)
     */
    public void removeInstrument(Instrument instrument) throws Exception {
        currentSong.removeInstrument(instrument);
    }

    /**
     * Accessor for the ArrayList of Notes within a given Measure
     * 
     * @param measure The Measure from which the Notes are being accessed from
     * @return an Arraylist of type Note taken from the parameter
     */
    public ArrayList<Note> getNotes(Measure measure) {
        return measure.getNotes();
    }

    /**
     * sets the BPM of the currentSong Song object
     * 
     * @param BPM Integer which is the BPM
     * @throws Exception When BPM is less than 30 or greater than 400
     */
    public void setBPM(int BPM) throws Exception {
        currentSong.setTempo(BPM);
    }

    /**
     * Mutator that sets the Chord for the currentMeasureGroup object of type
     * MeasureGroup
     * 
     * @param chord Chord to be set
     */
    public void setChord(Chord chord) {
        currentMeasureGroup.setChord(chord);
    }

    /**
     * Returns the measures of the current selected instrument
     * 
     * @return Measures of the current instruments
     */
    public ArrayList<Measure> getMeasures(){
        return currentSong.getMeasures(currentInstrument);
    }

    /**
     * Inserts a measure into the currentSong object of type Song (for all
     * Instruments)
     */
    public void insertMeasure() {
        currentSong.insertMeasure(currentMeasureGroup);
    }

    /**
     * Removes a measure from the currentSong object of type Song (for all
     * Instruments)
     * 
     * @throws Exception When currentSong has 1 or less measures
     */
    public void removeMeasure() throws Exception {
        ArrayList<MeasureGroup> measureGroups = currentSong.getMeasureGroups();
        if(measureGroups.size()<=1)
            throw new Exception("Song cannot have 0 measures");

        MeasureGroup deletedMeasureGroup = currentMeasureGroup;
        int index = measureGroups.indexOf(deletedMeasureGroup);

        if (index <= 0)
            currentMeasureGroup = measureGroups.get(1);
        if (index >= measureGroups.size() - 1)
            currentMeasureGroup = measureGroups.get(measureGroups.size() - 2);
        else
            currentMeasureGroup = measureGroups.get(index - 1);

        currentSong.removeMeasure(deletedMeasureGroup);
    }

    /**
     * inserts a Note into the currentSong object of type Song
     */
    public void insertNote() {
        currentSong.insertNote(currentNote);
    }

    /**
     * turns the currentNote object (of type Note) into a rest
     */
    public void deleteNote() {
        currentNote.setPitch(Pitch.REST);
    }

    /**
     * inserts a Chord into the position of the currentNote
     */
    public void insertChord() {
        if (currentNote.getPitch() != Pitch.REST)
            return;
        currentNote.setPitch(Pitch.CHORD);
    }

    /**
     * increases the currentNote by one scale degree
     * 
     * @throws Exception If by increasing the currentNote, the octave of that note
     *                   is above 6
     */
    public void noteUp() throws Exception {
        currentSong.noteUp(currentNote);
    }

    /**
     * decreases the currentNote by one scale degree
     * 
     * @throws Exception if by decreasing the currentNote, the octave of that note
     *                   is lower than 1
     */
    public void noteDown() throws Exception {
        currentSong.noteDown(currentNote);
    }

    /**
     * Splits the current note into equal parts (half note to two quarter notes,
     * quarter note to two eighth notes, etc)
     * 
     * @param division The divsor for the division operation. is an int
     * @throws Exception if division is less than 2 or greater than 4
     */
    public void splitNote(int division) throws Exception {
        currentMeasureGroup.getMeasure(currentInstrument).splitNote(currentNote, division);
    }

    /**
     * Changes the current note into a quarter note (and removes any notes necessary
     * to keep the measure size correct)
     * 
     * @throws Exception if the Current note is equal to or greater than a quarter
     *                   note (such as a half note)
     */
    public void combineNotes() throws Exception {
        currentMeasureGroup.getMeasure(currentInstrument).combineNotes(currentNote);
    }

    /**
     * Ensures that the currentUser is the author of the currentSong (to see if they
     * may edit it)
     * 
     * @return True if the currentUser is the author of the currentSong, false if
     *         not
     */
    public boolean editPermission() {
        User currentUser = UserList.getInstance().getCurrentUser();
        return currentSong.getAuthor().equals(currentUser);
    }

    /**
     * turns all the currentSong, currentInstrument, currentMeasureGroup, and
     * currentNote null
     */
    public void logout() {
        currentSong = null;
        currentInstrument = null;
        currentMeasureGroup = null;
        currentNote = null;
    }

    /**
     * Prints the current song to a .txt file
     * 
     * @throws Exception If the fileWriter is not able to write to a .txt file
     */
    public void printSong() throws Exception {
        MusicPrinter.printSong(currentSong, currentInstrument);
    }

    /**
     * Returns the tempo of the current song
     * 
     * @return int tempo of the current song
     */
    public int getBPM(){
        return currentSong.getTempo();
    }

    /**
     * Turns a given song into a Pattern object that JFugue can read
     * 
     * @return A new pattern object that is derived from the currentSong object (of
     *         type Song)
     */
    private Pattern generatePattern() {
        Pattern pattern = new Pattern();
        pattern.add("T" + currentSong.getTempo());

        ArrayList<Instrument> instruments = currentSong.getInstruments();
        for (int i = 0; i < instruments.size(); i++) {
            Instrument instrument = instruments.get(i);
            pattern.add("V" + i);
            pattern.add(generateInstrumentPattern(instrument));
        }

        return pattern;
    }

    /**
     * Turns a currentSong's part for a spefic instrument into a Pattern object that
     * JFugue can read from
     * 
     * @param instrument The instrument whose part will be translated into a Pattern
     * @return A new pattern object derived from the "instrument" parameter's
     *         measures
     */
    private Pattern generateInstrumentPattern(Instrument instrument) {
        Pattern pattern = new Pattern();
        pattern.add(" I[" + instrument.getName() + "] ");

        ArrayList<MeasureGroup> measureGroups = currentSong.getMeasureGroups();
        for (int i = measureGroups.indexOf(currentMeasureGroup); i < measureGroups.size(); i++) {
            MeasureGroup measureGroup = measureGroups.get(i);
            Chord chord = measureGroup.getChord();
            Measure measure = measureGroup.getMeasure(instrument);
            pattern.add(generateMeasurePattern(measure, chord) + "| ");
        }

        return pattern;
    }

    /**
     * Turns a given measure from a given instrument (taken from the currentSong
     * Song) into a Pattern object that JFugue can read from
     * 
     * @param measure The measure taken from the generateInstrumentPattern's
     *                Instrument
     * @param chord   The chord taken from the generateInstrumentPattern's
     *                Instrument
     * @return A new pattern object derived from the parameters
     */
    private Pattern generateMeasurePattern(Measure measure, Chord chord) {
        Pattern pattern = new Pattern();
        ArrayList<Note> notes = measure.getNotes();
        for (Note note : notes) {
            pattern.add(generateNotePattern(note, chord) + " ");
        }

        return pattern;
    }

    /**
     * Turns a given note from a measure (taken from the generateMeasurePattern)
     * into a Pattern object that JFugue can read from
     * 
     * @param note  The note taken from the generateMeasurePattern's measure
     * @param chord The chord taken from the generateInstrumentPattern's
     *              Instrument
     * @return A new pattern object derived from the parameters
     */
    private Pattern generateNotePattern(Note note, Chord chord) {
        Pattern pattern = new Pattern();
        pattern.add(note.getJFugue(chord));

        return pattern;
    }
}