package com.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A measure of a song
 */
public class Measure {
    private int length;
    private Chord chord;
    private HashMap<Instrument, Part> instruments;

    /**
     * Makes a new measure
     * @param length The length of this measure
     * @param chord The chord of this measure
     * @param currentInstruments The instruments that will play in this measure
     */
    public Measure(int length, Chord chord, ArrayList<Instrument> currentInstruments){
        // TODO
    }

    /**
     * Adds an instrument to this measure
     * @param instrument Instrument to add
     */
    public void addPart(Instrument instrument){
        // TODO
    }
}
