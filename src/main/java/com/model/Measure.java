package com.model;

import java.util.ArrayList;

/**
 * An instrument part of a measure
 * @author Simion Cartis
 */
public class Measure {
    private Instrument instrument;
    private ArrayList<Note> notes;
    private String text;

    /**
     * Constructsa new measure with specified instrument.
     * @param instrument Instrument of this part
     */
    public Measure(Instrument instrument){
        this.instrument = instrument;
        this.notes = new ArrayList<>();
        this.text = "";
    }

    /**
     * Constructs a new measure with a list of notes and text.
     * @param notes
     * @param text
     */
    public Measure(ArrayList<Note> notes, String text){
        this.notes = notes;
        this.text = text;
    }

    /**
     * Constructs a new measure with an instrument, list of notes, and text
     * @param instrument
     * @param notes
     * @param text
     */
    public Measure(Instrument instrument, ArrayList<Note> notes, String text){
        this.instrument = instrument;
        this.notes = notes;
        this.text = text;
    }

    /**
     * Inserts a note at the given position
     * @param note Note to insert
     * @param position Where to insert the note
     * @return Whether the provided position is valid
     */
    public boolean insertNote(Note note, int position){
        if (position < 0 || position > notes.size()) {
            return false;
        }
        notes.add(position, note);
        return true;
    }

    /**
     * Replaces a note with a rest
     * @param position The position to insert the rest
     * @return Whether the given position is valid
     */
    public boolean removeNote(int position){
        if (position < 0 || position >= notes.size()) {
            return false;
        }
        notes.set(position, new Note(0, "rest")); // Assuming "rest" represents a silent note
        return true;
    }
}
