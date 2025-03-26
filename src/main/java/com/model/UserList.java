package com.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Singleton class that manages a list of users.
 * Provides functionality to add users, check credentials,
 * retrieve users, handle signups, logins, and save user data.
 * 
 * @author Simion Cartis
 */
public class UserList {
    private static UserList instance;
    private ArrayList<User> users;
    private User currentUser;

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
     * 
     * @return The singleton instance
     */
    public static UserList getInstance() {
        if (instance == null) {
            instance = new UserList();
        }
        return instance;
    }

    /**
     * Retrieves all users in the system
     * 
     * @return The ArrayList users of type User that contains all the users in the
     *         system
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Retrieves the current logged-in user.
     * 
     * @return The current User object.
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Initializes the users ArrayList
     * 
     * @param users The arrayList that is used to initialize this singleton's users
     *              data member
     */
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    /**
     * Checks if a given username already exists.
     * 
     * @param username The username to check.
     * @return True if the username exists, false otherwise.
     */
    public void usernameCheck(String username) throws Exception {
        if (username.length() < 3)
            throw new Exception("Username must be at least 3 characters");
        for (User user : users) {
            if (user.getUsername().equals(username))
                throw new Exception("The username " + username + " is already taken");
        }
    }

    /**
     * Checks if a given password matches any stored password.
     * 
     * @param password The password to check.
     * @return True if the password exists, false otherwise.
     */
    public void passwordCheck(String password) throws Exception {
        if (password.length() < 6)
            throw new Exception("Password must be at least 6 character");
    }

    /**
     * Retrieves a user by their unique user ID.
     * 
     * @param userID The UUID of the user.
     * @return The corresponding User object or null if not found.
     */
    public User getUser(UUID userID) {
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                return user;
            }
        }
        System.out.println("No such user exists");
        return null;
    }

    /**
     * Handles user signup by creating a new user and adding it to the list.
     * 
     * @param username  The username for the new user.
     * @param password  The password for the new user.
     * @param firstName The first name of the user.
     * @param lastName  The last name of the user.
     * @return True if signup is successful, false otherwise.
     */
    public void signup(String username, String password, String firstName, String lastName) throws Exception {
        usernameCheck(username);
        passwordCheck(password);
        UUID userID = UUID.randomUUID();
        User user = new User(username, password, firstName, lastName, userID);
        users.add(user);
        currentUser = user;
    }

    /**
     * Handles user login by checking credentials.
     * 
     * @param username The username entered.
     * @param password The password entered.
     * @return True if login is successful, false otherwise.
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
     * 
     * @param song The song to toggle.
     */
    public void toggleFavoriteSong(Song song) {
        currentUser.toggleFavoriteSong(song);
    }

    /**
     * allows the current user to add/remove a user from their favAuthors ArrrayList
     * 
     * @param author The user that will be added/removed
     */
    public void toggleFavoriteAuthor(User author) {
        currentUser.toggleFavoriteAuthor(author);
    }

    /**
     * Checks if a song exists in the current user's favSongs ArrayList
     * 
     * @param song the Song object that is being checked.
     * @return Returns true if the given parameter is in the current user's FavSongs
     *         ArrayList, false if not
     */
    public boolean isFavorite(Song song) {
        return currentUser.getFavSongs().contains(song);
    }

    /**
     * Adds a reaction (created by the current user) to a given song
     * 
     * @param song The song the reaction is being added to
     * @param rating The rating the new reaction will have (of type int)
     * @param comment The comment the new reaction will have (of type String)
     */
    public void addReaction(Song song, int rating, String comment) {
        song.addReaction(rating, comment, currentUser);
    }

    /**
     * Saves the current user data using DataWriter.
     */
    public void logout() throws Exception {
        currentUser = null;
        DataWriter.getInstance().saveUsers();
    }
}
