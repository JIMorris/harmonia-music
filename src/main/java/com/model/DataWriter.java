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
     * Makes a new DataWriter
     */
    private DataWriter(){
        //TODO
    }

    /**
     * Gets an instance of DataReader
     * @return The instance of DataReader
     */
    public static DataWriter getInstance(){
        if(instance==null){
            instance = new DataWriter();
        }
        return instance;
    }

    /**
     * Saves Users to user json file
     * @return Whether the file saving was successful
     */
    public ArrayList<User> saveUsers() throws Exception{
        //Hardcoding a User object
        
        //Implementation with UserList
        UserList userList = UserList.getInstance();
        ArrayList<User> users = userList.getUsers();
        JSONArray usersJSON = new JSONArray();

        //Creating all the json user objects
        for(int i=0; i < users.size(); i++) {
            usersJSON.add(getUserJSON(users.get(i)));
        }

        //Writing user JSON file
        try (FileWriter file = new FileWriter(USER_TEMP_FILE_NAME)) {
            file.write(usersJSON.toJSONString());
            file.flush();
            return users;
        } catch (IOException e) {
            throw e;
        }
    }

    public static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();
        userDetails.put(USER_ID, user.getUserID().toString());
        userDetails.put(USER_USERNAME, user.getUsername());
        userDetails.put(USER_PASSWORD, user.getPassword());
        userDetails.put(USER_FIRST_NAME, user.getFirstName());
        userDetails.put(USER_LAST_NAME, user.getLastName());
        userDetails.put(USER_FAV_SONGS, user.getFavSongs());
        
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
     * Saves Instruments to instrument json file
     * @return Whether the file saving was successful
     */
    public ArrayList<Instrument> saveInstruments() throws Exception{
        //Hardcoded Instrument
        //ArrayList<Instrument> instruments = new ArrayList<>();
        //instruments.add(new Instrument(UUID.fromString("ff79cdfd-424c-4026-9a5b-43b9a4653228"), "Piano", "piano.png"));
        
        //Implementation with InstrumentList
        InstrumentList instrumentList = InstrumentList.getInstance();
        ArrayList<Instrument> instruments = instrumentList.getInstruments();
        JSONArray instrumentsJSON = new JSONArray();

        //Creating all the json instrument objects
        for(int i=0; i < instruments.size(); i++) {
            instrumentsJSON.add(getInstrumentJSON(instruments.get(i)));
        }

        //Writing instrument JSON file
        try (FileWriter file = new FileWriter(INSTRUMENT_TEMP_FILE_NAME)) {
            file.write(instrumentsJSON.toJSONString());
            file.flush();
            return instruments;
        } catch (IOException e) {
            throw e;
        }
    }

    public static JSONObject getInstrumentJSON(Instrument instrument) {
        JSONObject instrumentDetails = new JSONObject();
        instrumentDetails.put(INSTRUMENT_ID, instrument.getInstrumentID().toString());
        instrumentDetails.put(INSTRUMENT_NAME, instrument.getName());
        instrumentDetails.put(INSTRUMENT_IMAGE_FILE, instrument.getImageFile());
        return instrumentDetails;
    }

    /**
     * Saves Songs to song json file
     * @return Whether the file saving was successful
     */
    public ArrayList<Song> saveSongs() throws Exception {
        //Hardcoded Song
        //ArrayList<Song> songs = new ArrayList<>();
        //songs.add(new Song(UUID.fromString("ff79cdfd-424c-4026-9a5b-43b9a4653228"), "Song Title", "Song Author", "Song Description", new ArrayList<String>(), 1, new ArrayList<Reaction>(), 0, new ArrayList<Comment>(), true, 120, "C", 4, 4, new ArrayList<Measure>(), 4, new Chord(), new ArrayList<Instrument>(), new ArrayList<Music>(), new ArrayList<Text>()));
        
        //Implementation with SongList
        SongList songList = SongList.getInstance();
        ArrayList<Song> songs = songList.getSongs();
        JSONArray songsJSON = new JSONArray();

        //Creating all the json song objects
        for(int i=0; i < songs.size(); i++) {
            songsJSON.add(getSongJSON(songs.get(i)));
        }

        //Writing song JSON file
        try (FileWriter file = new FileWriter(SONG_TEMP_FILE_NAME)) {
            file.write(songsJSON.toJSONString());
            file.flush();
            return songs;
        } catch (IOException e) {
            throw e;
        }
    }

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

        // Convert genres to a list of strings
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
        
        // Convert instruments to a list of strings
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
                noteDetails.put("length", note.getLength());
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

    public static void main(String[] args) {
        DataWriter writer = DataWriter.getInstance();
        DataLoader loader = DataLoader.getInstance();
        try {
            // Load existing data
            InstrumentList instrumentList = InstrumentList.getInstance();
            UserList userList = UserList.getInstance();
            SongList songList = SongList.getInstance();

            instrumentList.setInstruments(loader.loadInstruments());
            userList.setUsers(loader.loadUsers());
            songList.setSongs(loader.loadSongs());
            writer.saveInstruments();
            writer.saveUsers();
            writer.saveSongs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}