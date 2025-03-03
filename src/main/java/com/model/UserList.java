package com.model;
import java.util.ArrayList;
import java.util.UUID;

public class UserList {
    private static UserList instance;

    private ArrayList<User> users;

    private UserList() {
        users = new ArrayList<>();
    }

    public static UserList getInstance() {
        // Code logic here
        return instance;
    }

    public boolean usernameCheck(String username) {
        //Code logic here
        return false;
    }

    public boolean passwordCheck(String password) {
        //Code logic here
        return false;
    }

    public User getUser(UUID userID) {
        //Code logic here
        return null;
    }

    public User signup(String username, String password, String firstName, String lastName) {
        // Code logic here
        return null;
    }

    public User login(String username, String password) {
        // Code logic here
        return null;
    }

    public void save() {
        // Code logic here
    }
}
