package com.model;

import java.util.ArrayList;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

/**
 * TODO
 */
public class AudioPlayer {
    private static AudioPlayer instance;
    private Song currentSong;
    private Instrument currentInstrument;
    private MeasureGroup currentMeasureGroup;
    private Note currentNote;
    private Player player;

    /**
     * TODO
     */
    public AudioPlayer() {
        this.player = new Player();
    }

    public static AudioPlayer getInstance(){
        if(instance==null){
            instance = new AudioPlayer();
        }
        return instance;
    }

    /**
     * TODO
     */
    public void play() {
        player = new Player();
        Pattern pattern = generatePattern();
        player.delayPlay(500, pattern);
    }

    /*
     * TODO
     */
    public void stop() {
        player.getManagedPlayer().finish();
    }

    /**
     * TODO
     * @return
     */
    private Pattern generatePattern() {
        Pattern pattern = new Pattern();
        pattern.add("T" + currentSong.getTempo());

        ArrayList<Instrument> instruments = currentSong.getInstruments();
        for(int i=0; i<instruments.size(); i++){
            Instrument instrument = instruments.get(i);
            pattern.add("V" + i);
            pattern.add(generateInstrumentPattern(instrument));
        }

        return pattern;
    }

    /**
     * TODO
     * @param instrument
     * @return
     */
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

    /**
     * TODO
     * @param measure
     * @return
     */
    private Pattern generateMeasurePattern(Measure measure){
        Pattern pattern = new Pattern();
        ArrayList<Note> notes = measure.getNotes();
        for(Note note : notes){
            pattern.add(generateNotePattern(note) + " ");
        }

        return pattern;
    }

    /**
     * TODO
     * @param note
     * @return
     */
    private Pattern generateNotePattern(Note note){
        Pattern pattern = new Pattern();
        pattern.add(note.getJFugue());

        return pattern;
    }



    //--- EDITING/SELECTING ---//

    /**
     * TODO
     * @param song
     * @return
     */
    public ArrayList<Instrument> openSong(Song song){
        this.currentSong = song;
        this.currentInstrument = song.getInstruments().get(0);
        this.currentMeasureGroup = song.getMeasureGroups().get(0);
        this.currentNote = currentMeasureGroup.getMeasure(currentInstrument).getNotes().get(0);
        return song.getInstruments();
    }

    /**
     * 
     * @param instrument
     * @return
     */
    public ArrayList<Measure> selectInstrument(Instrument instrument){
        this.currentInstrument = instrument;
        return currentSong.getMeasures(instrument);
    }

    /**
     * TODO
     * @param measure
     */
    public void selectMeasure(Measure measure){
        this.currentMeasureGroup = currentSong.getMeasureGroup(measure);
    }

    /**
     * TODO
     * @param note
     */
    public void selectNote(Note note){
        this.currentNote = note;
    }

    /**
     * TODO
     * @return
     */
    public ArrayList<Instrument> getInstruments(){
        return this.currentSong.getInstruments();
    }

    /**
     * TODO
     * @param instrument
     */
    public void addInstrument(Instrument instrument){
        currentSong.addInstrument(instrument);
    }

    /**
     * TODO
     * @param instrument
     */
    public void removeInstrument(Instrument instrument){
        currentSong.removeInstrument(instrument);
    }

    /**
     * TODO
     * @param measure
     * @return
     */
    public ArrayList<Note> getNotes(Measure measure){
        return measure.getNotes();
    }

    /**
     * TODO
     * @param BPM
     * @return
     */
    public boolean setBPM(int BPM){
        return currentSong.setTempo(BPM);
    }

    /**
     * TODO
     * @param chord
     */
    public void setChord(Chord chord){
        currentMeasureGroup.setChord(chord);
    }

    /**
     * TODO
     */
    public void insertMeasure(){
        currentSong.insertMeasure(currentMeasureGroup);
    }

    /**
     * TODO
     */
    public void deleteMeasure(){
        ArrayList<MeasureGroup> measureGroups = currentSong.getMeasureGroups();
        MeasureGroup deletedMeasureGroup = currentMeasureGroup;
        
        int index = measureGroups.indexOf(deletedMeasureGroup);
        if(index>=measureGroups.size()-1)
            currentMeasureGroup = measureGroups.get(measureGroups.size()-2);
        else
            currentMeasureGroup = measureGroups.get(index-1);

        currentSong.deleteMeasure(deletedMeasureGroup);
    }

    /**
     * TODO
     */
    public void insertNote(){
        currentSong.insertNote(currentNote);
    }

    /**
     * TODO
     */
    public void deleteNote(){
        currentNote.setPitch(Pitch.REST);
    }

    /**
     * TODO
     * @return
     */
    public boolean noteUp(){
        return currentSong.noteUp(currentNote);
    }

    /**
     * TODO
     * @return
     */
    public boolean noteDown(){
        return currentSong.noteDown(currentNote);
    }

    /**
     * TODO
     * @param division
     * @return
     */
    public boolean splitNote(int division){
        return currentMeasureGroup.getMeasure(currentInstrument).splitNote(currentNote, division);
    }

    /**
     * TODO
     * @return
     */
    public boolean combineNotes(){
        return currentMeasureGroup.getMeasure(currentInstrument).combineNotes(currentNote);
    }
}