package com.model;

/**
 * The pitch enum represents all tones of notes, including natural, sharp, and
 * flat notes.
 * 
 * @author Simion Cartis
 */
public enum Pitch {
    CHORD("CHORD", new String[] {"x"," ","C","H","O","R","D"," ","x"}, 8),
    REST("R", new String[] {"x"," ","R","E","-","S","T"," ","x"}, 8),
    A_FLAT("Ab", new String[] {"-"," ","-"," ","-","x","-"," ","-"}, 1),
    A("A", new String[] {"-"," ","-"," ","-","x","-"," ","-"}, 1),
    A_SHARP("A#", new String[] {"-"," ","-"," ","-","x","-"," ","-"}, 1),
    B_FLAT("Bb", new String[] {"-"," ","-"," ","x"," ","-"," ","-"}, 0),
    B("B", new String[] {"-"," ","-"," ","x"," ","-"," ","-"}, 0),
    B_SHARP("B#", new String[] {"-"," ","-"," ","x"," ","-"," ","-"}, 0),
    C_FLAT("Cb", new String[] {"-"," ","-","x","-"," ","-"," ","-"}, 6),
    C("C", new String[] {"-"," ","-","x","-"," ","-"," ","-"}, 6),
    C_SHARP("C#", new String[] {"-"," ","-","x","-"," ","-"," ","-"}, 6),
    D_FLAT("Db", new String[] {"-"," ","x"," ","-"," ","-"," ","-"}, 5),
    D("D", new String[] {"-"," ","x"," ","-"," ","-"," ","-"}, 5),
    D_SHARP("D#", new String[] {"-"," ","x"," ","-"," ","-"," ","-"}, 5),
    E_FLAT("Eb", new String[] {"-","x","-"," ","-"," ","-"," ","-"}, 4),
    E("E", new String[] {"-","x","-"," ","-"," ","-"," ","-"}, 4),
    E_SHARP("E#", new String[] {"-","x","-"," ","-"," ","-"," ","-"}, 4),
    F_FLAT("Fb", new String[] {"-"," ","-"," ","-"," ","-","x","-"}, 3),
    F("F", new String[] {"-"," ","-"," ","-"," ","-","x","-"},3 ),
    F_SHARP("F#", new String[] {"-"," ","-"," ","-"," ","-","x","-"}, 3),
    G_FLAT("Gb", new String[] {"-"," ","-"," ","-"," ","x"," ","-"}, 2),
    G("G", new String[] {"-"," ","-"," ","-"," ","x"," ","-"}, 2),
    G_SHARP("G#", new String[] {"-"," ","-"," ","-"," ","x"," ","-"},2);

    public final String label;
    public final String[] sheetMusic;
    public final int position;

    /**
     *  constructs an instance of the Pitch enumeration
     * 
     * @param label takes in a label (String) for the values within the enumeration
     */
    private Pitch(String label, String[] sheetMusic, int position) {
        this.label = label;
        this.sheetMusic = sheetMusic;
        this.position = position;
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
