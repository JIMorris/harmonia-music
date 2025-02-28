package com.model;

public enum Genre {
    POP(""),
    CLASSICAL(""),
    JAZZ(""),
    ROCK("");

    public final String label;

    private Genre(String label) {
        this.label = label;
    }
}
