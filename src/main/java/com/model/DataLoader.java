package com.model;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import javax.sound.midi.Instrument;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 * A file reader to load in info from json files
 */
public class DataLoader extends DataConstants {
    private static FileReader instance;
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
                String firstName = (String)userJSON.get(USER_FIRST_NAME);
                String lastName = (String)userJSON.get(USER_LAST_NAME);
                
                users.add(new User(id, username, firstName, lastName));
            }

            return users;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Loads in Songs from json
     * @return Whether the file reading succeeded
     */
    public ArrayList<Song> loadSongs(){
        //TODO
        return null;
    }

    /**
     * Loads in Instruments from json
     * @return Whether the file reading succeeded
     */
    public ArrayList<Instrument> loadInstruments(){
        ArrayList<Insturment> instruments = new ArrayList<>();

        try {
            FileReader reader = new FileReader(USER_FILE_NAME);
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
}
