package com.model;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
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
                Key key = Key.getKey(keyString); //TODO Add method to Key

                int timeSigNum = (int)songJSON.get(SONG_TIME_SIGNATURE_NUMERATOR);
                int timeSigDen = (int)songJSON.get(SONG_TIME_SIGNATURE_DENOMINATOR);

                ArrayList<Instrument> instruments = getInstruments((JSONArray)songJSON.get(SONG_INSTRUMENTS));

                ArrayList<MeasureGroup> measures = getMeasures((JSONArray)songJSON.get(SONG_MEASURES), instruments);

                

                Song newSong = new Song(id, title, author, description, genres, difficulty, reactions, test, tempo, key, timeSigNum, timeSigDen, measures, instruments);
            }
        } catch (Exception e) {
            throw e;
        }

        return songs;
    }

    private ArrayList<Genre> getGenres(JSONArray genresJSON){
        ArrayList<Genre> genres = new ArrayList<>();

        for(int i=0; i < genresJSON.size(); i++){
            String genreString = (String)genresJSON.get(i);
            Genre genre = Genre.getGenre(genreString); //TODO Add method to Genre
            genres.add(genre);
        }

        return genres;
    }

    private ArrayList<Reaction> getReactions(JSONArray reactionsJSON){
        ArrayList<Reaction> reactions = new ArrayList<>();
        UserList userList = UserList.getInstance();

        for(int i=0; i < reactionsJSON.size(); i++){
            JSONObject reactionJSON = (JSONObject)reactionsJSON.get(i);

            int rating = (int)reactionJSON.get(SONG_RATING);
            String comment = (String)reactionJSON.get(SONG_COMMENT);
            
            String idString = (String)reactionJSON.get(SONG_COMMENTOR);
            User commentor = userList.getUser(UUID.fromString(idString));

            reactions.add(new Reaction(rating, comment, commentor));
        }

        return reactions;
    }

    private ArrayList<Instrument> getInstruments(JSONArray instrumentsJSON){
        ArrayList<Instrument> instruments = new ArrayList<>();
        InstrumentList instrumentList = InstrumentList.getInstance();

        for(int i=0; i<instrumentsJSON.size(); i++){
            String idString = (String)instrumentsJSON.get(i);
            Instrument instrument = instrumentList.getInstrument(); //TODO Add Instrument method
            instruments.add(instrument);
        }
        return instruments;
    }

    private ArrayList<MeasureGroup> getMeasures(JSONArray measureGroupsJSON, ArrayList<Instrument> instruments){
        ArrayList<MeasureGroup> measureGroups = new ArrayList<>();

        for(int i=0; i<measureGroupsJSON.size(); i++){
            JSONObject measureGroupJSON = (JSONObject)measureGroupsJSON.get(i);

            int length = (int)measureGroupJSON.get(SONG_LENGTH);
            
            String chordString = (String)measureGroupJSON.get(SONG_CHORD);
            Chord chord = Chord.fromString(chordString); //TODO Add Chord method
           
            HashMap<Instrument, Measure> measures = new HashMap<>();
            for(int j=0; j<instruments.size(); j++){
                Instrument instrument = instruments.get(j);
                UUID instrumentID = instrument.getInstrumentID();
                
                JSONObject measureJSON = (JSONObject)measureGroupJSON.get(instrumentID.toString());
                Measure measure = getMeasure(measureJSON);
                
                measures.put(instrument, measure);
            }
            measureGroups.add(new MeasureGroup(length, chord, measures)); //TODO Add MeasureGroup Constructor
        }
        return measureGroups;
    }

    private Measure getMeasure(JSONObject measureJSON){
        JSONArray notesJSON = (JSONArray)measureJSON.get(SONG_MUSIC);
        ArrayList<Note> notes = new ArrayList<>();

        for(int i=0; i<notesJSON.size(); i++){
            JSONObject noteJSON = (JSONObject)notesJSON.get(i);
            
            int duration = (int)noteJSON.get(SONG_NOTE_DURATION); //Add Constant
            
            String pitchString = (String)noteJSON.get(SONG_NOTE_PITCH); //Add Constant
            Pitch pitch = Pitch.fromString(pitchString); //Add Pitch method

            int octave = (int)noteJSON.get(SONG_NOTE_OVTAVE);

            Note note = new Note(duration, pitch, octave);
            notes.add(note);
        }

        String text = (String)measureJSON.get(SONG_TEXT);
        
        return new Measure(notes, text); //Add Measure constructor
    }
}
