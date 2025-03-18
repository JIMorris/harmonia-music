package com.model;

import java.util.ArrayList;

// Change to Enum
public class Chord {
    private String name;
    private String abreviation; //MISSPELLED IN THE UML
    private  ArrayList<Pitch> notes;

    public Chord(String name, String abbreviation, ArrayList<Pitch> notes) {

    }

    public Chord(Pitch root, String type) {

    }

    public Pitch getRoot() {
        return null;
    }

    /**
     * TODO
     * @param chord
     * @return
     */
    public static Chord fromString(String chord){
        //TODO
        return null;
    }
}
