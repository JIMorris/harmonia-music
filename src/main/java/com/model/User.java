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

    public User(String username, String password, String firstName, String lastName, UUID userID) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = userID;
    }

    public boolean idMatch(UUID userID) {
        return this.userID == userID;
    }

    public boolean passwordMatch(String password) {
        return this.password == password; //TODO, NOT IN UML
    }

    public boolean usernameMatch(String username) {
        return this.username == username; //TODO, NOT IN UML
    }

    public void addFavoriteSong(Song song) {
        favSongs.add(song);
    }

    public void addFavoriteAuthor(User author) {
        favAuthors.add(author);
    }

    public void removeFavoriteSong(Song song) {
        for (int i = 0; i < favSongs.size(); ++i) {
            if (favSongs.get(i) == song) {
                favSongs.remove(i);
                break;
            } // can this be simplified and improved by just doing favSongs.remove(song)?
              // .remove() seems to have an overriden method which removes by object (will also
              // remove any duplicate objects I presume) - Simion
        }// TODO, PROBABLY NEED A METHOD TO CHECK IF A FAV SONG/AUTHOR EXISTS IN THE LIST ALREADY
    }

    public void removeFavoriteAuthor(User author) {
        // for (int i = 0; i < favAuthors.size(); ++i) {
        //     if (favAuthors.get(i) == author) {
        //         favSongs.remove(i);
        //         break;
        //     }
        // }
        favAuthors.remove(author); //like this I guess (see previous comment) - simion
    }

    public ArrayList<Song> getAuthoredSongs() {
        return null;
    }

    public boolean isMatch(String username, String password) {
        return username == this.username && password == this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public UUID getUserID () {
        return this.userID; //these get methods are not in UML, but don't think they need to be?
    }
}
