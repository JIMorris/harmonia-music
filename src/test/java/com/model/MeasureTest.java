package com.model;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class MeasureTest {
    Note defaultNote;
    ArrayList<Note> defaultNotes;
    Measure defaultMeasure;

    @Before
	public void setup() {
        defaultNote = new Note(Note.QUARTER_LENGTH, Pitch.REST, 4);
        defaultNotes = new ArrayList<>();
        for(int i=0; i<Measure.DEFAULT_LENGTH; i++){
            Note newNote = new Note(defaultNote);
            defaultNotes.add(newNote);
        }
        defaultMeasure = new Measure(defaultNotes, "text");
	}



// Load Constructor

    @Test
    public void constructFull(){
        Measure measure = new Measure(defaultNotes, "text");

        ArrayList<Note> testNotes = measure.getNotes();
        assertEquals(testNotes, defaultNotes);
    }

    @Test
    public void constructEmptyNotes(){
        ArrayList<Note> emptyList = new ArrayList<>();
        Measure measure = new Measure(emptyList, "text");

        int size = measure.getNotes().size();
        assertNotEquals(size, 0);
    }

    @Test
    public void constructNullNotes(){
        Measure measure = new Measure(null, "text");

        ArrayList<Note> testNotes = measure.getNotes();
        assertNotNull(testNotes);
    }
    
    @Test
    public void constructEmptyText(){
        Measure measure = new Measure(defaultNotes, "");

        String text = measure.getText();
        assertEquals(text, "");
    }

    @Test
    public void constructNullText(){
        Measure measure = new Measure(defaultNotes, null);

        String text = measure.getText();
        assertNotNull(text);
    }



// Copy Constructor

    @Test
    public void defaultCopy(){
        Measure copy = new Measure(defaultMeasure);

        int testSize = defaultNotes.size();
        int copySize = copy.getNotes().size();
        assertEquals(copySize, testSize);
    }

    @Test
    public void nullCopy(){
        Measure copy = null;

        try {
            copy = new Measure(null);
            
        } catch (Exception e) {
            assertTrue(false);
        }

        assertNotNull(copy);
    }



// splitNote

    @Test
    public void splitNullNote(){
        int originalSize = defaultMeasure.getNotes().size();

        try {
            defaultMeasure.splitNote(null, 3);
        } catch (Exception e) {
            assertTrue(false);
        }

        int newSize = defaultMeasure.getNotes().size();
        assertEquals(newSize, originalSize);
    }

    @Test
    public void splitMissingNote(){
        int originalSize = defaultMeasure.getNotes().size();

        try {
            defaultMeasure.splitNote(defaultNote, 3);
        } catch (Exception e) {
            assertTrue(false);
        }

        int newSize = defaultMeasure.getNotes().size();
        assertEquals(newSize, originalSize);
    }

    @Test
    public void splitBadDivision(){
        assertThrows(Exception.class, () -> {
            defaultMeasure.splitNote(defaultNote, 5);
        });
    }

    @Test
    public void splitTwoDivisions(){
        Note firstNote = defaultMeasure.getNotes().get(0);
        try { 
            defaultMeasure.splitNote(firstNote, 2);
        } catch (Exception e) {
            assertTrue(false);
        }

        int size = defaultMeasure.getNotes().size();
        assertEquals(size, 5);
    }

    @Test
    public void splitThreeDivisions(){
        Note firstNote = defaultMeasure.getNotes().get(0);
        try { 
            defaultMeasure.splitNote(firstNote, 3);
        } catch (Exception e) {
            assertTrue(false);
        }

        int size = defaultMeasure.getNotes().size();
        assertEquals(size, 6);
    }

    @Test
    public void splitFourDivisions(){
        Note firstNote = defaultMeasure.getNotes().get(0);
        try { 
            defaultMeasure.splitNote(firstNote, 4);
        } catch (Exception e) {
            assertTrue(false);
        }

        int size = defaultMeasure.getNotes().size();
        assertEquals(size, 7);
    }



// combineNotes

    @Test
    public void combineQuarterNotes(){
        Note firstNote = defaultMeasure.getNotes().get(0);
        assertThrows(Exception.class, () -> {
            defaultMeasure.combineNotes(firstNote);
        });
    }

    @Test
    public void combineNullNotes(){
        try { 
            defaultMeasure.combineNotes(null);
        } catch (Exception e) {
            assertTrue(false);
        }

        int size = defaultMeasure.getNotes().size();
        assertEquals(size, 4);
    }

    @Test
    public void combineMissingNotes(){
        try { 
            defaultMeasure.combineNotes(defaultNote);
        } catch (Exception e) {
            assertTrue(false);
        }

        int size = defaultMeasure.getNotes().size();
        assertEquals(size, 4);
    }

    @Test
    public void combineBadNoteGrouping(){
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(new Note(defaultNote));
        Note note = new Note(6, Pitch.REST, 4);
        notes.add(note);
        notes.add(new Note(defaultNote));
        notes.add(new Note(6, Pitch.REST, 4));
        notes.add(new Note(defaultNote));
        notes.add(new Note(defaultNote));
        int originalSize = notes.size();

        Measure measure = new Measure(notes, "text");
        try {
            measure.combineNotes(note);
        } catch (Exception e) {
            assertTrue(false);
        }

        int size = measure.getNotes().size();
        assertEquals(size, originalSize);
    }

    @Test
    public void combineTwoNotes(){
        Note firstNote = defaultMeasure.getNotes().get(0);
        try { 
            defaultMeasure.splitNote(firstNote, 2);
            defaultMeasure.combineNotes(firstNote);
        } catch (Exception e) {
            assertTrue(false);
        }

        int size = defaultMeasure.getNotes().size();
        assertEquals(size, 4);
    }

    @Test
    public void combineThreeNotes(){
        Note firstNote = defaultMeasure.getNotes().get(0);
        try { 
            defaultMeasure.splitNote(firstNote, 3);
            defaultMeasure.combineNotes(firstNote);
        } catch (Exception e) {
            assertTrue(false);
        }

        int size = defaultMeasure.getNotes().size();
        assertEquals(size, 4);
    }

    @Test
    public void combineFourNotes(){
        Note firstNote = defaultMeasure.getNotes().get(0);
        try { 
            defaultMeasure.splitNote(firstNote, 4);
            defaultMeasure.combineNotes(firstNote);
        } catch (Exception e) {
            assertTrue(false);
        }

        int size = defaultMeasure.getNotes().size();
        assertEquals(size, 4);
    }
}