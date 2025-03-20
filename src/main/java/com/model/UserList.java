package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class UserList {
    private static UserList instance;

    private ArrayList<User> users;
    private User currentUser; // should this be private? - Simion

    private UserList() {
        try {
            users = DataLoader.getInstance().loadUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UserList getInstance() {
        if (instance == null) {
            instance = new UserList();
        }
        return instance;
    }

    public void addUsers(User user) {
        users.add(user); // TODO NOT IN UML, ALSO MAY NOT BE CORRECT - simion
    }

    public boolean usernameCheck(String username) {
        // Code logic here
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean passwordCheck(String password) {
        for (User user : users) {
            if (user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public User getUser(UUID userID) {
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                return user;
            }
        }
        System.out.println("no such user exists"); // TEMP MESSAGE
        return null;
    }

    public boolean signup(String username, String password, String firstName, String lastName) {
        UUID userID = UUID.randomUUID();
        User user = new User(username, password, firstName, lastName, userID);
        if(usernameCheck(username)) {
            System.out.println("invalid username"); // temp error message
            return false;
        }
        addUsers(user); // ?????
        currentUser = user;
        return true;
    }

    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.passwordMatch(password) && user.usernameMatch(username)) {
                currentUser = user;
                return true;
            }
        }
        System.out.println("no such user found"); // TEMP MESSAGE
        return false;
    }

    public void toggleFavoriteSong(Song song) {
        if (currentUser.favSongsExists(song)) {
            currentUser.addFavoriteSong(song);
        }
        currentUser.removeFavoriteSong(song);
    } //do we want to add a toggle fav authors method? -Simion

    public User getCurrentUser() {
        return currentUser;
    }

    public void save() {
        DataWriter.getInstance().saveUsers(); // ????? - simion
    }
}
