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
    private UserList userList;
    private SongList songList;
    private InstrumentList instrumentList;

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
        //TODO
        return null;
    }

    /**
     * Saves Users to user json file
     * @return Whether the file saving was successful
     */
    public boolean saveUsers(){
        //TODO
        return false;
    }

    /**
     * Saves Songs to song json file
     * @return Whether the file saving was successful
     */
    public boolean saveSongs(){
        //TODO
        return false;
    }

    /**
     * Saves Instruments to instrument json file
     * @return Whether the file saving was successful
     */
    public ArrayList<Instrument> saveInstruments() throws Exception{
        //Instrument instrument = Instrument
        //ArrayList<Instrument> instruments = new ArrayList<>();
        //Hardcoded Instruments
        ArrayList<Instrument> instrumentList = new ArrayList();
		instrumentList.add(new Instrument("2309553344", "Piano", "piano.png",));

        JSONArray jsonInstruments = new JSONArray();
        for(int i=0; i < instruments.size(); i++) {
            jsonInstruments.add(getInstrumentJSON(instruments.get(i)));
        }

        try (FileWriter file = new FileWriter(INSTRUMENT_TEMP_FILE_NAME)) {

            file.write(jsonInstruments.toJSONString());
            file.flush();

            return instruments;
        } catch (Exception e) {
            throw e;
        }

        public static JSONObject getInstrumentJSON(Instrument instrument) {
            JSONObject instrumentDetails = new JSONObject();
            instrumentDetails.put(INSTRUMENT_ID, instrument.getUuid());
            instrumentDetails.put(INSTRUMENT_NAME, instrument.getName());
            instrumentDetails.put(INSTRUMENT_IMAGE_FILE, instrument.getImageFile());
            
            return instrumentDetails;
        }

        public static void main(String[] args) {
            DataWriter.saveInstruments();
        }

    }
}