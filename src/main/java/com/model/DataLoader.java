package com.model;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 * A file reader to load and parse information from JSON files.
 * This class provides methods to load instruments, users, and songs
 * from JSON files and convert them into Java objects.
 * 
 * @author James Morris
 */
public class DataLoader extends DataConstants {
    private static DataLoader instance;

    /**
     * Makes a new FileReader
     */
    private DataLoader(){
        
    }

    /**
     * Gets an instance of FileReader
     * If not instance exists, a new one is created
     * 
     * @return The instance of FileReader
     */
    public static DataLoader getInstance(){
        if(instance==null){
            instance = new DataLoader();
        }
        return instance;
    }

    /**
     * Loads instrument objects from the instrument.json file.
     * This method reads the JSON file, parses the data, and converts
     * each instrument into a Java object.
     * 
     * @return Whether the file reading succeeded
     * @throws Exception If there is an error reading or parsing the file.
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
     * Loads user objects from the user.json file.
     * This method reads the JSON file, parses the data, and converts
     * each user into a Java object.
     *
     * @return A list of users loaded from the JSON file.
     * @throws Exception If there is an error reading or parsing the file.
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

            for(int i=0; i < usersJSON.size(); i++){
                JSONObject userJSON = (JSONObject)usersJSON.get(i);
                UUID id = UUID.fromString((String)userJSON.get(USER_ID));
                
                User currentUser = users.get(0);
                for(User user : users){
                    if(user.idMatch(id)){
                        currentUser = user;
                        break;
                    }
                }

                JSONArray favoriteAuthorsJSON = (JSONArray)userJSON.get(USER_FAV_AUTHS);
                ArrayList<User> favoriteAuthors = getFavoriteAuthors(favoriteAuthorsJSON, users);

                currentUser.setFavoriteAuthors(favoriteAuthors);
            }

            return users;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Retrieves a list of favorite authors for a user from a JSON array.
     * This method matches the author IDs in the JSON array with the list of users
     * and returns the corresponding User objects.
     *
     * @param favoriteAuthorsJSON The JSON array containing the IDs of favorite authors.
     * @param users The list of all users to match the IDs against.
     * @return A list of User objects representing the favorite authors.
     */
    private ArrayList<User> getFavoriteAuthors(JSONArray favoriteAuthorsJSON, ArrayList<User> users){
        ArrayList<User> favoriteAuthors = new ArrayList<>();

        for(int i=0; i<favoriteAuthorsJSON.size(); i++){
            String idString = (String)favoriteAuthorsJSON.get(i);
            UUID id = UUID.fromString(idString);
            for(User user : users){
                if(user.idMatch(id)){
                    favoriteAuthors.add(user);
                    break;
                }
            }
        }

        return favoriteAuthors;
    }


    /**
     * Loads song objects from the song.json file.
     * This method reads the JSON file, parses the data, and converts
     * each song into a Java object. It also associates favorite songs
     * with users.
     *
     * @return A list of songs loaded from the JSON file.
     * @throws Exception If there is an error reading or parsing the file.
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

                int difficulty = Math.toIntExact((long)songJSON.get(SONG_DIFFICULTY));

                ArrayList<Reaction> reactions = getReactions((JSONArray)songJSON.get(SONG_REACTIONS));

                boolean test = (boolean)songJSON.get(SONG_PUBLISHED);

                int tempo = Math.toIntExact((long)songJSON.get(SONG_TEMPO));

                String keyString = (String)songJSON.get(SONG_KEY_SIGNATURE);
                Key key = Key.fromString(keyString);

                int timeSigNum = Math.toIntExact((long)songJSON.get(SONG_TIME_SIGNATURE_NUMERATOR));
                int timeSigDen = Math.toIntExact((long)songJSON.get(SONG_TIME_SIGNATURE_DENOMINATOR));

                ArrayList<Instrument> instruments = getInstruments((JSONArray)songJSON.get(SONG_INSTRUMENTS));

                ArrayList<MeasureGroup> measures = getMeasures((JSONArray)songJSON.get(SONG_MEASURES), instruments);

                

                Song newSong = new Song(id, title, author, description, genres, difficulty, reactions, test, tempo, key, timeSigNum, timeSigDen, measures, instruments);
                songs.add(newSong);
            }
        } catch (Exception e) {
            throw e;
        }

        loadFavoriteSongs(songs);

        return songs;
    }

    /**
     * Converts a JSON array of genres into a list of Genre objects.
     *
     * @param genresJSON The JSON array containing genre strings.
     * @return A list of Genre objects parsed from the JSON array.
     */
    private ArrayList<Genre> getGenres(JSONArray genresJSON){
        ArrayList<Genre> genres = new ArrayList<>();

        for(int i=0; i < genresJSON.size(); i++){
            String genreString = (String)genresJSON.get(i);
            Genre genre = Genre.fromString(genreString);
            genres.add(genre);
        }

        return genres;
    }

    /**
     * Converts a JSON array of reactions into a list of Reaction objects.
     * Each reaction includes a rating, comment, and the user who made the comment.
     *
     * @param reactionsJSON The JSON array containing reaction data.
     * @return A list of Reaction objects parsed from the JSON array.
     */
    private ArrayList<Reaction> getReactions(JSONArray reactionsJSON){
        ArrayList<Reaction> reactions = new ArrayList<>();
        UserList userList = UserList.getInstance();

        for(int i=0; i < reactionsJSON.size(); i++){
            JSONObject reactionJSON = (JSONObject)reactionsJSON.get(i);

            int rating = Math.toIntExact((long)reactionJSON.get(SONG_RATING));
            String comment = (String)reactionJSON.get(SONG_COMMENT);
            
            String idString = (String)reactionJSON.get(SONG_COMMENTOR);
            User commentor = userList.getUser(UUID.fromString(idString));

            reactions.add(new Reaction(rating, comment, commentor));
        }

        return reactions;
    }

    /**
     * Converts a JSON array of instrument IDs into a list of Instrument objects.
     * This method retrieves the instruments from the InstrumentList singleton.
     *
     * @param instrumentsJSON The JSON array containing instrument IDs.
     * @return A list of Instrument objects corresponding to the IDs in the JSON array.
     */
    private ArrayList<Instrument> getInstruments(JSONArray instrumentsJSON){
        ArrayList<Instrument> instruments = new ArrayList<>();
        InstrumentList instrumentList = InstrumentList.getInstance();

        // //TODO remove
        // try {
        //     ArrayList<Instrument> temp = loadInstruments();
        //     for(Instrument instrument : temp){
        //         instrumentList.addInstrument(instrument);
        //     }
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        // //TODO finsh remove
       

        for(int i=0; i<instrumentsJSON.size(); i++){
            String idString = (String)instrumentsJSON.get(i);
            UUID id = UUID.fromString(idString);
            Instrument instrument = instrumentList.getInstrument(id);
            instruments.add(instrument);
        }
        return instruments;
    }

    /**
     * Converts a JSON array of measure groups into a list of MeasureGroup objects.
     * Each measure group includes a length, chord, and a map of instruments to measures.
     *
     * @param measureGroupsJSON The JSON array containing measure group data.
     * @param instruments The list of instruments to associate with the measures.
     * @return A list of MeasureGroup objects parsed from the JSON array.
     */
    private ArrayList<MeasureGroup> getMeasures(JSONArray measureGroupsJSON, ArrayList<Instrument> instruments){
        ArrayList<MeasureGroup> measureGroups = new ArrayList<>();

        for(int i=0; i<measureGroupsJSON.size(); i++){
            JSONObject measureGroupJSON = (JSONObject)measureGroupsJSON.get(i);

            int length = Math.toIntExact((long)measureGroupJSON.get(SONG_LENGTH));
            
            String chordString = (String)measureGroupJSON.get(SONG_CHORD);
            Chord chord = Chord.fromString(chordString);
           
            HashMap<Instrument, Measure> measures = new HashMap<>();
            for(int j=0; j<instruments.size(); j++){
                JSONObject measuresJSON = (JSONObject)measureGroupJSON.get(SONG_INSTRUMENTS);

                Instrument instrument = instruments.get(j);
                UUID instrumentID = instrument.getInstrumentID();

                JSONObject measureJSON = (JSONObject)measuresJSON.get(instrumentID.toString());
                Measure measure = getMeasure(measureJSON);
                
                measures.put(instrument, measure);
            }
            measureGroups.add(new MeasureGroup(length, chord, measures));
        }
        return measureGroups;
    }

    /**
     * Converts a JSON object representing a measure into a Measure object.
     * A measure includes a list of notes and optional text.
     *
     * @param measureJSON The JSON object containing measure data.
     * @return A Measure object parsed from the JSON object.
     */
    private Measure getMeasure(JSONObject measureJSON){
        JSONArray notesJSON = (JSONArray)measureJSON.get(SONG_MUSIC);
        ArrayList<Note> notes = new ArrayList<>();

        for(int i=0; i<notesJSON.size(); i++){
            JSONObject noteJSON = (JSONObject)notesJSON.get(i);
            
            int duration = Math.toIntExact((long)noteJSON.get(SONG_NOTE_LENGTH));
            
            String pitchString = (String)noteJSON.get(SONG_NOTE_PITCH);
            Pitch pitch = Pitch.fromString(pitchString);

            int octave = Math.toIntExact((long)noteJSON.get(SONG_NOTE_OCTAVE));

            Note note = new Note(duration, pitch, octave);
            notes.add(note);
        }

        String text = (String)measureJSON.get(SONG_TEXT);
        
        return new Measure(notes, text);
    }

    /**
     * Loads favorite songs for each user from the user.json file.
     * This method associates favorite songs with their respective users.
     *
     * @param songs The list of songs to associate with users.
     */
    private void loadFavoriteSongs(ArrayList<Song> songs){
        try {
            FileReader reader = new FileReader(USER_FILE_NAME);
            JSONArray usersJSON = (JSONArray)new JSONParser().parse(reader);

            for(int i=0; i<usersJSON.size(); i++){
                JSONObject userJSON = (JSONObject)usersJSON.get(i);
                UUID userID = UUID.fromString((String)userJSON.get(USER_ID));
                User currentUser = UserList.getInstance().getUser(userID);

                JSONArray favoriteSongsJSON = (JSONArray)userJSON.get(USER_FAV_SONGS);
                for(int j=0; j<favoriteSongsJSON.size(); j++){
                    UUID songID = UUID.fromString((String)favoriteSongsJSON.get(j));
                    for(Song song : songs){
                        if(song.idMatch(songID)){
                            currentUser.toggleFavoriteSong(song);
                            break;
                        }
                    }
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * The main method for testing the DataLoader functionality.
     * It loads existing data for instruments, users, and songs from their respective JSON files
     * and prints any errors encountered during the process.
     *
     * @param args 
     */
    public static void main(String[] args) {
        DataLoader dataLoader = DataLoader.getInstance();
        try {
            InstrumentList instrumentList = InstrumentList.getInstance();
            UserList userList = UserList.getInstance();
            SongList songList = SongList.getInstance();
            System.out.println("test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}