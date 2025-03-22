package com.model;

import java.util.ArrayList;

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
     * @return Whether the username and password are allowed
     */
    public boolean signup(String username, String password, String firstName, String lastName){
        return userList.signup(username, password, firstName, lastName);
    }

    /**
     * Logins into an existing users account. Sets the facades currentUser to 
     * @param username Username of user
     * @param password Password of user
     * @return Whether login was successful
     */
    public boolean login(String username, String password){
        return userList.login(username, password);
    }

    /**
     * Logs out user and saves all data
     */
    public void logout(){
        instrumentList.save();
        userList.save();
        songList.save();
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
        return songList.getMySongs();
    }

    /**
     * Opens favorite songs
     * @return List of favorite songs
     */
    public ArrayList<Song> openFavorites(){
        return songList.getMyFavorites();
    }

    /**
     * Filters song list
     * @param category Type of filter to use
     * @param filter Fitler specification
     * @return Filtered song list
     */
    public ArrayList<Song> filterSongs(String category, String filter){
        return songList.filterSongs(String category, String filter);
    }

    /**
     * Toggles favorite on a song
     * @param song Song to toggle favorite status of
     */
    public void toggleFavorite(Song song){
        userList.toggleFavorite(song);
    }

    /**
     * Opens a song
     * @param song Song to open
     */
    public ArrayList<Instrument> openSong(Song song){
        return audioPlayer.openSong(song);
    }

    public ArrayList<Instrument> getInstruments(){
        return audioPlayer.getInstruments();
    }

    public void addInstrument(Instrument instrument){
        audioPlayer.addInstrument();
    }

    /**
     * Creates a copy of a song, only changing the author to the current user
     * @param song Song to copy
     */
    public void copySong(Song song){
        songList.copySong(song);
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
    public Song newSong(String title, String description, ArrayList<Genre> genres, int difficulty, Key keySignature){
        return songList.newSong(title, description, genres, difficulty, keySignature, new int[] {4, 4});  
    }

    /**
     * Selects the current instrument
     * @param instrument Instrument selected
     * @return The list of measures of this song
     */
    public ArrayList<Measure> selectInstrument(Instrument instrument){
        return audioPlayer.selectInstrument();
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
     * Selects a measure 
     * @param measure Measure to select
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
     * Pauses the current song, stopping on the current measure
     * @return Measure that was paused on
     */
    public int pauseSong(){
        return audioPlayer.pause();
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
    public void deleteMeasure(){
        audioPlayer.deleteMeasure();
    }


     
    /**
     * Moves selected note up one pitch
     * @return Whether the note can move up
     */
    public boolean noteUp(){
        return audioPlayer.noteUp();
    }    

    /**
     * Moves selected note down one pitch
     * @param note Note to move down
     * @return Whether the note can move down
     */
    public boolean noteDown(){
        return audioPlayer.noteDown();
    }

    /**
     * TODO
     * @return
     */
    public boolean splitNote(int division){
        return audioPlayer.splitNote(int division);
    }

    /**
     * TODO
     * @return
     */
    public boolean combineNotes(){
        return audioPlayer.combineNotes();
    }

    /**
     * Todo
     */
    public void insertNote(){
        audioPlayer.insertNote();
    }

    /**
     * Deletes note, replacing it with a rest
     * @param note Note to delete
     */
    public void deleteNote(){
        audioPlayer.deleteNote();
    }

    /**
     * Sets the bpm of the current song
     * @param BPM BPM to change to
     * @return Whether that BPM is allowed
     */
    public boolean setBPM(int BPM){
        return audioPlayer.setBPM(BPM);
    }

    /**
     * Sets chord of selected measure
     * @param chord Chord to set measure to
     */
    public void setChord(Chord chord){
        audioPlayer.setChord(chord);
    }
}
