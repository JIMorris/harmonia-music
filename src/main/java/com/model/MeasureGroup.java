package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * A measure of a song
 * 
 * @author Simion Cartis
 */
public class MeasureGroup {
    private final int length;
    private Chord chord;
    private HashMap<Instrument, Measure> measures;

    /**
     * Makes a new measure
     * 
     * @param length             The length of this measure
     * @param currentInstruments The instruments that will play in this measure
     */
    public MeasureGroup(int length, Chord chord, ArrayList<Instrument> currentInstruments) {
        this.length = length;
        this.chord = chord;
        this.measures = new HashMap<>();
        for (Instrument instrument : currentInstruments) {
            addMeasure(instrument);
        }
    }

    /**
     * Constructs a new measure with a specified length, chord, and existing
     * measures.
     * 
     * @param length The length of measure
     * @param chord The chord of the measure
     * @param measures The existing measure
     */
    public MeasureGroup(int length, Chord chord, HashMap<Instrument, Measure> measures) {
        this.length = length;
        this.chord = chord;
        this.measures = measures;
    }

    /**
     * Copy constructor for creating a copy of existing measure
     * 
     * @param measureGroup The existing measure
     */
    public MeasureGroup(MeasureGroup measureGroup) {
        this.length = measureGroup.getLength();
        this.chord = measureGroup.getChord();
        this.measures = copyMeasures(measureGroup.getMeasures());
    }

    /**
     * Retrieves the length of the measure
     * 
     * @return The length of the measure
     */
    public int getLength() {
        return length;
    }

    /**
     * Retrieves the chord of the measure
     * 
     * @return The chord of the measure
     */
    public Chord getChord() {
        return chord;
    }

    /**
     * Retrieves the map of instruments to their corresponding measure
     * 
     * @return The map of instruments to their measure
     */
    public HashMap<Instrument, Measure> getMeasures() {
        return measures;
    }

    /**
     * Sets the chord for this measure
     * 
     * @param chord The chord of the measure
     */
    public void setChord(Chord chord) {
        this.chord = chord;
    }

    /**
     * Adds an instrument to this measure
     * 
     * @param instrument Instrument to add
     */
    public final void addMeasure(Instrument instrument) {
        measures.put(instrument, new Measure());
    }

    /**
     * Removes measure associated with the specified instrument
     * 
     * @param instrument Instrument to remove
     */
    public void removeMeasure(Instrument instrument) {
        measures.remove(instrument);
    }

    /**
     * Gets the part of the specified instrument
     * 
     * @param instrument Instrument to get part of
     * @return Part of the given instrument
     */
    public Measure getMeasure(Instrument instrument) {
        return measures.get(instrument);
    }

    /**
     * Creates a copy of the given measures map
     * 
     * @param measures The original measure
     * @return Copy of the measure
     */
    private HashMap<Instrument, Measure> copyMeasures(HashMap<Instrument, Measure> measures) {
        HashMap<Instrument, Measure> copy = new HashMap<>();
        Set<Instrument> instrumentSet = measures.keySet();
        ArrayList<Instrument> instruments = new ArrayList<>(instrumentSet);

        for (Instrument instrument : instruments) {
            Measure currentMeasure = measures.get(instrument);
            Measure measureCopy = new Measure(currentMeasure);
            copy.put(instrument, measureCopy);
        }

        return copy;
    }

}
