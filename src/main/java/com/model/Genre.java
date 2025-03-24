package com.model;

/**
 * an enumeration for the genre aspect of a song. has the values POP, CASSICAL,
 * JAZZ, ROCK, COUNTRY, FUNK, etc
 * 
 * @author Simion Cartis
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
    RNB("R&B");

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
     * Converts a string representation of a Genre into its corresponding enum value
     * 
     * @param genre is a String which will be used to match the desired Genre's
     *              label
     * @return returns an enumeration of type Genre whose label matches the String
     *         parameter
     */
    public static Genre fromString(String genre) {
        for (Genre g : values()) {
            if (g.label.equalsIgnoreCase(genre))
                return g;
        }
        return null;
    }
}
