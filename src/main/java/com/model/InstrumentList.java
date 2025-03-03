package com.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * class that creates an instance which has an arraylist of instruments
 * 
 * @author kcrase
 */
public class InstrumentList {
    private static InstrumentList instance;
    private ArrayList<Instrument> instruments;

    /**
     * private constructor that creates a single instance of the InstrumentList 
     * object
     */
    private InstrumentList() {

    }

    /**
     * public method which calls the private InstrumentList constructor, 
     * returns the instance of InstrumentList
     */
    public InstrumentList getInstance() {
        return null;
    }

    /**
     * public method which adds a new instrument to the arraylist
     */
    public addInstrument(Instrument instrument) {
        return null;
    }
    
    /**
     * public method which removes an instrument from the arraylist
     */
    public removeInstrument(Instrument instrument) {
        return null;
    }

    /**
     * public method which saves/updates the arraylist of instruments
     */
    public save() {
        return null;
    }

}