package com.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * A file reader to write and add info for json files
 *
 * @author kcrase
 */
public class DataWriter extends DataConstants {
    private static DataWriter instance;

    /**
     * Gets an instance of DataReader
     *
     * @return The instance of DataReader or create an instance if there is none
     */
    public static DataWriter getInstance() {
        if (instance == null) {
            instance = new DataWriter();
        }
        return instance;
    }

    /**
     * Saves user objects to the user.json file.
     * This method retrieves the list of user from the UserList singleton,
     * converts each instrument into a JSON object, and writes them to the file.
     *
     * @return The list of users saved in the system
     * @throws IOException If there is an error writing to the file
     */
    public ArrayList<User> saveUsers() throws Exception {
        //Implementation with UserList
        UserList userList = UserList.getInstance();
        ArrayList<User> users = userList.getUsers();
        JSONArray usersJSON = new JSONArray();

        //Creating all the json user objects
        for (int i = 0; i < users.size(); i++) {
            usersJSON.add(getUserJSON(users.get(i)));
        }

        //Writing user JSON file
        try (FileWriter file = new FileWriter(USER_FILE_NAME)) {
            file.write(usersJSON.toJSONString());
            file.flush();
            return users;
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Converts a user object to a JSON object for saving to the user.json file
     *
     * @param user The user to convert
     * @return The JSON object representing the user object
     */
    public static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();
        userDetails.put(USER_ID, user.getUserID().toString());
        userDetails.put(USER_USERNAME, user.getUsername());
        userDetails.put(USER_PASSWORD, user.getPassword());
        userDetails.put(USER_FIRST_NAME, user.getFirstName());
        userDetails.put(USER_LAST_NAME, user.getLastName());
        userDetails.put(USER_FAV_SONGS, user.getFavSongs());
        userDetails.put(USER_ICON_FILE, user.getIconFilePath().replace(DATA_FOLDER, ""));

        // Convert favorite songs to a list of strings
        ArrayList<String> favSongIDs = new ArrayList<>();
        for (Song song : user.getFavSongs()) {
            favSongIDs.add(song.getSongID().toString());
        }
        userDetails.put(USER_FAV_SONGS, favSongIDs);

        // Convert favorite authors to a list of strings
        ArrayList<String> favAuthIDs = new ArrayList<>();
        for (User author : user.getFavAuthors()) {
            favAuthIDs.add(author.getUserID().toString());
        }
        userDetails.put(USER_FAV_AUTHS, favAuthIDs);
        return userDetails;
    }

    /**
     * Saves instrument objects to the instrument.json file.
     * This method retrieves the list of instruments from the InstrumentList singleton,
     * converts each instrument into a JSON object, and writes them to the file.
     *
     * @return The list of instruments saved in the system
     * @throws IOException If there is an error writing to the file
     */
    public ArrayList<Instrument> saveInstruments() throws Exception {
        //Implementation with InstrumentList
        InstrumentList instrumentList = InstrumentList.getInstance();
        ArrayList<Instrument> instruments = instrumentList.getInstruments();
        JSONArray instrumentsJSON = new JSONArray();

        //Creating all the json instrument objects
        for (int i = 0; i < instruments.size(); i++) {
            instrumentsJSON.add(getInstrumentJSON(instruments.get(i)));
        }

        //Writing instrument JSON file
        try (FileWriter file = new FileWriter(INSTRUMENT_FILE_NAME)) {
            file.write(instrumentsJSON.toJSONString());
            file.flush();
            return instruments;
        } catch (IOException e) {
            throw e;
        }
    }
    /**
     * Converts an instrument object to a JSON object for saving to the instrument.json file
     *
     * @param instrument The instrument to convert
     * @return The JSON object representing the instrument object
     */
    public static JSONObject getInstrumentJSON(Instrument instrument) {
        JSONObject instrumentDetails = new JSONObject();
        instrumentDetails.put(INSTRUMENT_ID, instrument.getInstrumentID().toString());
        instrumentDetails.put(INSTRUMENT_NAME, instrument.getName());
        instrumentDetails.put(INSTRUMENT_IMAGE_FILE, instrument.getImageFile());
        return instrumentDetails;
    }

    /**
     * Saves song objects to the instrument.json file.
     * This method retrieves the list of songs from the SongList singleton,
     * converts each song into a JSON object, and writes them to the file.
     *
     * @return The list of songs saved in the system
     * @throws IOException If there is an error writing to the file
     */
    public ArrayList<Song> saveSongs() throws Exception {
        //Implementation with SongList
        SongList songList = SongList.getInstance();
        ArrayList<Song> songs = songList.getSongs();
        JSONArray songsJSON = new JSONArray();

        //Creating all the json song objects
        for (int i = 0; i < songs.size(); i++) {
            songsJSON.add(getSongJSON(songs.get(i)));
        }

        //Writing song JSON file
        try (FileWriter file = new FileWriter(SONG_FILE_NAME)) {
            file.write(songsJSON.toJSONString());
            file.flush();
            return songs;
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Converts a song object to a JSON object
     *
     * @param song The song to convert
     * @return The JSON object representing the song object
     */
    public static JSONObject getSongJSON(Song song) {
        JSONObject songDetails = new JSONObject();
        songDetails.put(SONG_ID, song.getSongID().toString());
        songDetails.put(SONG_TITLE, song.getTitle());
        songDetails.put(SONG_AUTHOR, song.getAuthor().getUserID().toString());
        songDetails.put(SONG_DESCRIPTION, song.getDescription());
        songDetails.put(SONG_DIFFICULTY, song.getDifficulty());
        songDetails.put(SONG_PUBLISHED, song.isPublished());
        songDetails.put(SONG_TEMPO, song.getTempo());
        songDetails.put(SONG_KEY_SIGNATURE, song.getKeySignature().toString());
        songDetails.put(SONG_TIME_SIGNATURE_NUMERATOR, song.getTimeSignatureNum());
        songDetails.put(SONG_TIME_SIGNATURE_DENOMINATOR, song.getTimeSignatureDen());
        songDetails.put(SONG_ICON_FILE, song.getIconFilePath().replace(DATA_FOLDER, ""));

        // Convert genre ENUMs to a list of strings
        ArrayList<String> genres = new ArrayList<>();
        for (Genre genre : song.getGenres()) {
            if (genre != null) {
                genres.add(genre.toString());
            }
        }
        songDetails.put(SONG_GENRES, genres);

        // Convert reactions to a list of JSON objects
        JSONArray reactionsJSON = new JSONArray();
        for (Reaction reaction : song.getReactions()) {
            JSONObject reactionDetails = new JSONObject();
            reactionDetails.put("rating", reaction.getRating());
            reactionDetails.put("comment", reaction.getComment());
            reactionDetails.put("commentor", reaction.getAuthor().getUserID().toString());
            reactionsJSON.add(reactionDetails);
        }
        songDetails.put(SONG_REACTIONS, reactionsJSON);

        // Convert instrument UUIDs to a list of strings
        ArrayList<String> instrumentsIDs = new ArrayList<>();
        for (UUID instrumentID : song.getInstrumentIDs()) {
            instrumentsIDs.add(instrumentID.toString());
        }
        songDetails.put(SONG_INSTRUMENTS, instrumentsIDs);

        // Convert measures to a list of JSON objects
        JSONArray measuresJSON = new JSONArray();
        for (MeasureGroup measureGroup : song.getMeasureGroups()) {
            JSONObject measureDetails = new JSONObject();
            measureDetails.put("length", measureGroup.getLength());
            measureDetails.put("chord", measureGroup.getChord().label);

            JSONObject instrumentsJSON = new JSONObject();
            for (Instrument instrument : measureGroup.getMeasures().keySet()) {
                Measure measure = measureGroup.getMeasures().get(instrument);
                JSONObject instrumentDetails = new JSONObject();

                JSONArray musicJSON = new JSONArray();
                for (Note note : measure.getNotes()) {
                    JSONObject noteDetails = new JSONObject();
                    noteDetails.put("length", note.getDuration());
                    noteDetails.put("pitch", note.getLabel());
                    noteDetails.put("octave", note.getOctave());
                    musicJSON.add(noteDetails);
                }
                instrumentDetails.put("music", musicJSON);
                instrumentDetails.put("text", measure.getText());
                instrumentsJSON.put(instrument.getInstrumentID().toString(), instrumentDetails);
            }
            measureDetails.put("instruments", instrumentsJSON);
            measuresJSON.add(measureDetails);
        }
        songDetails.put(SONG_MEASURES, measuresJSON);
        return songDetails;
    }

    // /**
    // * The main method for testing the DataWriter functionality.
    // * It loads existing data for instruments, users, and songs from their respective JSON files,
    // * saves the data to temporary files, and prints any errors encountered during the process.
    // *
    // * @param args
    // */
    // public static void main(String[] args) {
    //     DataWriter writer = DataWriter.getInstance();
    //     DataLoader loader = DataLoader.getInstance();
    //     try {
    //         // Load existing data
    //         InstrumentList instrumentList = InstrumentList.getInstance();
    //         UserList userList = UserList.getInstance();
    //         SongList songList = SongList.getInstance();

    //         instrumentList.setInstruments(loader.loadInstruments());
    //         userList.setUsers(loader.loadUsers());
    //         songList.setSongs(loader.loadSongs());
    //         writer.saveInstruments();
    //         writer.saveUsers();
    //         writer.saveSongs();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }
}
