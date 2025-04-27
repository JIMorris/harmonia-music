package com.model;

/**
 * The pitch enum represents all tones of notes, including natural, sharp, and
 * flat notes.
 * 
 * @author Simion Cartis
 */
public enum Pitch {
    CHORD("CHORD", new String[] {"x"," ","C","H","O","R","D"," ","x"}, 5),
    REST("R", new String[] {"x"," ","R","E","-","S","T"," ","x"}, 5),
    A_FLAT("Ab", new String[] {"-"," ","-"," ","-","x","-"," ","-"}, 5),
    A("A", new String[] {"-"," ","-"," ","-","x","-"," ","-"}, 5),
    A_SHARP("A#", new String[] {"-"," ","-"," ","-","x","-"," ","-"}, 5),
    B_FLAT("Bb", new String[] {"-"," ","-"," ","x"," ","-"," ","-"}, 6),
    B("B", new String[] {"-"," ","-"," ","x"," ","-"," ","-"}, 6),
    B_SHARP("B#", new String[] {"-"," ","-"," ","x"," ","-"," ","-"}, 6),
    C_FLAT("Cb", new String[] {"-"," ","-","x","-"," ","-"," ","-"}, 0),
    C("C", new String[] {"-"," ","-","x","-"," ","-"," ","-"}, 0),
    C_SHARP("C#", new String[] {"-"," ","-","x","-"," ","-"," ","-"}, 0),
    D_FLAT("Db", new String[] {"-"," ","x"," ","-"," ","-"," ","-"}, 1),
    D("D", new String[] {"-"," ","x"," ","-"," ","-"," ","-"}, 1),
    D_SHARP("D#", new String[] {"-"," ","x"," ","-"," ","-"," ","-"}, 1),
    E_FLAT("Eb", new String[] {"-","x","-"," ","-"," ","-"," ","-"}, 2),
    E("E", new String[] {"-","x","-"," ","-"," ","-"," ","-"}, 2),
    E_SHARP("E#", new String[] {"-","x","-"," ","-"," ","-"," ","-"}, 2),
    F_FLAT("Fb", new String[] {"-"," ","-"," ","-"," ","-","x","-"}, 3),
    F("F", new String[] {"-"," ","-"," ","-"," ","-","x","-"},3 ),
    F_SHARP("F#", new String[] {"-"," ","-"," ","-"," ","-","x","-"}, 3),
    G_FLAT("Gb", new String[] {"-"," ","-"," ","-"," ","x"," ","-"}, 4),
    G("G", new String[] {"-"," ","-"," ","-"," ","x"," ","-"}, 4),
    G_SHARP("G#", new String[] {"-"," ","-"," ","-"," ","x"," ","-"},4 );

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
