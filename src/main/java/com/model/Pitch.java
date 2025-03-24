package com.model;

/**
 * The pitch enum represents all tones of notes, including natural, sharp, and
 * flat notes.
 * 
 * @author Simion Cartis
 */
public enum Pitch {
    CHORD("CHORD"),
    REST("R"),
    A_FLAT("Ab"),
    A("A"),
    A_SHARP("A#"),
    B_FLAT("Bb"),
    B("B"),
    B_SHARP("B#"),
    C_FLAT("Cb"),
    C("C"),
    C_SHARP("C#"),
    D_FLAT("Db"),
    D("D"),
    D_SHARP("D#"),
    E_FLAT("Eb"),
    E("E"),
    E_SHARP("E#"),
    F_FLAT("Fb"),
    F("F"),
    F_SHARP("F#"),
    G_FLAT("Gb"),
    G("G"),
    G_SHARP("G#");

    public final String label;

    /**
     *  constructs an instance of the Pitch enumeration
     * 
     * @param label takes in a label (String) for the values within the enumeration
     */
    private Pitch(String label) {
        this.label = label;
    }

    /**
     * Converts a string representation of a Pitch into its corresponding enum value
     * 
     * @param pitch is a String which will be used to match the desired Pitch's
     *              label
     * @return returns an enumeration of type Pitch whose label matches the String
     *         parameter
     */
    public static Pitch fromString(String pitch) {
        for (Pitch p : values()) {
            if (p.label.equalsIgnoreCase(pitch))
                return p;
        }
        return null;
    }

}
