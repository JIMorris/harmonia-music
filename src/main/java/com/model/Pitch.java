package com.model;

/**
 * The pitch enum represents all tones of notes, including natural, sharp, and flat notes.
 * 
 * @author Simion Cartis
 */
public enum Pitch {
    CHORD("CHORD", new String[] {"x"," ","C","H","O","R","D"," ","x"}),
    REST("R", new String[] {"x"," ","R","E","-","S","T"," ","x"}),
    A_FLAT("Ab", new String[] {"-"," ","-"," ","-","x","-"," ","-"}),
    A("A", new String[] {"-"," ","-"," ","-","x","-"," ","-"}),
    A_SHARP("A#", new String[] {"-"," ","-"," ","-","x","-"," ","-"}),
    B_FLAT("Bb", new String[] {"-"," ","-"," ","x"," ","-"," ","-"}),
    B("B", new String[] {"-"," ","-"," ","x"," ","-"," ","-"}),
    B_SHARP("B#", new String[] {"-"," ","-"," ","x"," ","-"," ","-"}),
    C_FLAT("Cb", new String[] {"-"," ","-","x","-"," ","-"," ","-"}),
    C("C", new String[] {"-"," ","-","x","-"," ","-"," ","-"}),
    C_SHARP("C#", new String[] {"-"," ","-","x","-"," ","-"," ","-"}),
    D_FLAT("Db", new String[] {"-"," ","x"," ","-"," ","-"," ","-"}),
    D("D", new String[] {"-"," ","x"," ","-"," ","-"," ","-"}),
    D_SHARP("D#", new String[] {"-"," ","x"," ","-"," ","-"," ","-"}),
    E_FLAT("Eb", new String[] {"-","x","-"," ","-"," ","-"," ","-"}),
    E("E", new String[] {"-","x","-"," ","-"," ","-"," ","-"}),
    E_SHARP("E#", new String[] {"-","x","-"," ","-"," ","-"," ","-"}),
    F_FLAT("Fb", new String[] {"-"," ","-"," ","-"," ","-","x","-"}),
    F("F", new String[] {"-"," ","-"," ","-"," ","-","x","-"}),
    F_SHARP("F#", new String[] {"-"," ","-"," ","-"," ","-","x","-"}),
    G_FLAT("Gb", new String[] {"-"," ","-"," ","-"," ","x"," ","-"}),
    G("G", new String[] {"-"," ","-"," ","-"," ","x"," ","-"}),
    G_SHARP("G#", new String[] {"-"," ","-"," ","-"," ","x"," ","-"});

    public final String label;
    public final String[] sheetMusic;

    /**
     * Constructs a pitch with the specified string label.
     * @param label The string repesentation of the pitch
     */
    private Pitch(String label, String[] sheetMusic) {
        this.label = label;
        this.sheetMusic = sheetMusic;
    }
    
    /**
     * Converts a string representation of a pitch into its corresponding pitch enum value.
     * @param pitch
     * @return
     */
    public static Pitch fromString(String pitch){
        for (Pitch p : values()) {
            if (p.label.equalsIgnoreCase(pitch))
                return p;
        }
        return null;
    }

}
