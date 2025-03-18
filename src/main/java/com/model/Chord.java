package com.model;

public enum Chord {
    A_FLAT_MAJ("Abmaj"),
    A_FLAT_MIN("Abmin"),
    A_MAJ("Amaj"),
    A_MIN("Amin"),
    A_SHARP_MAJ("A#maj"),
    A_SHARP_MIN("A#min"),
    B_FLAT_MAJ("Bbmaj"),
    B_FLAT_MIN("Bbmin"),
    B_MAJ("Bmaj"),
    B_MIN("Bmin"),
    B_SHARP_MAJ("B#maj"),
    B_SHARP_MIN("B#min"),
    C_FLAT_MAJ("Cbmaj"),
    C_FLAT_MIN("Cbmin"),
    C_MAJ("Cmaj"),
    C_MIN("Cmin"),
    C_SHARP_MAJ("C#maj"),
    C_SHARP_MIN("C#min"),
    D_FLAT_MAJ("Dbmaj"),
    D_FLAT_MIN("Dbmin"),
    D_MAJ("Dmaj"),
    D_MIN("Dmin"),
    D_SHARP_MAJ("D#maj"),
    D_SHARP_MIN("D#min"),
    E_FLAT_MAJ("Ebmaj"),
    E_FLAT_MIN("Ebmin"),
    E_MAJ("Emaj"),
    E_MIN("Emin"),
    E_SHARP_MAJ("E#maj"),
    E_SHARP_MIN("E#min"),
    F_FLAT_MAJ("Fbmaj"),
    F_FLAT_MIN("Fbmin"),
    F_MAJ("Fmaj"),
    F_MIN("Fmin"),
    F_SHARP_MAJ("F#maj"),
    F_SHARP_MIN("F#min"),
    G_FLAT_MAJ("Gbmaj"),
    G_FLAT_MIN("Gbmin"),
    G_MAJ("Gmaj"),
    G_MIN("Gmin"),
    G_SHARP_MAJ("G#maj"),
    G_SHARP_MIN("G#min");

    public final String label;

    private Chord (String label) {
        this.label = label;
    }

    public static Chord fromString(String chord) {
        for (Chord c : values()) {
            if (c.label.equalsIgnoreCase(chord))
                return c;
        }
        return null;
    }
}
