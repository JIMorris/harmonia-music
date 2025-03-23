package com.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents a User with personal details and favorite songs and authors
 */
public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    private UUID userID;
    private ArrayList<Song> favSongs;
    private ArrayList<User> favAuthors;

    /**
     * Constructor for User class with parameters
     * 
     * @param username the username provided by the user
     * @param password the password provided by the user
     * @param firstName the first name provided of the user
     * @param lastName the last name provided by the user
     * @param userID a unique ID code used to indentify each individual user
     */
    public User(String username, String password, String firstName, String lastName, UUID userID) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = userID;
        this.favSongs = new ArrayList<>();
        this.favAuthors = new ArrayList<>();
    }

    /**
     * Constructor for a new User
     * 
     * @param username Username of user
     * @param password Password of user
     * @param firstName First Name of user
     * @param lastName Last Name of user
     */
    public User(String username, String password, String firstName, String lastName){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = UUID.randomUUID();
        this.favSongs = new ArrayList<>();
        this.favAuthors = new ArrayList<>();
    }

    /**
     * Checks if the userID matches the user's unique ID
     * 
     * @param userID the UUID that needs to be checked
     * @return true if the ID's match, otherwise it is false
     */
    public boolean idMatch(UUID userID) {
        return this.userID.equals(userID);
    }

    /**
     * Checks if the password provided by the user matches the user's provided username
     * 
     * @param password the password that needs to be checked
     * @return true if the provided password matches the user's username, otherwise it is false
     */
    public boolean passwordMatch(String password) {
        return this.password.equals(password);
    }

    /**
     * Checks if the password provided by the user matches the user's username
     * 
     * @param username the username that needs to be checked
     * @return true if the usernames are a match, otherwise it is false
     */
    public boolean usernameMatch(String username) {
        return this.username.equals(username);
    }

    /**
     * Adds a song to the user's list of their favorite songs given it is not already in the favorite's list 
     * 
     * @param song the song the user wishes to add
     */
    public void toggleFavoriteSong(Song song) {
        if(this.favSongs.contains(song))
            favSongs.remove(song);
        else
            favSongs.add(song);
    }

    /**
     * TODO
     * @param author
     */
    public void toggleFavoriteAuthor(User author){
        if(this.favAuthors.contains(author))
            favAuthors.remove(author);
        else
            favAuthors.add(author);
    }

    /**
     * Checks if the author the user wished to add to the favorite authors list is not already present
     * 
     * @param author the author that needs to be checked
     * @return true if the author is in the favorite authors list, otherwise it is false 
     */
    public boolean favAuthorExists(User author) { // TODO UML
        for (User authorInList : favAuthors) {
            if (authorInList.getUserID() == author.getUserID()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the provided song is already in the favorite songs list
     * 
     * @param song the song that needs to be checked
     * @return true if the song is present in the list, otherwise it is false 
     */
    public boolean favSongsExists(Song song) { // TODO UML
        for (Song songInList : favSongs) {
            if (songInList.getSongID() == song.getSongID()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a song from the user's list of favorite songs 
     * 
     * @param song the song the user wishes to remove
     */
    public void removeFavoriteSong(Song song) {
        // for (int i = 0; i < favSongs.size(); ++i) {
        // if (favSongs.get(i) == song) {
        // favSongs.remove(i);
        // break;
        // }
        // }
        favSongs.remove(song);
    }

    /**
     * Removes the author from the user's list of favorite authors
     * 
     * @param author the author the user wishes to remove 
     */
    public void removeFavoriteAuthor(User author) {
        // for (int i = 0; i < favAuthors.size(); ++i) {
        // if (favAuthors.get(i) == author) {
        // favSongs.remove(i);
        // break;
        // }
        // }
        favAuthors.remove(author);
    }

    /**
     * Set favAuthors to the parameter provided
     * 
     * @param favAuthors What to set favAuthors to
     */
    public void setFavoriteAuthors(ArrayList<User> favAuthors){
        this.favAuthors = favAuthors;
    }

    /**
     * Gets the list of songs that have been authored by the user
     * 
     * @return a list of songs that have been authored by the user
     */
    public ArrayList<Song> getAuthoredSongs() {
        return null; //TODO
    }

    /**
     * Checks both the username and password to see if it matches the user's credentials 
     * 
     * @param username the username that needs to be checked
     * @param password the password that needs to be checked 
     * @return true if both the username and password are a match, otherwise it is false
     */
    public boolean isMatch(String username, String password) {
        return username.equals(this.username) && password.equals(this.password);
    }

    /**
     * Retrieves the username of the user
     *
     * @return the username 
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Retrieves the password of the user 
     * 
     * @return the password 
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Retrieves the unique user ID for the user
     * 
     * @return the user's ID
     */
    public UUID getUserID() {
        return this.userID;
    }

    public ArrayList<Song> getFavSongs() {
        return this.favSongs;
    }
    
    public ArrayList<User> getFavAuthors() {
        return this.favAuthors;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }
}
