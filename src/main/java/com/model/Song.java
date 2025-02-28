package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class Song {
    private UUID songID;
    private String title;
    private User author;
    private String description;
    private ArrayList<Genre> genres;
    private boolean published;

    private int tempo;
    private Key keySignature;
    private int timeSignatureNum;
    private int timeSignatureDen;
    private ArrayList<Measure> measures;
    private ArrayList<Instrument> instruments;

    public Song(String title, User author) {

    }

    public Song(Song song) {

    }

    public boolean idMatch(UUID songID) {
        return this.songID == songID;
    }

    public Reaction addReaction(int rating, String comment, User author) {
        return null;
    }

    public void removeReaction(Reaction reaction) {

    }

    public Measure getMeasure(int number) {
        return null;
    }

    public void addInstrument(Instrument instrument) {

    }

    public void removeInstrument(Instrument instrument) {

    }

    public void addMeasure() {

    }

    public void removeMeasure(int measureNumber) {

    }

}
