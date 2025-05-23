package com.model;

/**
 * Provides data constants for the data writer and loader
 * 
 * @author James Morris
 */
public class DataConstants {
    public static final String DATA_FOLDER = "src/main/java/data/";
    public static final String TEST_DATA_FOLDER = "src/test/java/data/";

    public static final String SONG_FILE_NAME = isJUnitTest() ? TEST_DATA_FOLDER + "song.json" : DATA_FOLDER + "song.json";
    public static final String USER_FILE_NAME = isJUnitTest() ? TEST_DATA_FOLDER + "user.json" : DATA_FOLDER + "user.json";
    public static final String INSTRUMENT_FILE_NAME = isJUnitTest() ? TEST_DATA_FOLDER + "instrument.json" : DATA_FOLDER + "instrument.json";

    public static final String SONG_ID = "uuid";
    public static final String SONG_TITLE = "title";
    public static final String SONG_AUTHOR = "author";
    public static final String SONG_DESCRIPTION = "description";
    public static final String SONG_GENRES = "genres";
    public static final String SONG_DIFFICULTY = "difficulty";
    public static final String SONG_REACTIONS = "reactions";
    public static final String SONG_RATING = "rating";
    public static final String SONG_COMMENT = "comment";
    public static final String SONG_COMMENTOR = "commentor";
    public static final String SONG_PUBLISHED = "published";
    public static final String SONG_TEMPO = "tempo";
    public static final String SONG_KEY_SIGNATURE = "keySignature";
    public static final String SONG_TIME_SIGNATURE_NUMERATOR = "timeSignatureNum";
    public static final String SONG_TIME_SIGNATURE_DENOMINATOR = "timeSignatureDen";
    public static final String SONG_MEASURES = "measures";
    public static final String SONG_LENGTH = "length";
    public static final String SONG_CHORD = "chord";
    public static final String SONG_INSTRUMENTS = "instruments";
    public static final String SONG_MUSIC = "music";
    public static final String SONG_TEXT = "text";
    public static final String SONG_NOTE_LENGTH = "length";
    public static final String SONG_NOTE_PITCH = "pitch";
    public static final String SONG_NOTE_OCTAVE = "octave";
    public static final String SONG_ICON_FILE = "iconFile";

    public static final String USER_ID = "uuid";
    public static final String USER_USERNAME = "username";
    public static final String USER_PASSWORD = "password";
    public static final String USER_FIRST_NAME = "firstName";
    public static final String USER_LAST_NAME = "lastName";
    public static final String USER_FAV_SONGS = "favSongs";
    public static final String USER_FAV_AUTHS = "favAuthors";
    public static final String USER_ICON_FILE = "iconFile";

    public static final String INSTRUMENT_ID = "uuid";
    public static final String INSTRUMENT_NAME = "name";
    public static final String INSTRUMENT_IMAGE_FILE = "imageFile";

    public static boolean isJUnitTest() {  
		for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
		  if (element.getClassName().startsWith("org.junit.")) {
			return true;
		  }           
		}
		return false;
	  }
}
