package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class SongList {
    private static SongList instnace;
    private ArrayList<Song> songs;

    private SongList() {

    }

    public SongList getInstance() {
        return null;
    }

    public Song getSong(UUID songID) {
        return null;
    }

    public Song newSong() {
        return null;
    }

    public Song copySong(Song song) {
        return null;
    }

    public void removeSong(Song song) {

    }

    public static ArrayList<Song> sort(String type, boolean up, ArrayList<Song> songs) {
        return null;
    }

    // (BPM, Length, Difficulty)

    public ArrayList<Song> filterByTitle(String title) {
        return null;
    }

    public ArrayList<Song> filterByGenre(Genre genre) {
        return null;
    }

    public ArrayList<Song> filterByBPM(int minBPM, int maxBPM) {
        return null;
    }

    public ArrayList<Song> filterBylength(int minLength, int maxLength) {
        return null;
    }

    public ArrayList<Song> filterByDifficulty(int difficulty) {
        return null;
    }

    public void save() {
        
    }
}
