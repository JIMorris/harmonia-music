package com.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * class which creates an Instrument object which has an instrumentID (UUID), 
 * name(String), and an image file(String)
 * 
 * @author kcrase
 */
public class Instrument {
    private UUID instrumentID;
    private String name;
    private String imageFile;

    /**
     * constructor that creates a new instrument
     * 
     * @param instrumentID takes in a UUID for the instrumentID of the new instrument
     * @param name takes in a string for the name of the new instrument
     * @param imageFile takes in a string for the image file of the new instrument
     */
    public Instrument(UUID instrumentID, String name, User imageFile) {
        //TODO
    }
}
