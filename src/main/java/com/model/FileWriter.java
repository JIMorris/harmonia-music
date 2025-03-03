package com.model;

/**
 * A file reader to write and add info for json files
 * 
 * @author kcrase
 */
public class FileWriter extends DataConstants {
    private static FileWriter instance;
    private UserList userList;
    private SongList songList;
    private InstrumentList instrumentList;


    private FileWriter(){
        //TODO
    }

    /**
     * Gets an instance of FileReader
     * @return The instance of FileReader
     */
    public static FileWriter getInstance(){
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
    public boolean saveInstruments(){
        //TODO
        return false;
    }
}