package com.model;

import java.util.ArrayList;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class AudioPlayer {
    private static AudioPlayer instance;
    private Song currentSong;
    private Instrument currentInstrument;
    private MeasureGroup currentMeasureGroup;
    private Note currentNote;
    private Player player;

    //Constructor
    public AudioPlayer() {
        this.player = new Player();
    }

    public static AudioPlayer getInstance(){
        if(instance==null){
            instance = new AudioPlayer();
        }
        return instance;
    }

    //Starts the playback speed of the song
    public void play() {
        player = new Player();
        Pattern pattern = generatePattern();
        player.delayPlay(500, pattern);
    }

    // Stop playback entirely
    public void stop() {
        player.getManagedPlayer().finish();
    }

    // Generate a full song pattern for JFugue
    private Pattern generatePattern() {
        Pattern pattern = new Pattern();
        pattern.add("T" + currentSong.getTempo()); // Sets the tempo

        ArrayList<Instrument> instruments = currentSong.getInstruments();
        for(int i=0; i<instruments.size(); i++){
            Instrument instrument = instruments.get(i);
            pattern.add("V" + i);
            pattern.add(generateInstrumentPattern(instrument));
        }

        return pattern;
    }

    private Pattern generateInstrumentPattern(Instrument instrument){
        Pattern pattern = new Pattern();
        pattern.add(" I[" + instrument.getName() + "] ");

        ArrayList<MeasureGroup> measureGroups = currentSong.getMeasureGroups();
        for(int i=measureGroups.indexOf(currentMeasureGroup); i<measureGroups.size(); i++){
            MeasureGroup measureGroup = measureGroups.get(i);
            Measure measure = measureGroup.getMeasure(instrument);
            pattern.add(generateMeasurePattern(measure) + "| ");
        }

        return pattern;
    }


    private Pattern generateMeasurePattern(Measure measure){
        Pattern pattern = new Pattern();
        ArrayList<Note> notes = measure.getNotes();
        for(Note note : notes){
            pattern.add(generateNotePattern(note) + " ");
        }

        return pattern;
    }

    private Pattern generateNotePattern(Note note){
        Pattern pattern = new Pattern();
        pattern.add(note.getJFugue());

        return pattern;
    }



    //--- EDITING/SELECTING ---//

    public ArrayList<Instrument> openSong(Song song){
        this.currentSong = song;
        this.currentInstrument = song.getInstruments().get(0);
        this.currentMeasureGroup = song.getMeasureGroups().get(0);
        this.currentNote = currentMeasureGroup.getMeasure(currentInstrument).getNotes().get(0);
        return song.getInstruments();
    }

    public ArrayList<Measure> selectInstrument(Instrument instrument){
        this.currentInstrument = instrument;
        return currentSong.getMeasures(instrument);
    }

    public void selectMeasure(Measure measure){
        this.currentMeasureGroup = currentSong.getMeasureGroup(measure);
    }

    public void selectNote(Note note){
        this.currentNote = note;
    }

    public ArrayList<Instrument> getInstruments(){
        return this.currentSong.getInstruments();
    }

    public void addInstrument(Instrument instrument){
        currentSong.addInstrument(instrument);
    }

    public void removeInstrument(Instrument instrument){
        currentSong.removeInstrument(instrument);
    }

    public ArrayList<Note> getNotes(Measure measure){
        return measure.getNotes();
    }

    public boolean setBPM(int BPM){
        return currentSong.setTempo(BPM);
    }

    public void setChord(Chord chord){
        currentMeasureGroup.setChord(chord);
    }

    public void insertMeasure(){
        currentSong.insertMeasure(currentMeasureGroup);
    }

    public void deleteMeasure(){
        currentSong.deleteMeasure(currentMeasureGroup);
    }

    public void insertNote(){
        currentSong.insertNote(currentNote);
    }

    public void deleteNote(){
        currentNote.setPitch(Pitch.REST);
    }

    public boolean noteUp(){
        return currentSong.noteUp(currentNote);
    }

    public boolean noteDown(){
        return currentSong.noteDown(currentNote);
    }

    public boolean splitNote(int division){
        return currentMeasureGroup.getMeasure(currentInstrument).splitNote(currentNote, division);
    }

    public boolean combineNotes(){
        return currentMeasureGroup.getMeasure(currentInstrument).combineNotes(currentNote);
    }
}