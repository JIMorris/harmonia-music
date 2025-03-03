package com.model;

public class FileWriter extends DataConstants {
    private static FileWriter instance;
    private UserList userList;
    private SongList songList;
    private InstrumentList instrumentList;

    private FileWriter(){
        //TODO
    }

    public static FileWriter getInstance(){
        //TODO
        return null;
    }

    public boolean saveUsers(){
        //TODO
        return false;
    }

    public boolean saveSongs(){
        //TODO
        return false;
    }

    public boolean saveInstruments(){
        //TODO
        return false;
    }
}