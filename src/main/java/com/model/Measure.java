package com.model;

import java.util.ArrayList;

/**
 * An instrument part of a measure
 */
public class Measure {
    private Instrument instrument;
    private ArrayList<Note> music;
    private String test;

    /**
     * Makes a new part
     * @param instrument Instrument of this part
     */
    public Measure(Instrument instrument){
        //TODO
    }

    /**
     * Inserts or changes a note at the given position
     * @param note Note to insert
     * @param position Where to insert the note
     * @return Whether the provided position is valid
     */
    public boolean insertNote(Note note, int position){
        //TODO
        return false;
    }

    /**
     * Replaces a note with a rest
     * @param position The position to insert the rest
     * @return Whether the given position is valid
     */
    public boolean removeNote(int position){
        //TODO
        return false;
    }
}
