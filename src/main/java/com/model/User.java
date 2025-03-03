package com.model;
import java.util.ArrayList;
import java.util.UUID;

public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    private UUID userID;
    private ArrayList<Song> favSongs;
    private ArrayList<User> favAuthors;

    public User(String username, String password, String firstName, String lastname, UUID userID) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = userID;
    }

    public boolean idMatch(UUID userID){
        return this.userID == userID;
    }

    public void addFavoriteSong(Song song) {

    }

    public void addFavoriteAuthor(User author) {

    }

    public void removeFavoriteSong(Song song) {

    }

    public void removeFavoriteAuthor(User author) {

    }

    public ArrayList<Song> getAuthoredSongs() {
        return null;
    }

    public boolean isMatch(String username, String password) {
        
    }
}


