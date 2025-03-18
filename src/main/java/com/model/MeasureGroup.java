package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * A measure of a song
 */
public class MeasureGroup {
    private final int length;
    private Chord chord;
    private HashMap<Instrument, Measure> measures;

    /**
     * Makes a new measure
     * @param length The length of this measure
     * @param currentInstruments The instruments that will play in this measure
     */
    public MeasureGroup(int length, ArrayList<Instrument> currentInstruments){
        this.length = length;
        this.measures = new HashMap<>();
        for(Instrument instrument : currentInstruments){
            addMeasure(instrument);
        }
    }

    /**
     * TODO
     * @param length
     * @param chord
     * @param measures
     */
    public MeasureGroup(int length, Chord chord, HashMap<Instrument, Measure> measures){
        this.length = length;
        this.chord = chord;
        this.measures = measures;
    }

    /**
     * TODO
     * @param measureGroup
     */
    public MeasureGroup(MeasureGroup measureGroup){
        this.length = measureGroup.getLength();
        this.chord = measureGroup.getChord();
        this.measures = copyMeasures(measureGroup.getMeasures());
    }
    
    /**
     * TODO
     * @return
     */
    public int getLength() {
        return length;
    }

    /**
     * TODO
     * @return
     */
    public Chord getChord() {
        return chord;
    }

    /**
     * TODO
     * @return
     */
    public HashMap<Instrument, Measure> getMeasures(){
        return measures;
    }

    /**
     * TODO
     * @param chord
     */
    public void setChord(Chord chord){
        this.chord = chord;
    }

    /**
     * Adds an instrument to this measure
     * @param instrument Instrument to add
     */
    public final void addMeasure(Instrument instrument){
        measures.put(instrument, new Measure(instrument));
    }

    /**
     * TODO
     * @param instrument
     */
    public void removeMeasure(Instrument instrument){
        measures.remove(instrument);
    }

    /***
     * Gets the part of the specified instrument
     * @param instrument Instrument to get part of
     * @return Part of the given instrument
     */
    public Measure getMeasure(Instrument instrument){
        return measures.get(instrument);
    }

    /**
     * TODO
     * @param measures
     * @return
     */
    public HashMap<Instrument, Measure> copyMeasures(HashMap<Instrument, Measure> measures){
        HashMap<Instrument, Measure> copy = new HashMap<>();
        Set<Instrument> instrumentSet = measures.keySet();
        ArrayList<Instrument> instruments = new ArrayList<>(instrumentSet);
        
        for(Instrument instrument : instruments){
            Measure currentMeasure = measures.get(instrument);
            Measure measureCopy = new Measure(currentMeasure); //TODO
            copy.put(instrument, measureCopy);
        }

        return copy;
    }

}
