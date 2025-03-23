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
     * Constructs a note with the given duration and note name.
     * 
     * @param duration
     * @param noteName
     */
    public Note(int duration, String noteName) {
        this.duration = duration;
        this.pitch = Pitch.fromString(noteName);
        this.octave = 4; // default octave, can be modified later
    }

    /**
     * TODO
     * @param originalNote
     */
    public Note(Note originalNote){
        this.duration = originalNote.getDuration();
        this.pitch = originalNote.getPitch();
        this.octave = originalNote.getOctave();
    }

    public void setPitch(Pitch pitch){
        this.pitch = pitch;
    }

    public void setOctave(int octave){
        this.octave = octave;
    }

    public Pitch getPitch(){
        return this.pitch;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getOctave(){
        return this.octave;
    }

    public void changeDuration(int change) {
            this.duration *= change;
    }

    public void changeDuration() {
        this.duration = QUARTER_LENGTH;
    }

    public boolean up(Key keySignature){
        if(octave>=7)
            return false;
        ArrayList<Pitch> keyPitches = keySignature.getPitches();
        Pitch currentPitch = this.pitch;
        int index = keyPitches.indexOf(currentPitch);
        Pitch newPitch;
        
        if(index==keyPitches.size()-1)
            newPitch = keyPitches.get(0);
        else
            newPitch = keyPitches.get(index+1);

        if((currentPitch==Pitch.B || currentPitch==Pitch.C_FLAT || currentPitch==Pitch.B_FLAT || currentPitch==Pitch.A_SHARP)
                && (newPitch==Pitch.C || newPitch==Pitch.B_SHARP || newPitch==Pitch.C_SHARP || newPitch==Pitch.D_FLAT))
            this.octave++;

        this.pitch = newPitch;
        return true;
    }

    public boolean down(Key keySignature){
        if(octave<=0)
            return false;
        ArrayList<Pitch> keyPitches = keySignature.getPitches();
        Pitch currentPitch = this.pitch;
        int index = keyPitches.indexOf(currentPitch);
        Pitch newPitch;

        if(index==0)
            newPitch = keyPitches.get(keyPitches.size()-1);
        else
            newPitch = keyPitches.get(index-1);


        if((currentPitch==Pitch.C || currentPitch==Pitch.B_SHARP || currentPitch==Pitch.C_SHARP || currentPitch==Pitch.D_FLAT)
                && (newPitch==Pitch.B || newPitch==Pitch.C_FLAT || newPitch==Pitch.B_FLAT || newPitch==Pitch.A_SHARP))
            this.octave--;

        this.pitch = newPitch;
        return true;
    }


    public String getJFugue(){
        String jFugue = "";
        jFugue += pitch.label;
        jFugue += octave;
        jFugue += getJFugueDuration();

        return jFugue;
    }

    private String getJFugueDuration(){
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

    public String getPitch() {
        return pitch.toString();
    }

    public int getLength() {
        return duration;
    }

    public int getOctave() {
        return octave;
    }
}
