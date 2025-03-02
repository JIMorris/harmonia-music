package com.model;

/**
 * A file reader to load in info from json files
 */
public class FileReader extends DataConstants {
    private static FileReader instance;
    private UserList userList;
    private SongList songList;
    private InstrumentList instrumentList;

    /**
     * Makes a new FileReader
     */
    private FileReader(){
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
    public boolean loadUsers(){
        //TODO
        return false;
    }

    /**
     * Loads in Songs from json
     * @return Whether the file reading succeeded
     */
    public boolean loadSongs(){
        //TODO
        return false;
    }

    /**
     * Loads in Instruments from json
     * @return Whether the file reading succeeded
     */
    public boolean loadInstruments(){
        //TODO
        return false;
    }
}
