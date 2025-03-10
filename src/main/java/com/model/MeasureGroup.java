package com.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A measure of a song
 */
public class MeasureGroup {
    private int length;
    private Chord chord;
    private HashMap<Instrument, Measure> instruments;

    /**
     * Makes a new measure
     * @param length The length of this measure
     * @param chord The chord of this measure
     * @param currentInstruments The instruments that will play in this measure
     */
    public MeasureGroup(int length, Chord chord, ArrayList<Instrument> currentInstruments){
        // TODO
    }

    /**
     * Adds an instrument to this measure
     * @param instrument Instrument to add
     */
    public void addPart(Instrument instrument){
        // TODO
    }

    /***
     * Gets the part of the specified instrument
     * @param instrument Instrument to get part of
     * @return Part of the given instrument
     */
    public Measure getPart(Instrument instrument){
        //TODO
        return null;
    }
}
