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
     * Adds a new user to the user list.
     * @param user The user to be added.
     */
    public void addUsers(User user) {
        users.add(user); // TODO: Not in UML, also may not be correct - Simion
    }

    /**
     * Checks if a given username already exists.
     * @param username The username to check.
     * @return true if the username exists, false otherwise.
     */
    public boolean usernameCheck(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a given password matches any stored password.
     * @param password The password to check.
     * @return true if the password exists, false otherwise.
     */
    public boolean passwordCheck(String password) {
        for (User user : users) {
            if (user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
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
    public boolean signup(String username, String password, String firstName, String lastName) {
        UUID userID = UUID.randomUUID();
        User user = new User(username, password, firstName, lastName, userID);
        if (usernameCheck(username)) {
            System.out.println("Invalid username"); // TEMP ERROR MESSAGE
            return false;
        }
        addUsers(user);
        currentUser = user;
        return true;
    }

    /**
     * Handles user login by checking credentials.
     * @param username The username entered.
     * @param password The password entered.
     * @return true if login is successful, false otherwise.
     */
    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.passwordMatch(password) && user.usernameMatch(username)) {
                currentUser = user;
                return true;
            }
        }
        System.out.println("No such user found"); // TEMP MESSAGE
        return false;
    }

    /**
     * Toggles a song as a favorite for the current user.
     * @param song The song to toggle.
     */
    public void toggleFavoriteSong(Song song) {
        if (currentUser.favSongsExists(song)) {
            currentUser.addFavoriteSong(song);
        }
        currentUser.removeFavoriteSong(song);
    } // Do we want to add a toggle favorite authors method? - Simion

    /**
     * Retrieves the current logged-in user.
     * @return The current User object.
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Saves the current user data using DataWriter.
     */
    public void save() {
        DataWriter.getInstance().saveUsers(); // ????? - Simion
    }
}
