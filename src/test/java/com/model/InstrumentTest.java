package com.model;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;

public class InstrumentTest {
    
    @Test
    public void testInstrumentConstructorAndGetters() {
        UUID instrumentID = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        String name = "Guitar";
        String imageFile = "guitar.jpg";


        Instrument instrument = new Instrument(instrumentID, name, imageFile);
        
        assertEquals(instrumentID, instrument.getInstrumentID());
        assertEquals(name, instrument.getName());
        assertEquals(imageFile, instrument.getImageFile());
   
    }

}
