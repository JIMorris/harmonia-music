package com.model;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.classfile.Label;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class DataWriterTest {
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
        // Initialize the DataWriter
        dataWriter = DataWriter.getInstance();
        dataLoader = DataLoader.getInstance();

        // Clear all JSON files before running tests
        clearJSONFile(DataConstants.USER_FILE_NAME);
        clearJSONFile(DataConstants.SONG_FILE_NAME);
        clearJSONFile(DataConstants.INSTRUMENT_FILE_NAME);

        // Clear in-memory data
        userList.clear();
        instrumentList.clear();
        songList.clear();
    }

    @After
    public void tearDown() throws IOException {
        // Clear all JSON files after running tests
        clearJSONFile(DataConstants.USER_FILE_NAME);
        clearJSONFile(DataConstants.SONG_FILE_NAME);
        clearJSONFile(DataConstants.INSTRUMENT_FILE_NAME);

        userList.clear();
        instrumentList.clear();
        songList.clear();
    }

    @Test
    public void testWriteZeroUsers() throws Exception {
        ArrayList<User> loadedUsers = dataLoader.loadUsers();
        assertTrue("DataLoader should receive an empty user list", loadedUsers.isEmpty());
    }

    // User with null username is saved, should not be saved and return an empty object
    @Test
    public void testSaveUserWithNullUsername() throws Exception {
        User user = new User(null, "password123", "John", "Doe", UUID.randomUUID());
        users.add(user);
        dataWriter.saveUsers();
    
        ArrayList<User> loadedUsers = dataLoader.loadUsers();
        assertTrue("User with null username should not be saved", loadedUsers.isEmpty());
    }
    // User with null password is saved, should not be saved and return an empty object
    @Test
    public void testSaveUserWithNullPassword() throws Exception {
        User user = new User("username", null, "John", "Doe", UUID.randomUUID());
        users.add(user);
        dataWriter.saveUsers();
    
        ArrayList<User> loadedUsers = dataLoader.loadUsers();
        assertTrue("User with null password should not be saved", loadedUsers.isEmpty());
    }
    
    // User with null first name is saved, should not be saved and return an empty object
    @Test
    public void testSaveUserWithNullFirstName() throws Exception {
        User user = new User("username", "password123", null, "Doe", UUID.randomUUID());
        users.add(user);
        dataWriter.saveUsers();
    
        ArrayList<User> loadedUsers = dataLoader.loadUsers();
        assertTrue("User with null first name should not be saved", loadedUsers.isEmpty());
    }
    
    // User with null last name is saved, should not be saved and return an empty object
    @Test
    public void testSaveUserWithNullLastName() throws Exception {
        User user = new User("username", "password123", "John", null, UUID.randomUUID());
        users.add(user);
        dataWriter.saveUsers();
    
        ArrayList<User> loadedUsers = dataLoader.loadUsers();
        assertTrue("User with null last name should not be saved", loadedUsers.isEmpty());
    }
    
    // User with null UUID causes a null ptr exception when saving, should not be saved and return an empty object
    @Test
    public void testSaveUserWithNullUUID() throws Exception {
        User user = new User("username", "password123", "John", "Doe", null);
        users.add(user);
        dataWriter.saveUsers();
    
        ArrayList<User> loadedUsers = dataLoader.loadUsers();
        assertTrue("User with null UUID should not be saved", loadedUsers.isEmpty());
    }

    // User with empty username should not be saved, return an empty object
    @Test
    public void testSaveUserWithEmptyUsername() throws Exception {
        User user = new User("", "password123", "John", "Doe", UUID.randomUUID());
        users.add(user);
        dataWriter.saveUsers();
    
        ArrayList<User> loadedUsers = dataLoader.loadUsers();
        assertTrue("User with empty username should not be saved", loadedUsers.isEmpty());
    }
    
    // User with empty password should not be saved, return an empty object
    @Test
    public void testSaveUserWithEmptyPassword() throws Exception {
        User user = new User("username", "", "John", "Doe", UUID.randomUUID());
        users.add(user);
        dataWriter.saveUsers();
    
        ArrayList<User> loadedUsers = dataLoader.loadUsers();
        assertTrue("User with empty password should not be saved", loadedUsers.isEmpty());
    }

    // User with empty first name should not be saved, return an empty object
    @Test
    public void testSaveUserWithEmptyFirstName() throws Exception {
        User user = new User("username", "password123", "", "Doe", UUID.randomUUID());
        users.add(user);
        dataWriter.saveUsers();

        ArrayList<User> loadedUsers = dataLoader.loadUsers();
        assertTrue("User with empty first name should not be saved", loadedUsers.isEmpty());
    }

    // User with empty last name should not be saved, return an empty object
    @Test
    public void testSaveUserWithEmptyLastName() throws Exception {
        User user = new User("username", "password123", "John", "", UUID.randomUUID());
        users.add(user);
        dataWriter.saveUsers();

        ArrayList<User> loadedUsers = dataLoader.loadUsers();
        assertTrue("User with empty last name should not be saved", loadedUsers.isEmpty());
    }

    // User with empty UUID causes a null ptr exception when saving, should not be saved and return an empty object
    @Test  
    public void testSaveUserWithEmptyUUID() throws Exception {
        User user = new User("username", "password123", "John", "Doe", null); // Null UUID
        users.add(user);
        dataWriter.saveUsers();

        ArrayList<User> loadedUsers = dataLoader.loadUsers();
        assertTrue("User with null UUID should not be saved", loadedUsers.isEmpty());
    }

    @Test
    public void testSaveUserWithLongUsername() throws Exception {
        String longUsername = "a".repeat(1000); // Adjust length based on your application's constraints
        User user = new User(longUsername, "password123", "John", "Doe", UUID.randomUUID());
        users.add(user);
        dataWriter.saveUsers();
    
        ArrayList<User> loadedUsers = dataLoader.loadUsers();
        assertEquals("User with long username should be saved", 1, loadedUsers.size());
    }

    @Test
    public void testSaveUserWithSpecialCharactersInUsername() throws Exception {
        User user = new User("user@name!", "password123", "John", "Doe", UUID.randomUUID());
        users.add(user);
        dataWriter.saveUsers();
    
        ArrayList<User> loadedUsers = dataLoader.loadUsers();
        assertEquals("User with special characters in username should be saved", 1, loadedUsers.size());
    }

    // Error caused by favorite songs not matching expected value in the testWriteOneUser method, ensure that the favorite song and author are properly initialized before saving
    @Test
    public void testWriteOneUser() throws Exception {
        // Add one fully populated user to the user list
        User user = new User(
            "singleUser", 
            "password123", 
            "John", 
            "Doe", 
            UUID.randomUUID()
        );
    
        // Add a favorite song and a favorite author using the proper methods
        Song favoriteSong = new Song(UUID.randomUUID(), "Favorite Song", null, "A test favorite song", new ArrayList<>(), 3, new ArrayList<>(), true, 120, Key.C_MAJOR, 4, 4, new ArrayList<>(), new ArrayList<>());
        User favoriteAuthor = new User("favAuthor", "password456", "Jane", "Smith", UUID.randomUUID());
    
        user.toggleFavoriteSong(favoriteSong);
        user.toggleFavoriteAuthor(favoriteAuthor);
        users.add(user);
        dataWriter.saveUsers();
        ArrayList<User> loadedUsers = dataLoader.loadUsers();
        assertEquals("DataLoader should receive one user", 1, loadedUsers.size());
    
        User loadedUser = loadedUsers.get(0);
        assertEquals("Username should match", user.getUsername(), loadedUser.getUsername());
        assertEquals("First name should match", user.getFirstName(), loadedUser.getFirstName());
        assertEquals("Last name should match", user.getLastName(), loadedUser.getLastName());
        assertEquals("Favorite songs should match", user.getFavSongs().size(), loadedUser.getFavSongs().size());
        assertEquals("Favorite authors should match", user.getFavAuthors().size(), loadedUser.getFavAuthors().size());
    }
    
    // Test to ensure that duplicate users are not saved when saving the user list. This should ensure that only unique UUIDs are saved in the JSON file.
    @Test
    public void testSaveDuplicateUsers() throws Exception {
        UUID userId = UUID.randomUUID();
        User user1 = new User("username1", "password123", "John", "Doe", userId);
        User user2 = new User("username2", "password456", "Jane", "Smith", userId); // Same UUID as user1
        users.add(user1);
        users.add(user2);
        dataWriter.saveUsers();
    
        ArrayList<User> loadedUsers = dataLoader.loadUsers();
        assertEquals("Duplicate users should not be saved", 1, loadedUsers.size());
    }

    // When favorite song and author are omitted, users are properly saved
    @Test
    public void testWriteTenUsers() throws Exception {
        // Add 10 users to the user list
        for (int i = 0; i < 10; i++) {
            users.add(new User("user" + i, "password" + i, "First" + i, "Last" + i, UUID.randomUUID()));
        }
        dataWriter.saveUsers();
        ArrayList<User> loadedUsers = dataLoader.loadUsers();
        assertEquals("DataLoader should receive 10 users", 10, loadedUsers.size());
    }

    @Test
    public void testWriteZeroSongs() throws Exception {
        ArrayList<Song> loadedSongs = dataLoader.loadSongs();
        assertTrue("DataLoader should receive an empty song list", loadedSongs.isEmpty());
    }

    // Should not save songs with null title. This ensures that all required fields are properly initialized before saving the song object.
    @Test
    public void testSaveSongWithNullTitle() throws Exception {
        Song song = new Song(null, new User("author", "password", "John", "Doe", UUID.randomUUID()), 
                             "Description", new ArrayList<>(), 3, 120, Key.C_MAJOR, 4, 4, 
                             new Instrument(UUID.randomUUID(), "Piano", "piano.png"));
        songs.add(song);
        dataWriter.saveSongs();
    
        ArrayList<Song> loadedSongs = dataLoader.loadSongs();
        assertTrue("Song with null title should not be saved", loadedSongs.isEmpty());
    }
    
    // Should not save songs with null author, this ensures that the song object has a valid author before saving to the JSON file
    @Test
    public void testSaveSongWithNullAuthor() throws Exception {
        Song song = new Song("Title", null, "Description", new ArrayList<>(), 3, 120, Key.C_MAJOR, 4, 4, 
                             new Instrument(UUID.randomUUID(), "Piano", "piano.png"));
        songs.add(song);
        dataWriter.saveSongs();
    
        ArrayList<Song> loadedSongs = dataLoader.loadSongs();
        assertTrue("Song with null author should not be saved", loadedSongs.isEmpty());
    }
    
    // Should not save songs with null description, this ensures that the song object has a valid description before saving to the JSON file
    @Test
    public void testSaveSongWithNullDescription() throws Exception {
        Song song = new Song("Title", new User("author", "password", "John", "Doe", UUID.randomUUID()), 
                             null, new ArrayList<>(), 3, 120, Key.C_MAJOR, 4, 4, 
                             new Instrument(UUID.randomUUID(), "Piano", "piano.png"));
        songs.add(song);
        dataWriter.saveSongs();
    
        ArrayList<Song> loadedSongs = dataLoader.loadSongs();
        assertTrue("Song with null description should not be saved", loadedSongs.isEmpty());
    }
    
    // Should not save songs with null genres, this ensures that the song object has valid genres before saving to the JSON file
    @Test
    public void testSaveSongWithNullGenres() throws Exception {
        Song song = new Song("Title", new User("author", "password", "John", "Doe", UUID.randomUUID()), 
                             "Description", null, 3, 120, Key.C_MAJOR, 4, 4, 
                             new Instrument(UUID.randomUUID(), "Piano", "piano.png"));
        songs.add(song);
        dataWriter.saveSongs();
    
        ArrayList<Song> loadedSongs = dataLoader.loadSongs();
        assertTrue("Song with null genres should not be saved", loadedSongs.isEmpty());
    }
    
    // Should not save songs with null instruments, this ensures that the song object has valid instruments before saving to the JSON file
    @Test
    public void testSaveSongWithNullInstrument() throws Exception {
        Song song = new Song("Title", new User("author", "password", "John", "Doe", UUID.randomUUID()), 
                             "Description", new ArrayList<>(), 3, 120, Key.C_MAJOR, 4, 4, null);
        songs.add(song);
        dataWriter.saveSongs();
    
        ArrayList<Song> loadedSongs = dataLoader.loadSongs();
        assertTrue("Song with null instrument should not be saved", loadedSongs.isEmpty());
    }

    // Test to ensure that a song with an empty title is not saved in the JSON file. This ensures that the title field is properly validated before saving.
    @Test
    public void testSaveSongWithEmptyTitle() throws Exception {
        Song song = new Song("", new User("author", "password", "John", "Doe", UUID.randomUUID()), 
                             "Description", new ArrayList<>(), 3, 120, Key.C_MAJOR, 4, 4, 
                             new Instrument(UUID.randomUUID(), "Piano", "piano.png"));
        songs.add(song);
        dataWriter.saveSongs();
    
        ArrayList<Song> loadedSongs = dataLoader.loadSongs();
        assertTrue("Song with empty title should not be saved", loadedSongs.isEmpty());
    }

    // Error caused by null ptr exceptions when saving songs with null values for required fields, ensure all required fields are properly initialized before saving the song object
    // kcrase - I cannot tell where the null value is supposed to be populated, everytime I fix one another appears
    @Test
    public void testWriteOneSong() throws Exception {
        // Add one fully populated song to the song list
        ArrayList<Genre> genres = new ArrayList<>(List.of(Genre.POP, Genre.JAZZ));
        ArrayList<Reaction> reactions = new ArrayList<>(List.of(new Reaction(5, "Great song!", new User("author", "password", "John", "Doe", UUID.randomUUID()))));
        ArrayList<MeasureGroup> measures = new ArrayList<>(List.of(new MeasureGroup(4, Chord.C_MAJ, new ArrayList<>())));
        ArrayList<Instrument> instruments = new ArrayList<>(List.of(new Instrument(UUID.randomUUID(), "Piano", "piano.png")));

        Song song = new Song(
            UUID.randomUUID(), 
            "Single Song", 
            new User("author", "password", "John", "Doe", UUID.randomUUID()), // Author
            "A test song", 
            genres, 
            3, 
            reactions, 
            true, 
            120, 
            Key.C_MAJOR, 
            4, 
            4, 
            measures, 
            instruments
        );
        songs.add(song);
        dataWriter.saveSongs();

        ArrayList<Song> loadedSongs = dataLoader.loadSongs();
        assertEquals("DataLoader should receive one song", 1, loadedSongs.size());

        Song loadedSong = loadedSongs.get(0);
        assertEquals("Title should match", song.getTitle(), loadedSong.getTitle());
        assertEquals("Description should match", song.getDescription(), loadedSong.getDescription());
        assertEquals("Genres should match", song.getGenres(), loadedSong.getGenres());
        assertEquals("Tempo should match", song.getTempo(), loadedSong.getTempo());
        assertEquals("Key signature should match", song.getKeySignature(), loadedSong.getKeySignature());
        assertEquals("Time signature numerator should match", song.getTimeSignatureNum(), loadedSong.getTimeSignatureNum());
        assertEquals("Time signature denominator should match", song.getTimeSignatureDen(), loadedSong.getTimeSignatureDen());
        assertEquals("Instruments should match", song.getInstruments(), loadedSong.getInstruments());
    }

    // Test to ensure that duplicate songs are not saved in the JSON file. This ensures that only unique UUIDs are saved in the JSON file for songs.
    @Test
    public void testSaveDuplicateSongs() throws Exception {
        UUID songId = UUID.randomUUID();
        Song song1 = new Song(songId, "Title1", new User("author1", "password1", "John", "Doe", UUID.randomUUID()), 
                              "Description1", new ArrayList<>(), 3, new ArrayList<>(), true, 120, 
                              Key.C_MAJOR, 4, 4, new ArrayList<>(), new ArrayList<>());
        Song song2 = new Song(songId, "Title2", new User("author2", "password2", "Jane", "Smith", UUID.randomUUID()), 
                              "Description2", new ArrayList<>(), 4, new ArrayList<>(), true, 140, 
                              Key.G_MAJOR, 3, 4, new ArrayList<>(), new ArrayList<>());
        songs.add(song1);
        songs.add(song2);
        dataWriter.saveSongs();
    
        ArrayList<Song> loadedSongs = dataLoader.loadSongs();
        assertEquals("Duplicate songs should not be saved", 1, loadedSongs.size());
    }

    // Error caused by null ptr exceptions when saving songs with null values for required fields, ensure all required fields are properly initialized before saving the song object
    @Test
    public void testWriteTenSongs() throws Exception {
        // Add 10 songs to the song list
        for (int i = 0; i < 10; i++) {
            Instrument instrument = new Instrument(UUID.randomUUID(), "Instrument" + i, "image" + i + ".png");
            MeasureGroup measureGroup = new MeasureGroup(4, Chord.C_MAJ, new HashMap<>());
            measureGroup.addMeasure(instrument);
    
            // Create a Song object with all fields properly initialized
            songs.add(new Song(
                UUID.randomUUID(),
                "Song" + i,
                new User("author" + i, "password" + i, "First" + i, "Last" + i, UUID.randomUUID()), // Author
                "Description" + i,
                new ArrayList<>(List.of(Genre.POP)), // Genres
                i, // Difficulty
                new ArrayList<>(), // Reactions
                true, // Published
                120, // Tempo
                Key.C_MAJOR, // Key Signature
                4, // Time Signature Numerator
                4, // Time Signature Denominator
                new ArrayList<>(List.of(measureGroup)), // Measures
                new ArrayList<>(List.of(instrument)) // Instruments
            ));
        }
        dataWriter.saveSongs();
        ArrayList<Song> loadedSongs = dataLoader.loadSongs();
        assertEquals("DataLoader should receive 10 songs", 10, loadedSongs.size());

        for (int i = 0; i < 10; i++) {
            Song originalSong = songs.get(i);
            Song loadedSong = loadedSongs.get(i);
    
            assertEquals("Title should match", originalSong.getTitle(), loadedSong.getTitle());
            assertEquals("Description should match", originalSong.getDescription(), loadedSong.getDescription());
            assertEquals("Genres should match", originalSong.getGenres(), loadedSong.getGenres());
            assertEquals("Tempo should match", originalSong.getTempo(), loadedSong.getTempo());
            assertEquals("Key signature should match", originalSong.getKeySignature(), loadedSong.getKeySignature());
            assertEquals("Time signature numerator should match", originalSong.getTimeSignatureNum(), loadedSong.getTimeSignatureNum());
            assertEquals("Time signature denominator should match", originalSong.getTimeSignatureDen(), loadedSong.getTimeSignatureDen());
            assertEquals("Instruments should match", originalSong.getInstruments(), loadedSong.getInstruments());
        }
    }

    @Test
    public void testWriteZeroInstruments() throws Exception {
        ArrayList<Instrument> loadedInstruments = dataLoader.loadInstruments();
        assertTrue("DataLoader should receive an empty instrument list", loadedInstruments.isEmpty());
    }

    // Instrument with null UUID causes null ptr exception when saving, should not be saved and return an empty object
    @Test
    public void testSaveInstrumentWithNullUUID() throws Exception {
        Instrument instrument = new Instrument(null, "Piano", "piano.png");
        instruments.add(instrument);
        dataWriter.saveInstruments();

        ArrayList<Instrument> loadedInstruments = dataLoader.loadInstruments();
        assertTrue("Instrument with null UUID should not be saved", loadedInstruments.isEmpty());
    }

    // Instrument with null name is saved, should not be saved and return an empty object
    @Test
    public void testSaveInstrumentWithNullName() throws Exception {
        Instrument instrument = new Instrument(UUID.randomUUID(), null, "piano.png");
        instruments.add(instrument);
        dataWriter.saveInstruments();

        ArrayList<Instrument> loadedInstruments = dataLoader.loadInstruments();
        assertTrue("Instrument with null name should not be saved", loadedInstruments.isEmpty());
    }

    // Instrument with null image file is saved, should not be saved and return an empty object
    @Test
    public void testSaveInstrumentWithNullImageFile() throws Exception {
        Instrument instrument = new Instrument(UUID.randomUUID(), "Piano", null);
        instruments.add(instrument);
        dataWriter.saveInstruments();

        ArrayList<Instrument> loadedInstruments = dataLoader.loadInstruments();
        assertTrue("Instrument with null image file should not be saved", loadedInstruments.isEmpty());
    }

    // Test to ensure that an instrument with an empty name is not saved in the JSON file
    @Test
    public void testSaveInstrumentWithEmptyName() throws Exception {
        Instrument instrument = new Instrument(UUID.randomUUID(), "", "piano.png");
        instruments.add(instrument);
        dataWriter.saveInstruments();
    
        ArrayList<Instrument> loadedInstruments = dataLoader.loadInstruments();
        assertTrue("Instrument with empty name should not be saved", loadedInstruments.isEmpty());
    }
    
    // Test to ensure that an instrument with an empty image file is not saved in the JSON file
    @Test
    public void testSaveInstrumentWithEmptyImageFile() throws Exception {
        Instrument instrument = new Instrument(UUID.randomUUID(), "Piano", "");
        instruments.add(instrument);
        dataWriter.saveInstruments();
    
        ArrayList<Instrument> loadedInstruments = dataLoader.loadInstruments();
        assertTrue("Instrument with empty image file should not be saved", loadedInstruments.isEmpty());
    }

    // Test to ensure that an instrument with an empty UUID is not saved in the JSON file
    @Test
    public void testSaveIntrumentWithEmptyUUID() throws Exception {
        Instrument instrument = new Instrument(null, "Piano", "piano.png"); 
        instruments.add(instrument);
        dataWriter.saveInstruments();

        ArrayList<Instrument> loadedInstruments = dataLoader.loadInstruments();
        assertTrue("Instrument with null UUID should not be saved", loadedInstruments.isEmpty());
    }
    
    @Test
    public void testWriteOneInstrument() throws Exception {
        // Add one fully populated instrument to the instrument list
        Instrument instrument = new Instrument(
            UUID.randomUUID(), 
            "Piano", 
            "piano.png"
        );
        instruments.add(instrument);
        dataWriter.saveInstruments();
        ArrayList<Instrument> loadedInstruments = dataLoader.loadInstruments();
        assertEquals("DataLoader should receive one instrument", 1, loadedInstruments.size());

        Instrument loadedInstrument = loadedInstruments.get(0);
        assertEquals("Name should match", instrument.getName(), loadedInstrument.getName());
        assertEquals("Image file should match", instrument.getImageFile(), loadedInstrument.getImageFile());
    }

    // Some instruments have special characters in their names, this test ensures that the DataWriter can handle such cases without issues
    @Test
    public void testSaveInstrumentWithSpecialCharactersInName() throws Exception {
        Instrument instrument = new Instrument(UUID.randomUUID(), "Piano@123!", "piano.png");
        instruments.add(instrument);
        dataWriter.saveInstruments();
    
        ArrayList<Instrument> loadedInstruments = dataLoader.loadInstruments();
        assertEquals("Instrument with special characters in name should be saved", 1, loadedInstruments.size());
    }

    // Duplicates should not be saved
    @Test
    public void testSaveDuplicateInstruments() throws Exception {
        UUID instrumentId = UUID.randomUUID();
        Instrument instrument1 = new Instrument(instrumentId, "Piano", "piano1.png");
        Instrument instrument2 = new Instrument(instrumentId, "Guitar", "guitar1.png"); // Same UUID as instrument1
        instruments.add(instrument1);
        instruments.add(instrument2);
        dataWriter.saveInstruments();
    
        ArrayList<Instrument> loadedInstruments = dataLoader.loadInstruments();
        assertEquals("Duplicate instruments should not be saved", 1, loadedInstruments.size());
    }

    @Test
    public void testWriteTenInstruments() throws Exception {
        // Add 10 instruments to the instrument list
        for (int i = 0; i < 10; i++) {
            instruments.add(new Instrument(UUID.randomUUID(), "Instrument" + i, "image" + i + ".png"));
        }
        dataWriter.saveInstruments();
        ArrayList<Instrument> loadedInstruments = dataLoader.loadInstruments();
        assertEquals("DataLoader should receive 10 instruments", 10, loadedInstruments.size());
    }

    private void clearJSONFile(String fileName) throws IOException {
        try (FileWriter file = new FileWriter(fileName)) {
            file.write("[]");
            file.flush();
        }
    }
}

