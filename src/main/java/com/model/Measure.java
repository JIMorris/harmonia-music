package com.model;

import java.util.ArrayList;

/**
 * An instrument part of a measure
 * 
 * @author Simion Cartis
 */
public class Measure {
    private int length;
    private ArrayList<Note> notes;
    private String text;

    /**
     * Constructsa new blank measure
     */
    public Measure() {
        this.length = 4;
        this.notes = new ArrayList<>();
        fillNotes();
        this.text = "";
    }

    /**
     * Constructs a new measure with a list of notes and text.
     * 
     * @param notes
     * @param text
     */
    public Measure(ArrayList<Note> notes, String text) {
        this.length=4;
        this.notes = notes;
        this.text = text;
    }

    /**
     * TODO
     * @param original
     */
    public Measure(Measure original){
        this.length = original.getLength();
        this.text = original.getText();
        this.notes = cloneNotes(notes);
    }

    /**
     * TODO
     */
    private void fillNotes(){
        for(int i=0; i<this.length; i++){
            notes.add(new Note(Note.QUARTER_LENGTH, Pitch.REST, 4));
        }
    }

    private ArrayList<Note> cloneNotes(ArrayList<Note> originalNotes){
        ArrayList<Note> newNotes = new ArrayList<>();
        for(Note note : originalNotes){
            newNotes.add(new Note(note));
        }

        return null;
    }

    /**
     * Inserts a note at the given position
     * 
     * @param note     Note to insert
     * @param position Where to insert the note
     * @return Whether the provided position is valid
     */
    public boolean insertNote(Note note, int position) {
        if (position < 0 || position > notes.size()) {
            return false;
        }
        notes.add(position, note);
        return true;
    }

    /**
     * Replaces a note with a rest
     * 
     * @param position The position to insert the rest
     * @return Whether the given position is valid
     */
    public boolean removeNote(int position) {
        if (position < 0 || position >= notes.size()) {
            return false;
        }
        notes.set(position, new Note(0, "rest")); // Assuming "rest" represents a silent note
        return true;
    }

    public boolean splitNote(Note note, int division) {
        if (division < 2 && division > 4) {
            System.out.println("invalid division size"); // temp error message
            return false;
        }
        note.changeDuration(division);
        for (int i = 1; i < 2 * (division - 1); ++i) {
            Note NoteCopy = new Note(note.getDuration(), "copy of note");
            insertNote(NoteCopy, notes.indexOf(note) + i);
        }
        return true;
    }

    public boolean combineNotes(Note note) {
        if(note.getDuration()>= Note.QUARTER_LENGTH) {
            System.out.println("note cannot be further combined"); //temp error message
            return false;
        }
        for (int i = 1; i < note.getDuration()/Note.QUARTER_LENGTH; ++i) {
            removeNote(notes.indexOf(note)+i);
        }
        note.changeDuration();
        return true;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public int getLength(){
        return length;
    }

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }
}
