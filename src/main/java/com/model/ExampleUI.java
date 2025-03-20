package com.model;

import java.util.ArrayList;

public class ExampleUI {
    public static void main(String[] args) {
        MusicFacade facade = MusicFacade.getInstance();
        try {
            scenario1(facade);
            scenario2(facade);
            scenario3(facade);
            scenario4(facade);
            scenario5(facade);
            scenario6(facade);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    // Signup, Filter, Logout
    // Signup - Open Public Songs - Filter Songs - Logout
    private static void scenario1(MusicFacade facade){
        ArrayList<Song> songs;
        facade.signup("SomeUser", "password", "John", "Smith");
        songs = facade.openPublicSongs();
        songs = facade.filterSongs("title", "Monster");
        songs = facade.filterSongs("genre", "rock");
        songs = facade.filterSongs("bpm", "90 150");
        songs = facade.filterSongs("difficulty", "4");
        facade.logout();
    }

    // Login, Favorite
    // Login - Open Public Songs - Favorite Songs - Open Favorite Songs - Unfavorite Song - Logout
    private static void scenario2(MusicFacade facade){
        ArrayList<Song> songs;
        facade.login("TestUser", "securePassword");
        songs = facade.openPublicSongs();
        facade.toggleFavorite(songs.get(0));
        facade.toggleFavorite(songs.get(1));
        songs = facade.openFavorites();
        facade.toggleFavorite(songs.get(0));
        songs = facade.openFavorites();
        facade.logout();
    }

    // Play Song
    // Login - Open Public Songs - Open Song - Play - Pause - Select Measure - Play - Stop - Play - Logout
    private static void scenario3(MusicFacade facade) throws InterruptedException{
        ArrayList<Song> songs;
        ArrayList<Instrument> instruments;
        ArrayList<Measure> measures;
        facade.login("TestUser", "securePassword");
        songs = facade.openPublicSongs();
        instruments = facade.openSong(songs.get(0));
        measures = facade.selectInstrument(instruments.get(0));
        facade.playSong();
        Thread.sleep(3000);
        facade.pauseSong();
        facade.selectMeasure(measures.get(2));
        facade.playSong();
        Thread.sleep(3000);
        facade.stopSong();
        facade.playSong();
        Thread.sleep(10000);
        facade.logout();
    }

    // New Song
    // Login - Open Personal Songs - New Song - Add Notes - Play - Logout
    private static void scenario4(MusicFacade facade) throws Exception{
        ArrayList<Song> songs;
        ArrayList<Instrument> instruments;
        ArrayList<Measure> measures;
        ArrayList<Note> notes;
        Song song;
        ArrayList<Genre> genres = new ArrayList<>();
        genres.add(Genre.JAZZ);
        facade.login("TestUser", "securePassword");
        songs = facade.openMySongs();
        song = facade.newSong("The Lick", "A popular jazz phrase", genres, 1, Key.A_MINOR);
        facade.openSong(song);
        facade.addInstrument(InstrumentList.getInstance().getInstruments().get(0));
        instruments = facade.getInstruments();
        measures = facade.selectInstrument(instruments.get(0));
        notes = facade.selectMeasure(measures.get(0));
        facade.selectNote(notes.get(0));
        facade.splitNote(2);
        facade.selectNote(notes.get(2));
        facade.splitNote(2);
        facade.selectNote(notes.get(5));
        facade.splitNote(2);
        facade.selectNote(notes.get(0));
        facade.noteUp();
        facade.noteUp();
        facade.noteUp();
        facade.selectNote(notes.get(1));
        facade.noteUp();
        facade.noteUp();
        facade.noteUp();
        facade.noteUp();
        facade.selectNote(notes.get(2));
        facade.noteUp();
        facade.noteUp();
        facade.noteUp();
        facade.noteUp();
        facade.noteUp();
        facade.selectNote(notes.get(3));
        facade.noteUp();
        facade.noteUp();
        facade.noteUp();
        facade.noteUp();
        facade.noteUp();
        facade.noteUp();
        facade.selectNote(notes.get(4));
        facade.noteUp();
        facade.noteUp();
        facade.noteUp();
        facade.noteUp();
        facade.selectNote(notes.get(5));
        facade.noteUp();
        facade.noteUp();
        facade.selectNote(notes.get(6));
        facade.noteUp();
        facade.noteUp();
        facade.noteUp();
        facade.playSong();
        Thread.sleep(3000);
        facade.logout();
    }

    // Copy Song
    // Login - Open Public Songs - Copy Song - Open Personal Songs - Open Song - Play - Logout
    private static void scenario5(MusicFacade facade) throws Exception{
        ArrayList<Song> songs;
        facade.login("TestUser", "securePassword");
        songs = facade.openPublicSongs();
        facade.copySong(songs.get(1));
        songs = facade.openMySongs();
        facade.openSong(songs.get(1));
        facade.playSong();
        Thread.sleep(3000);
        facade.stopSong();
        facade.logout();
    }

    // Edit Song
    // Login - Open Personal Songs - Open Song - Select Instrument - TODO What all needs edited? - Logout
    private static void scenario6(MusicFacade facade) throws Exception{
        ArrayList<Song> songs;
        ArrayList<Instrument> instruments;
        ArrayList<Measure> measures;
        ArrayList<Note> notes;
        facade.login("TestUser", "securePassword");
        songs = facade.openMySongs();
        instruments = facade.openSong(songs.get(1));
        measures = facade.selectInstrument(instruments.get(0));
        notes = facade.selectMeasure(measures.get(4));
        facade.selectNote(notes.get(3));
        facade.deleteNote();
        notes = facade.selectMeasure(measures.get(7));
        facade.selectNote(notes.get(2));
        facade.noteUp();
        facade.selectMeasure(measures.get(8));
        facade.deleteMeasure();
        facade.playSong();
        Thread.sleep(10000);
        facade.stopSong();
        facade.logout();
    }
}
