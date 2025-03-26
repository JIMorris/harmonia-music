package com.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents a song with metadata, musical structure, and associated user information.
 * A song includes attributes like title, author, description, genres, difficulty, tempo,
 * key signature, time signature, and musical content such as measures and instruments.
 * It also tracks reactions and whether it is publicly published.
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
     * Creates a new empty song with basic musical properties.
     *
     * @param title Title of the song
     * @param author Author of the song
     * @param description Description of the song
     * @param genres List of genres for this song
     * @param difficulty Difficulty rating from 1 to 5
     * @param tempo Tempo in beats per minute (30-400)
     * @param keySignature Key signature of the song
     * @param timeSignatureNum Top number of the time signature
     * @param timeSignatureDen Bottom number of the time signature
     */
    public Song(String title, User author, String description, ArrayList<Genre> genres, int difficulty,
                int tempo, Key keySignature, int timeSignatureNum, int timeSignatureDen) {
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
     * Constructs a copy of an existing song with a new author.
     *
     * @param song Song to copy
     * @param author New author for the copied song
     */
    public Song(Song song, User author) {
        this.songID = UUID.randomUUID();
        this.title = song.getTitle();
        this.author = author;
        this.description = song.getDescription();
        this.genres = (ArrayList<Genre>) song.getGenres().clone();
        this.difficulty = song.getDifficulty();
        this.reactions = new ArrayList<>();
        this.published = false;
        this.tempo = song.getTempo();
        this.keySignature = song.getKeySignature();
        this.timeSignatureNum = song.getTimeSignatureNum();
        this.timeSignatureDen = song.getTimeSignatureDen();
        this.measureGroups = copyMeasureGroups(song.getMeasureGroups());
        this.instruments = (ArrayList<Instrument>) song.getInstruments().clone();
    }

    /**
     * Constructs a song from full data, used for loading from persistent storage.
     *
     * @param id UUID of the song
     * @param title Title of the song
     * @param author Author of the song
     * @param description Description
     * @param genres List of genres
     * @param difficulty Difficulty rating (1-5)
     * @param reactions List of user reactions
     * @param published Whether the song is public
     * @param tempo Tempo in BPM
     * @param keySignature Key of the song
     * @param timeSignatureNum Top number of time signature
     * @param timeSignatureDen Bottom number of time signature
     * @param measures List of measure groups
     * @param instruments List of instruments
     */
    public Song(UUID id, String title, User author, String description, ArrayList<Genre> genres,
                int difficulty, ArrayList<Reaction> reactions, boolean published, int tempo,
                Key keySignature, int timeSignatureNum, int timeSignatureDen,
                ArrayList<MeasureGroup> measures, ArrayList<Instrument> instruments) {
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

    /**
     * @return UUID of the song
     */
    public UUID getSongID() { return this.songID; }

    /**
     * @return Title of the song
     */
    public String getTitle() { return title; }

    /**
     * @return Author of the song
     */
    public User getAuthor() { return author; }

    /**
     * @return Description of the song
     */
    public String getDescription() { return description; }

    /**
     * @return List of genres
     */
    public ArrayList<Genre> getGenres() { return genres; }

    /**
     * @return Difficulty rating (1-5)
     */
    public int getDifficulty() { return difficulty; }

    /**
     * @return List of reactions
     */
    public ArrayList<Reaction> getReactions() { return reactions; }

    /**
     * @return True if published, false otherwise
     */
    public boolean isPublished() { return published; }

    /**
     * @return Tempo in BPM
     */
    public int getTempo() { return tempo; }

    /**
     * @return Key signature of the song
     */
    public Key getKeySignature() { return keySignature; }

    /**
     * @return Time signature numerator
     */
    public int getTimeSignatureNum() { return timeSignatureNum; }

    /**
     * @return Time signature denominator
     */
    public int getTimeSignatureDen() { return timeSignatureDen; }

    /**
     * @return List of measure groups
     */
    public ArrayList<MeasureGroup> getMeasureGroups() { return measureGroups; }

    /**
     * @return List of instruments
     */
    public ArrayList<Instrument> getInstruments() { return instruments; }

    /**
     * Checks if the song ID matches the given UUID.
     *
     * @param songID UUID to compare
     * @return true if matches, false otherwise
     */
    public boolean idMatch(UUID songID) {
        return this.songID.equals(songID);
    }

    /**
     * Adds a new reaction to the song.
     *
     * @param rating Reaction rating
     * @param comment Reaction comment
     * @param author Author of the reaction
     * @return The created Reaction
     */
    public Reaction addReaction(int rating, String comment, User author) {
        Reaction reaction = new Reaction(rating, comment, author);
        this.reactions.add(reaction);
        return reaction;
    }

    /**
     * Removes a reaction from the song.
     *
     * @param reaction Reaction to remove
     */
    public void removeReaction(Reaction reaction) {
        this.reactions.remove(reaction);
    }

    /**
     * Returns a measure by index.
     *
     * @param number Index of measure
     * @return MeasureGroup object
     */
    public MeasureGroup getMeasure(int number) {
        return measureGroups.get(number);
    }

    /**
     * Moves a note up in pitch according to the key signature.
     *
     * @param note Note to move
     * @return True if successful, false otherwise
     */
    public boolean noteUp(Note note){
        return note.up(this.keySignature);
    }

    /**
     * Moves a note down in pitch according to the key signature.
     *
     * @param note Note to move
     * @return True if successful, false otherwise
     */
    public boolean noteDown(Note note){
        return note.down(this.keySignature);
    }

    /**
     * Adds an instrument to all measure groups.
     *
     * @param instrument Instrument to add
     */
    public void addInstrument(Instrument instrument) {
        for(MeasureGroup measureGroup : measureGroups){
            measureGroup.addPart(instrument);
        }
    }

    /**
     * Removes an instrument from all measure groups.
     *
     * @param instrument Instrument to remove
     */
    public void removeInstrument(Instrument instrument) {
        for(MeasureGroup measureGroup : measureGroups){
            measureGroup.removePart(instrument);
        }
    }

    /**
     * Adds a new measure group to the song.
     */
    public void addMeasure() {
        measureGroups.add(new MeasureGroup(timeSignatureNum, instruments));
    }

    /**
     * Removes a measure group by index.
     *
     * @param measureNumber Index to remove
     */
    public void removeMeasure(int measureNumber) {
        measureGroups.remove(measureNumber);
    }

    /**
     * Creates a deep copy of the measure groups.
     *
     * @param measureGroups Original list of measure groups
     * @return Copied list of measure groups
     */
    public ArrayList<MeasureGroup> copyMeasureGroups(ArrayList<MeasureGroup> measureGroups){
        ArrayList<MeasureGroup> copy = new ArrayList<>();
        for(MeasureGroup measureGroup : measureGroups){
            copy.add((MeasureGroup) measureGroup.copy());
        }
        return copy;
    }
}
