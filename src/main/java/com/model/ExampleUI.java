package com.model;

import java.util.ArrayList;

/**
 * Temporary UI to test the backend implementation
 * 
 * @author James Morris
 */
public class ExampleUI {
    public static void main(String[] args) {
        try {

            // Account Scenario
            System.out.println("Starting Account Scenario\n");
            accountScenarioSignup();
            accountScenarioLogin();
            System.out.println("\nAccount Scenario Done\n");

            // Play and Print Scenario
            System.out.println("\nStarting Play and Print Scenario\n");
            playAndPrintScenario();
            System.out.println("\nPlay and Print Scenario Done\n");

            // New Song Scenario
            System.out.println("\nStating Make and Print Scenario\n");
            newSongScenarioCreate();
            newSongScenarioPlay();
            System.out.println("\nMake and Print Scenario Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * Fail to signup as ffredrickson, then signup as ffred
     * 
     * @throws Exception Any errors that may occur during this scenario (except username already taken)
     */
    private static void accountScenarioSignup() throws Exception{
        MusicFacade facade = MusicFacade.getInstance();

        // Attempt to signup as ffredrickson
        try {
            facade.signup("ffredrickson", "uniquePassword", "Fred", "Fredrickson");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Signup as ffred
        facade.signup("ffred", "uniquePassword", "Fred", "Fredrickson");
        facade.logout();

    }

    /**
     * Login as ffred then logout
     * 
     * @throws Exception Any errors that may occur during this scenario
     */
    private static void accountScenarioLogin() throws Exception{
        MusicFacade facade = MusicFacade.getInstance();

        // Login and logout of ffred
        facade.login("ffred", "uniquePassword");
        facade.logout();
    }

    /**
     * Open a song, play it, then print it to a txt
     * 
     * @throws Exception Any errors that may occur during this scenario
     */
    private static void playAndPrintScenario() throws Exception{
        MusicFacade facade = MusicFacade.getInstance();
        ArrayList<Song> songs;
        ArrayList<Instrument> instruments;
        ArrayList<String> filter= new ArrayList<>();

        // Login and filter songs
        facade.login("ffred", "uniquePassword");
        filter.add("JIMorris");
        songs = facade.filterSongs("author", filter);
        System.out.println("Songs:");
        for(Song song : songs){
            System.out.println(song.getTitle());
        }

        // Open song and play song
        instruments = facade.openSong(songs.get(0));
        facade.playSong();
        Thread.sleep(17500);
        facade.stopSong();

        // Print song and logout
        facade.printSong();
        facade.logout();
    }

    /**
     * Create a new song, add some notes, and play it
     * 
     * @throws Exception Any errors that may occur during this scenario
     */
    private static void newSongScenarioCreate() throws Exception{
        MusicFacade facade = MusicFacade.getInstance();
        ArrayList<Instrument> instruments = facade.getAllInstruments();
        ArrayList<Measure> measures;
        ArrayList<Note> notes;
        ArrayList<Genre> genres = new ArrayList<>();
        genres.add(Genre.POP);

        // Login and create new song
        facade.login("ffredrickson", "myPassword");
        facade.newSong("A Horse Journey", "An example song", genres, 2, 120, Key.A_MINOR, instruments.get(0));

        // Look in ffredricksons songs, and open song
        ArrayList<Song> songs = facade.openMySongs();
        instruments = facade.openSong(songs.get(0));
        measures = facade.selectInstrument(instruments.get(0));
        notes = facade.selectMeasure(measures.get(0));

        // Edit Music
        facade.selectNote(notes.get(0));
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
        facade.selectNote(notes.get(5));
        facade.insertNote();

        // Play song and logout
        facade.selectMeasure(measures.get(0));
        facade.playSong();
        Thread.sleep(7500);
        facade.stopSong();
        facade.logout();
    }

    /**
     * Open a song, play it
     * 
     * @throws Exception Any errors that may occur during this scenario
     */
    public static void newSongScenarioPlay() throws Exception{
        MusicFacade facade = MusicFacade.getInstance();
        ArrayList<Song> songs;
        ArrayList<String> filter = new ArrayList<>();

        // Login and Filter Songs
        facade.login("ffred", "uniquePassword");
        filter.add("A Horse Journey");
        songs = facade.filterSongs("title", filter);

        // Open song, play it, and log out
        facade.openSong(songs.get(0));
        facade.playSong();
        Thread.sleep(7500);
        Thread.sleep(7500);
        facade.logout();
    }
}