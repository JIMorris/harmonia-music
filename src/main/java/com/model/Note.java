package com.model;

import java.util.ArrayList;

/**
 * Represents a musical note with a duration, pitch, and octave
 * 
 * @author
 */
public class Note {
    public static final int QUARTER_LENGTH = 12;
    private int duration;
    private Pitch pitch;
    private int octave;

    /**
     * Constructs a note object with a duration, pitch, and octave
     * 
     * @param duration
     * @param pitch
     * @param octave
     */
    public Note(int duration, Pitch pitch, int octave) {
        this.duration = duration;
        this.pitch = pitch;
        this.octave = octave;
    }

    /**
     * TODO
     * 
     * @param originalNote
     */
    public Note(Note originalNote) {
        this.duration = originalNote.getDuration();
        this.pitch = originalNote.getPitch();
        this.octave = originalNote.getOctave();
    }

    public Pitch getPitch() {
        return this.pitch;
    }

    // TODO Consolidate these two methods
    public int getDuration() {
        return this.duration;
    }

    public int getLength() {
        return duration;
    }

    public int getOctave() {
        return this.octave;
    }

    public String getLabel() {
        return pitch.label;
    }

    public void setPitch(Pitch pitch) {
        this.pitch = pitch;
    }

    public void setOctave(int octave) {
        this.octave = octave;
    }

    public void changeDuration(int change) {
        this.duration /= change;
    }

    public void changeDuration() {
        this.duration = QUARTER_LENGTH;
    }

    public void up(Key keySignature) throws Exception {
        if (octave >= 7)
            throw new Exception("Highest pitch reached");
        ArrayList<Pitch> keyPitches = keySignature.pitches;
        Pitch currentPitch = this.pitch;
        int index = keyPitches.indexOf(currentPitch);
        Pitch newPitch;

        if (index == keyPitches.size() - 1)
            newPitch = keyPitches.get(0);
        else
            newPitch = keyPitches.get(index + 1);

        if ((currentPitch == Pitch.B || currentPitch == Pitch.C_FLAT || currentPitch == Pitch.B_FLAT
                || currentPitch == Pitch.A_SHARP)
                && (newPitch == Pitch.C || newPitch == Pitch.B_SHARP || newPitch == Pitch.C_SHARP
                        || newPitch == Pitch.D_FLAT))
            this.octave++;

        this.pitch = newPitch;
    }

    public void down(Key keySignature) throws Exception {
        if (octave <= 0)
            throw new Exception("Lowest pitch reaches");
        ArrayList<Pitch> keyPitches = keySignature.pitches;
        Pitch currentPitch = this.pitch;
        int index = keyPitches.indexOf(currentPitch);
        Pitch newPitch;

        if (index == 0)
            newPitch = keyPitches.get(keyPitches.size() - 1);
        else
            newPitch = keyPitches.get(index - 1);

        if ((currentPitch == Pitch.C || currentPitch == Pitch.B_SHARP || currentPitch == Pitch.C_SHARP
                || currentPitch == Pitch.D_FLAT)
                && (newPitch == Pitch.B || newPitch == Pitch.C_FLAT || newPitch == Pitch.B_FLAT
                        || newPitch == Pitch.A_SHARP))
            this.octave--;

        this.pitch = newPitch;
    }

    public String[] getSheetMusic() {
        String[] sheetMusic = pitch.sheetMusic.clone();
        for (int i = 0; i < sheetMusic.length; i++) {
            if (sheetMusic[i].equals("x"))
                sheetMusic[i] = getSheetMusicDuration();
        }
        return sheetMusic;
    }

    public String getJFugue(Chord chord) {
        String jFugue = "";
        if (pitch == Pitch.CHORD) {
            jFugue += chord.label;
        } else {
            jFugue += pitch.label;
            jFugue += octave;
        }
        jFugue += getJFugueDuration();

        return jFugue;
    }

    private String getSheetMusicDuration() {
        switch (duration) {
            case 12:
                return "q";
            case 6:
                return "e";
            case 4:
                return "t";
            case 3:
                return "s";
            default:
                throw new AssertionError();
        }
    }

    private String getJFugueDuration() {
        switch (duration) {
            case 12:
                return "q";
            case 6:
                return "i";
            case 4:
                return "i*";
            case 3:
                return "s";
            default:
                throw new AssertionError();
        }
    }
}
