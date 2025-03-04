package com.model;

/**
 * Facade for the music app
 */
public class MusicFacade {
    private static MusicFacade instance;
    private UserList userList;
    private SongList songList;
    private InstrumentList instrumentList;
    private User currentUser;
    private ArrayList<Song> currentSongs;
    private AudioPlayer currentPlayer;

    /**
     * Makes the facade
     */
    private MusicFacade(){
        //TODO
    }

    /**
     * Gets an instance of MusicFacade
     * @return instance of MusicFacade
     */
    public static MusicFacade getInstance(){
        //TODO
        return null;
    }

    /**
     * Creates a new user
     * @param username Username of new user. Must be unique
     * @param password Password of new user. Must meet requirements
     * @param firstName First name of new user
     * @param lastName Last name of new user
     * @return Whether the username and password are allowed
     */
    public boolean signup(String username, String password, String firstName, String lastName){
        //TODO
        return false;
    }

    /**
     * Logins into an existing users account. Sets the facades currentUser to 
     * @param username Username of user
     * @param password Password of user
     * @return Whether login was successful
     */
    public boolean login(String username, String password){
        //TODO
        return false;
    }

    /**
     * Logs out user and saves all data
     */
    public void logout(){
        //TODO
    }

    /**
     * Opens the public song library
     * @return List of public songs
     */
    public ArrayList<Song> openPublicSongs(){
        //TODO
        return null;
    }

    /**
     * Opens user created songs
     * @return List of crteated songs
     */
    public ArrayList<Song> openMySongs(){
        //TODO
        return null;
    }

    /**
     * Opens favorite songs
     * @return List of favorite songs
     */
    public ArrayList<Song> openFavorites(){
        //TODO
        return null;
    }

    /**
     * Filters song list
     * @param category Type of filter to use
     * @param filter Fitler specification
     * @return Filtered song list
     */
    public ArrayList<Song> filterSongs(String category, String filter){
        //TODO
        return null;
    }

    /**
     * Sorts song list
     * @param sort What to sort by
     * @param up Direction to sort
     * @return Sorted list
     */
    public ArrayList<Song> sortSongs(String sort, boolean up){
        //TODO
        return null;
    }

    /**
     * Clears the filter on songs
     * @return Unfiltered song list
     */
    public ArrayList<Song> clearFilter(){
        //TODO
        return null;
    }

    /**
     * Clears the sort on songs
     * @return Unsorted song list
     */
    public ArrayList<Song> clearSort(){
        //TODO
        return null;
    }

    /**
     * Toggles favorite on a song
     * @param song Song to toggle favorite status of
     */
    public void favoriteSong(Song song){
        //TODO
    }

    /**
     * Opens a song
     * @param song Song to open
     */
    public void openSong(Song song){
        //TODO
    }

    /**
     * Creates a copy of a song, only changing the author to the current user
     * @param song Song to copy
     */
    public void copySong(Song song){
        //TODO
    }

    /**
     * Creates a new song
     * @param title Title of song
     * @param description Description of song
     * @param genres Genres of song
     * @param difficulty Difficulty of song
     * @param keySignature Key of song
     * @param timeSignature Time signature of song
     * @return The created song
     */
    public Song newSong(String title, String description, ArrayList<Genre> genres, int difficulty, Key keySignature, int[] timeSignature){
        //TODO
        return null;
    }

    /**
     * Selects the current instrument
     * @param instrument Instrument selected
     * @return The list of measures of this song
     */
    public ArrayList<Part> selectInstrument(Instrument instrument){
        //TODO
        return null;
    }

    /**
     * Get the notes of a measure
     * @param part Measure to get notes of
     * @return A list of the notes of the given measure
     */
    public ArrayList<Note> getNotes(Part part){
        //TODO
        return null;
    }

    /**
     * Plays the current song
     */
    public void playSong(){
        //TODO
    }

    /**
     * Pauses the current song, stopping on the current measure
     * @return Measure that was paused on
     */
    public int pauseSong(){
        //TODO
        return -1;
    }

    /**
     * Stops the current measure, keeping the selected measure the same as when play was hit
     */
    public void stopSong(){
        //TODO
    }

    /**
     * Selects a measure 
     * @param measure Measure to select
     */
    public void selectMeasure(Measure measure){
        //TODO
    }

    /**
     * Moves the selected measure forward one
     * @return New selected measure
     */
    public int forwardMeasure(){
        //TODO
        return -1;
    }

    /**
     * Moves selected measure backward one
     * @return New selected measure
     */
    public int backwardMeasure(){
        //TODO
        return -1;
    }

    /**
     * Adds a measure after the current selected measure
     * @param measure Measure to insert after
     */
    public void insertMeasure(int measure){
        //TODO
    }

    /**
     * Deletes selected measure
     * @param measure Measure to delete
     */
    public void deleteMeasure(int measure){
        //TODO
    }
     
    /**
     * Moves note up one pitch
     * @param note Note to move up
     * @return Whether the note can move up
     */
    public boolean noteUp(Note note){
        //TODO
        return false;
    }    

    /**
     * Moves note down one pitch
     * @param note Note to move down
     * @return Whether the note can move down
     */
    public boolean noteDown(Note note){
        //TODO
        return false;
    }

    /**
     * Deletes note, replacing it with a rest
     * @param note Note to delete
     */
    public void deleteNote(Note note){
        //TODO
    }

    /**
     * Sets the bpm of the current song
     * @param BPM BPM to change to
     * @return Whether that BPM is allowed
     */
    public boolean setBPM(int BPM){
        //TODO
        return false;
    }

    /**
     * Sets playback speed of song
     * @param speed Speed to set playback to
     */
    public void setPlaybackSpeed(double speed){
        //TODO
    }

    /**
     * Sets chord of selected measure
     * @param chord Chord to set measure to
     */
    public void setChord(Chord chord){
        //TODO
    }
}
