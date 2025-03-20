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
    this.rating = rating;
    this.comment = comment;
    this.author = author;
  }

  /**
   * allows the user to edit a pre-existing reaction's rating
   * 
   * @param rating takes in a new rating (int)
   */
  public void setRating(int rating) {
    this.rating = rating;
  }

  /**
   * allows the user to edit a pre-existing reaction's comment
   * 
   * @param comment takes in a new String (comment)
   */
  public void setComment(String comment) {
    this.comment = comment;
  }

  /**
   * Retrieves the rating provided by the user
   * 
   * @return the user's rating
   */
  public int getRating() {
    return this.rating;
  }

  /**
   * Retrieves the comment provided by the user
   * 
   * @return the user's comment
   */
  public String getComment() {
    return this.comment;
  }

  /**
   * Retrieves the author the reaction
   * 
   * @return the author of the reaction
   */
  public User getAuthor() {
    return author;
  }

}
