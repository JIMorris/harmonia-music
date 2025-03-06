package com.model;

import java.util.ArrayList;

public enum Key {
    A_FLAT_MAJOR(null),
    A_FLAT_MINOR(null),
    A_MAJOR(null),
    A_MINOR(null),
    A_SHARP_MAJOR(null),
    A_SHARP_MINOR(null),
    B_FLAT_MAJOR(null),
    B_FLAT_MINOR(null),
    B_MAJOR(null),
    B_MINOR(null),
    C_MAJOR(null),
    C_SHARP_MAJOR(null),
    C_SHARP_MINOR(null),
    D_FLAT_MAJOR(null),
    D_FLAT_MINOR(null),
    D_MAJOR(null),
    D_MINOR(null),
    D_SHARP_MAJOR(null),
    D_SHARP_MINOR(null),
    E_FLAT_MAJOR(null),
    E_FLAT_MINOR(null),
    E_MAJOR(null),
    E_MINOR(null),
    F_MAJOR(null),
    F_MINOR(null),
    F_SHARP_MAJOR(null),
    F_SHARP_MINOR(null),
    G_FLAT_MAJOR(null),
    G_FLAT_MINOR(null),
    G_MAJOR(null),
    G_MINOR(null),
    G_SHARP_MAJOR(null),
    G_SHARP_MINOR(null);

    public final ArrayList<Pitch> label; 

    private Key(ArrayList<Pitch> label) {
        this.label = label;
    }

}
