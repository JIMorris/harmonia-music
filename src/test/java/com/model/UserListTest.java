package com.model;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;

//Written and tested by Collin Floyd
public class UserListTest {
    private UserList userList;
    private User testUser;
    private Song testSong;

    @Before
    public void setUp() {
        userList = UserList.getInstance();
        userList.setUsers(new ArrayList<>()); // Reset users before each test

        UUID userID = UUID.randomUUID();
        testUser = new User("testUser", "password123", "John", "Doe", userID);
        userList.setCurrentUser(testUser);
        userList.getUsers().add(testUser);
    }

    @Test
    public void testSingletonInstance() {
        UserList anotherInstance = UserList.getInstance();
        assertSame("UserList should be a singleton", userList, anotherInstance);
    }

    @Test
    public void testGetUsers() {
        assertEquals("UserList should contain one user", 1, userList.getUsers().size());
    }

    @Test
    public void testGetCurrentUser() {
        assertEquals("Current user should be testUser", testUser, userList.getCurrentUser());
    }

    @Test
    public void testGetUserById() {
        assertEquals("User should be found by UUID", testUser, userList.getUser(testUser.getUserID()));
    }

    @Test
    public void testSignupSuccess() throws Exception {
        userList.signup("newUser", "securePass", "Jane", "Smith");
        assertEquals("UserList should contain two users", 2, userList.getUsers().size());
    }

    @Test
    public void testSignupDuplicateUsername() {
        Exception exception = assertThrows(Exception.class, () -> {
            userList.signup("testUser", "securePass", "Jane", "Smith");
        });
        assertEquals("The username testUser is already taken", exception.getMessage());
    }

    @Test
    public void testLoginSuccess() throws Exception {
        userList.login("testUser", "password123");
        assertEquals("Login should set the current user", testUser, userList.getCurrentUser());
    }

    @Test
    public void testLoginFailure() {
        Exception exception = assertThrows(Exception.class, () -> {
            userList.login("wrongUser", "wrongPass");
        });
        assertEquals("Username or password were incorrect", exception.getMessage());
    }

    @Test
    public void testToggleFavoriteSong() {
        userList.toggleFavoriteSong(testSong);
        assertTrue("Song should be in favorite list", userList.isFavorite(testSong));

        userList.toggleFavoriteSong(testSong);
        assertFalse("Song should be removed from favorite list", userList.isFavorite(testSong));
    }

    @Test
    public void testLogout() throws Exception {
        userList.logout();
        assertNull("Current user should be null after logout", userList.getCurrentUser());
    }

    // --- Additional tests to break the code intentionally ---
    
    @Test
    public void testSignupNullUsername() {
        Exception exception = assertThrows(Exception.class, () -> {
            userList.signup(null, "password", "Jane", "Doe");
        });
        assertNotNull("Exception should be thrown for null username", exception);
    }

    @Test
    public void testSignupEmptyPassword() {
        Exception exception = assertThrows(Exception.class, () -> {
            userList.signup("newUser", "", "Jane", "Doe");
        });
        assertNotNull("Exception should be thrown for empty password", exception);
    }

    @Test
    public void testLoginWithNullUsername() {
        Exception exception = assertThrows(Exception.class, () -> {
            userList.login(null, "password123");
        });
        assertNotNull("Exception should be thrown for null username login", exception);
    }

    @Test
    public void testGetUserByInvalidId() {
        UUID fakeID = UUID.randomUUID();
        assertNull("Fetching user by non-existent UUID should return null", userList.getUser(fakeID));
    }
}
