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
    public Song(String title, User author, String description, ArrayList<Genre> genres, int difficulty, int tempo, Key keySignature, int timeSignatureNum, int timeSignatureDen) {
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
        this.measureGroups = new ArrayList<>();
        this.instruments = new ArrayList<>();
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
        this. timeSignatureDen = timeSignatureDen;
        this.measureGroups = measures;
        this.instruments = instruments;
    }

    //TODO
    public UUID getSongID () {
        return this.songID;
    }

    //TODO
    public String getTitle() {
        return title;
    }

    //TODO
    public User getAuthor() {
        return author;
    }

    //TODO
    public String getDescription() {
        return description;
    }

    //TODO
    public ArrayList<Genre> getGenres() {
        return genres;
    }

    //TODO
    public int getDifficulty() {
        return difficulty;
    }

    //TODO
    public ArrayList<Reaction> getReactions() {
        return reactions;
    }

    //TODO
    public boolean isPublished() {
        return published;
    }

    //TODO
    public int getTempo() {
        return tempo;
    }

    //TODO
    public Key getKeySignature() {
        return keySignature;
    }

    //TODO
    public int getTimeSignatureNum() {
        return timeSignatureNum;
    }

    //TODO
    public int getTimeSignatureDen() {
        return timeSignatureDen;
    }

    //TODO
    public ArrayList<MeasureGroup> getMeasureGroups() {
        return measureGroups;
    }

    //TODO
    public ArrayList<Instrument> getInstruments() {
        return instruments;
    }

    /**
     * checks if a song's UUID is the same as another UUID
     * 
     * @param songID takes in a UUID of another song
     * @return returns true if the UUID is the same as a songs, false if not
     */
    public boolean idMatch(UUID songID) {
        return this.songID == songID;
    }

    /**
     * creates a reaction for a song and adds it to the Song's reactions arraylist
     * 
     * @param rating  takes in a rating for the reaction (of type int)
     * @param comment takes in a comment for the reaction (of type String)
     * @param author  takes in an author for the reaction (of type User)
     * @return returns the reaction that has been created
     */
    public Reaction addReaction(int rating, String comment, User author) {
        Reaction reaction = new Reaction(rating, comment, author);
        this.reactions.add(reaction);
        return reaction;
    }

    /**
     * removes a reaction from a song's arraylist of reactions
     * 
     * @param reaction takes in a reaction (of type Reaction)
     *                 to be removed from the arraylist
     */
    public void removeReaction(Reaction reaction) {
        this.reactions.remove(reaction);
    }

    /**
     * returns a measure that is within a song
     * 
     * @param number takes in a number (of type int) which will help identify which
     *               measure to be returned
     * @return returns the desired measure
     */
    public MeasureGroup getMeasure(int number) {
        return measureGroups.get(number);
    }

    /**
     * Moves the specified note up one scale tone
     * 
     * @param note Note to move
     * @return Whether the new note is in range
     */
    public boolean noteUp(Note note){
        return note.up(this.keySignature);//TODO
    }

    /**
     * Moves the specified note down one scale tone
     * 
     * @param measure Measure where note is
     * @param instrument Instrument that note is played by
     * @param note Note to move
     * @return Whether the new note is in range
     */
    public boolean noteDown(Note note){
        return note.down(this.keySignature); //TODO
    }

    /**
     * adds an instrument to a song's arraylist of instruments
     * 
     * @param instrument takes in an instrument of type Instrument
     */
    public void addInstrument(Instrument instrument) {
        for(MeasureGroup measureGroup : measureGroups){
            measureGroup.addPart(instrument);
        }
    }

    /**
     * removes an instrument from a song's arraylist of instruments
     * 
     * @param instrument takes in an instrument of type instrument to be removed
     */
    public void removeInstrument(Instrument instrument) {
        for(MeasureGroup measureGroup : measureGroups){
            measureGroup.removePart(instrument); //TODO
        }
    }

    /**
     * adds a measure to a song's arraylist of measures
     */
    public void addMeasure() {
        measureGroups.add(new MeasureGroup(timeSignatureNum, instruments)); //TODO
    }

    /**
     * removes a measure from a song's arraylist of measures
     * 
     * @param measureNumber takes in an int named measureNumber to help identify the
     *                      measure to be removed
     */
    public void removeMeasure(int measureNumber) {
        measureGroups.remove(measureNumber);
    }



    //TODO
    public ArrayList<MeasureGroup> copyMeasureGroups(ArrayList<MeasureGroup> measureGroups){
        ArrayList<MeasureGroup> copy = new ArrayList<>();
        for(MeasureGroup measureGroup : measureGroups){
            copy.add((MeasureGroup)measureGroup.copy()); //TODO
        }
        return copy;
    }

    

}
