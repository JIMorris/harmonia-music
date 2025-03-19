package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class UserList {
    private static UserList instance;

    private ArrayList<User> users;

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
        users.add(user); //TODO NOT IN UML, ALSO MAY NOT BE CORRECT - simion
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

    public User signup(String username, String password, String firstName, String lastName) {
        UUID userID = UUID.randomUUID();
        User user = new User(username, password, firstName, lastName, userID);
        addUsers(user); //?????
        return user;
    } 

    public User login(String username, String password) {
        for (User user : users) {
            if (user.passwordMatch(password) && user.usernameMatch(username)) {
                return user;
            }
        }
        System.out.println("no such user found"); //TEMP MESSAGE
        return null;
    }

    public void save() {
        DataWriter.getInstance().saveUsers(); // ????? - simion
    }
}
