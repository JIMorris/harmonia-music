package com.model;

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

    public int getDuration() {
        return this.duration;
    }

    public void changeDuration(int change) {
            this.duration *= change;
    }

    public void changeDuration() {
        this.duration = QUARTER_LENGTH;
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
}
