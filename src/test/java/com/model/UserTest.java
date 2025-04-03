package com.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

//Created and Tested by Collin Floyd
public class UserTest {
    private User UserlList;
    private User user;
    private UserList userList;

    private User testUser;
    private UUID testUUID;

    private ArrayList<Song> songs = new ArrayList<Song>();

    @Before
    void setUp() {
        testUUID = UUID.randomUUID();
        testUser = new User("testUser", "password123", "John", "Doe", testUUID);
    }

    @Test
    void testGetUsername() {
        assertEquals("testUser", testUser.getUsername());
    }

    @Test
    void testGetPassword() {
        assertEquals("password123", testUser.getPassword());
    }

    @Test
    void testGetUserID() {
        assertEquals(testUUID, testUser.getUserID());
    }

    @Test
    void testGetFavSongsInitiallyEmpty() {
        assertTrue(testUser.getFavSongs().isEmpty());
    }

    @Test
    void testGetFavAuthorsInitiallyEmpty() {
        assertTrue(testUser.getFavAuthors().isEmpty());
    }

    @Test
    void testPasswordMatch() {
        assertTrue(testUser.passwordMatch("password123"));
        assertFalse(testUser.passwordMatch("wrongPassword"));
    }

    @Test
    void testUsernameMatch() {
        assertTrue(testUser.usernameMatch("testUser"));
        assertFalse(testUser.usernameMatch("wrongUser"));
    }

    @Test
    void testToggleFavoriteAuthor() {
        User author = new User("authorUser", "pass", "Author", "User");
        testUser.toggleFavoriteAuthor(author);
        assertTrue(testUser.getFavAuthors().contains(author));
        
        testUser.toggleFavoriteAuthor(author);
        assertFalse(testUser.getFavAuthors().contains(author));
    }
}
