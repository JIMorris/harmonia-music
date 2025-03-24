package com.model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Key enum represents musical keys. Each key consists of a lavel and corresponding list of pitches.
 * @author Simion Cartis
 */
public enum Key {
    A_FLAT_MAJOR("Ab",
            new ArrayList<Pitch>(
                    Arrays.asList(Pitch.A_FLAT, Pitch.B_FLAT, Pitch.C,
                            Pitch.D_FLAT, Pitch.E_FLAT, Pitch.F, Pitch.G)), Chord.A_FLAT_MAJ),
    A_MAJOR("A", new ArrayList<Pitch>(
            Arrays.asList(Pitch.A, Pitch.B, Pitch.C_SHARP,
                    Pitch.D, Pitch.E, Pitch.F_SHARP, Pitch.G_SHARP)), Chord.A_MAJ),
    A_MINOR("Am", new ArrayList<Pitch>(
            Arrays.asList(Pitch.A, Pitch.B, Pitch.C,
                    Pitch.D, Pitch.E, Pitch.F, Pitch.G)), Chord.A_MIN),
    B_FLAT_MAJOR("Bb", new ArrayList<Pitch>(
            Arrays.asList(Pitch.B_FLAT, Pitch.C, Pitch.D,
                    Pitch.E_FLAT, Pitch.F, Pitch.G, Pitch.A)), Chord.B_FLAT_MAJ),
    B_FLAT_MINOR("Bbm", new ArrayList<Pitch>(
            Arrays.asList(Pitch.B_FLAT, Pitch.C, Pitch.D_FLAT,
                    Pitch.E_FLAT, Pitch.F, Pitch.G_FLAT, Pitch.A_FLAT)), Chord.B_FLAT_MIN),
    B_MAJOR("B", new ArrayList<Pitch>(
            Arrays.asList(Pitch.B, Pitch.C_SHARP, Pitch.D_SHARP,
                    Pitch.E, Pitch.F_SHARP, Pitch.G_SHARP, Pitch.A_SHARP)), Chord.B_MAJ),
    B_MINOR("Bm", new ArrayList<Pitch>(
            Arrays.asList(Pitch.B, Pitch.C_SHARP, Pitch.D,
                    Pitch.E, Pitch.F_SHARP, Pitch.G_SHARP, Pitch.A)), Chord.B_MIN),
    C_MAJOR("C", new ArrayList<Pitch>(
            Arrays.asList(Pitch.C, Pitch.D, Pitch.E,
                    Pitch.F, Pitch.G, Pitch.A, Pitch.B)), Chord.C_MAJ),
    C_MINOR("Cm", new ArrayList<Pitch>(
            Arrays.asList(Pitch.C, Pitch.D, Pitch.E_FLAT,
                    Pitch.F, Pitch.G, Pitch.A_FLAT, Pitch.B_FLAT)), Chord.C_MIN),
    C_SHARP_MINOR("C#m", new ArrayList<Pitch>(
            Arrays.asList(Pitch.C_SHARP, Pitch.D_SHARP, Pitch.E,
                    Pitch.F_SHARP, Pitch.G_SHARP, Pitch.A, Pitch.B)), Chord.C_SHARP_MIN),
    D_FLAT_MAJOR("Db", new ArrayList<Pitch>(
            Arrays.asList(Pitch.D_FLAT, Pitch.E_FLAT, Pitch.F,
                    Pitch.G_FLAT, Pitch.A_FLAT, Pitch.B_FLAT, Pitch.C)), Chord.D_FLAT_MAJ),
    D_MAJOR("D", new ArrayList<Pitch>(
            Arrays.asList(Pitch.D, Pitch.E, Pitch.F_SHARP,
                    Pitch.G, Pitch.A, Pitch.B, Pitch.C_SHARP)), Chord.D_MAJ),
    D_MINOR("Dm", new ArrayList<Pitch>(
            Arrays.asList(Pitch.D, Pitch.E, Pitch.F,
                    Pitch.G, Pitch.A, Pitch.B_FLAT, Pitch.C)), Chord.D_MIN),
    E_FLAT_MAJOR("Eb", new ArrayList<Pitch>(
            Arrays.asList(Pitch.E_FLAT, Pitch.F, Pitch.G,
                    Pitch.A_FLAT, Pitch.B_FLAT, Pitch.C, Pitch.D)), Chord.E_FLAT_MAJ),
    E_FLAT_MINOR("Ebm", new ArrayList<Pitch>(
            Arrays.asList(Pitch.E_FLAT, Pitch.F, Pitch.G_FLAT,
                    Pitch.A_FLAT, Pitch.B_FLAT, Pitch.C_FLAT, Pitch.D_FLAT)), Chord.E_FLAT_MAJ),
    E_MAJOR("E", new ArrayList<Pitch>(
            Arrays.asList(Pitch.E, Pitch.F_SHARP, Pitch.G_SHARP,
                    Pitch.A, Pitch.B, Pitch.C_SHARP, Pitch.D_SHARP)), Chord.E_MAJ),
    E_MINOR("Em", new ArrayList<Pitch>(
            Arrays.asList(Pitch.E, Pitch.F_SHARP, Pitch.G,
                    Pitch.A, Pitch.B, Pitch.C, Pitch.D)), Chord.E_MIN),
    F_MAJOR("F", new ArrayList<Pitch>(
            Arrays.asList(Pitch.F, Pitch.G, Pitch.A,
                    Pitch.B_FLAT, Pitch.C, Pitch.D, Pitch.E)), Chord.F_MAJ),
    F_MINOR("Fm", new ArrayList<Pitch>(
            Arrays.asList(Pitch.F, Pitch.G, Pitch.A_FLAT,
                    Pitch.B_FLAT, Pitch.C, Pitch.D_FLAT, Pitch.E_FLAT)), Chord.E_FLAT_MIN),
    F_SHARP_MINOR("F#m", new ArrayList<Pitch>(
            Arrays.asList(Pitch.F_SHARP, Pitch.G_SHARP, Pitch.A,
                    Pitch.B, Pitch.C_SHARP, Pitch.D, Pitch.E)), Chord.F_SHARP_MIN),
    G_FLAT_MAJOR("Gb", new ArrayList<Pitch>(
            Arrays.asList(Pitch.G_FLAT, Pitch.A_FLAT, Pitch.B_FLAT,
                    Pitch.C_FLAT, Pitch.D_FLAT, Pitch.E_FLAT, Pitch.F)), Chord.G_FLAT_MAJ),
    G_MAJOR("G", new ArrayList<Pitch>(
            Arrays.asList(Pitch.G, Pitch.A, Pitch.B,
                    Pitch.C, Pitch.D, Pitch.E, Pitch.F_SHARP)), Chord.G_MAJ),
    G_MINOR("Gm", new ArrayList<Pitch>(
            Arrays.asList(Pitch.G, Pitch.A, Pitch.B_FLAT,
                    Pitch.C, Pitch.D, Pitch.E_FLAT, Pitch.F)), Chord.G_MIN),
    G_SHARP_MINOR("G#m", new ArrayList<Pitch>(
            Arrays.asList(Pitch.G_SHARP, Pitch.A_SHARP, Pitch.B,
                    Pitch.C_SHARP, Pitch.D_SHARP, Pitch.E, Pitch.F_SHARP)), Chord.G_SHARP_MIN);

    public final String label;
    public final ArrayList<Pitch> pitches;
    public final Chord rootChord;

        /**
         * Constructs a key with a label and pitch.
         * @param label
         * @param pitches
         */
    private Key(String label, ArrayList<Pitch> pitches, Chord rootChord) {
        this.label = label;
        this.pitches = pitches;
        this.rootChord = rootChord;
    }

    public Pitch getRoot(){
        return this.pitches.get(0);
    }

    /**
     * Converts a string representation of a key into its corresponding enum value
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
