package com.model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Facade for the music app
 */
public class MusicFacade {
    private static MusicFacade instance;
    private InstrumentList instrumentList;
    private UserList userList;
    private SongList songList;
    private AudioPlayer audioPlayer;


    /**
     * Makes the facade
     */
    private MusicFacade(){
        this.instrumentList = InstrumentList.getInstance();
        this.userList = UserList.getInstance();
        this.songList = SongList.getInstance();
        this.audioPlayer = AudioPlayer.getInstance();
    }

    /**
     * Gets an instance of MusicFacade
     * @return instance of MusicFacade
     */
    public static MusicFacade getInstance(){
        if(instance==null){
            instance = new MusicFacade();
        }
        return instance;
    }

    /**
     * Creates a new user
     * @param username Username of new user. Must be unique
     * @param password Password of new user. Must meet requirements
     * @param firstName First name of new user
     * @param lastName Last name of new user
     * @throws Exception if the username is taken or password is invalid
     */
    public void signup(String username, String password, String firstName, String lastName) throws Exception{
        userList.signup(username, password, firstName, lastName);
    }

    /**
     * Logins into an existing users account. Sets the facades currentUser to 
     * @param username Username of user
     * @param password Password of user
     * @throws Exception If username/password is incorrect
     */
    public void login(String username, String password) throws Exception{
        userList.login(username, password);
    }

    /**
     * Logs out user and saves all data
     * @throws Exception if there is an issue logging out
     */
    public void logout() throws Exception{
        instrumentList.logout();
        userList.logout();
        songList.logout();   
        audioPlayer.logout();
    }

    /**
     * Opens the public song library
     * @return List of public songs
     */
    public ArrayList<Song> openPublicSongs(){
        return songList.getPublicSongs();
    }

    /**
     * Opens user created songs
     * @return List of crteated songs
     */
    public ArrayList<Song> openMySongs(){
        return songList.openMySongs();
    }

    /**
     * Opens favorite songs
     * @return List of favorite songs
     */
    public ArrayList<Song> openFavorites(){
        return songList.openFavorites();
    }

    /**
     * Filters song list
     * @param category Type of filter to use
     * @param filter Fitler specification
     * @return Filtered song list
     */
    public ArrayList<Song> filterSongs(String category, String filter){
        return songList.filterSongs(category, filter);
    }

    /**
     * Toggles favorite on a song
     * @param song Song to toggle favorite status of
     */
    public void toggleFavorite(Song song){
        userList.toggleFavoriteSong(song);
    }

    /**
     * Opens a song
     * @param song Song to open
     * @return a list of instruments used in the song
     */
    public ArrayList<Instrument> openSong(Song song){
        return audioPlayer.openSong(song);
    }

    /**
     * Gets a list of available instruments
     * @return the list of instruments
     */
    public ArrayList<Instrument> getInstruments(){
        return audioPlayer.getInstruments();
    }

    /**
     * Adds an instrument to the song
     * @param instrument the instrument to add
     */
    public void addInstrument(Instrument instrument){
        audioPlayer.addInstrument(instrument);
    }
    
    /**
     * Renives an instrument from the song
     * @param instrument the instrument to remove
     * @throws Exception if the instrument cannot be removed
     */
    public void removeInstrument(Instrument instrument) throws Exception{
        audioPlayer.removeInstrument(instrument);
    }

    /**
     * Creates a copy of a song, only changing the author to the current user
     * @param song Song to copy
     * @return the copied song
     */
    public Song copySong(Song song){
        return songList.copySong(song);
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
    public Song newSong(String title, String description, ArrayList<Genre> genres, int difficulty, int tempo, Key keySignature, Instrument defaultInstrument){
        return songList.newSong(title, description, genres, difficulty, tempo, keySignature, new int[] {4, 4}, defaultInstrument);  
    }

    /**
     * Removes a song
     * @param song the song to be removed
     * @throws Exception if the song cannot be removed
     */
    public void removeSong(Song song) throws Exception{
        songList.removeSong(song);
    }

    /**
     * Selects the current instrument
     * 
     * @param instrument Instrument selected
     * @return The list of measures for this Instrument
     */
    public ArrayList<Measure> selectInstrument(Instrument instrument){
        return audioPlayer.selectInstrument(instrument);
    }


    /**
     * Returns the measures of the current selected instrument
     * 
     * @return Measures of the current selected instrument
     */
    public ArrayList<Measure> getMeasures(){
        return audioPlayer.getMeasures();
    }
    
    /**
     * Get the notes of a measure
     * @param part Measure to get notes of
     * @return A list of the notes of the given measure
     */
    public ArrayList<Note> getNotes(Measure measure){
        return audioPlayer.getNotes(measure);
    }

    /**
     * Selects a Measure 
     * 
     * @param measure Measure to select
     * @return The Arraylist of Notes within that measure
     */
    public ArrayList<Note> selectMeasure(Measure measure){
        audioPlayer.selectMeasure(measure);
        return getNotes(measure);
    }

    /**
     * Selects a note
     * @param note Note to select
     */
    public void selectNote(Note note){
        audioPlayer.selectNote(note);
    }

    /**
     * Plays the current song
     */
    public void playSong(){
        audioPlayer.play();
    }

    /**
     * Stops the current measure, keeping the selected measure the same as when play was hit
     */
    public void stopSong(){
        audioPlayer.stop();
    }

    /**
     * Adds a measure after the current selected measure
     */
    public void insertMeasure(){
        audioPlayer.insertMeasure();
    }

    /**
     * Deletes selected measure
     */
    public void removeMeasure() throws Exception{
        audioPlayer.removeMeasure();
    }
     
    /**
     * Moves selected note up one pitch
     * @return Whether the note can move up
     */
    public void noteUp() throws Exception{
        audioPlayer.noteUp();
    }    

    /**
     * Moves selected note down one pitch
     * @param note Note to move down
     * @return Whether the note can move down
     */
    public void noteDown() throws Exception{
        audioPlayer.noteDown();
    }

    /**
     * Splits a note into smaller divisions
     * @param division the number of divisions to split the note into
     * @throws Exception if the operation fails
     */
    public void splitNote(int division) throws Exception{
        audioPlayer.splitNote(division);
    }

    /**
     * Combines notes into a single note
     * @throws Exception if the operation fails
     */
    public void combineNotes() throws Exception{
        audioPlayer.combineNotes();
    }

    /**
     * Inserts a new note
     */
    public void insertNote(){
        audioPlayer.insertNote();
    }

    /**
     * Deletes note, replacing it with a rest
     */
    public void deleteNote(){
        audioPlayer.deleteNote();
    }

    /**
     * Inserts a new chord
     */
    public void insertChord(){
        audioPlayer.insertChord();
    }

    /**
     * Sets the bpm of the current song
     * @param BPM BPM to change to
     * @return Whether that BPM is allowed
     */
    public void setBPM(int BPM) throws Exception{
        audioPlayer.setBPM(BPM);
    }

    /**
     * Sets chord of selected measure
     * @param chord Chord to set measure to
     */
    public void setChord(Chord chord){
        audioPlayer.setChord(chord);
    }

    /**
     * Checks to see if a song is a favorite
     * @param song The song to check
     * @return True if the song is a favorite, false otherwise
     */
    public boolean isFavorite(Song song){
        return userList.isFavorite(song);
    }

    /**
     * Determines if the user has permission to edit song
     * @return True if the user has permissions, false if otherwise
     */
    public boolean editPermission(){
        return audioPlayer.editPermission();
    }

    /**
     * Retrieves a list of available chords
     * @return a list of all possible chord values
     */
    public ArrayList<Chord> getChords(){
        return new ArrayList<>(Arrays.asList(Chord.values()));
    }

    /**
     * Adds a reaction to a song
     * @param song the song to react to
     * @param rating the rating given to the song
     * @param comment the comment left with the reaction
     */
    public void addReaction(Song song, int rating, String comment){
        userList.addReaction(song, rating, comment);
    }

    /**
     * Removes a reaction from a song
     * @param song the song to remove reaction from
     * @param reaction the reaction to be removed
     * @throws Exception if the operation fails
     */
    public void removeReaction(Song song, Reaction reaction) throws Exception{
        song.removeReaction(reaction);
    }

    /**
     * Prints the current song details
     * @throws Exception If an error occurs
     */
    public void printSong() throws Exception{
        audioPlayer.printSong();
    }

    /**
     * Returns an ArrayList of all instruments in available to use
     * 
     * @return All instruments
     */
    public ArrayList<Instrument> getAllInstruments(){
        return instrumentList.getInstruments();
    }
}
