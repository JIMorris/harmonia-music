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
     * Private constructor that creates a single instance of the InstrumentList 
     */
    private InstrumentList() {
        try {
            instruments = DataLoader.getInstance().loadInstruments();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Public method which calls the private InstrumentList constructor
     * 
     * @return the instance of InstrumentList
     */
    public static InstrumentList getInstance() {
        if (instance == null) {
            instance = new InstrumentList();
        }
        return instance;
    }

    /**
     * Retrieves the list of instruments
     * 
     * @return an ArrayList of instruments
     */
    public ArrayList<Instrument> getInstruments() {
        return instruments;
    }

    /**
     * Gets the instrument that has the matching UUID
     * 
     * @param id UUID of the instrument to get
     * @return Instrument with given UUID
     */
    public Instrument getInstrument(UUID id){
        for(Instrument instrument : instruments){
            if(instrument.getInstrumentID().equals(id))
                return instrument;
        }
        return null;
    }

    /**
     * public method which saves/updates the arraylist of instruments
     * 
     * @throws Exception if an error occurs during saving
     */
    public void logout() throws Exception {
        DataWriter.getInstance().saveInstruments();
    }

    /**
     * Sets the instrument list 
     * 
     * @param instruments The new list of instruments
     */
    public void setInstruments(ArrayList<Instrument> instruments) {
        this.instruments = instruments;
    }
}