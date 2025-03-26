package com.model;

import java.util.ArrayList;

public class ExampleUI {
    public static void main(String[] args) {
        try {
            System.out.println("Starting Account Scenario\n");
            accountScenarioSignup();
            accountScenarioLogin();
            System.out.println("\nAccount Scenario Done\n");
            System.out.println("\nStarting Play and Print Scenario\n");
            // playAndPrintScenario();
            System.out.println("\nPlay and Print Scenario Done\n");
            System.out.println("\nStating Make and Print Scenario\n");
            newSongScenarioCreate();
            newSongScenarioPlay();
            System.out.println("\nMake and Print Scenario Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    private static void accountScenarioSignup() throws Exception{
        MusicFacade facade = MusicFacade.getInstance();
        try {
            facade.signup("ffredrickson", "uniquePassword", "Fred", "Fredrickson");
            System.out.println("Signed up as ffredrickson");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

            facade.signup("ffred", "uniquePassword", "Fred", "Fredrickson");
            System.out.println("Signed up as ffred");
            facade.logout();

    }

    private static void accountScenarioLogin() throws Exception{
        MusicFacade facade = MusicFacade.getInstance();
        try {
            facade.login("ffred", "uniquePassword");
            System.out.println("Signued up as ffred");
        } catch (Exception e) {
            e.printStackTrace();
        }
        facade.logout();
    }

    // private static void playAndPrintScenario() throws Exception{
    //     MusicFacade facade = MusicFacade.getInstance();



    //     facade.printSong();
    // }

    private static void newSongScenarioCreate() throws Exception{
        MusicFacade facade = MusicFacade.getInstance();
        ArrayList<Instrument> instruments = facade.getAllInstruments();
        ArrayList<Measure> measures;
        ArrayList<Note> notes;
        ArrayList<Genre> genres = new ArrayList<>();
        genres.add(Genre.POP);
        facade.login("ffredrickson", "myPassword");
        facade.newSong("A Horse Journey", "An example song", genres, 2, 120, Key.A_MINOR, instruments.get(0));
        ArrayList<Song> songs = facade.openMySongs();
        instruments = facade.openSong(songs.get(0));
        measures = facade.selectInstrument(instruments.get(0));
        notes = facade.getNotes(measures.get(0));
        facade.insertNote();
        facade.noteUp();
        facade.noteUp();
        facade.noteUp();
        facade.noteUp();
        facade.selectNote(notes.get(1));
        facade.insertNote();
        facade.noteUp();
        facade.noteUp();
        facade.noteUp();
        facade.selectNote(notes.get(2));
        facade.insertNote();
        facade.noteUp();
        facade.noteUp();
        facade.selectNote(notes.get(3));
        facade.splitNote(2);
        facade.insertNote();
        facade.noteUp();
        facade.selectNote(notes.get(4));
        facade.noteDown();
        facade.insertMeasure();
        measures = facade.getMeasures();
        notes = facade.selectMeasure(measures.get(1));
        facade.insertNote();
        facade.selectNote(notes.get(1));
        facade.insertNote();
        facade.noteDown();
        facade.splitNote(3);
        facade.selectNote(notes.get(4));
        facade.insertNote();
        facade.playSong();
        facade.logout();
    }

    public static void newSongScenarioPlay() throws Exception{
        MusicFacade facade = MusicFacade.getInstance();
        facade.login("ffred", "uniquePassword");
        ArrayList<Song> songs = facade.filterSongs("title", "A Horse Journey");
        facade.openSong(songs.get(0));
        facade.playSong();
        facade.logout();
    }













//--- UNSUSED SCENARIOS ---//

/*
    // Signup, Filter, Logout
    // Signup - Open Public Songs - Filter Songs - Logout
    private static void scenario1(MusicFacade facade) throws Exception{
        ArrayList<Song> songs;
        facade.signup("SomeUser", "password", "John", "Smith");
        songs = facade.openPublicSongs();
        songs = facade.filterSongs("title", "Monster");
        songs = facade.filterSongs("genre", "rock");
        songs = facade.filterSongs("bpm", "90 150");
        songs = facade.filterSongs("difficulty", "4");
        // facade.logout();
        System.out.println("Scenario 1 Done");
    }

    // Login, Favorite
    // Login - Open Public Songs - Favorite Songs - Open Favorite Songs - Unfavorite Song - Logout
    private static void scenario2(MusicFacade facade) throws Exception{
        ArrayList<Song> songs;
        facade.login("TestUser", "securePassword");
        songs = facade.openPublicSongs();
        facade.toggleFavorite(songs.get(0));
        facade.toggleFavorite(songs.get(1));
        songs = facade.openFavorites();
        facade.toggleFavorite(songs.get(1));
        // facade.logout();
        System.out.println("Scenario 2 Done");
    }

    // Play Song
    // Login - Open Public Songs - Open Song - Play - Select Measure - Play - Stop - Play - Logout
    private static void scenario3(MusicFacade facade) throws Exception{
        ArrayList<Song> songs;
        ArrayList<Instrument> instruments;
        ArrayList<Measure> measures;
        facade.login("TestUser", "securePassword");
        songs = facade.openPublicSongs();
        instruments = facade.openSong(songs.get(0));
        measures = facade.selectInstrument(instruments.get(0));
        facade.playSong();
        Thread.sleep(5000);
        facade.stopSong();
        facade.selectMeasure(measures.get(2));
        Thread.sleep(2000);
        facade.playSong();
        Thread.sleep(5000);
        facade.stopSong();
        facade.selectMeasure(measures.get(0));
        Thread.sleep(2000);
        facade.playSong();
        Thread.sleep(17000);
        facade.stopSong();
        // facade.logout();
        System.out.println("Scenario 3 Done");
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
        song = facade.newSong("The Lick", "A popular jazz phrase", genres, 2, 120, Key.A_MINOR, InstrumentList.getInstance().getInstruments().get(0));
        facade.openSong(song);
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
        facade.insertNote();
        facade.noteUp();
        facade.noteUp();
        facade.noteUp();
        facade.selectNote(notes.get(1));
        facade.insertNote();
        facade.noteUp();
        facade.noteUp();
        facade.noteUp();
        facade.noteUp();
        facade.selectNote(notes.get(2));
        facade.insertNote();
        facade.noteUp();
        facade.noteUp();
        facade.noteUp();
        facade.noteUp();
        facade.noteUp();
        facade.selectNote(notes.get(3));
        facade.insertNote();
        facade.noteUp();
        facade.noteUp();
        facade.noteUp();
        facade.noteUp();
        facade.noteUp();
        facade.noteUp();
        facade.selectNote(notes.get(4));
        facade.insertNote();
        facade.noteUp();
        facade.noteUp();
        facade.noteUp();
        facade.noteUp();
        facade.selectNote(notes.get(5));
        facade.insertNote();
        facade.noteUp();
        facade.noteUp();
        facade.selectNote(notes.get(6));
        facade.insertNote();
        facade.noteUp();
        facade.noteUp();
        facade.noteUp();
        facade.playSong();
        Thread.sleep(10000);
        facade.stopSong();
        // facade.logout();
        System.out.println("Scenario 4 Done");
    }

    // Copy Song
    // Login - Open Public Songs - Copy Song - Open Personal Songs - Open Song - Play - Logout
    private static void scenario5(MusicFacade facade) throws Exception{
        ArrayList<Song> songs;
        facade.login("TestUser", "securePassword");
        songs = facade.openPublicSongs();
        facade.copySong(songs.get(0));
        songs = facade.openMySongs();
        facade.openSong(songs.get(1));
        facade.playSong();
        Thread.sleep(5000);
        facade.stopSong();
        // facade.logout();
        System.out.println("Scenario 5 Done");
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
        facade.playSong();
        Thread.sleep(20000);
        facade.stopSong();
        measures = facade.selectInstrument(instruments.get(0));
        notes = facade.selectMeasure(measures.get(3));
        facade.selectNote(notes.get(3));
        facade.deleteNote();
        notes = facade.selectMeasure(measures.get(6));
        facade.selectNote(notes.get(1));
        facade.noteUp();
        facade.selectMeasure(measures.get(8));        
        facade.removeMeasure();
        facade.selectMeasure(measures.get(0));
        facade.playSong();
        Thread.sleep(17000);
        facade.stopSong();
        // facade.logout();
        System.out.println("Scenario 6 Done");
    }
*/

}
