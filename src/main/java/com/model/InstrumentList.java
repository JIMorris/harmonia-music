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
        //TODO
    }

    /**
     * public method which calls the private InstrumentList constructor, 
     * returns the instance of InstrumentList
     */
    public InstrumentList getInstance() {
        //TODO
        return null;
    }

    /**
     * public method which adds a new instrument to the arraylist
     */
    public addInstrument(Instrument instrument) {
        //TODO
        return null;
    } //TODO, THERE IS NO RETURN TYPE GIVEN FOR THIS IN THE UML EITHER. NEED TO FIGURE OUT A RETURN TYPE
    
    /**
     * public method which removes an instrument from the arraylist
     */
    public removeInstrument(Instrument instrument) {
        //TODO
        return null;
    }

    /**
     * public method which saves/updates the arraylist of instruments
     */
    public save() {
        //TODO
        return null;
    }

}