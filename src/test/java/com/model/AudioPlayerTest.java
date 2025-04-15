package com.model;

import java.util.ArrayList;

import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AudioPlayerTest {
    ArrayList<Song> songs;
    Instrument defaultInstrument;
    Song defaultSong;
    ArrayList<MeasureGroup> defaultMeasureGroups;
    static AudioPlayer audioPlayer;

    @BeforeClass
    public static void classSetup(){
        audioPlayer = AudioPlayer.getInstance();
    }

    @Before
	public void setup() {
        try {
            DataLoader.getInstance().loadInstruments().get(0);
            DataLoader.getInstance().loadUsers();
            songs = DataLoader.getInstance().loadSongs();
            defaultSong = songs.get(0);
            defaultInstrument = defaultSong.getInstruments().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        defaultMeasureGroups = defaultSong.getMeasureGroups();
        audioPlayer.openSong(defaultSong);
	}
	
	@AfterClass
	public static void tearDown() {
        try {
            DataLoader.getInstance().loadInstruments();
            DataLoader.getInstance().loadUsers();
            DataLoader.getInstance().loadSongs();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}



// removeMeasure

    @Test
    public void removeNormalMeasure(){
        MeasureGroup secondMeasureGroup = defaultMeasureGroups.get(1);
        MeasureGroup thirdMeasureGroup = defaultMeasureGroups.get(2);
        
        audioPlayer.selectMeasure(secondMeasureGroup.getMeasure(defaultInstrument));
        try {
            audioPlayer.removeMeasure();
        } catch (Exception e) {
            assertTrue(false);
        }
        
        MeasureGroup newSecondMeasureGroup = defaultMeasureGroups.get(1);
        assertEquals(newSecondMeasureGroup, thirdMeasureGroup);
    }

    @Test
    public void removeFirstMeasure(){
        MeasureGroup firstMeasureGroup = defaultMeasureGroups.get(0);
        MeasureGroup secondMeasureGroup = defaultMeasureGroups.get(1);
        
        audioPlayer.selectMeasure(firstMeasureGroup.getMeasure(defaultInstrument));
        try {
            audioPlayer.removeMeasure();
        } catch (Exception e) {
            assertTrue(false);
        }
        
        MeasureGroup newFirstMeasureGroup = defaultMeasureGroups.get(0);
        assertEquals(newFirstMeasureGroup, secondMeasureGroup);
    }

    @Test
    public void removeFinalMeasure(){
        int size = defaultMeasureGroups.size();
        MeasureGroup penultimateMeasureGroup = defaultMeasureGroups.get(size-2);
        MeasureGroup finalMeasureGroup = defaultMeasureGroups.get(size-1);
        
        audioPlayer.selectMeasure(penultimateMeasureGroup.getMeasure(defaultInstrument));
        try {
            audioPlayer.removeMeasure();
        } catch (Exception e) {
            assertTrue(false);
        }
        
        
        size = defaultMeasureGroups.size();
        MeasureGroup newFinalMeasureGroup = defaultMeasureGroups.get(size-1);
        assertEquals(newFinalMeasureGroup, finalMeasureGroup);
    }

    @Test
    public void removeMissingMeasure(){
        int originalSize = defaultMeasureGroups.size();
        MeasureGroup incorrectMeasureGroup = songs.get(1).getMeasureGroups().get(0);
        
        audioPlayer.selectMeasure(incorrectMeasureGroup.getMeasure(defaultInstrument));
        try {
            audioPlayer.removeMeasure();
        } catch (Exception e) {
            assertTrue(false);
        }
        
        int newSize = defaultMeasureGroups.size();
        assertEquals(newSize, originalSize);
    }

    @Test
    public void removeNullMeasure(){
        int originalSize = defaultMeasureGroups.size();
        
        try {  
            audioPlayer.selectMeasure(null);
            audioPlayer.removeMeasure();
        } catch (Exception e) {
            assertTrue(false);
        }
        
        int newSize = defaultMeasureGroups.size();
        assertEquals(newSize, originalSize);
    }



// insertChord

    @Test
    public void insertChordAtRestNote(){
        audioPlayer.deleteNote();
        audioPlayer.insertChord();
        Measure measure = defaultSong.getMeasureGroups().get(0).getMeasure(defaultInstrument);
        
        Pitch notePitch = measure.getNotes().get(0).getPitch();
        Pitch correctPitch = Pitch.CHORD;
        assertEquals(notePitch, correctPitch);
    }

    @Test
    public void insertChordAtWrongNote(){
        audioPlayer.insertChord();
        Measure measure = defaultSong.getMeasureGroups().get(0).getMeasure(defaultInstrument);
        
        Pitch notePitch = measure.getNotes().get(0).getPitch();
        Pitch correctPitch = Pitch.CHORD;
        assertNotEquals(notePitch, correctPitch);
    }
}