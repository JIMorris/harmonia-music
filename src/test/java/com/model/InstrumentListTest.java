package com.model;


import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import com.model.InstrumentList;
import com.model.Instrument;

import java.util.ArrayList;
import java.util.UUID;

public class InstrumentListTest {

    private InstrumentList instrumentList;
    private Instrument testInstrument;
    private UUID testID;

    private class TestInstrument extends Instrument {
        private UUID id;
    
        public TestInstrument(UUID id) {
            super(id, "TestName", "Guitar.png");
            this.id = id;
        }
    
        @Override
        public UUID getInstrumentID() {
            return id;
        }
    }


   @Before
   public void setup() {
    instrumentList = InstrumentList.getInstance();

    testID = UUID.randomUUID();
    testInstrument = new TestInstrument(testID);
    ArrayList<Instrument> list = new ArrayList<>();
    list.add(testInstrument);
    instrumentList.setInstruments(list);
   }

   @Test
   public void testSingletonInstance() {
    assertSame(instrumentList, InstrumentList.getInstance());
   }

   @Test
   public void testGetInstrument() {
    assertEquals(1, instrumentList.getInstruments().size());
   }

   @Test
   public void testGetInstrumentById() {
    assertEquals(testInstrument, instrumentList.getInstrument(testID));
   }

   @Test
   public void testSetInstruments() {
    ArrayList<Instrument> emptyList = new ArrayList<>();
    instrumentList.setInstruments(emptyList);
    assertTrue(instrumentList.getInstruments().isEmpty());
   }
}
