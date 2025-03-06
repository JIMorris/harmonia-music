package com.model;

import java.util.ArrayList;

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

}
