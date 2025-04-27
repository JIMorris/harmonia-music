package com.model;

import java.util.ArrayList;

/**
 * Represents a musical note with a duration, pitch, and octave
 * This class provides methods to manipulate the note's properties,
 * such as changing its duration, pitch, or octave.
 * 
 * @author James Morris
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
     * Constructs a new Note object as a copy of an existing Note.
     *
     * @param originalNote The Note object to copy.
     */
    public Note(Note originalNote) {
        this.duration = originalNote.getDuration();
        this.pitch = originalNote.getPitch();
        this.octave = originalNote.getOctave();
    }

    /**
     * Retrieves the pitch of the note.
     *
     * @return The pitch of the note.
     */
    public Pitch getPitch() {
        return this.pitch;
    }

    /**
     * Retrieves the duration of the note.
     *
     * @return The duration of the note.
     */
    public int getDuration() {
        return this.duration;
    }

    /**
     * Retrieves the octave of the note.
     *
     * @return The octave of the note.
     */
    public int getOctave() {
        return this.octave;
    }

    /**
     * Retrieves the label of the note's pitch.
     *
     * @return The label of the note's pitch.
     */
    public String getLabel() {
        return pitch.label;
    }

    /**
     * Sets the pitch of the note.
     *
     * @param pitch The new pitch to set for the note.
     */
    public void setPitch(Pitch pitch) {
        this.pitch = pitch;
    }

    /**
     * Sets the octave of the note.
     *
     * @param octave The new octave to set for the note.
     */
    public void setOctave(int octave) {
        this.octave = octave;
    }

    /**
     * Changes the duration of the note by dividing it by a specified value.
     *
     * @param change The value to divide the duration by.
     */
    public void changeDuration(int change) {
        this.duration /= change;
    }

    /**
     * Resets the duration of the note to the default quarter length.
     */
    public void changeDuration() {
        this.duration = QUARTER_LENGTH;
    }

    /**
     * Raises the pitch of the note by one step within the given key signature.
     * If the pitch reaches the highest note in the key, it wraps around and increases the octave.
     *
     * @param keySignature The key signature to use for determining the next pitch.
     * @throws Exception If the note is already at the highest possible pitch.
     */
    public void up(Key keySignature) throws Exception {
        if (octave >= 6)
            throw new Exception("Highest pitch reached");
        ArrayList<Pitch> keyPitches = keySignature.pitches;
        Pitch currentPitch = this.pitch;
        int index = keyPitches.indexOf(currentPitch);
        Pitch newPitch;

        if (index >= keyPitches.size() - 1)
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

    /**
     * Lowers the pitch of the note by one step within the given key signature.
     * If the pitch reaches the lowest note in the key, it wraps around and decreases the octave.
     *
     * @param keySignature The key signature to use for determining the previous pitch.
     * @throws Exception If the note is already at the lowest possible pitch.
     */
    public void down(Key keySignature) throws Exception {
        if (octave <= 3)
            throw new Exception("Lowest pitch reached");
        ArrayList<Pitch> keyPitches = keySignature.pitches;
        Pitch currentPitch = this.pitch;
        int index = keyPitches.indexOf(currentPitch);
        Pitch newPitch;

        if(index<=0)
            newPitch = keyPitches.get(keyPitches.size()-1);
        else
            newPitch = keyPitches.get(index - 1);

        if ((currentPitch == Pitch.C || currentPitch == Pitch.B_SHARP || currentPitch == Pitch.C_SHARP
                || currentPitch == Pitch.D_FLAT)
                && (newPitch == Pitch.B || newPitch == Pitch.C_FLAT || newPitch == Pitch.B_FLAT
                        || newPitch == Pitch.A_SHARP))
            this.octave--;

        this.pitch = newPitch;
    }

    /**
     * Retrieves the sheet music representation of the note.
     * This includes the pitch and duration of the note.
     *
     * @return An array of strings representing the sheet music for the note.
     */
    public String[] getSheetMusic() {
        String[] sheetMusic = pitch.sheetMusic.clone();
        for (int i = 0; i < sheetMusic.length; i++) {
            if (sheetMusic[i].equals("x"))
                sheetMusic[i] = getSheetMusicDuration();
        }
        return sheetMusic;
    }

    /**
     * Retrieves the JFugue representation of the note.
     * This includes the pitch, octave, and duration of the note.
     *
     * @param chord The chord associated with the note.
     * @return A string representing the note in JFugue format.
     */
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

    public int getMusicPosition(){
        if(getMusicIcon().equals("error"))
            return 5;
        else if(this.pitch==Pitch.REST || this.pitch==Pitch.CHORD)
            return 8;
        return this.pitch.position + 36 - octave*7;
    }

    public String getMusicIcon(){
        String length;
        String type;

        switch (this.duration) {
            case 12:
                length = "quarter";
                break;
            case 6:
                length = "eighth";
                break;
            case 3:
                length = "sixteenth";
                break;
            default:
                return "error";
        }

        switch (this.pitch) {
            case REST:
                type = "Rest";
                break;
            case CHORD:
                type = "Chord";
                break;
            default:
                type = "Note";
        }

        return length + type;
        
    }

    /**
     * Retrieves the sheet music duration of the note.
     *
     * @return A string representing the duration of the note in sheet music format.
     */
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

    /**
     * Retrieves the JFugue duration of the note.
     *
     * @return A string representing the duration of the note in JFugue format.
     */
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
