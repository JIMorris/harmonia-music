package com.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SongTest {
    private InstrumentList instrumentList;
    private Instrument instrument;
    private ArrayList<Instrument> instruments = new ArrayList<Instrument>();
    private ArrayList<Genre> genres = new ArrayList<Genre>();

    private User userOne;
    private User userTwo;
    private UserList userList;

    private SongList songList;
    private Song song;
    private Song defaultSong;
    private Reaction reaction;
    private ArrayList<Reaction> reactions = new ArrayList<Reaction>();
    private ArrayList<MeasureGroup> measures = new ArrayList<MeasureGroup>();

    @Before
    public void setup() throws Exception {
        userList = UserList.getInstance();
        userOne = new User("jjjKoolKat", "2kool4Dis", "John", "Johnson");
        userTwo = new User("epicpulledPorkLover", "puLLedp0rkAyuP",
                "leJaorius", "Porkington");

        instrumentList = InstrumentList.getInstance();
        instrument = instrumentList.getInstruments().get(0);
        genres.add(Genre.BLUES);

        songList = SongList.getInstance();
        defaultSong = new Song("pick of destiny", userOne, "epic song",
                genres, 5, 100, Key.A_FLAT_MAJOR,
                4, 4, instrument);
        song = songList.getSongs().get(0);

        reaction = new Reaction(5, "this pretty cool", userOne);
        reactions.add(reaction);
    }

    @After
    public void tearDown() throws Exception {
        genres.clear();
        instruments.clear();
        reactions.clear();
        // userList.logout();
        // songList.logout();
        // instrumentList.logout();
        // userList.getUsers().clear();
        // songList.getSongs().clear();
        // instrumentList.getInstruments().clear();
    }

    // test for parameterized constructor
    @Test
    public void testSongNullTitle() {
        assertThrows(Exception.class, () -> {
            Song songNullTitle = new Song(null, userOne, "best song ever",
                    genres, 1, 100, Key.A_FLAT_MAJOR,
                    4, 4, instrument);
        });
    }

    @Test
    public void testSongNullAuthor() {
        Song song = new Song("pick of destiny", null, "best song ever",
                genres, 1, 100, Key.A_FLAT_MAJOR,
                4, 4, instrument);
        assertNotNull(song);
    }

    @Test
    public void testSongNullDescription() {
        Song song = new Song("pick of destiny", userOne, null,
                genres, 1, 100, Key.A_FLAT_MAJOR,
                4, 4, instrument);
        assertNotNull(song);
    }

    @Test
    public void testSongNullGenre() {
        assertThrows(Exception.class, () -> {
            Song song = new Song("pick of destiny", userOne, "epic song",
                    null, 1, 100, Key.A_FLAT_MAJOR,
                    4, 4, instrument);
        });
    }

    @Test
    public void testSongInvalidDifficultyLess() {
        assertThrows(Exception.class, () -> {
            Song song = new Song("pick of destiny", userOne, "epic song",
                    genres, 0, 100, Key.A_FLAT_MAJOR,
                    4, 4, instrument);
        });
    }

    @Test
    public void testSongInvalidDifficultyMore() {
        assertThrows(Exception.class, () -> {
            Song song = new Song("pick of destiny", userOne, "epic song",
                    genres, 5, 100, Key.A_FLAT_MAJOR,
                    4, 4, instrument);
        });
    }

    @Test
    public void testSongInvalidTempoLess() {
        assertThrows(Exception.class, () -> {
            Song song = new Song("pick of destiny", userOne, "epic song",
                    genres, 5, 0, Key.A_FLAT_MAJOR,
                    4, 4, instrument);
        });
    }

    @Test
    public void testSongInvalidTempoMore() {
        assertThrows(Exception.class, () -> {
            Song song = new Song("pick of destiny", userOne, "epic song",
                    genres, 5, 500000, Key.A_FLAT_MAJOR,
                    4, 4, instrument);
        });
    }

    @Test
    public void testSongNullKey() {
        assertThrows(Exception.class, () -> {
            Song song = new Song("pick of destiny", userOne, "epic song",
                    genres, 5, 100, null,
                    4, 4, instrument);
        });
    }

    @Test
    public void testSongInvalidTimeNum() {
        assertThrows(Exception.class, () -> {
            Song song = new Song("pick of destiny", userOne, "epic song",
                    genres, 5, 100, Key.A_FLAT_MAJOR,
                    0, 4, instrument);
        });
    }

    @Test
    public void testSongInvalidTimeDenom() {
        assertThrows(Exception.class, () -> {
            Song song = new Song("pick of destiny", userOne, "epic song",
                    genres, 5, 100, Key.A_FLAT_MAJOR,
                    4, 0, instrument);
        });
    }

    @Test
    public void testSongNullInstrument() {
        assertThrows(Exception.class, () -> {
            Song song = new Song("pick of destiny", userOne, "epic song",
                    genres, 5, 100, Key.A_FLAT_MAJOR,
                    4, 4, null);
        });
    }

    @Test
    public void testSongAllParams() {
        Song song = new Song("pick of destiny", userOne, "epic song",
                genres, 5, 100, Key.A_FLAT_MAJOR,
                4, 4, instrument);
        assertNotNull(song);
    }

    // tests for copy constructor
    @Test
    public void testSongCopyNullSong() {
        assertThrows(Exception.class, () -> {
            Song song = new Song(null, userOne);
        });
    }

    @Test
    public void testSongCopyNullAuthor() {
        Song song = new Song(defaultSong, null);
        assertNotNull(song);
    }

    @Test
    public void testSongCopyAllParams() {
        Song defaultCopy = new Song(defaultSong, userTwo);
        assertNotNull(defaultCopy);
    }

    // Tests for Json constructor
    @Test
    public void testSongJsonNullUUID() {
        assertThrows(Exception.class, () -> {
            Song song = new Song(null, "kickin' chicken'", userOne, "love me some chicken",
                    genres, 2, reactions, false, 100,
                    Key.A_MAJOR, 4, 4, measures, instruments);
        });
    }

    @Test
    public void testSongJsonNullTitle() {
        assertThrows(Exception.class, () -> {
            Song song = new Song(UUID.randomUUID(), null, userOne, "love me some chicken",
                    genres, 2, reactions, false, 100,
                    Key.A_MAJOR, 4, 4, measures, instruments);
        });
    }

    @Test
    public void testSongJsonNullAuthor() {
        Song song = new Song(UUID.randomUUID(), "kickin' chicken'", null, "love me some chicken",
                genres, 2, reactions, false, 100,
                Key.A_MAJOR, 4, 4, measures, instruments);
    }

    @Test
    public void testSongJsonNullDescription() {
        Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userOne, null,
                genres, 2, reactions, false, 100,
                Key.A_MAJOR, 4, 4, measures, instruments);
    }

    @Test
    public void testSongJsonNullGenre() {
        assertThrows(Exception.class, () -> {
            Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userOne, "love me some chicken",
                    null, 2, reactions, false, 100,
                    Key.A_MAJOR, 4, 4, measures, instruments);
        });
    }

    @Test
    public void testSongJsonInvalidDifficultyLess() {
        assertThrows(Exception.class, () -> {
            Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userOne, "love me some chicken",
                    genres, -1, reactions, false, 100,
                    Key.A_MAJOR, 4, 4, measures, instruments);
        });
    }

    @Test
    public void testSongJsonInvalidDifficultyMore() {
        assertThrows(Exception.class, () -> {
            Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userOne, "love me some chicken",
                    genres, 1000, reactions, false, 100,
                    Key.A_MAJOR, 4, 4, measures, instruments);
        });
    }

    @Test
    public void testSongjsonNullReaction() {
        Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userOne, "love me some chicken",
                genres, 2, null, false, 100,
                Key.A_MAJOR, 4, 4, measures, instruments);
        assertNotNull(song);
    }

    @Test
    public void testSongJsonInvalidTempoLess() {
        assertThrows(Exception.class, () -> {
            Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userOne, "love me some chicken",
                    genres, 2, reactions, false, 0,
                    Key.A_MAJOR, 4, 4, measures, instruments);
        });
    }

    @Test
    public void testSongJsonInvalidTempoMore() {
        assertThrows(Exception.class, () -> {
            Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userOne, "love me some chicken",
                    genres, 2, reactions, false, 10000,
                    Key.A_MAJOR, 4, 4, measures, instruments);
        });
    }

    @Test
    public void testSongJsonNullKey() {
        assertThrows(Exception.class, () -> {
            Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userOne, "love me some chicken",
                    genres, 2, reactions, false, 100,
                    null, 4, 4, measures, instruments);
        });
    }

    @Test
    public void testSongJsonInvalidTimeNum() {
        assertThrows(Exception.class, () -> {
            Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userOne, "love me some chicken",
                    genres, 2, reactions, false, 100,
                    Key.A_MAJOR, 0, 4, measures, instruments);
        });
    }

    @Test
    public void testSongJsonInvalidTimeDenom() {
        assertThrows(Exception.class, () -> {
            Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userOne, "love me some chicken",
                    genres, 2, reactions, false, 100,
                    Key.A_MAJOR, 4, 0, measures, instruments);
        });
    }

    @Test
    public void testSongJsonNullInstruments() {
        assertThrows(Exception.class, () -> {
            Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userOne, "love me some chicken",
                    genres, 2, reactions, false, 100,
                    Key.A_MAJOR, 4, 4, measures, null);
        });
    }

    @Test
    public void testSongJsonAllParams() {
        Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userOne, "love me some chicken",
                genres, 2, reactions, false, 100,
                Key.A_MAJOR, 4, 4, measures, instruments);
        assertNotNull(song);
    }

    // tests for SetTempo
    @Test
    public void testInvalidSetTempoLess() {
        assertThrows(Exception.class, () -> {
            defaultSong.setTempo(0);
        });
    }

    @Test
    public void testInvalidSetTempoMore() {
        assertThrows(Exception.class, () -> {
            defaultSong.setTempo(50000);
        });
    }

    @Test
    public void testValidSetTempo() throws Exception {
        int newTempo = 150;
        defaultSong.setTempo(newTempo);
        assertEquals(newTempo, defaultSong.getTempo());
    }

    // tests for getMeasures and getMeasureGroup
    @Test
    public void testGetMeasuresFromNull() {
        assertThrows(Exception.class, () -> {
            defaultSong.getMeasures(null);
        });
    }

    @Test
    public void testGetMeasureGroupFromNull() {
        assertThrows(Exception.class, () -> {
            defaultSong.getMeasureGroup(null);
        });
    }

    @Test
    public void testGetMeasureGroupNotNull() {

        MeasureGroup actualMeasureGroup = song.getMeasureGroup(song.getMeasures(instrument).get(0));
        assertEquals(song.getMeasureGroups().get(0), actualMeasureGroup);
    }

    // test for getInstrumentIDs
    @Test
    public void testGetInstrumentIDs() {
        ArrayList<UUID> expectedIDs = new ArrayList<UUID>();
        expectedIDs.add(instrument.getInstrumentID());
        ArrayList<UUID> actualIDs = defaultSong.getInstrumentIDs();
        assertEquals(expectedIDs, actualIDs);
    }

    // tests for addReaction
    @Test
    public void testaddReaction() {
        Reaction expectedReaction = new Reaction(5, "yes", userOne);
        defaultSong.addReaction(5, "yes", userOne);
        Reaction actualReaction = defaultSong.getReactions().get(0);
        assertTrue(expectedReaction.getRating() == defaultSong.getReactions().get(0).getRating()
                && expectedReaction.getComment().equals(defaultSong.getReactions().get(0).getComment())
                && expectedReaction.getAuthor() == defaultSong.getReactions().get(0).getAuthor());
    }

    // tests for removeReaction
    @Test
    public void testRemoveReactionDiffUser() throws Exception {
        userList.signup("jjjKoolKat", "2kool4Dis", "John", "Johnson");
        defaultSong.addReaction(0, "boo", userOne);
        userList.signup("epicpulledPorkLover", "puLLedp0rkAyuP",
                "leJaorius", "Porkington");
        assertThrows(Exception.class, () -> {
            defaultSong.removeReaction(defaultSong.getReactions().get(0));
        });
    }

    @Test
    public void testRemoveReactiondSameUser() throws Exception {
        userList.signup("jjjKoolKat", "2kool4Dis", "John", "Johnson");
        defaultSong.addReaction(0, "boo", userOne);
        defaultSong.removeReaction(defaultSong.getReactions().get(0));
        assertEquals(0, defaultSong.getReactions().size());
    } // FLASE POSITIVE

    // tests for inserMeasure
    @Test
    public void testInsertMeasureNull() {
        assertThrows(Exception.class, () -> {
            song.insertMeasure(null);
        });
    }

    @Test
    public void testInsertMeasureValid() {
        int i = song.getMeasureGroups().size();
        song.insertMeasure(song.getMeasureGroups().get(0));
        assertEquals(i + 1, song.getMeasureGroups().size());
    }

    // tests for removeMeaure
    @Test
    public void testRemoveMeasureNull() {
        assertThrows(Exception.class, () -> {
            song.removeMeasure(null);
        });
    }

    @Test
    public void testRemoveMeasureOneMeasureGroup() {
        assertThrows(Exception.class, () -> {
            defaultSong.removeMeasure(defaultSong.getMeasureGroups().get(0));
        });
    }

    @Test
    public void testRemoveMeasureValid() throws Exception {
        int i = song.getMeasureGroups().size();
        song.removeMeasure(song.getMeasureGroups().get(0));
        assertEquals(i - 1, song.getMeasureGroups().size());
    }

    // test for insertNote
    @Test
    public void testInsertNoteNull() {
        assertThrows(Exception.class, () -> {
            song.insertNote(null);
        });
    }// I literally don't know why this passed

    @Test
    public void testInsertNoteValid() {
        defaultSong.insertNote(defaultSong.getMeasures(instrument).get(0).getNotes().get(0));
        assertEquals(Pitch.A_FLAT, defaultSong.getMeasures(instrument).get(0).getNotes().get(0).getPitch());
    }

    // test for noteUp
    @Test
    public void testNoteUpNull() {
        assertThrows(Exception.class, () -> {
            song.noteUp(null);
        });
    }

    @Test
    public void testNoteUpValid() throws Exception {
        defaultSong.insertNote(defaultSong.getMeasures(instrument).get(0).getNotes().get(0));
        defaultSong.noteUp(defaultSong.getMeasures(instrument).get(0).getNotes().get(0));
        assertEquals(Pitch.B_FLAT, defaultSong.getMeasures(instrument).get(0).getNotes().get(0).getPitch());
    }

    // test for noteDown
    @Test
    public void testNoteDownNull() {
        assertThrows(Exception.class, () -> {
            song.noteDown(null);
        });
    }

    @Test
    public void testNotedownValid() throws Exception {
        defaultSong.insertNote(defaultSong.getMeasures(instrument).get(0).getNotes().get(0));
        defaultSong.noteDown(defaultSong.getMeasures(instrument).get(0).getNotes().get(0));
        assertEquals(Pitch.G, defaultSong.getMeasures(instrument).get(0).getNotes().get(0).getPitch());
    }

    // tests for addInstrument
    @Test
    public void testAddInstrumentNull() {
        assertThrows(Exception.class, () -> {
            song.addInstrument(null);
        });
    }

    @Test
    public void testAddInstrumentValid() {
        song.addInstrument(instrumentList.getInstruments().get(1));
        assertEquals(2, song.getInstruments().size());
    }

    // tests for removeInstrument
    @Test
    public void testRemoveInstrumentNull() {
        assertThrows(Exception.class, () -> {
            song.removeInstrument(null);
        });
    }

    @Test
    public void testRemoveInstrumentOne() {
        assertThrows(Exception.class, () -> {
            song.removeInstrument(song.getInstruments().get(0));
        });
    }

    @Test
    public void testRemoveInstrumentValid() throws Exception {
        song.addInstrument(instrumentList.getInstruments().get(1));
        song.removeInstrument(song.getInstruments().get(0));
        assertEquals(instrumentList.getInstruments().get(1).getInstrumentID(),
            song.getInstrumentIDs().get(0));
    }
}
