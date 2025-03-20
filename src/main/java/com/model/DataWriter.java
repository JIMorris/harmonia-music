package com.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
        ArrayList<User> favAuths = user.getFavAuthors();
        ArrayList<String> favAuthIDs = new ArrayList<>();
        
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
        //I am not loading instruments from the InstrumentList class
        //I think the logic is not added so I cannot write the current information from instrumentjson

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
    public boolean saveSongs(){
        //TODO
        return false;
    }

    public static void main(String[] args) {
        DataWriter writer = DataWriter.getInstance();
        try {
            writer.saveUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}