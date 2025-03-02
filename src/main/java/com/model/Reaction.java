package com.model;

//A USER MAY WANT TO HAVE A LIST OF THEIR REACTIONS. ADD AN ARRAYLIST INTO THE USER CLASS? - SIMION
/**
 * a class that creates an object of type Reaction, with a rating (int), comment
 * (string),
 * and an author (User)
 * 
 * @author Simoin Cartis
 */
public class Reaction {
    private int rating;
    private String comment;
    private User author;

    /**
     * constructor that creates a Reaction
     * 
     * @param rating  takes in a rating (int)
     * @param comment takes in a comment (String)
     * @param author  takes in an author (User)
     */
    public Reaction(int rating, String comment, User author) {

    }

    /**
     * allows the user to edit a pre-existing reaction's rating
     * 
     * @param rating takes in a new rating (int)
     */
    public void editRating(int rating) {

    }

    /**
     * allows the user to edit a pre-existing reaction's comment 
     * 
     * @param comment takes in a new String (comment)
     */
    public void editComment(String comment) {

    } // THESE METHODS ARE NOT CURRENTLY IN THE UML, PROBABLY WANT TO ADD THESE
      // FEATURES THOUGH
      // WHERE WOULD THE EDIT RATING FEATURE BE THOUGH? IN THE SONG CLASS, USER CLASS,
      // OR BOTH?- SIMION
}
