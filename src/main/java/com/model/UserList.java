package com.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Singleton class that manages a list of users.
 * Provides functionality to add users, check credentials,
 * retrieve users, handle signups, logins, and save user data.
 */
public class UserList {
    private static UserList instance;
    private ArrayList<User> users;
    private User currentUser; // Should this be private? - Simion

    /**
     * Private constructor to initialize the user list from stored data.
     */
    private UserList() {
        try {
            users = DataLoader.getInstance().loadUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the singleton instance of UserList.
     * @return the singleton instance
     */
    public static UserList getInstance() {
        if (instance == null) {
            instance = new UserList();
        }
        return instance;
    }

    /**
     * Checks if a given username already exists.
     * @param username The username to check.
     * @return true if the username exists, false otherwise.
     */
    public void usernameCheck(String username) throws Exception {
        if (username.length()<3)
            throw new Exception("Username must be at least 3 characters");
        for (User user : users) {
            if (user.getUsername().equals(username)) 
                throw new Exception("The username " + username + " is already taken");
        }
    }

    /**
     * Checks if a given password matches any stored password.
     * @param password The password to check.
     * @return true if the password exists, false otherwise.
     */
    public void passwordCheck(String password) throws Exception{
        if(password.length()<6)
            throw new Exception("Password must be at least 6 character");
    }

    /**
     * Retrieves a user by their unique user ID.
     * @param userID The UUID of the user.
     * @return The corresponding User object or null if not found.
     */
    public User getUser(UUID userID) {
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                return user;
            }
        }
        System.out.println("No such user exists"); // TEMP MESSAGE
        return null;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    /**
     * Handles user signup by creating a new user and adding it to the list.
     * @param username The username for the new user.
     * @param password The password for the new user.
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     * @return true if signup is successful, false otherwise.
     */
    public void signup(String username, String password, String firstName, String lastName) throws Exception{
        usernameCheck(username);
        passwordCheck(password);
        UUID userID = UUID.randomUUID();
        User user = new User(username, password, firstName, lastName, userID);
        users.add(user);
        currentUser = user;
    }

    /**
     * Handles user login by checking credentials.
     * @param username The username entered.
     * @param password The password entered.
     * @return true if login is successful, false otherwise.
     */
    public void login(String username, String password) throws Exception {
        for (User user : users) {
            if (user.passwordMatch(password) && user.usernameMatch(username)) {
                currentUser = user;
                return;
            }
        }
        throw new Exception("Username or password were incorrect");
    }

    /**
     * Toggles a song as a favorite for the current user.
     * @param song The song to toggle.
     */
    public void toggleFavoriteSong(Song song) {
        currentUser.toggleFavoriteSong(song);
    }

    public void toggleFavoriteAuthor(User author){
        currentUser.toggleFavoriteAuthor(author);
    }

    /**
     * Retrieves the current logged-in user.
     * @return The current User object.
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * TODO
     * @param song
     * @return
     */
    public boolean isFavorite(Song song){
        return currentUser.getFavSongs().contains(song);
    }

    /**
     * Saves the current user data using DataWriter.
     */
    public void save() throws Exception{
        DataWriter.getInstance().saveUsers();
    }
}
