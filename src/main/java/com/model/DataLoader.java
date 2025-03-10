package com.model;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 * A file reader to load in info from json files
 */
public class DataLoader extends DataConstants {
    private static DataLoader instance;
    private UserList userList;
    private SongList songList;
    private InstrumentList instrumentList;

    /**
     * Makes a new FileReader
     */
    private DataLoader(){
        //TODO
    }

    /**
     * Gets an instance of FileReader
     * @return The instance of FileReader
     */
    public static FileReader getInstance(){
        //TODO
        return null;
    }

    /**
     * Loads in Users from json
     * @return Whether the file reading succeeded
     */
    public ArrayList<User> loadUsers() throws Exception{
        ArrayList<User> users = new ArrayList<>();

        try {
            FileReader reader = new FileReader(USER_FILE_NAME);
            JSONArray usersJSON = (JSONArray)new JSONParser().parse(reader);

            for(int i=0; i < usersJSON.size(); i++){
                JSONObject userJSON = (JSONObject)usersJSON.get(i);
                UUID id = UUID.fromString((String)userJSON.get(USER_ID));
                String username = (String)userJSON.get(USER_USERNAME);
                String password = (String)userJSON.get(USER_PASSWORD);
                String firstName = (String)userJSON.get(USER_FIRST_NAME);
                String lastName = (String)userJSON.get(USER_LAST_NAME);
                
                users.add(new User(username, password, firstName, lastName, id));
            }

            return users;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Loads in Instruments from json
     * @return Whether the file reading succeeded
     */
    public ArrayList<Instrument> loadInstruments() throws Exception{
        ArrayList<Instrument> instruments = new ArrayList<>();

        try {
            FileReader reader = new FileReader(INSTRUMENT_FILE_NAME);
            JSONArray instrumentsJSON = (JSONArray)new JSONParser().parse(reader);

            for(int i=0; i < instrumentsJSON.size(); i++){
                JSONObject instrumentJSON = (JSONObject)instrumentsJSON.get(i);
                UUID id = UUID.fromString((String)instrumentJSON.get(INSTRUMENT_ID));
                String name = (String)instrumentJSON.get(INSTRUMENT_NAME);
                String imageFile = (String)instrumentJSON.get(INSTRUMENT_IMAGE_FILE);
                
                instruments.add(new Instrument(id, name, imageFile));
            }

            return instruments;
        } catch (Exception e) {
            throw e;
        }
    }


        /**
     * Loads in Songs from json
     * @return Whether the file reading succeeded
     */
    public ArrayList<Song> loadSongs() throws Exception{
        UserList userList = UserList.getInstance();
        ArrayList<Song> songs = new ArrayList<>();

        try {
            FileReader reader = new FileReader(SONG_FILE_NAME);
            JSONArray songsJSON = (JSONArray)new JSONParser().parse(reader);

            for(int i=0; i < songsJSON.size(); i++){
                JSONObject songJSON = (JSONObject)songsJSON.get(i);

                String idString = (String)songJSON.get(SONG_ID);
                UUID id = UUID.fromString(idString);

                String title = (String)songJSON.get(SONG_TITLE);

                String autorString = (String)songJSON.get(SONG_AUTHOR);
                UUID authorID = UUID.fromString(autorString);
                User author = userList.getUser(authorID);

                String description = (String)songJSON.get(SONG_DESCRIPTION);

                ArrayList<Genre> genres = getGenres((JSONArray)songJSON.get(SONG_GENRES));

                int difficulty = (int)songJSON.get(SONG_DIFFICULTY);

                ArrayList<Reaction> reactions = getReactions((JSONArray)songJSON.get(SONG_REACTIONS));

                boolean test = (boolean)songJSON.get(SONG_PUBLISHED);

                int tempo = (int)songJSON.get(SONG_TEMPO);

                String keyString = (String)songJSON.get(SONG_KEY_SIGNATURE);
                // Key key = Key.getKey(keyString);
                Key key = Key.C_MAJOR;

                int timeSigNum = (int)songJSON.get(SONG_TIME_SIGNATURE_NUMERATOR);
                int timeSigDen = (int)songJSON.get(SONG_TIME_SIGNATURE_DENOMINATOR);

                ArrayList<MeasureGroup> measures = getMeasures((JSONArray)songJSON.get(SONG_MEASURES));

                ArrayList<Instrument> instruments = getInstruments((JSONArray)songJSON.get(SONG_INSTRUMENTS));

                Song newSong = new Song(id, title, author, description, genres, difficulty, reactions, test, tempo, key, timeSigNum, timeSigDen, measures, instruments);
            }
        } catch (Exception e) {
            throw e;
        }

        return songs;
    }

    private ArrayList<Genre> getGenres(JSONArray genresJSON){
        //TODO
        return null;
    }

    private ArrayList<Reaction> getReactions(JSONArray reactionsJSON){
        //TODO
        return null;
    }

    private ArrayList<MeasureGroup> getMeasures(JSONArray measureGroupsJSON){
        //TODO
        return null;
    }

    private ArrayList<Instrument> getInstruments(JSONArray instrumentsJSON){
        //TODO
        return null;
    }
}
