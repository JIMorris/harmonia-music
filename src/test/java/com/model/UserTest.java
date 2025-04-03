package com.model;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

//Written and Tested by Collin Floyd
public class UserTest {
    private User user;
    private User testUser;
    private UUID testUUID;
    private UserList userList;


    private ArrayList<Song> songs = new ArrayList<Song>();

    @Before
    public void setUp() {
        testUUID = UUID.randomUUID();
        testUser = new User("testUser", "password123", "John", "Doe", testUUID);
    }

    @Test
    public void testGetUsername() {
        assertEquals("testUser", testUser.getUsername()); // This will pass
    }

    @Test
    public void testGetPassword() {
        assertEquals("wrongPassword", testUser.getPassword()); // This will fail because the expected value is incorrect
    }

    @Test
    public void testGetUserID() {
        assertEquals(UUID.randomUUID(), testUser.getUserID()); // This will fail because we generate a new UUID each time
    }

    @Test
    public void testGetFavSongsInitiallyEmpty() {
        assertFalse(testUser.getFavSongs().isEmpty()); // This will fail because we expect the list to be empty, but this test will check the opposite
    }

    @Test
    public void testGetFavAuthorsInitiallyEmpty() {
        assertFalse(testUser.getFavAuthors().isEmpty()); // Same as above, will fail for the same reason
    }

    @Test
    public void testPasswordMatch() {
        assertTrue(testUser.passwordMatch("wrongPassword")); // This will fail because "wrongPassword" doesn't match the actual password
    }

    @Test
    public void testUsernameMatch() {
        assertTrue(testUser.usernameMatch("nonExistentUser")); // This will fail because the username doesn't exist
    }

    @Test
    public void testToggleFavoriteAuthor() {
        User author = new User("authorUser", "pass", "Author", "User", UUID.randomUUID());
        testUser.toggleFavoriteAuthor(author);
        assertFalse(testUser.getFavAuthors().contains(author)); // This will fail because the author should have been added to the list
    }

    @Test
    public void testUsernameCaseSensitivity() {
        assertTrue(testUser.usernameMatch("TestUser"));  // Should pass if case-insensitive
    }

    @Test
    public void testPasswordCaseSensitivity() {
        assertTrue(testUser.passwordMatch("Password123"));  // Should pass if case-insensitive
    }

    @Test
    public void testToggleFavoriteAuthorWithDuplicates() {
        User author = new User("authorUser", "pass", "Author", "User");
        testUser.toggleFavoriteAuthor(author);
        testUser.toggleFavoriteAuthor(author);  // Remove it
        testUser.toggleFavoriteAuthor(author);  // Add again

        assertTrue(testUser.getFavAuthors().contains(author));
    }

    @Test
    public void testToggleFavoriteAuthorWithNull() {
        testUser.toggleFavoriteAuthor(null);  // Should not cause NullPointerException
    }

    @Test
    public void testSignupWithBlankUsername() throws Exception {
        userList.signup("", "password123", "Jane", "Smith");  // Should fail
    }

    @Test
    public void testSignupWithNullUsername() throws Exception {
        userList.signup(null, "password123", "Jane", "Smith");  // Should fail
    }

    @Test
    public void testLoginWithExtraWhitespace() throws Exception {
        userList.signup("testUser", "password123", "John", "Doe");
        userList.login("  testUser  ", "password123");  // Should pass if trimming is implemented
    }

    @Test
    public void testGetUserByIdNotFound() {
        UUID randomUUID = UUID.randomUUID();
        assertNull(userList.getUser(randomUUID));  // Should return null
    }

    @Test
    public void testLoginAfterLogout() throws Exception {
        userList.login("testUser", "password123");
        userList.logout();
        userList.login("testUser", "password123");

        assertEquals(testUser, userList.getCurrentUser());
    }

    @Test
    public void testNullUserGetUsername() {
        User nullUser = null;
        assertThrows(NullPointerException.class, () -> {
            nullUser.getUsername();
        });
    }

    @Test
    public void testGetNonExistentUserById() {
        UUID randomUUID = UUID.randomUUID();
        assertNull("Current user should be null", testUser);
    }

    @Test
    public void testAddNullUser() {
        assertThrows(NullPointerException.class, () -> {
            userList.getUsers().add(null);
        });
    }

    @Test
    public void testLoginWithNullUsername() {
        assertThrows(NullPointerException.class, () -> {
            userList.login(null, "password123");
        });
    }
    
    @Test
    public void testLoginWithNullPassword() {
        assertThrows(NullPointerException.class, () -> {
            userList.login("testUser", null);
        });
    }
        
    @Test
    public void testToggleNullFavoriteSong() {
        assertThrows(NullPointerException.class, () -> {
            testUser.toggleFavoriteSong(null);
        });
    }

    @Test
    public void testSignupWithNullValues() {
        assertThrows(NullPointerException.class, () -> {
            userList.signup(null, null, null, null);
        });
    }

    @Test
    public void testSetCurrentUserToNull() {
        userList.setCurrentUser(null);
        assertThrows(NullPointerException.class, () -> {
            userList.getCurrentUser().getUsername();
        });
    }    

}
