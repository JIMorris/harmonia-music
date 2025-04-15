package com.model;

import java.util.ArrayList;

/**
 * An instrument part of a measure
 * 
 * @author Simion Cartis
 */
public class Measure {
    public static final int DEFAULT_LENGTH = 4;
    private int length;
    private ArrayList<Note> notes;
    private String text;

    /**
     * Constructsa new blank measure
     */
    public Measure() {
        this.length = DEFAULT_LENGTH;
        this.notes = new ArrayList<>();
        fillNotes();
        this.text = "";
    }

    /**
     * Constructs a new measure with a list of notes and text.
     * 
     * @param notes The list of notes to be included in the measure
     * @param text The text for the measure
     */
    public Measure(ArrayList<Note> notes, String text) {
        this.length = DEFAULT_LENGTH;
        this.notes = notes;
        this.text = text;
    }

    /**
     * Contstructs a copy of an existing measure
     * 
     * @param original The measure to be copied
     */
    public Measure(Measure original){
        this.length = original.getLength();
        this.text = original.getText();
        this.notes = cloneNotes(original.getNotes());
    }
    
    /**
     * Returns the list of notes in the measure
     * 
     * @return The list of notes
     */
    public ArrayList<Note> getNotes() {
        return notes;
    }

    /**
     * Returns the length of the measure
     * 
     * @return The length of the measure
     */
    public int getLength(){
        return length;
    }

    /**
     * Returns the associated text with the measure
     * 
     * @return The measure's text annotation
     */
    public String getText(){
        return text;
    }

    /**
     * Sets the text annotation for the measure
     * 
     * @param text The text to set
     */
    public void setText(String text){
        this.text = text;
    }

    /**
     * Splits a given note into multiple smaller notes of equal duration
     * 
     * @param note The note to be split
     * @param division The number of parts to divide the note into (2, 3, 4)
     * @throws Exception If the division value is invalid
     */
    public void splitNote(Note note, int division) throws Exception{
        if (division < 2 || division > 4)
            throw new Exception("Division size must be 2, 3, or 4");

        note.changeDuration(division);
        for (int i = 1; i < division; ++i) {
            Note noteCopy = new Note(note);
            notes.add(notes.indexOf(note) + i, noteCopy);
        }
    }

    /**
     * Combines multiple smaller notes into a single larger note if possible
     * 
     * @param note The note to be combined with adjacent notes
     * @throws Exception If the note can't be further combined
     */
    public void combineNotes(Note note) throws Exception{
        if(note.getDuration()>= Note.QUARTER_LENGTH)
            throw new Exception("Note cannot be further combined");

        Note firstNote = getFirstNote(note);
        for (int i = 1; i < Note.QUARTER_LENGTH/firstNote.getDuration(); ++i) {
            notes.remove(notes.indexOf(firstNote)+i);
        }
        firstNote.changeDuration();
    }

    /**
     * Retrieves the first note in a group of combined notes
     * 
     * @param groupNote A note belonging to a group of combined notes
     * @return The first group, or null if not found
     */
    private Note getFirstNote(Note groupNote){
        ArrayList<Note> noteGroup = new ArrayList<>();
        int count = 0;
        for(Note note : notes){
            count += note.getDuration();
            noteGroup.add(note);
            if(count!=Note.QUARTER_LENGTH)
                continue;
            if(noteGroup.contains(groupNote))
                return noteGroup.get(0);
            else{
                count = 0;
                noteGroup = new ArrayList<>();
            }
        }
        return null;
    }

    /**
     * Initializes the measure with default rest notes
     */
    private void fillNotes(){
        for(int i=0; i<this.length; i++){
            notes.add(new Note(Note.QUARTER_LENGTH, Pitch.REST, 4));
        }
    }

    /**
     * Creates a copy of a given list of notes
     * 
     * @param originalNotes The original list of notes to be cloned
     * @return A new list containing copies of the original notes
     */
    private ArrayList<Note> cloneNotes(ArrayList<Note> originalNotes){
        ArrayList<Note> newNotes = new ArrayList<>();
        for(Note note : originalNotes){
            newNotes.add(new Note(note));
        }
        return newNotes;
    }
}
