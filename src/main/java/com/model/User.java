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
        return this.password == password; // TODO, NOT IN UML
    }

    public boolean usernameMatch(String username) {
        return this.username == username; // TODO, NOT IN UML
    }

    public void addFavoriteSong(Song song) {
        if (!favSongsExists(song))
            favSongs.add(song);
        System.out.println("song already exists in Favorite Songs");
    }

    public void addFavoriteAuthor(User author) {
        if (!favAuthorExists(author))
            favAuthors.add(author);
        System.out.println("song already exists in Favorite Author");
    }

    public boolean favAuthorExists(User author) { // TODO UML
        for (User authorInList : favAuthors) {
            if (authorInList.getUserID() == author.getUserID()) {
                return true;
            }
        }
        return false;
    }

    public boolean favSongsExists(Song song) { // TODO UML
        for (Song songInList : favSongs) {
            if (songInList.getSongID() == song.getSongID()) {
                return true;
            }
        }
        return false;
    }

    public void removeFavoriteSong(Song song) {
        // for (int i = 0; i < favSongs.size(); ++i) {
        // if (favSongs.get(i) == song) {
        // favSongs.remove(i);
        // break;
        // }
        // }
        favSongs.remove(song);
    }

    public void removeFavoriteAuthor(User author) {
        // for (int i = 0; i < favAuthors.size(); ++i) {
        // if (favAuthors.get(i) == author) {
        // favSongs.remove(i);
        // break;
        // }
        // }
        favAuthors.remove(author);
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

    public UUID getUserID() {
        return this.userID;
    }
}
