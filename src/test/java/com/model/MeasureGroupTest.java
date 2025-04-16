package com.model;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class MeasureGroupTest {
    Note defaultNote;
    ArrayList<Note> defaultNotes;
    Measure defaultMeasure;
    Instrument defaultInstrument;

    @Before
	public void setup() {
        defaultNote = new Note(Note.QUARTER_LENGTH, Pitch.REST, 4);
        defaultNotes = new ArrayList<>();
        for(int i=0; i<Measure.DEFAULT_LENGTH; i++){
            Note newNote = new Note(defaultNote);
            defaultNotes.add(newNote);
        }
        defaultMeasure = new Measure(defaultNotes, "text");
        try {  
            defaultInstrument = DataLoader.getInstance().loadInstruments().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}



// New Constructor

    @Test
    public void normalListConstructor(){
        ArrayList<Instrument> instruments = new ArrayList<>();
        instruments.add(defaultInstrument);
        MeasureGroup measureGroup = new MeasureGroup(4, Chord.A_FLAT_MAJ, instruments);

        boolean containsInstrument = measureGroup.getMeasures().containsKey(defaultInstrument);
        assertTrue(containsInstrument);
    }

    @Test
    public void emptyListConstructor(){
        ArrayList<Instrument> instruments = new ArrayList<>();

        assertThrows(Exception.class, () -> {   
            MeasureGroup measureGroup = new MeasureGroup(4, Chord.A_FLAT_MAJ, instruments);
        });
    }

    @Test
    public void nullListConstructor(){
        ArrayList<Instrument> instruments = null;

        assertThrows(Exception.class, () -> {   
            MeasureGroup measureGroup = new MeasureGroup(4, Chord.A_FLAT_MAJ, instruments);
        });
    }



// Copy Contructor

    @Test
    public void normalCopy(){
        ArrayList<Instrument> instruments = new ArrayList<>();
        instruments.add(defaultInstrument);
        MeasureGroup measureGroup = new MeasureGroup(4, Chord.A_FLAT_MAJ, instruments);
        MeasureGroup copy = new MeasureGroup(measureGroup);
        
        Chord originalChord = measureGroup.getChord();
        Chord copyChord = copy.getChord();
        assertEquals(originalChord, copyChord);
    }

    @Test
    public void nullCopy(){
        MeasureGroup copy = null;

        try {
            copy = new MeasureGroup(null);
        } catch (Exception e) {
            assertTrue(false);
        }

        assertNull(copy);
    }
}