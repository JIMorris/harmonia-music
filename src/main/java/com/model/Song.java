package com.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * class that creates a Song object which has a songID (UUID), title (String),
 * author (User), description (String), arraylist of genres and
 * reactions (of types Genre and Reaction), difficulty (int), and whether or not
 * it is public
 * (boolean).
 * 
 * a song will consist of a tempo, key signature, time signature, a arraylist of
 * measures,
 * and an arraylist of insturments.
 * 
 * @author Simion Cartis
 */
public class Song {
    private UUID songID;
    private String title;
    private User author;
    private String description;
    private ArrayList<Genre> genres;
    private int difficulty;
    private ArrayList<Reaction> reactions;
    private boolean published;

    private int tempo;
    private Key keySignature;
    private int timeSignatureNum;
    private int timeSignatureDen;
    private ArrayList<MeasureGroup> measureGroups;
    private ArrayList<Instrument> instruments;

    /**
     * Creates a new empty song
     * 
     * @param title String for the title of the song
     * @param author User that authored the song
     * @param description String for the description of the song
     * @param genres List of Genres for this song
     * @param difficulty int difficulty of the song (1-5)
     * @param tempo int tempo of the song (30-400)
     * @param keySignature Key of the song
     * @param timeSignatureNum Top number of the time signature
     * @param timeSignatureDen Bottom number of the time signature
     */
    public Song(String title, User author, String description, ArrayList<Genre> genres, int difficulty, int tempo, Key keySignature, int timeSignatureNum, int timeSignatureDen, Instrument defaultInstrument) {
        this.songID = UUID.randomUUID();
        this.title = title;
        this.author = author;
        this.description = description;
        this.genres = genres;
        this.difficulty = difficulty;
        this.reactions = new ArrayList<>();
        this.published = false;
        this.tempo = tempo;
        this.keySignature = keySignature;
        this.timeSignatureNum = timeSignatureNum;
        this.timeSignatureDen = timeSignatureDen;
        this.instruments = new ArrayList<>();
        this.measureGroups = new ArrayList<>();
        instruments.add(defaultInstrument);
        this.measureGroups.add(new MeasureGroup(timeSignatureNum, keySignature.rootChord, instruments));
    }

    /**
     * constructor used for making a copy of a song
     * 
     * @param song takes in a song to be copied
     */
    public Song(Song song, User author) {
        this.songID = UUID.randomUUID();
        this.title = song.getTitle();
        this.author = author;
        this.description = song.getDescription();
        this.genres = (ArrayList<Genre>)song.getGenres().clone();
        this.difficulty = song.getDifficulty();
        this.reactions = new ArrayList<>();
        this.published = false;
        this.tempo = song.getTempo();
        this.keySignature = song.getKeySignature();
        this.timeSignatureNum = song.getTimeSignatureNum();
        this.timeSignatureDen = song.getTimeSignatureDen();
        this.measureGroups = copyMeasureGroups(song.getMeasureGroups());
        this.instruments = (ArrayList<Instrument>)song.getInstruments().clone();
    }

    /**
     * Constructor for loading from JSON
     * 
     * @param id UUID of this song
     * @param title String title of this song
     * @param author User that authored this song
     * @param description String description of this song
     * @param genres List of genres this song matches
     * @param difficulty int difficulty of this song from 1-5
     * @param reactions List of reactions to this song
     * @param published Boolean of whether this song is public
     * @param tempo int tempo of this song from 30-400
     * @param keySignature Key of this song
     * @param timeSignatureNum Top number of the key signature of this song
     * @param timeSignatureDen Bottom number of the key signature of this song
     * @param measures List of the measures of this song
     * @param instruments List of instruments in this song
     */
    public Song(UUID id, String title, User author, String description, ArrayList<Genre> genres,
            int difficulty, ArrayList<Reaction> reactions, boolean published, int tempo,
            Key keySignature, int timeSignatureNum, int timeSignatureDen, ArrayList<MeasureGroup> measures, ArrayList<Instrument> instruments){
        this.songID = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.genres = genres;
        this.difficulty = difficulty;
        this.reactions = reactions;
        this.published = published;
        this.tempo = tempo;
        this.keySignature = keySignature;
        this.timeSignatureNum = timeSignatureNum;
        this.timeSignatureDen = timeSignatureDen;
        this.measureGroups = measures;
        this.instruments = instruments;
    }

    public UUID getSongID () {
        return this.songID;
    }

    public String getTitle() {
        return title;
    }

    public User getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public ArrayList<Reaction> getReactions() {
        return reactions;
    }

    public boolean isPublished() {
        return published;
    }

    public int getTempo() {
        return tempo;
    }

    public Key getKeySignature() {
        return keySignature;
    }

    public int getTimeSignatureNum() {
        return timeSignatureNum;
    }

    /**
     * 
     * @return
     */
    public int getTimeSignatureDen() {
        return timeSignatureDen;
    }

    /**
     * 
     * @return
     */
    public ArrayList<MeasureGroup> getMeasureGroups() {
        return measureGroups;
    }

    /**
     * 
     * @return
     */
    public ArrayList<Instrument> getInstruments() {
        return instruments;
    }

    /**
     * 
     * @param tempo
     */
    public void setTempo(int tempo) throws Exception{
        if(tempo<30 || tempo>400)
            throw new Exception("Tempo must be between 30 and 400");
        this.tempo = tempo;
    }

    public ArrayList<Measure> getMeasures(Instrument instrument){
        ArrayList<Measure> measures = new ArrayList<>();
        for(MeasureGroup measureGroup : measureGroups){
            measures.add(measureGroup.getMeasure(instrument));
        }
        return measures;
    }

    /**
     * TODO
     * @param measure
     * @return
     */
    public MeasureGroup getMeasureGroup(Measure measure){
        for(MeasureGroup measureGroup : measureGroups){
            for(Instrument instrument : instruments){
                if(measureGroup.getMeasure(instrument) == measure)
                    return measureGroup;
            }
        }
        return null;
    }

    public ArrayList<UUID> getInstrumentIDs() {
        ArrayList<UUID> instrumentIDs = new ArrayList<>();
        for (Instrument instrument : instruments) {
            instrumentIDs.add(instrument.getInstrumentID());
        }
        return instrumentIDs;
    }
    

    /**
     * checks if a song's UUID is the same as another UUID
     * 
     * @param songID takes in a UUID of another song
     * @return returns true if the UUID is the same as a songs, false if not
     */
    public boolean idMatch(UUID songID) {
        return this.songID.equals(songID);
    }

    /**
     * creates a reaction for a song and adds it to the Song's reactions arraylist
     * 
     * @param rating  takes in a rating for the reaction (of type int)
     * @param comment takes in a comment for the reaction (of type String)
     * @param author  takes in an author for the reaction (of type User)
     * @return returnsR the reaction that has been created
     */
    public Reaction addReaction(int rating, String comment, User author) {
        Reaction reaction = new Reaction(rating, comment, author);
        this.reactions.add(reaction);
        return reaction;
    }

    /**
     * TODO
     * @param reaction
     * @throws Exception
     */
    public void removeReaction(Reaction reaction) throws Exception {
        if(reaction.getAuthor() == UserList.getInstance().getCurrentUser())
            throw new Exception("You can only delete your own reaction");
        reactions.remove(reaction);
    }

    /**
     * TODO
     * @param measureGroup
     */
    public void insertMeasure(MeasureGroup measureGroup){
        int index = measureGroups.indexOf(measureGroup);
        measureGroups.add(index+1, new MeasureGroup(timeSignatureNum, keySignature.rootChord, instruments));
    }

    /**
     * TODO
     * @param measureGroup
     */
    public void removeMeasure(MeasureGroup measureGroup) throws Exception{
        if(measureGroups.size()<=1)
            throw new Exception("Song cannot have 0 measures");
        measureGroups.remove(measureGroup);
    }

    /**
     * TODO
     * @param note
     * @return
     */
    public void insertNote(Note note){
        if(note.getPitch()!=Pitch.REST)
            return;
        note.setPitch(keySignature.getRootPitch());
        note.setOctave(4);
    }

    /**
     * Moves the specified note up one scale tone
     * 
     * @param note Note to move
     * @return Whether the new note is in range
     */
    public void noteUp(Note note) throws Exception{
        note.up(this.keySignature);
    }

    /**
     * Moves the specified note down one scale tone
     * 
     * @param measure Measure where note is
     * @param instrument Instrument that note is played by
     * @param note Note to move
     * @return Whether the new note is in range
     */
    public void noteDown(Note note) throws Exception{
        note.down(this.keySignature);
    }

    /**
     * adds an instrument to a song's arraylist of instruments
     * 
     * @param instrument takes in an instrument of type Instrument
     */
    public void addInstrument(Instrument instrument) {
        instruments.add(instrument);
        for(MeasureGroup measureGroup : measureGroups){
            measureGroup.addMeasure(instrument);
        }
    }

    /**
     * removes an instrument from a song's arraylist of instruments
     * 
     * @param instrument takes in an instrument of type instrument to be removed
     */
    public void removeInstrument(Instrument instrument) throws Exception {
        if(instruments.size()<=1)
            throw new Exception("Song must have at least 1 instrument");
        instruments.remove(instrument);
        for(MeasureGroup measureGroup : measureGroups){
            measureGroup.removeMeasure(instrument);
        }
    }

    /**
     * TODO
     * @param measureGroups
     * @return
     */
    private ArrayList<MeasureGroup> copyMeasureGroups(ArrayList<MeasureGroup> measureGroups){
        ArrayList<MeasureGroup> copy = new ArrayList<>();
        for(MeasureGroup measureGroup : measureGroups){
            copy.add(new MeasureGroup(measureGroup));
        }
        return copy;
    }

}
