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
    private ArrayList<Measure> measures;
    private ArrayList<Instrument> instruments;

    /**
     * constructure that creates a new song.
     * 
     * @param title  takes in a string for the title of the new song
     * @param author takes in a User for the author of the new song
     */
    public Song(String title, User author) {

    }

    /**
     * constructor used for making a copy of a song
     * 
     * @param song takes in a song to be copied
     */
    public Song(Song song) {

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
        return null;
    }

    /**
     * removes a reaction from a song's arraylist of reactions
     * 
     * @param reaction takes in a reaction (of type Reaction)
     *                 to be removed from the arraylist
     */
    public void removeReaction(Reaction reaction) {

    }

    /**
     * returns a measure that is within a song
     * 
     * @param number takes in a number (of type int) which will help identify which
     *               measure to be returned
     * @return returns the desired measure
     */
    public Measure getMeasure(int number) {
        return null;
    }

    /**
     * adds an instrument to a song's arraylist of instruments
     * 
     * @param instrument takes in an instrument of type Instrument
     */
    public void addInstrument(Instrument instrument) {

    }

    /**
     * removes an instrument from a song's arraylist of instruments
     * 
     * @param instrument takes in an instrument of type instrument to be removed
     */
    public void removeInstrument(Instrument instrument) {

    }

    /**
     * adds a measure to a song's arraylist of measures
     */
    public void addMeasure() {

    }

    /**
     * removes a measure from a song's arraylist of measures
     * 
     * @param measureNumber takes in an int named measureNumber to help identify the
     *                      measure to be removed
     */
    public void removeMeasure(int measureNumber) {

    }

}
