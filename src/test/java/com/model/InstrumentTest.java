package com.model;

import static org.junit.Assert.*;
import org.junit.Test;

public class InstrumentTest {
    
    @Test
    public void testInstrumentConstructorAndGetters() {
        Instrument instrument = new Instrument("instrumentID", "name", "imageFile");
    }
}
