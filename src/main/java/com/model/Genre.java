package com.model;

/**
 * an enumeration for the genre aspect of a song. has the values POP, CASSICAL,
 * JAZZ, ROCK
 */
public enum Genre {
    POP(""),
    CLASSICAL(""),
    JAZZ(""),
    ROCK("");

    public final String label;

    /**
     * constructs an instance of the Genre enumeration
     * 
     * @param label takes in a label (String) for the values within the enumeration
     */
    private Genre(String label) {
        this.label = label;
    }
}
