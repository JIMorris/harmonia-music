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
        try {
            songs = DataLoader.getInstance().loadSongs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * method that is used to call the private SongList constructor (if no song list
     * exists), returns the instance of SongList
     * 
     * @return returns the singular instance of SongList
     */
    public static SongList getInstance() {
        if (instance == null)
            instance = new SongList();
        return instance;
    }

    /**
     * Gets the songs of the song list
     * 
     * @return ArrayList of all songs
     */
    public ArrayList<Song> getSongs() {
        return songs;
    }

    /**
     * Sets the songs in this song list
     * 
     * @param songs ArrayList to set songs to
     */
    public void setSongs(ArrayList<Song> songs){
        this.songs = songs;
    }

    /**
     * retrives a desired Song from the arralist of Songs
     * 
     * @param songID takes in a UUID to identify the song
     * @return returns the desired song
     */
    public Song getSong(UUID songID) {
        for (Song song : songs) {
            if (song.getSongID().equals(songID))
                return song;
        }
        return null;
    }

    /**
     * Creates a new empty song, adds it the song list, and returns it
     * 
     * @param title            String for the title of the song
     * @param author           User that authored the song
     * @param description      String for the description of the song
     * @param genres           List of Genres for this song
     * @param difficulty       int difficulty of the song (1-5)
     * @param tempo            int tempo of the song (30-400)
     * @param keySignature     Key of the song
     * @param timeSignatureNum Top number of the time signature
     * @param timeSignatureDen Bottom number of the time signature
     * 
     * @return The new blank song
     */
    public Song newSong(String title, String description,
            ArrayList<Genre> genres, int difficulty, int tempo,
            Key keySignature, int[] timeSignature, Instrument defaultInstrument) {
        Song newSong = new Song(title, UserList.getInstance().getCurrentUser(), description, genres, difficulty, tempo,
                keySignature, timeSignature[0], timeSignature[1], defaultInstrument);
        songs.add(newSong);
        return newSong;
    }

    /**
     * adds a copy of a Song to the arraylist of Songs
     * 
     * @param song takes in a song (of type Song) to be copied
     * @return returns the Song's copy
     */
    public Song copySong(Song song) { //this is the new copySong method
        Song copy = new Song(song, UserList.getInstance().getCurrentUser());
        songs.add(copy);
        return copy;
    }

    /**
     * removes a Song from the arraylist of Songs
     * 
     * @param song takes in a song (of type Song) to be removed
     */
    public void removeSong(Song song) throws Exception{
        if(song.getAuthor() != UserList.getInstance().getCurrentUser())
            throw new Exception("You can only delete your own songs");
        songs.remove(song);
    }

    /**
     * Returns a list of all songs with published=true
     * @return ArrayList of all published songs
     */
    public ArrayList<Song> getPublicSongs() {
        ArrayList<Song> publicSongs = new ArrayList<>();
        for (Song song : songs) {
            if (song.isPublished()) {
                publicSongs.add(song);
            }
        }
        return publicSongs;
    }

    /**
     * Returns a list of all songs authured by the current user
     * 
     * @return ArrayList of the current users songs
     */
    public ArrayList<Song> openMySongs() {
        ArrayList<Song> mySongs = new ArrayList<>();
        for (Song song : songs) {
            if (song.getAuthor() == UserList.getInstance().getCurrentUser()) {
                mySongs.add(song);
            }
        }
        return mySongs;
    }

    /**
     * Returns a list of all songs favorited by the current user
     * 
     * @return ArrayList of the current users favorite songs
     */
    public ArrayList<Song> openFavorites() {
        return UserList.getInstance().getCurrentUser().getFavSongs();
    }

    /**
     * Filters the song list by title, genre, bpm, difficulty, or author
     * 
     * @param category Category to filter by
     * @param filter What to use in the filter
     * @return Filtered ArrayList of songs
     */
    public ArrayList<Song> filterSongs(String category, ArrayList<String> filter) {
        if (category.equalsIgnoreCase("title")) {
            return filterByTitle(filter.get(0));
        } else if (category.equalsIgnoreCase("genre")) {
            ArrayList<Genre> genres = new ArrayList<>();
            for(String genreString : filter){
                genres.add(Genre.fromString(genreString));
            }
            return filterByGenre(genres);
        } else if (category.equalsIgnoreCase("BPM")) {
            int lowestBPM = Integer.parseInt(filter.get(0));
            int highestBPM = Integer.parseInt(filter.get(1));
            return filterByBPM(lowestBPM, highestBPM);
        } else if (category.equalsIgnoreCase("difficulty")) {
            return filterByDifficulty(Integer.parseInt(filter.get(0)));
        } else if (category.equalsIgnoreCase("author")) {
            for(User user : UserList.getInstance().getUsers()){
                if(user.getUsername().equals(filter.get(0)))
                    return filterByAuthor(user);
            }   
        }
        return new ArrayList<>();
    }

    /**
     * saves the chnages made to the instance of SongList
     */
    public void logout() throws Exception{
        DataWriter.getInstance().saveSongs();
    }

    /**
     * filters the arraylist of Songs by a desired title (way in which it is sorted)
     * 
     * @param title takes in a title (String) that will be used to retrieve the
     *              desied songs
     * @return returns the newly filtered arraylist of Songs
     */
    private ArrayList<Song> filterByTitle(String title) {
        ArrayList<Song> filteredSongs = new ArrayList<>();

        for (Song song : songs) {
            if (song.getTitle().equals(title))
                filteredSongs.add(song);
        }

        return filteredSongs;
    }

    /**
     * filters the arraylist of Songs by a desired genre
     * 
     * @param genre takes in a genre (of type Genre) to be used to filter the
     *              arrraylist
     * @return returns the newly filtered arraylist of Songs
     */
    private ArrayList<Song> filterByGenre(ArrayList<Genre> genres) {
        ArrayList<Song> filteredSongs = new ArrayList<>();

        for (Song song : songs) {
            for(Genre genre : genres){
                if (!filteredSongs.contains(song) && song.getGenres().contains(genre))
                    filteredSongs.add(song);
            }
        }

        return filteredSongs;
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
    private ArrayList<Song> filterByBPM(int minBPM, int maxBPM) {
        ArrayList<Song> filteredSongs = new ArrayList<>();

        for (Song song : songs) {
            int tempo = song.getTempo();
            if (tempo >= minBPM && tempo <= maxBPM)
                filteredSongs.add(song);
        }

        return filteredSongs;
    }

    /**
     * filters the arraylist of Songs by their difficulty
     * 
     * @param difficulty takes in the desired difficulty (int) to be used to filter
     *                   the arraylist
     * @return returns the newly filtered arraylist of Songs
     */
    private ArrayList<Song> filterByDifficulty(int difficulty) {
        ArrayList<Song> filteredSongs = new ArrayList<>();

        for (Song song : songs) {
            if (song.getDifficulty() == difficulty)
                filteredSongs.add(song);
        }

        return filteredSongs;
    }

    /**
     * Filters the ArrayList of Songs by their author
     * 
     * @param author Author to filter songs by
     * @return ArrayList of songs filtered by author
     */
    private ArrayList<Song> filterByAuthor(User author){
        ArrayList<Song> filteredSongs = new ArrayList<>();

        for(Song song : songs){
            if(song.getAuthor() == author)
                filteredSongs.add(song);
        }

        return filteredSongs;
    }
    
    public void clear() {
        if (songs != null) {
            songs.clear();
        }
    }
}
