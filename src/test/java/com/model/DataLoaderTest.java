package com.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;


public class DataLoaderTest {
    private UserList userList = UserList.getInstance();
    private ArrayList<User> users = UserList.getInstance().getUsers();
    private InstrumentList instrumentList = InstrumentList.getInstance();
    private ArrayList<Instrument> instruments = InstrumentList.getInstance().getInstruments();
    private SongList songList = SongList.getInstance();
    private ArrayList<Song> songs = SongList.getInstance().getSongs();
    private DataLoader dataLoader; 
    private DataWriter dataWriter;

    @Before
    public void setUp() throws IOException {
        // Initialize the DataLoader
        dataLoader = DataLoader.getInstance();

        // Populate the json files with test data before running tests
        populateUserJSON();
        populateSongJSON();
        populateInstrumentJSON();
    }

    @After
    public void tearDown() {
        users.clear();
        instruments.clear();
        songs.clear();
    }

    @Test
    public void testUserListNotEmpty() throws Exception {
        ArrayList<User> loadedUsers = dataLoader.loadUsers();
        assertFalse("User list should not be empty", loadedUsers.isEmpty());
    }

    @Test
    public void testLoadValidUsers() throws Exception {
        ArrayList<User> loadedUsers = dataLoader.loadUsers();
        assertEquals("Should load 2 users", 2, loadedUsers.size());
        assertEquals("First user's username should match", "testuser1", loadedUsers.get(0).getUsername());
        assertEquals("Second user's username should match", "testuser2", loadedUsers.get(1).getUsername());
    }

    @Test(expected = Exception.class)
    public void testLoadInvalidUserJSON() throws Exception {
        writeInvalidJSON(DataConstants.USER_FILE_NAME);
        dataLoader.loadUsers();
    }

    // Test case to check loading users with missing usernames and whether they return nothing
    @Test
    public void testLoadUserWithMissingUsername() throws Exception {
        JSONArray usersJSON = new JSONArray();
        JSONObject user = new JSONObject();
        user.put("uuid", "123e4567-e89b-12d3-a456-426614174000");
        user.put("password", "password1");
        user.put("firstName", "John");
        user.put("lastName", "Doe");
        usersJSON.add(user);
    
        try (FileWriter file = new FileWriter(DataConstants.USER_FILE_NAME)) {
            file.write(usersJSON.toJSONString());
            file.flush();
        }
    
        ArrayList<User> loadedUsers = dataLoader.loadUsers();
        assertTrue("Users with missing username should not be loaded", loadedUsers.isEmpty());
    }

    // Test case to check loading users with missing passwords and whether they return nothing or not
    @Test
    public void testLoadUserWithMissingPassword() throws Exception {
        JSONArray usersJSON = new JSONArray();
        JSONObject user = new JSONObject();
        user.put("uuid", "123e4567-e89b-12d3-a456-426614174000");
        user.put("username", "testuser");
        user.put("firstName", "John");
        user.put("lastName", "Doe");
        usersJSON.add(user);
    
        try (FileWriter file = new FileWriter(DataConstants.USER_FILE_NAME)) {
            file.write(usersJSON.toJSONString());
            file.flush();
        }
    
        ArrayList<User> loadedUsers = dataLoader.loadUsers();
        assertTrue("Users with missing password should not be loaded", loadedUsers.isEmpty());
    }

    // Test case to check loading users with missing first names and whether they return nothing or not
    @Test
    public void testLoadUserWithMissingFirstName() throws Exception {
        JSONArray usersJSON = new JSONArray();
        JSONObject user = new JSONObject();
        user.put("uuid", "123e4567-e89b-12d3-a456-426614174000");
        user.put("username", "testuser");
        user.put("password", "password1");
        user.put("lastName", "Doe");
        usersJSON.add(user);

        try (FileWriter file = new FileWriter(DataConstants.USER_FILE_NAME)) {
            file.write(usersJSON.toJSONString());
            file.flush();
        }

        ArrayList<User> loadedUsers = dataLoader.loadUsers();
        assertTrue("Users with missing firstName should not be loaded", loadedUsers.isEmpty());
    }

    // Test case to check loading users with missing last names and whether they return nothing or not
    @Test
    public void testLoadUserWithMissingLastName() throws Exception {
        JSONArray usersJSON = new JSONArray();
        JSONObject user = new JSONObject();
        user.put("uuid", "123e4567-e89b-12d3-a456-426614174000");
        user.put("username", "testuser");
        user.put("password", "password1");
        user.put("firstName", "John");
        usersJSON.add(user);
    
        try (FileWriter file = new FileWriter(DataConstants.USER_FILE_NAME)) {
            file.write(usersJSON.toJSONString());
            file.flush();
        }
    
        ArrayList<User> loadedUsers = dataLoader.loadUsers();
        assertTrue("Users with missing lastName should not be loaded", loadedUsers.isEmpty());
    }

    // Test case to check loading users with missing UUIDs and whether they return nothing or not
    @Test
    public void testLoadUserWithMissingUUID() throws Exception {
        JSONArray usersJSON = new JSONArray();
    
        // User with missing UUID
        JSONObject user = new JSONObject();
        user.put("username", "testuser");
        user.put("password", "password1");
        user.put("firstName", "John");
        user.put("lastName", "Doe");
        usersJSON.add(user);
    
        try (FileWriter file = new FileWriter(DataConstants.USER_FILE_NAME)) {
            file.write(usersJSON.toJSONString());
            file.flush();
        }
    
        ArrayList<User> loadedUsers = dataLoader.loadUsers();
        assertTrue("Users with missing UUID should not be loaded", loadedUsers.isEmpty());
    }

    // Test case to check loading users with invalid UUID format
    @Test
    public void testLoadUserWithInvalidUUID() throws Exception {
        JSONArray usersJSON = new JSONArray();

        // User with invalid UUID format
        JSONObject user = new JSONObject();
        user.put("uuid", "invalid-uuid-format"); // Invalid UUID
        user.put("username", "testuser");
        user.put("password", "password1");
        user.put("firstName", "John");
        user.put("lastName", "Doe");
        usersJSON.add(user);

        try (FileWriter file = new FileWriter(DataConstants.USER_FILE_NAME)) {
            file.write(usersJSON.toJSONString());
            file.flush();
        }

        ArrayList<User> loadedUsers = dataLoader.loadUsers();
        assertTrue("Users with invalid UUID should not be loaded", loadedUsers.isEmpty());
    }

    @Test
    public void testLoadEmptyUserList() throws Exception {
        clearJSONFile(DataConstants.USER_FILE_NAME); // Clear the file
        ArrayList<User> loadedUsers = dataLoader.loadUsers();
        assertTrue("User list should be empty", loadedUsers.isEmpty());
    }

    // Test case to check loading users with duplicate UUIDs, returns 2 should return 1
    @Test
    public void testLoadDuplicateUsers() throws Exception {
        JSONArray usersJSON = new JSONArray();
    
        JSONObject user1 = new JSONObject();
        user1.put("uuid", "123e4567-e89b-12d3-a456-426614174000");
        user1.put("username", "testuser1");
        user1.put("password", "password1");
        user1.put("firstName", "John");
        user1.put("lastName", "Doe");
    
        JSONArray favAuthors1 = new JSONArray();
        favAuthors1.add("123e4567-e89b-12d3-a456-426614174001"); 
        user1.put("favAuthors", favAuthors1);
        usersJSON.add(user1);
    
        JSONObject user2 = new JSONObject();
        user2.put("uuid", "123e4567-e89b-12d3-a456-426614174000"); 
        user2.put("username", "testuser2");
        user2.put("password", "password2");
        user2.put("firstName", "Jane");
        user2.put("lastName", "Smith");
    
        JSONArray favAuthors2 = new JSONArray();
        favAuthors2.add("123e4567-e89b-12d3-a456-426614174000"); 
        user2.put("favAuthors", favAuthors2);
    
        usersJSON.add(user2);
    
        try (FileWriter file = new FileWriter(DataConstants.USER_FILE_NAME)) {
            file.write(usersJSON.toJSONString());
            file.flush();
        }
    
        ArrayList<User> loadedUsers = dataLoader.loadUsers();
        assertEquals("Duplicate users should not be loaded", 1, loadedUsers.size());
    }

    @Test
    public void testLoadUserWithMissingFavAuthors() throws Exception {
        JSONArray usersJSON = new JSONArray();
        JSONObject user = new JSONObject();
        user.put("uuid", "123e4567-e89b-12d3-a456-426614174000");
        user.put("username", "testuser");
        user.put("password", "password1");
        user.put("firstName", "John");
        user.put("lastName", "Doe");
        user.put("favAuthors", new JSONArray());
        usersJSON.add(user);

        try (FileWriter file = new FileWriter(DataConstants.USER_FILE_NAME)) {
            file.write(usersJSON.toJSONString());
            file.flush();
        }

        ArrayList<User> loadedUsers = dataLoader.loadUsers();
        assertEquals("One user should be loaded", 1, loadedUsers.size());
        assertTrue("User's favAuthors list should be empty", loadedUsers.get(0).getFavAuthors().isEmpty());
    }

    // Test case to check loading users with favAuthors that do not exist in the system
    // Should return an empty list for favAuthors if they do not exist in the system
    @Test
    public void testLoadUserWithNonExistentFavAuthors() throws Exception {
        JSONArray usersJSON = new JSONArray();
        JSONObject user = new JSONObject();
        user.put("uuid", "123e4567-e89b-12d3-a456-426614174000");
        user.put("username", "testuser");
        user.put("password", "password1");
        user.put("firstName", "John");
        user.put("lastName", "Doe");

        JSONArray favAuthors = new JSONArray();
        favAuthors.add("non-existent-uuid"); 
        user.put("favAuthors", favAuthors);
        usersJSON.add(user);

        try (FileWriter file = new FileWriter(DataConstants.USER_FILE_NAME)) {
            file.write(usersJSON.toJSONString());
            file.flush();
        }

        ArrayList<User> loadedUsers = dataLoader.loadUsers();
        assertEquals("One user should be loaded", 1, loadedUsers.size());
        assertTrue("User's favAuthors list should be empty", loadedUsers.get(0).getFavAuthors().isEmpty());
    } 

    // Test case to check loading users with duplicate favAuthors, should return only unique entries in the favAuthors list
    @Test
    public void testLoadUserWithDuplicateFavAuthors() throws Exception {
        JSONArray usersJSON = new JSONArray();
        JSONObject user = new JSONObject();
        user.put("uuid", "123e4567-e89b-12d3-a456-426614174000");
        user.put("username", "testuser");
        user.put("password", "password1");
        user.put("firstName", "John");
        user.put("lastName", "Doe");

        JSONArray favAuthors = new JSONArray();
        favAuthors.add("123e4567-e89b-12d3-a456-426614174001");
        favAuthors.add("123e4567-e89b-12d3-a456-426614174001");
        user.put("favAuthors", favAuthors);
        usersJSON.add(user);

        try (FileWriter file = new FileWriter(DataConstants.USER_FILE_NAME)) {
            file.write(usersJSON.toJSONString());
            file.flush();
        }

        ArrayList<User> loadedUsers = dataLoader.loadUsers();
        assertEquals("One user should be loaded", 1, loadedUsers.size());
        assertEquals("User's favAuthors list should contain 1 unique entry", 1, loadedUsers.get(0).getFavAuthors().size());
    }

    @Test
    public void testInstrumentListNotEmpty() throws Exception {
        ArrayList<Instrument> loadedInstruments = dataLoader.loadInstruments();
        assertFalse("Instrument list should not be empty", loadedInstruments.isEmpty());
    }

    @Test
    public void testLoadValidInstruments() throws Exception {
        ArrayList<Instrument> loadedInstruments = dataLoader.loadInstruments();
        assertEquals("Should load 2 instruments", 2, loadedInstruments.size());
        assertEquals("First instrument's name should match", "Piano", loadedInstruments.get(0).getName());
        assertEquals("Second instrument's name should match", "Guitar", loadedInstruments.get(1).getName());
    }

    @Test(expected = Exception.class)
    public void testLoadInvalidInstrumentJSON() throws Exception {
        writeInvalidJSON(DataConstants.INSTRUMENT_FILE_NAME);
        dataLoader.loadInstruments();
    }

    // Test case to check loading instruments with missing names, should return an empty list
    @Test
    public void testLoadInstrumentWithMissingName() throws Exception {
        JSONArray instrumentsJSON = new JSONArray();
        JSONObject instrument = new JSONObject();
        instrument.put("uuid", "ff79cdfd-424c-4026-9a5b-43b9a4653228");
        instrument.put("imageFile", "piano.png");
        instrumentsJSON.add(instrument);

        try (FileWriter file = new FileWriter(DataConstants.INSTRUMENT_FILE_NAME)) {
            file.write(instrumentsJSON.toJSONString());
            file.flush();
        }

        ArrayList<Instrument> loadedInstruments = dataLoader.loadInstruments();
        assertTrue("Instruments with missing name should not be loaded", loadedInstruments.isEmpty());
    }

    // Test case to check loading instruments with missing image name, should return an empty list
    @Test
    public void testLoadInstrumentWithMissingImageFile() throws Exception {
        JSONArray instrumentsJSON = new JSONArray();
        JSONObject instrument = new JSONObject();
        instrument.put("uuid", "ff79cdfd-424c-4026-9a5b-43b9a4653228");
        instrument.put("name", "Piano");
        instrumentsJSON.add(instrument);

        try (FileWriter file = new FileWriter(DataConstants.INSTRUMENT_FILE_NAME)) {
            file.write(instrumentsJSON.toJSONString());
            file.flush();
        }

        ArrayList<Instrument> loadedInstruments = dataLoader.loadInstruments();
        assertTrue("Instruments with missing imageFile should not be loaded", loadedInstruments.isEmpty());
    }

    // Test case to check loading instruments with missing UUIDs, should return an empty list
    @Test
    public void testLoadInstrumentWithMissingUUID() throws Exception {
        JSONArray instrumentsJSON = new JSONArray();
        JSONObject instrument = new JSONObject();
        instrument.put("name", "Piano");
        instrument.put("imageFile", "piano.png");
        instrumentsJSON.add(instrument);

        try (FileWriter file = new FileWriter(DataConstants.INSTRUMENT_FILE_NAME)) {
            file.write(instrumentsJSON.toJSONString());
            file.flush();
        }

        ArrayList<Instrument> loadedInstruments = dataLoader.loadInstruments();
        assertTrue("Instruments with missing UUID should not be loaded", loadedInstruments.isEmpty());
    }

    // Test case to check loading instruments with invalid UUID format, should return an empty list
    @Test
    public void testLoadInstrumentWithInvalidUUID() throws Exception {
        JSONArray instrumentsJSON = new JSONArray();
        JSONObject instrument = new JSONObject();
        instrument.put("uuid", "invalid-uuid-format");
        instrument.put("name", "Piano");
        instrument.put("imageFile", "piano.png");
        instrumentsJSON.add(instrument);

        try (FileWriter file = new FileWriter(DataConstants.INSTRUMENT_FILE_NAME)) {
            file.write(instrumentsJSON.toJSONString());
            file.flush();
        }

        ArrayList<Instrument> loadedInstruments = dataLoader.loadInstruments();
        assertTrue("Instruments with invalid UUID should not be loaded", loadedInstruments.isEmpty());
    }

    @Test
    public void testLoadEmptyInstrumentList() throws Exception {
        clearJSONFile(DataConstants.INSTRUMENT_FILE_NAME);
        ArrayList<Instrument> loadedInstruments = dataLoader.loadInstruments();
        assertTrue("Instrument list should be empty", loadedInstruments.isEmpty());
    }

    // Test case to check loading instruments with duplicate UUIDs, should return only unique entries in the instrument list
    @Test
    public void testLoadDuplicateInstruments() throws Exception {
        JSONArray instrumentsJSON = new JSONArray();
        JSONObject instrument1 = new JSONObject();
        instrument1.put("uuid", "ff79cdfd-424c-4026-9a5b-43b9a4653228");
        instrument1.put("name", "Piano");
        instrument1.put("imageFile", "piano.png");
        instrumentsJSON.add(instrument1);

        JSONObject instrument2 = new JSONObject();
        instrument2.put("uuid", "ff79cdfd-424c-4026-9a5b-43b9a4653228"); 
        instrument2.put("name", "Guitar");
        instrument2.put("imageFile", "guitar.png");
        instrumentsJSON.add(instrument2);

        try (FileWriter file = new FileWriter(DataConstants.INSTRUMENT_FILE_NAME)) {
            file.write(instrumentsJSON.toJSONString());
            file.flush();
        }

        ArrayList<Instrument> loadedInstruments = dataLoader.loadInstruments();
        assertEquals("Duplicate instruments should not be loaded", 1, loadedInstruments.size());
    }

    @Test
    public void testSongListNotEmpty() throws Exception {
        ArrayList<Song> loadedSongs = dataLoader.loadSongs();
        assertFalse("Song list should not be empty", loadedSongs.isEmpty());
    }

    @Test
    public void testLoadValidSongs() throws Exception {
        ArrayList<Song> loadedSongs = dataLoader.loadSongs();
        assertEquals("Should load 2 songs", 2, loadedSongs.size());
        assertEquals("First song's title should match", "Song One", loadedSongs.get(0).getTitle());
        assertEquals("Second song's title should match", "Song Two", loadedSongs.get(1).getTitle());
    }

    @Test(expected = Exception.class)
    public void testLoadInvalidSongJSON() throws Exception {
        writeInvalidJSON(DataConstants.SONG_FILE_NAME);
        dataLoader.loadSongs();
    }
    
    // Test case to check loading songs with missing titles, should return an empty list
    @Test
    public void testLoadSongWithMissingTitle() throws Exception {
        JSONArray songsJSON = new JSONArray();
        JSONObject song = new JSONObject();
        song.put("uuid", "57e7ddb0-3224-426a-adb2-5a3db7b77ce9");
        song.put("author", "123e4567-e89b-12d3-a456-426614174000");
        song.put("description", "This is a test song without a title.");
        song.put("genres", new JSONArray());
        song.put("difficulty", 3);
        song.put("reactions", new JSONArray());
        song.put("published", true);
        song.put("tempo", 120);
        song.put("keySignature", "C");
        song.put("timeSignatureNum", 4);
        song.put("timeSignatureDen", 4);
        song.put("measures", new JSONArray()); 
        song.put("instruments", new JSONArray()); 
        songsJSON.add(song);

        try (FileWriter file = new FileWriter(DataConstants.SONG_FILE_NAME)) {
            file.write(songsJSON.toJSONString());
            file.flush();
        }

        ArrayList<Song> loadedSongs = dataLoader.loadSongs();
        assertTrue("Songs with missing title should not be loaded", loadedSongs.isEmpty());
    }

    @Test
    public void testLoadEmptySongList() throws Exception {
        clearJSONFile(DataConstants.SONG_FILE_NAME);
        ArrayList<Song> loadedSongs = dataLoader.loadSongs();
        assertTrue("Song list should be empty", loadedSongs.isEmpty());
    }

    // Test case to check duplicated songs, should return unique entries only
    @Test
    public void testLoadDuplicateSongs() throws Exception {
        JSONArray songsJSON = new JSONArray();

        // Song 1
        JSONObject song1 = new JSONObject();
        song1.put("uuid", "57e7ddb0-3224-426a-adb2-5a3db7b77ce9");
        song1.put("title", "Duplicate Song");
        song1.put("author", "123e4567-e89b-12d3-a456-426614174000");
        song1.put("description", "This is a duplicate song.");
        song1.put("genres", new JSONArray());
        song1.put("difficulty", 3);
        song1.put("reactions", new JSONArray());
        song1.put("published", true);
        song1.put("tempo", 120);
        song1.put("keySignature", "C");
        song1.put("timeSignatureNum", 4);
        song1.put("timeSignatureDen", 4);
        song1.put("measures", new JSONArray());
        song1.put("instruments", new JSONArray());
        songsJSON.add(song1);

        // Song 2 with the same UUID as Song 1
        JSONObject song2 = new JSONObject();
        song2.put("uuid", "57e7ddb0-3224-426a-adb2-5a3db7b77ce9"); // Duplicate UUID
        song2.put("title", "Duplicate Song Again");
        song2.put("author", "123e4567-e89b-12d3-a456-426614174001");
        song2.put("description", "This is another duplicate song.");
        song2.put("genres", new JSONArray());
        song2.put("difficulty", 4);
        song2.put("reactions", new JSONArray());
        song2.put("published", false);
        song2.put("tempo", 140);
        song2.put("keySignature", "G");
        song2.put("timeSignatureNum", 3);
        song2.put("timeSignatureDen", 4);
        song2.put("measures", new JSONArray());
        song2.put("instruments", new JSONArray());
        songsJSON.add(song2);

        try (FileWriter file = new FileWriter(DataConstants.SONG_FILE_NAME)) {
            file.write(songsJSON.toJSONString());
            file.flush();
        }

        ArrayList<Song> loadedSongs = dataLoader.loadSongs();
        assertEquals("Duplicate songs should not be loaded", 1, loadedSongs.size());
    }

    // Test case to check songs with duplicate genres, should return only unique entries in the genres list
    @Test
    public void testLoadSongWithDuplicateGenres() throws Exception {
        JSONArray songsJSON = new JSONArray();
        JSONObject song = new JSONObject();
        song.put("uuid", "57e7ddb0-3224-426a-adb2-5a3db7b77ce9");
        song.put("title", "Song With Duplicate Genres");
        song.put("author", "123e4567-e89b-12d3-a456-426614174000");
        song.put("description", "This song has duplicate genres.");
        
        JSONArray genres = new JSONArray();
        genres.add("Rock");
        genres.add("Rock");
        genres.add("Pop");
        song.put("genres", genres);

        song.put("difficulty", 3);
        song.put("reactions", new JSONArray());
        song.put("published", true);
        song.put("tempo", 120);
        song.put("keySignature", "C");
        song.put("timeSignatureNum", 4);
        song.put("timeSignatureDen", 4);
        song.put("measures", new JSONArray());
        song.put("instruments", new JSONArray());
        songsJSON.add(song);

        try (FileWriter file = new FileWriter(DataConstants.SONG_FILE_NAME)) {
            file.write(songsJSON.toJSONString());
            file.flush();
        }

        ArrayList<Song> loadedSongs = dataLoader.loadSongs();
        assertEquals("One song should be loaded", 1, loadedSongs.size());
        assertEquals("Genres should contain only unique entries", 2, loadedSongs.get(0).getGenres().size());
    }

    private void populateUserJSON() throws IOException {
        JSONArray usersJSON = new JSONArray();

        // User 1
        JSONObject user1 = new JSONObject();
        user1.put("uuid", "123e4567-e89b-12d3-a456-426614174000");
        user1.put("username", "testuser1");
        user1.put("password", "password1");
        user1.put("firstName", "John");
        user1.put("lastName", "Doe");
        user1.put("favSongs", new JSONArray());
        user1.put("favAuthors", new JSONArray());

        // User 2
        JSONObject user2 = new JSONObject();
        user2.put("uuid", "123e4567-e89b-12d3-a456-426614174001");
        user2.put("username", "testuser2");
        user2.put("password", "password2");
        user2.put("firstName", "Jane");
        user2.put("lastName", "Smith");
        user2.put("favSongs", new JSONArray());
        user2.put("favAuthors", new JSONArray());

        usersJSON.add(user1);
        usersJSON.add(user2);

        try (FileWriter file = new FileWriter(DataConstants.USER_FILE_NAME)) {
            file.write(usersJSON.toJSONString());
            file.flush();
        }
    }

    private void populateSongJSON() throws IOException {
        JSONArray songsJSON = new JSONArray();

        // Song 1
        JSONObject song1 = new JSONObject();
        song1.put("uuid", "57e7ddb0-3224-426a-adb2-5a3db7b77ce9");
        song1.put("title", "Song One");
        song1.put("author", "123e4567-e89b-12d3-a456-426614174000");
        song1.put("description", "A test song");
        song1.put("genres", new JSONArray());
        song1.put("difficulty", 3);
        song1.put("reactions", new JSONArray());
        song1.put("published", true);
        song1.put("tempo", 120);
        song1.put("keySignature", "C");
        song1.put("timeSignatureNum", 4);
        song1.put("timeSignatureDen", 4);
        song1.put("measures", new JSONArray());
        song1.put("instruments", new JSONArray());

        // Song 2
        JSONObject song2 = new JSONObject();
        song2.put("uuid", "316cd42c-834b-4e7e-8e64-1effb8226f30");
        song2.put("title", "Song Two");
        song2.put("author", "123e4567-e89b-12d3-a456-426614174001");
        song2.put("description", "Another test song");
        song2.put("genres", new JSONArray());
        song2.put("difficulty", 2);
        song2.put("reactions", new JSONArray());
        song2.put("published", false);
        song2.put("tempo", 100);
        song2.put("keySignature", "G");
        song2.put("timeSignatureNum", 3);
        song2.put("timeSignatureDen", 4);
        song2.put("measures", new JSONArray());
        song2.put("instruments", new JSONArray());

        songsJSON.add(song1);
        songsJSON.add(song2);

        try (FileWriter file = new FileWriter(DataConstants.SONG_FILE_NAME)) {
            file.write(songsJSON.toJSONString());
            file.flush();
        }
    }

    private void populateInstrumentJSON() throws IOException {
        JSONArray instrumentsJSON = new JSONArray();

        // Instrument 1
        JSONObject instrument1 = new JSONObject();
        instrument1.put("uuid", "ff79cdfd-424c-4026-9a5b-43b9a4653228");
        instrument1.put("name", "Piano");
        instrument1.put("imageFile", "piano.png");

        // Instrument 2
        JSONObject instrument2 = new JSONObject();
        instrument2.put("uuid", "30c429e7-0956-4930-9eeb-9562e7810636");
        instrument2.put("name", "Guitar");
        instrument2.put("imageFile", "guitar.png");

        instrumentsJSON.add(instrument1);
        instrumentsJSON.add(instrument2);

        try (FileWriter file = new FileWriter(DataConstants.INSTRUMENT_FILE_NAME)) {
            file.write(instrumentsJSON.toJSONString());
            file.flush();
        }
    }

    private void clearJSONFile(String fileName) throws IOException {
        try (FileWriter file = new FileWriter(fileName)) {
            file.write("[]");
            file.flush();
        }
    }

    private void writeInvalidJSON(String fileName) throws IOException {
        try (FileWriter file = new FileWriter(fileName)) {
            file.write("{invalid json}");
            file.flush();
        }
    }
}