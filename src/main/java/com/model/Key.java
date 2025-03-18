package com.model;

import java.util.ArrayList;
import java.util.Arrays;

public enum Key {
    A_FLAT_MAJOR("Ab",
            new ArrayList<Pitch>(
                    Arrays.asList(Pitch.A_FLAT, Pitch.B_FLAT, Pitch.C,
                            Pitch.D_FLAT, Pitch.E_FLAT, Pitch.F, Pitch.G))),
    A_MAJOR("A", new ArrayList<Pitch>(
            Arrays.asList(Pitch.A, Pitch.B, Pitch.C_SHARP,
                    Pitch.D, Pitch.E, Pitch.F_SHARP, Pitch.G_SHARP))),
    A_MINOR("Am", new ArrayList<Pitch>(
            Arrays.asList(Pitch.A, Pitch.B, Pitch.C,
                    Pitch.D, Pitch.E, Pitch.F, Pitch.G))),
    B_FLAT_MAJOR("Bb", new ArrayList<Pitch>(
            Arrays.asList(Pitch.B_FLAT, Pitch.C, Pitch.D,
                    Pitch.E_FLAT, Pitch.F, Pitch.G, Pitch.A))),
    B_FLAT_MINOR("Bbm", new ArrayList<Pitch>(
            Arrays.asList(Pitch.B_FLAT, Pitch.C, Pitch.D_FLAT,
                    Pitch.E_FLAT, Pitch.F, Pitch.G_FLAT, Pitch.A_FLAT))),
    B_MAJOR("B", new ArrayList<Pitch>(
            Arrays.asList(Pitch.B, Pitch.C_SHARP, Pitch.D_SHARP,
                    Pitch.E, Pitch.F_SHARP, Pitch.G_SHARP, Pitch.A_SHARP))),
    B_MINOR("Bm", new ArrayList<Pitch>(
            Arrays.asList(Pitch.B, Pitch.C_SHARP, Pitch.D,
                    Pitch.E, Pitch.F_SHARP, Pitch.G_SHARP, Pitch.A))),
    C_MAJOR("C", new ArrayList<Pitch>(
            Arrays.asList(Pitch.C, Pitch.D, Pitch.E,
                    Pitch.F, Pitch.G, Pitch.A, Pitch.B))),
    C_MINOR("Cm", new ArrayList<Pitch>(
            Arrays.asList(Pitch.C, Pitch.D, Pitch.E_FLAT,
                    Pitch.F, Pitch.G, Pitch.A_FLAT, Pitch.B_FLAT))),
    C_SHARP_MINOR("C#m", new ArrayList<Pitch>(
            Arrays.asList(Pitch.C_SHARP, Pitch.D_SHARP, Pitch.E,
                    Pitch.F_SHARP, Pitch.G_SHARP, Pitch.A, Pitch.B))),
    D_FLAT_MAJOR("Db", new ArrayList<Pitch>(
            Arrays.asList(Pitch.D_FLAT, Pitch.E_FLAT, Pitch.F,
                    Pitch.G_FLAT, Pitch.A_FLAT, Pitch.B_FLAT, Pitch.C))),
    D_MAJOR("D", new ArrayList<Pitch>(
            Arrays.asList(Pitch.D, Pitch.E, Pitch.F_SHARP,
                    Pitch.G, Pitch.A, Pitch.B, Pitch.C_SHARP))),
    D_MINOR("Dm", new ArrayList<Pitch>(
            Arrays.asList(Pitch.D, Pitch.E, Pitch.F,
                    Pitch.G, Pitch.A, Pitch.B_FLAT, Pitch.C))),
    E_FLAT_MAJOR("Eb", new ArrayList<Pitch>(
            Arrays.asList(Pitch.E_FLAT, Pitch.F, Pitch.G,
                    Pitch.A_FLAT, Pitch.B_FLAT, Pitch.C, Pitch.D))),
    E_FLAT_MINOR("Ebm", new ArrayList<Pitch>(
            Arrays.asList(Pitch.E_FLAT, Pitch.F, Pitch.G_FLAT,
                    Pitch.A_FLAT, Pitch.B_FLAT, Pitch.C_FLAT, Pitch.D_FLAT))),
    E_MAJOR("E", new ArrayList<Pitch>(
            Arrays.asList(Pitch.E, Pitch.F_SHARP, Pitch.G_SHARP,
                    Pitch.A, Pitch.B, Pitch.C_SHARP, Pitch.D_SHARP))),
    E_MINOR("Em", new ArrayList<Pitch>(
            Arrays.asList(Pitch.E, Pitch.F_SHARP, Pitch.G,
                    Pitch.A, Pitch.B, Pitch.C, Pitch.D))),
    F_MAJOR("F", new ArrayList<Pitch>(
            Arrays.asList(Pitch.F, Pitch.G, Pitch.A,
                    Pitch.B_FLAT, Pitch.C, Pitch.D, Pitch.E))),
    F_MINOR("Fm", new ArrayList<Pitch>(
            Arrays.asList(Pitch.F, Pitch.G, Pitch.A_FLAT,
                    Pitch.B_FLAT, Pitch.C, Pitch.D_FLAT, Pitch.E_FLAT))),
    F_SHARP_MINOR("F#m", new ArrayList<Pitch>(
            Arrays.asList(Pitch.F_SHARP, Pitch.G_SHARP, Pitch.A,
                    Pitch.B, Pitch.C_SHARP, Pitch.D, Pitch.E))),
    G_FLAT_MAJOR("Gb", new ArrayList<Pitch>(
            Arrays.asList(Pitch.G_FLAT, Pitch.A_FLAT, Pitch.B_FLAT,
                    Pitch.C_FLAT, Pitch.D_FLAT, Pitch.E_FLAT, Pitch.F))),
    G_MAJOR("G", new ArrayList<Pitch>(
            Arrays.asList(Pitch.G, Pitch.A, Pitch.B,
                    Pitch.C, Pitch.D, Pitch.E, Pitch.F_SHARP))),
    G_MINOR("Gm", new ArrayList<Pitch>(
            Arrays.asList(Pitch.G, Pitch.A, Pitch.B_FLAT,
                    Pitch.C, Pitch.D, Pitch.E_FLAT, Pitch.F))),
    G_SHARP_MINOR("G#m", new ArrayList<Pitch>(
            Arrays.asList(Pitch.G_SHARP, Pitch.A_SHARP, Pitch.B,
                    Pitch.C_SHARP, Pitch.D_SHARP, Pitch.E, Pitch.F_SHARP)));

    public final String label;
    public final ArrayList<Pitch> pitchs;

    private Key(String label, ArrayList<Pitch> pitchs) {
        this.label = label;
        this.pitchs = pitchs;
    }

    /**
     * TODO
     * 
     * @param key
     * @return
     */
    public static Key fromString(String key) {
        for (Key k : values()) {
            if (k.label.equalsIgnoreCase(key))
                return k;
        }
        return null;
    }
}
