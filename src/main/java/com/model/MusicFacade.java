package com.model;

import java.util.ArrayList;

public class MusicFacade {
    private static MusicFacade instance;
    private UserList userList;
    private SongList songList;
    private InstrumentList instrumentList;
    private User currentUser;
    private ArrayList<Song> currentSongs;
    private AudioPlayer currentPlayer;

    private MusicFacade(){
        //TODO
    }

    public static MusicFacade getInstance(){
        //TODO
        return null;
    }

    public boolean signup(String username, String password, String firstName, String lastName){
        //TODO
        return false;
    }

    public boolean login(String username, String password){
        //TODO
        return false;
    }

    public void logout(){
        //TODO
    }

    public ArrayList<Song> openPublicSongs(){
        //TODO
        return null;
    }

    public ArrayList<Song> openMySongs(){
        //TODO
        return null;
    }

    public ArrayList<Song> openFavorites(){
        //TODO
        return null;
    }

    public ArrayList<Song> filterSongs(String category, String filter){
        //TODO
        return null;
    }

    public ArrayList<Song> sortSongs(String sort, boolean up){
        //TODO
        return null;
    }

    public ArrayList<Song> clearFilter(){
        //TODO
        return null;
    }

    public ArrayList<Song> clearSort(){
        //TODO
        return null;
    }

    public void favoriteSong(Song song){
        //TODO
    }

    public void openSong(Song song){
        //TODO
    }

    public void copySong(Song song){
        //TODO
    }

    public Song newSong(String title, String description, ArrayList<Genre> genres, int difficulty, Key keySignature, int[] timeSignature){
        //TODO
        return null;
    }

    public ArrayList<Measure> selectInstrument(Instrument instrument){
        //TODO
        return null;
    }

    public ArrayList<Note> getNotes(Measure measure){
        //TODO
        return null;
    }

    public void playSong(){
        //TODO
    }

    public int pauseSong(){
        //TODO
        return -1;
    }

    public void stopSong(){
        //TODO
    }

    public void selectMeasure(Measure measure){
        //TODO
    }

    public int forwardMeasure(){
        //TODO
        return -1;
    }

    public int backwardMeasure(){
        //TODO
        return -1;
    }

    public void insertMeasure(int measure){
        //TODO
    }

    public void deleteMeasure(int measure){
        //TODO
    }

    public boolean noteUp(Note note){
        //TODO
        return false;
    }    

    public boolean noteDown(Note note){
        //TODO
        return false;
    }

    public void deleteNote(Note note){
        //TODO
    }

    public boolean setBPM(int BPM){
        //TODO
        return false;
    }

    public void setPlaybackSpeed(int speed){
        //TODO
    }

    public void setChord(Chord chord){
        //TODO
    }

}
