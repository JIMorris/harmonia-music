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
    public Instrument(UUID instrumentID, String name, String imageFile) {
        this.instrumentID = instrumentID;
        this.name = name;
        this.imageFile = imageFile;
    }

    public UUID getInstrumentID() {
        return instrumentID;
    }

    public String getName() {
        return name;
    }

    public String getImageFile() {
        return imageFile;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    @Override
    public String toString() {
        return "Instrument{" +
                "instrumentID=" + instrumentID +
                ", name='" + name + '\'' +
                ", imageFile='" + imageFile + '\'' +
                '}';
    }
}

