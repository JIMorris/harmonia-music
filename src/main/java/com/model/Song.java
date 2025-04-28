package com.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents a song with metadata, musical structure, and associated user information.
 * A song includes attributes like title, author, description, genres, difficulty, tempo,
 * key signature, time signature, and musical content such as measures and instruments.
 * It also tracks reactions and whether it is publicly published.
 * 
 * @author Simion Cartis
 */
public class Song {
    private static final String DEFAULT_SONG_ICON_FILE = "defaultSongIcon.png";

    private UUID songID;
    private String title;
    private User author;
    private String description;
    private ArrayList<Genre> genres;
    private int difficulty;
    private ArrayList<Reaction> reactions;
    private boolean published;
    private String iconFile;

    private int tempo;
    private Key keySignature;
    private int timeSignatureNum;
    private int timeSignatureDen;
    private ArrayList<MeasureGroup> measureGroups;
    private ArrayList<Instrument> instruments;

    /**
     * Creates a new empty song with basic musical properties.
     *
     * @param title Title of the song
     * @param author Author of the song
     * @param description Description of the song
     * @param genres List of genres for this song
     * @param difficulty Difficulty rating from 1 to 5
     * @param tempo Tempo in beats per minute (30-400)
     * @param keySignature Key signature of the song
     * @param timeSignatureNum Top number of the time signature
     * @param timeSignatureDen Bottom number of the time signature
     * @param defaultInstrument the default instrument that this song will be created with
     */
    public Song(String title, User author, String description, ArrayList<Genre> genres, int difficulty,
                int tempo, Key keySignature, int timeSignatureNum, int timeSignatureDen, Instrument defaultInstrument) {
        this.songID = UUID.randomUUID();
        this.title = title;
        this.author = author;
        this.description = description;
        this.genres = genres;
        this.difficulty = difficulty;
        this.reactions = new ArrayList<>();
        this.published = false;
        this.tempo = tempo;
        this.keySignature = keySignature;
        this.timeSignatureNum = timeSignatureNum;
        this.timeSignatureDen = timeSignatureDen;
        this.instruments = new ArrayList<>();
        this.measureGroups = new ArrayList<>();
        this.instruments.add(defaultInstrument);
        this.measureGroups.add(new MeasureGroup(timeSignatureNum, keySignature.rootChord, instruments));
        this.iconFile = DEFAULT_SONG_ICON_FILE;
    }

    /**
     * Constructs a copy of an existing song with a new author.
     *
     * @param song Song to copy
     * @param author New author for the copied song
     */
    public Song(Song song, User author) {
        this.songID = UUID.randomUUID();
        this.title = song.getTitle();
        this.author = author;
        this.description = song.getDescription();
        this.genres = (ArrayList<Genre>) song.getGenres().clone();
        this.difficulty = song.getDifficulty();
        this.reactions = new ArrayList<>();
        this.published = false;
        this.tempo = song.getTempo();
        this.keySignature = song.getKeySignature();
        this.timeSignatureNum = song.getTimeSignatureNum();
        this.timeSignatureDen = song.getTimeSignatureDen();
        this.measureGroups = copyMeasureGroups(song.getMeasureGroups());
        this.instruments = (ArrayList<Instrument>) song.getInstruments().clone();
        this.iconFile = DEFAULT_SONG_ICON_FILE;
    }

    /**
     * Constructs a song from full data, used for loading from persistent storage.
     *
     * @param id UUID of the song
     * @param title Title of the song
     * @param author Author of the song
     * @param description Description
     * @param genres List of genres
     * @param difficulty Difficulty rating (1-5)
     * @param reactions List of user reactions
     * @param published Whether the song is public
     * @param tempo Tempo in BPM
     * @param keySignature Key of the song
     * @param timeSignatureNum Top number of time signature
     * @param timeSignatureDen Bottom number of time signature
     * @param measures List of measure groups
     * @param instruments List of instruments
     */
    public Song(UUID id, String title, User author, String description, ArrayList<Genre> genres,
                int difficulty, ArrayList<Reaction> reactions, boolean published, int tempo,
                Key keySignature, int timeSignatureNum, int timeSignatureDen,
                ArrayList<MeasureGroup> measures, ArrayList<Instrument> instruments, String iconFile) {
        this.songID = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.genres = genres;
        this.difficulty = difficulty;
        this.reactions = reactions;
        this.published = published;
        this.tempo = tempo;
        this.keySignature = keySignature;
        this.timeSignatureNum = timeSignatureNum;
        this.timeSignatureDen = timeSignatureDen;
        this.measureGroups = measures;
        this.instruments = instruments;
        this.iconFile = iconFile;
    }

    /**
     * Returns the ID of the song
     * 
     * @return UUID of the song
     */
    public UUID getSongID() { 
        return this.songID; 
    }

    /**
     * Returns the title of the song
     * 
     * @return Title of the song
     */
    public String getTitle() { 
        return title; 
    }

    /**
     * Returns the author of the song
     * 
     * @return Author of the song
     */
    public User getAuthor() { 
        return author; 
    }

    /**
     * Returns the description of the song
     * 
     * @return Description of the song
     */
    public String getDescription() { 
        return description; 
    }

    /**
     * Returns the genres of the song
     * 
     * @return List of genres
     */
    public ArrayList<Genre> getGenres() { 
        return genres; 
    }

    public ArrayList<String> getGenreLabels() {
        ArrayList<String> labels = new ArrayList<String>();
        for (Genre genre: genres) {
            labels.add(genre.toString());
        }
        return labels;
    }

    public String viewGenres() {
        String toReturn = null;
        for (Genre genre: genres) {
            toReturn += genre.label + " ";
        }
        return toReturn;
    }

    /**
     * Returns the difficuty of the song
     * 
     * @return Difficulty rating (1-5)
     */
    public int getDifficulty() { 
        return difficulty; 
    }

    /**
     * Returns the reactions to this song
     * 
     * @return List of reactions
     */
    public ArrayList<Reaction> getReactions() { 
        return reactions; 
    }

    /**
     * Returns the icon for this song
     * 
     * @return File path for icon
     */
    public String getIconFilePath(){
        return DataConstants.DATA_FOLDER + iconFile;
    }

    /**
     * Returns whether this song is public
     * 
     * @return True if published, false otherwise
     */
    public boolean isPublished() { 
        return published; 
    }

    public void changePublish() {
        published = !published;
    }

    /**
     * Returns the tempo of this song
     * 
     * @return Tempo in BPM
     */
    public int getTempo() { 
        return tempo; 
    }

    /**
     * Returns the key of this song
     * 
     * @return Key signature of the song
     */
    public Key getKeySignature() { 
        return keySignature; 
    }

    /**
     * Returns the top number of this songs time signature
     * 
     * @return Time signature numerator
     */
    public int getTimeSignatureNum() { 
        return timeSignatureNum; 
    }

    /**
     * Returns the bottom number of this songs time signature
     * 
     * @return Time signature denominator
     */
    public int getTimeSignatureDen() { 
        return timeSignatureDen; 
    }

    /**
     * Returns this songs MeasureGroups
     * 
     * @return List of measure groups
     */
    public ArrayList<MeasureGroup> getMeasureGroups() { 
        return measureGroups; 
    }

    /**
     * Returns this songs instruments
     * 
     * @return List of instruments
     */
    public ArrayList<Instrument> getInstruments() { 
        return instruments; 
    }

    /**
     * Sets the icon file for this song
     * 
     * @param iconFile File for the icon (excluding path)
     */
    public void setIconFile(String iconFile){
        this.iconFile = iconFile;
    }

    /**
     * Sets the tempo of the song (bewtween 30 and 400 bpm)
     * 
     * @param tempo Tempo to set the song to
     * @throws Exception If the tempo isn't between 30 and 400
     */
    public void setTempo(int tempo) throws Exception{
        if(tempo<30 || tempo>400)
            throw new Exception("Tempo must be between 30 and 400");
        this.tempo = tempo;
    }

    /**
     * Returns the measures for the given instrument
     * 
     * @param instrument Instrument to retrieve measures from
     * @return Measures from the given instrument
     */
    public ArrayList<Measure> getMeasures(Instrument instrument){
        ArrayList<Measure> measures = new ArrayList<>();
        for(MeasureGroup measureGroup : measureGroups){
            measures.add(measureGroup.getMeasure(instrument));
        }
        return measures;
    }

    /**
     * Gets the MeasureGroup that the given Measure is a part of
     * 
     * @param measure Measure to find MeasureGroup of
     * @return MeasureGroup that contains Measure
     */
    public MeasureGroup getMeasureGroup(Measure measure){
        for(MeasureGroup measureGroup : measureGroups){
            for(Instrument instrument : instruments){
                if(measureGroup.getMeasure(instrument) == measure)
                    return measureGroup;
            }
        }
        return null;
    }

    /**
     * Gets the MeasureGroup that the given Note is a part of
     * 
     * @param measure Note to find MeasureGroup of
     * @return MeasureGroup that contains Note
     */
    public MeasureGroup getMeasureGroup(Note note){
        for(MeasureGroup measureGroup : measureGroups){
            for(Instrument instrument : instruments){
                for(Note checkNote : measureGroup.getMeasure(instrument).getNotes()){
                    if(checkNote == note)
                        return measureGroup;
                }
            }
        }
        return null;
    }

    /**
     * Returns a list of IDs for the Instruments in this song
     * 
     * @return IDs of Instrumnets in this song
     */
    public ArrayList<UUID> getInstrumentIDs() {
        ArrayList<UUID> instrumentIDs = new ArrayList<>();
        for (Instrument instrument : instruments) {
            instrumentIDs.add(instrument.getInstrumentID());
        }
        return instrumentIDs;
    }

    /**
     * Checks if the song ID matches the given UUID.
     *
     * @param songID UUID to compare
     * @return true if matches, false otherwise
     */
    public boolean idMatch(UUID songID) {
        return this.songID.equals(songID);
    }

    /**
     * Adds a new reaction to the song.
     *
     * @param rating Reaction rating
     * @param comment Reaction comment
     * @param author Author of the reaction
     * @return The created Reaction
     */
    public Reaction addReaction(int rating, String comment, User author) {
        Reaction reaction = new Reaction(rating, comment, author);
        this.reactions.add(reaction);
        return reaction;
    }

    /**
     * Removes a reaction from the song.
     *
     * @param reaction Reaction to remove
     * @throws Exception if the reaction doesn't belong to the current user
     */
    public void removeReaction(Reaction reaction) throws Exception {
        if(reaction.getAuthor() != UserList.getInstance().getCurrentUser())
            throw new Exception("You can only delete your own reaction");
        reactions.remove(reaction);
    }

    public int getAverageRating() {
        if (reactions.size() == 0) 
            return 0;
        int rating = 0;
        for (Reaction reaction: reactions) {
            rating += reaction.getRating();
        }
        return rating/reactions.size();
    }

    /**
     * Inserts a measure after the given MeasureGroup
     * 
     * @param measureGroup MeasureGroup to insert new MeasureGroup after
     */
    public void insertMeasure(MeasureGroup measureGroup){
        int index = measureGroups.indexOf(measureGroup);
        measureGroups.add(index+1, new MeasureGroup(timeSignatureNum, keySignature.rootChord, instruments));
    }

    /**
     * Removes the given measure
     * 
     * @param measureGroup MeasureGroup to remove
     * @throws Exception if only one measure remains
     */
    public void removeMeasure(MeasureGroup measureGroup) throws Exception{
        if(measureGroups.size()<=1)
            throw new Exception("Song cannot have 0 measures");
        measureGroups.remove(measureGroup);
    }

     /**
     * Sets a rest to a note at root pitch
     * 
     * @param note Note to set to root pitch
     */
    public void insertNote(Note note){
        if(note.getPitch()!=Pitch.REST)
            return;
        note.setPitch(keySignature.getRootPitch());
        note.setOctave(4);
    }

    /**
     * Moves the specified note up one scale tone
     *
     * @param note Note to move
     * @throws Exception If note is at the upper pitch boundary
     */
    public void noteUp(Note note) throws Exception{
        note.up(this.keySignature);
    }

    /**
     * Moves the specified note down one scale tone
     *
     * @param note Note to move
     * @throws Exception If note is at the lower pitch boundary
     */
    public void noteDown(Note note) throws Exception{
        note.down(this.keySignature);
    }

    /**
     * Adds an instrument to all measure groups
     *
     * @param instrument Instrument to add
     */
    public void addInstrument(Instrument instrument) {
        instruments.add(instrument);
        for(MeasureGroup measureGroup : measureGroups){
            measureGroup.addMeasure(instrument);
        }
    }

    /**
     * Removes an instrument from all measure groups
     *
     * @param instrument Instrument to remove
     * @throws Exception If only one instrument remains
     */
    public void removeInstrument(Instrument instrument) throws Exception {
        if(instruments.size()<=1)
            throw new Exception("Song must have at least 1 instrument");
        instruments.remove(instrument);
        for(MeasureGroup measureGroup : measureGroups){
            measureGroup.removeMeasure(instrument);
        }
    }
    /**
     * Creates a clone of the measure groups
     *
     * @param measureGroups Original list of measure groups
     * @return Copied list of measure groups
     */
    private ArrayList<MeasureGroup> copyMeasureGroups(ArrayList<MeasureGroup> measureGroups){
        ArrayList<MeasureGroup> copy = new ArrayList<>();
        for(MeasureGroup measureGroup : measureGroups){
            copy.add(new MeasureGroup(measureGroup));
        }
        return copy;
    }

    public void setDifficulty(int difficulty) {
        if (difficulty >= 1 && difficulty <= 5) {
            this.difficulty = difficulty;
        }
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
