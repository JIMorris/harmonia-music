package com.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents a User with personal details and favorite songs and authors
 * 
 * @author Simion Cartis
 */
public class User {
    public static final String defaultUserIconFile = "defaultUserIcon.png";

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String iconFile;

    private UUID userID;
    private ArrayList<Song> favSongs;
    private ArrayList<User> favAuthors;

    /**
     * Constructor for a new User
     * 
     * @param username  Username of user as a String
     * @param password  Password of user as a String
     * @param firstName First Name of user as a String
     * @param lastName  Last Name of user as a String
     */
    public User(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = UUID.randomUUID();
        this.favSongs = new ArrayList<>();
        this.favAuthors = new ArrayList<>();
        this.iconFile = defaultUserIconFile;
    }

    /**
     * Constructor for User class which includes a userID parameter (used for loading into json)
     * 
     * @param username  The username provided by the user
     * @param password  The password provided by the user
     * @param firstName The first name provided of the user
     * @param lastName  The last name provided by the user
     * @param userID    A unique ID code used to indentify each individual user
     */
    public User(String username, String password, String firstName, String lastName, UUID userID, String iconFile) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = userID;
        this.favSongs = new ArrayList<>();
        this.favAuthors = new ArrayList<>();
        this.iconFile = iconFile;
    }

    /**
     * Retrieves the username of the user
     *
     * @return The username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Retrieves the password of the user
     * 
     * @return The password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Retrieves the unique user ID for the user
     * 
     * @return The user's ID (which is a UUID)
     */
    public UUID getUserID() {
        return this.userID;
    }

    /**
     * Retrieves a users favorite songs
     * 
     * @return The user's favorite songs (which is an ArrayList of type Song)
     */
    public ArrayList<Song> getFavSongs() {
        return this.favSongs;
    }

    /**
     * Retrieves a users favorite authors
     * 
     * 
     * @return The user's favorite authors (which is an ArrayList of type User)
     */
    public ArrayList<User> getFavAuthors() {
        return this.favAuthors;
    }

    /**
     * Retrieves the user's first name
     * 
     * @return The user's first name (of type String)
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Retrieves the user's last name
     * 
     * @return The user's last name (of type String)
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Returns the icon for this user
     * 
     * @return File path for icon
     */
    public String getIconFilePath(){
        return DataConstants.DATA_FOLDER + iconFile;
    }

    /**
     * Sets the icon file for this user
     * 
     * @param iconFile File for the icon (excluding path)
     */
    public void setIconFile(String iconFile){
        this.iconFile = iconFile;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Set favAuthors to the parameter provided
     * 
     * @param favAuthors What to set favAuthors to
     */
    public void setFavoriteAuthors(ArrayList<User> favAuthors) {
        this.favAuthors = favAuthors;
    }

    /**
     * Checks if the userID matches the user's unique ID
     * 
     * @param userID The UUID that needs to be checked
     * @return True if the ID's match, otherwise it is false
     */
    public boolean idMatch(UUID userID) {
        return this.userID.equals(userID);
    }

    /**
     * Checks if the password provided by the user matches the user's provided
     * username
     * 
     * @param password The password that needs to be checked
     * @return True if the provided password matches the user's username, otherwise
     *         it is false
     */
    public boolean passwordMatch(String password) {
        return this.password.equals(password);
    }

    /**
     * Checks if the username provided by the user matches the user's username
     * 
     * @param username The username that needs to be checked
     * @return True if the usernames are a match, otherwise it is false
     */
    public boolean usernameMatch(String username) {
        return this.username.equals(username);
    }

    /**
     * Adds or removes a Song from the favorite songs ArrayList depending on wether
     * or not they are already in the list
     * 
     * @param song The song the user wishes to add
     */
    public void toggleFavoriteSong(Song song) {
        if (this.favSongs.contains(song))
            favSongs.remove(song);
        else
            favSongs.add(song);
    }

    /**
     * Adds or removes a User from the favorite author ArrayList depending on wether
     * or not they are already the list
     * 
     * @param author Is of type User and is the user that will be added/removed from
     *               the ArrayList
     */
    public void toggleFavoriteAuthor(User author) {
        if (this.favAuthors.contains(author))
            favAuthors.remove(author);
        else
            favAuthors.add(author);
    }

    public boolean isFavoriteSong(Song song) {
        for (Song aSong: favSongs) {
            if (aSong == song) 
                return true;
        }
    return false;
    }
}
