package com.model;

public enum Pitch {
    REST(""),
    A_FLAT(""),
    A(""),
    A_SHARP(""),
    B_FLAT(""),
    B(""),
    B_SHARP(""),
    C_FLAT(""),
    C(""),
    C_SHARP(""),
    D_FLAT(""),
    D(""),
    D_SHARP(""),
    E_FLAT(""),
    E(""),
    E_SHARP(""),
    F_FLAT(""),
    F(""),
    F_SHARP(""),
    G_FLAT(""),
    G(""),
    G_SHARP("");

    public final String label;

    private Pitch(String label) {
        this.label = label;
    }
    
    /**
     * TODO
     * @param pitch
     * @return
     */
    public static Pitch fromString(String pitch){
        //TODO
        return null;
    }

}
