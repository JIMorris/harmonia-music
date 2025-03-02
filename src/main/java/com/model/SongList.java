package com.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * creates a (singular) object named SongList which has an arraylist of Songs
 * 
 * @author Simion Cartis
 */
public class SongList {
    private static SongList instance;
    private ArrayList<Song> songs;

    /**
     * private constructor that creates a single instance of the SongList object
     */
    private SongList() {

    }

    /**
     * method that is used to call the private SongList constructor (if no song list
     * exists), returns the instance of SongList
     * 
     * @return returns the singular instance of SongList
     */
    public SongList getInstance() {
        return null;
    }

    /**
     * retrives a desired Song from the arralist of Songs
     * 
     * @param songID takes in a UUID to identify the song
     * @return returns the desired song
     */
    public Song getSong(UUID songID) {
        return null;
    }

    /**
     * adds a new Song to the arraylist of Songs
     * 
     * @return returns the new Song that is created
     */
    public Song newSong() {
        return null;
    }

    /**
     * adds a copy of a Song to the arraylist of Songs
     * 
     * @param song takes in a song (of type Song) to be copied
     * @return returns the Song's copy
     */
    public Song copySong(Song song) {
        return null;
    }

    /**
     * removes a Song from the arraylist of Songs
     * 
     * @param song takes in a song (of type Song) to be removed
     */
    public void removeSong(Song song) {

    }

    /**
     * sorts the arrayList of songs in a specified manner
     * 
     * @param type  takes in a String named type (determines how the arraylist will
     *              be sorted)
     * @param up    takes in a boolean named up to determine whether or not the
     *              arraylist should be sorted up or down (ex: lowest to highest bpm
     *              or vice versa)
     * @param songs takes in an arraylist of type Song to be sorted
     * @return returns the newly sorted arraylist of type Song
     */
    public static ArrayList<Song> sort(String type, boolean up, ArrayList<Song> songs) {
        return null;
    }

    // (BPM, Length, Difficulty) -added this here because its in the UML, not sure
    // why

    /**
     * filters the arraylist of Songs by a desired title
     * 
     * @param title takes in a title (String) that will be used to retrieve the
     *              desied songs
     * @return returns the newly filtered arraylist of Songs
     */
    public ArrayList<Song> filterByTitle(String title) {
        return null;
    }

    /**
     * filters the arraylist of Songs by a desired genre
     * 
     * @param genre takes in a genre (of type Genre) to be used to filter the
     *              arrraylist
     * @return returns the newly filtered arraylist of Songs
     */
    public ArrayList<Song> filterByGenre(Genre genre) {
        return null;
    }

    /**
     * filters the arraylist of Songs by BPM
     * 
     * @param minBPM takes in the minimum BPM (int) desired for the filtered
     *               arraylist of Songs
     * @param maxBPM takes in the maximum BPM (int) desired for the filtered
     *               arraylist of Songs
     * @return returns the newly filtered arraylist of Songs
     */
    public ArrayList<Song> filterByBPM(int minBPM, int maxBPM) {
        return null;
    }

    /**
     * filters the arraylist of Songs by their length
     * 
     * @param minLength takes in the minimum BPM (int) desired for the filtered
     *                  arraylist of Songs
     * @param maxLength takes in the maximum BPM (int) desired for the filtered
     *                  arraylist of Songs
     * @return returns the newly filtered arraylist of Songs
     */
    public ArrayList<Song> filterBylength(int minLength, int maxLength) {
        return null;
    }

    /**
     * filters the arraylist of Songs by their difficulty
     * 
     * @param difficulty takes in the desired difficulty (int) to be used to filter
     *                   the arraylist
     * @return returns the newly filtered arraylist of Songs
     */
    public ArrayList<Song> filterByDifficulty(int difficulty) {
        return null;
    }
    /**
     * saves the chnages made to the instance of SongList
     */
    public void save() {

    }
}
