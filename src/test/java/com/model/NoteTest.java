package com.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the Note class.
 */
public class NoteTest {
    private Note note;
    private Key keySignature;

    @Before
    public void setUp() {
        note = new Note(12, Pitch.C, 4);  // Example Note: C4, quarter note
        keySignature = Key.C_MAJOR;  // Using an enum constant instead of "new Key()"
    }

    @Test
    public void testGetPitch() {
        assertEquals(Pitch.C, note.getPitch());
    }

    @Test
    public void testGetDuration() {
        assertEquals(12, note.getDuration());
    }

    @Test
    public void testGetOctave() {
        assertEquals(4, note.getOctave());
    }

    @Test
    public void testChangeDuration() {
        note.changeDuration(2);
        assertEquals(6, note.getDuration());
    }

    @Test
    public void testChangeDurationToDefault() {
        note.changeDuration();
        assertEquals(Note.QUARTER_LENGTH, note.getDuration());
    }

    @Test
    public void testUp() throws Exception {
        note.up(keySignature);
        assertEquals(Pitch.D, note.getPitch()); // Assumes C → D in C Major
    }

    @Test
    public void testDown() throws Exception {
        note.down(keySignature);
        assertEquals(Pitch.B, note.getPitch()); // Assumes C → B in C Major
    }

    @Test(expected = Exception.class)
    public void testUpHighestOctave() throws Exception {
        Note highNote = new Note(12, Pitch.B, 7);
        highNote.up(keySignature);
    }

    @Test(expected = Exception.class)
    public void testDownLowestOctave() throws Exception {
        Note lowNote = new Note(12, Pitch.C, 0);
        lowNote.down(keySignature);
    }

    @Test
    public void testGetLabel() {
        assertEquals("C", note.getLabel());
    }

    @Test
    public void testGetSheetMusic() {
        String[] expectedSheetMusic = {"C", "q"}; // Assuming this structure
        assertArrayEquals(expectedSheetMusic, note.getSheetMusic());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidNoteWithNegativeOctave() {
        Note invalidNote = new Note(12, Pitch.C, -1); // Invalid octave
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidNoteWithHighOctave() {
        Note invalidNote = new Note(12, Pitch.C, 8); // Invalid octave
    }
    
    @Test(expected = Exception.class)
    public void testUpMethodAtMaxOctave() throws Exception {
        Note noteAtMaxOctave = new Note(12, Pitch.B, 7); // Assuming 7 is max
        noteAtMaxOctave.up(Key.C_MAJOR); // Should throw an exception
    }

    @Test(expected = Exception.class)
    public void testDownMethodAtMinOctave() throws Exception {
        Note noteAtMinOctave = new Note(12, Pitch.C, 0); // Assuming 0 is the min
        noteAtMinOctave.down(Key.C_MAJOR); // Should throw an exception
    }
    
    @Test(expected = NullPointerException.class)
    public void testSetNullPitch() {
        Note note = new Note(12, Pitch.C, 4);
        note.setPitch(null); // Setting null pitch should throw exception
    }
    
    @Test(expected = NullPointerException.class)
    public void testUpWithNullKey() throws Exception {
        Note note = new Note(12, Pitch.C, 4);
        note.up(null); // Passing null should throw exception
    }
    
    @Test
    public void testInvalidSheetMusicConversion() {
        Note invalidDurationNote = new Note(100, Pitch.C, 4); // Invalid duration
        String[] expectedSheetMusic = {"C", "x"};
        assertArrayEquals(expectedSheetMusic, invalidDurationNote.getSheetMusic());
    }

    @Test
    public void testUpMethodGoingTooFar() throws Exception {
        Note note = new Note(12, Pitch.B, 6);  // Assuming B is the highest note
        note.up(Key.C_MAJOR);  // This should cause an octave increase
        assertEquals(7, note.getOctave());  // If this passes, test should fail!
    }
    
    @Test
    public void testInvalidSheetMusicFormat() {
        Note note = new Note(100, Pitch.C, 4);  // Invalid duration
        String[] sheetMusic = note.getSheetMusic();  
        assertNotEquals("C", sheetMusic[0]);  // We expect the first element not to be "C" in invalid case
    }
    

}
