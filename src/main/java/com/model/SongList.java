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
            Key keySignature, int[] timeSignature) {
        Song newSong = new Song(title, UserList.getInstance().getCurrentUser(), description, genres, difficulty, tempo,
                keySignature, timeSignature[0], timeSignature[1]);
        songs.add(newSong);
        return newSong;
    }

    /**
     * adds a copy of a Song to the arraylist of Songs
     * 
     * @param song takes in a song (of type Song) to be copied
     * @return returns the Song's copy
     */
    // public Song copySong(Song song, User author) {
    // Song copy = new Song(song, author);
    // songs.add(copy);
    // return copy;
    // } // I assume this is an old method that is no longer going to be used -
    // Simion

    public void copySong(Song song) { //this is the new copySong method
        Song newSong = new Song(song, UserList.getInstance().getCurrentUser());
        songs.add(newSong);
    }

    /**
     * removes a Song from the arraylist of Songs
     * 
     * @param song takes in a song (of type Song) to be removed
     */
    public void removeSong(Song song) {
        songs.remove(song);
    }

    /**
     * filters the arraylist of Songs by a desired title (way in which it is sorted)
     * 
     * @param title takes in a title (String) that will be used to retrieve the
     *              desied songs
     * @return returns the newly filtered arraylist of Songs
     */
    public ArrayList<Song> filterByTitle(String title) {
        ArrayList<Song> filteredSongs = new ArrayList<>();

        for (Song song : songs) {
            if (song.getTitle().equals(title)) // TODO Add Getter to Song
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
    public ArrayList<Song> filterByGenre(Genre genre) {
        ArrayList<Song> filteredSongs = new ArrayList<>();

        for (Song song : songs) {
            if (song.getGenres().contains(genre)) // TODO Add getter to Song
                filteredSongs.add(song);
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
    public ArrayList<Song> filterByBPM(int minBPM, int maxBPM) {
        ArrayList<Song> filteredSongs = new ArrayList<>();

        for (Song song : songs) {
            int tempo = song.getTempo(); // TODO Add getter to Song
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
    public ArrayList<Song> filterByDifficulty(int difficulty) {
        ArrayList<Song> filteredSongs = new ArrayList<>();

        for (Song song : songs) {
            if (song.getDifficulty() == difficulty) // TODO Add getter to Song
                songs.add(song);
        }

        return filteredSongs;
    }

    public ArrayList<Song> getPublicSongs() {
        ArrayList<Song> publicSongs = new ArrayList<Song>();
        for (Song song : songs) {
            if (song.isPublished()) {
                publicSongs.add(song);
            }
        }
        return publicSongs;
    }

    public ArrayList<Song> openMySongs() {
        ArrayList<Song> mySongs = new ArrayList<Song>();
        for (Song song : songs) {
            if (song.getAuthor().equals(UserList.getInstance().getCurrentUser())) {
                mySongs.add(song);
            }
        }
        return mySongs;
    }

    public ArrayList<Song> openFavorites() {
        return UserList.getInstance().getCurrentUser().getFavSongs();
    }

    public ArrayList<Song> filterSongs(String category, String filter) {
        if (category.equalsIgnoreCase("title")) {
            return filterByTitle(filter);
        } else if (category.equalsIgnoreCase("genre")) {
            return filterByGenre(Genre.fromString(filter));
        } else if (category.equalsIgnoreCase("BPM")) {
            String[] splitString = filter.split(" ");
            int lowestBPM = Integer.parseInt(splitString[0]);
            int highestBPM = Integer.parseInt(splitString[1]);
            return filterByBPM(lowestBPM, highestBPM);
        } else if (category.equalsIgnoreCase("difficulty")) {
            return filterByDifficulty(Integer.parseInt(filter));
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * saves the chnages made to the instance of SongList
     */
    public void save() {
        DataWriter.getInstance().saveSongs();
    }
}
