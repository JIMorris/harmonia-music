package com.model;

/**
 * an enumeration for the genre aspect of a song. has the values POP, CASSICAL,
 * JAZZ, ROCK
 */
public enum Genre {
    POP("Pop"),
    CLASSICAL("Classical"),
    JAZZ("Jazz"),
    ROCK("Rock"),
    METAL("Metal"),
    COUNTRY("Country"),
    FUNK("Funk"),
    BLUES("Blues"),
    HIPHOP("HipHop"),
    EDM("EDM"),
    RNB("RNB");

    public final String label;

    /**
     * constructs an instance of the Genre enumeration
     * 
     * @param label takes in a label (String) for the values within the enumeration
     */
    private Genre(String label) {
        this.label = label;
    }

    /**
     * TODO
     * 
     * @param genre 
     * @return
     */
    public static Genre fromString(String genre) {
        for (Genre g : values()) {
            if (g.label.equalsIgnoreCase(genre))
                return g;
        }
        return null;
    }
}
