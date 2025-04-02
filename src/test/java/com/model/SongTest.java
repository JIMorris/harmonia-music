package com.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// Created and tested by Simion Cartis
public class SongTest {
    private InstrumentList instrumentList;
    private Instrument instrument;
    private ArrayList<Instrument> instruments = new ArrayList<Instrument>();
    private ArrayList<Genre> genres = new ArrayList<Genre>();

    private UserList userList;

    private SongList songList;
    private Song song;
    private Song OneMeasureSong;
    private ArrayList<Reaction> reactions = new ArrayList<Reaction>();
    private ArrayList<MeasureGroup> measures = new ArrayList<MeasureGroup>();

    @Before
    public void setup() throws Exception {
        genres.add(Genre.BLUES);
        instrumentList = InstrumentList.getInstance();
        instrumentList.setInstruments(DataLoader.getInstance().loadInstruments());
        instrument = instrumentList.getInstruments().get(0);

        userList = UserList.getInstance();
        userList.setUsers(DataLoader.getInstance().loadUsers());
        userList.setCurrentUser(userList.getUsers().get(0));

        songList = SongList.getInstance();
        songList.setSongs(DataLoader.getInstance().loadSongs());
        song = songList.getSongs().get(0);
        OneMeasureSong = new Song("Yes", userList.getUsers().get(0), "no", genres, 1, 100, Key.A_MAJOR, 4, 4,
                instrument);

    }

    // @After
    // public void tearDown() throws Exception {
    //     genres.clear();
    //     // instruments.clear();
    //     // reactions.clear();
    //     // userList.logout();
    //     // songList.logout();
    //     // instrumentList.logout();
    //     // userList.getUsers().clear();
    //     // songList.getSongs().clear();
    //     // instrumentList.getInstruments().clear();
    // }

    // test for parameterized constructor
    @Test
    public void testSongNullTitle() {
        assertThrows(Exception.class, () -> {
            Song song = new Song(null, userList.getUsers().get(0), "best song ever",
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
        Song song = new Song("pick of destiny", userList.getUsers().get(0), null,
                genres, 1, 100, Key.A_FLAT_MAJOR,
                4, 4, instrument);
        assertNotNull(song);
    }

    @Test
    public void testSongNullGenre() {
        assertThrows(Exception.class, () -> {
            Song song = new Song("pick of destiny", userList.getUsers().get(0), "epic song",
                    null, 1, 100, Key.A_FLAT_MAJOR,
                    4, 4, instrument);
        });
    }

    @Test
    public void testSongInvalidDifficultyLess() {
        assertThrows(Exception.class, () -> {
            Song song = new Song("pick of destiny", userList.getUsers().get(0), "epic song",
                    genres, 0, 100, Key.A_FLAT_MAJOR,
                    4, 4, instrument);
        });
    }

    @Test
    public void testSongInvalidDifficultyMore() {
        assertThrows(Exception.class, () -> {
            Song song = new Song("pick of destiny", userList.getUsers().get(0), "epic song",
                    genres, 50, 100, Key.A_FLAT_MAJOR,
                    4, 4, instrument);
        });
    }

    @Test
    public void testSongInvalidTempoLess() {
        assertThrows(Exception.class, () -> {
            Song song = new Song("pick of destiny", userList.getUsers().get(0), "epic song",
                    genres, 5, 0, Key.A_FLAT_MAJOR,
                    4, 4, instrument);
        });
    }

    @Test
    public void testSongInvalidTempoMore() {
        assertThrows(Exception.class, () -> {
            Song song = new Song("pick of destiny", userList.getUsers().get(0), "epic song",
                    genres, 5, 500000, Key.A_FLAT_MAJOR,
                    4, 4, instrument);
        });
    }

    @Test
    public void testSongNullKey() {
        assertThrows(Exception.class, () -> {
            Song song = new Song("pick of destiny", userList.getUsers().get(0), "epic song",
                    genres, 5, 100, null,
                    4, 4, instrument);
        });
    }

    @Test
    public void testSongInvalidTimeNum() {
        assertThrows(Exception.class, () -> {
            Song song = new Song("pick of destiny", userList.getUsers().get(0), "epic song",
                    genres, 5, 100, Key.A_FLAT_MAJOR,
                    0, 4, instrument);
        });
    }

    @Test
    public void testSongInvalidTimeDenom() {
        assertThrows(Exception.class, () -> {
            Song song = new Song("pick of destiny", userList.getUsers().get(0), "epic song",
                    genres, 5, 100, Key.A_FLAT_MAJOR,
                    4, 0, instrument);
        });
    }

    @Test
    public void testSongNullInstrument() {
        assertThrows(Exception.class, () -> {
            Song song = new Song("pick of destiny", userList.getUsers().get(0), "epic song",
                    genres, 5, 100, Key.A_FLAT_MAJOR,
                    4, 4, null);
        });
    }

    @Test
    public void testSongAllParams() {
        Song song = new Song("pick of destiny", userList.getUsers().get(0), "epic song",
                genres, 5, 100, Key.A_FLAT_MAJOR,
                4, 4, instrument);
        assertNotNull(song);
    }

    // tests for copy constructor
    @Test
    public void testSongCopyNullSong() {
        assertThrows(Exception.class, () -> {
            Song song = new Song(null, userList.getUsers().get(0));
        });
    }

    @Test
    public void testSongCopyNullAuthor() {
        Song song = new Song(songList.getSongs().get(0), null);
        assertNotNull(song);
    }

    @Test
    public void testSongCopyAllParams() {
        Song song = new Song(songList.getSongs().get(0), userList.getUsers().get(0));
        assertNotNull(song);
    }

    // Tests for Json constructor
    @Test
    public void testSongJsonNullUUID() {
        assertThrows(Exception.class, () -> {
            Song song = new Song(null, "kickin' chicken'", userList.getUsers().get(0), "love me some chicken",
                    genres, 2, reactions, false, 100,
                    Key.A_MAJOR, 4, 4, measures, instruments);
        });
    }

    @Test
    public void testSongJsonNullTitle() {
        assertThrows(Exception.class, () -> {
            Song song = new Song(UUID.randomUUID(), null, userList.getUsers().get(0), "love me some chicken",
                    genres, 2, reactions, false, 100,
                    Key.A_MAJOR, 4, 4, measures, instruments);
        });
    }

    @Test
    public void testSongJsonNullAuthor() {
        Song song = new Song(UUID.randomUUID(), "kickin' chicken'", null, "love me some chicken",
                genres, 2, reactions, false, 100,
                Key.A_MAJOR, 4, 4, measures, instruments);
        assertNotNull(song);
    }

    @Test
    public void testSongJsonNullDescription() {
        Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userList.getUsers().get(0), null,
                genres, 2, reactions, false, 100,
                Key.A_MAJOR, 4, 4, measures, instruments);
                assertNotNull(song);
    }

    @Test
    public void testSongJsonNullGenre() {
        assertThrows(Exception.class, () -> {
            Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userList.getUsers().get(0),
                    "love me some chicken",
                    null, 2, reactions, false, 100,
                    Key.A_MAJOR, 4, 4, measures, instruments);
        });
    }

    @Test
    public void testSongJsonInvalidDifficultyLess() {
        assertThrows(Exception.class, () -> {
            Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userList.getUsers().get(0),
                    "love me some chicken",
                    genres, -1, reactions, false, 100,
                    Key.A_MAJOR, 4, 4, measures, instruments);
        });
    }

    @Test
    public void testSongJsonInvalidDifficultyMore() {
        assertThrows(Exception.class, () -> {
            Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userList.getUsers().get(0),
                    "love me some chicken",
                    genres, 1000, reactions, false, 100,
                    Key.A_MAJOR, 4, 4, measures, instruments);
        });
    }

    @Test
    public void testSongjsonNullReaction() {
        Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userList.getUsers().get(0), "love me some chicken",
                genres, 2, null, false, 100,
                Key.A_MAJOR, 4, 4, measures, instruments);
        assertNotNull(song);
    }

    @Test
    public void testSongJsonInvalidTempoLess() {
        assertThrows(Exception.class, () -> {
            Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userList.getUsers().get(0),
                    "love me some chicken",
                    genres, 2, reactions, false, 0,
                    Key.A_MAJOR, 4, 4, measures, instruments);
        });
    }

    @Test
    public void testSongJsonInvalidTempoMore() {
        assertThrows(Exception.class, () -> {
            Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userList.getUsers().get(0),
                    "love me some chicken",
                    genres, 2, reactions, false, 10000,
                    Key.A_MAJOR, 4, 4, measures, instruments);
        });
    }

    @Test
    public void testSongJsonNullKey() {
        assertThrows(Exception.class, () -> {
            Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userList.getUsers().get(0),
                    "love me some chicken",
                    genres, 2, reactions, false, 100,
                    null, 4, 4, measures, instruments);
        });
    }

    @Test
    public void testSongJsonInvalidTimeNum() {
        assertThrows(Exception.class, () -> {
            Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userList.getUsers().get(0),
                    "love me some chicken",
                    genres, 2, reactions, false, 100,
                    Key.A_MAJOR, 0, 4, measures, instruments);
        });
    }

    @Test
    public void testSongJsonInvalidTimeDenom() {
        assertThrows(Exception.class, () -> {
            Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userList.getUsers().get(0),
                    "love me some chicken",
                    genres, 2, reactions, false, 100,
                    Key.A_MAJOR, 4, 0, measures, instruments);
        });
    }

    @Test
    public void testSongJsonNullInstruments() {
        assertThrows(Exception.class, () -> {
            Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userList.getUsers().get(0),
                    "love me some chicken",
                    genres, 2, reactions, false, 100,
                    Key.A_MAJOR, 4, 4, measures, null);
        });
    }

    @Test
    public void testSongJsonAllParams() {
        Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userList.getUsers().get(0), "love me some chicken",
                genres, 2, reactions, false, 100,
                Key.A_MAJOR, 4, 4, measures, instruments);
        assertNotNull(song);
    }

    // tests for SetTempo
    @Test
    public void testInvalidSetTempoLess() {
        assertThrows(Exception.class, () -> {
            song.setTempo(0);
        });
    }

    @Test
    public void testInvalidSetTempoMore() {
        assertThrows(Exception.class, () -> {
            song.setTempo(50000);
        });
    }

    @Test
    public void testValidSetTempo() throws Exception {
        int newTempo = 150;
        song.setTempo(newTempo);
        assertEquals(newTempo, song.getTempo());
    }

    // tests for getMeasures and getMeasureGroup
    @Test
    public void testGetMeasuresFromNull() {
        assertThrows(Exception.class, () -> {
            song.getMeasures(null);
        });
    }

    @Test
    public void testGetMeasureGroupFromNull() {
        assertThrows(Exception.class, () -> {
            song.getMeasureGroup(null);
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
        ArrayList<UUID> actualIDs = songList.getSongs().get(0).getInstrumentIDs();
        assertEquals(expectedIDs, actualIDs);
    }

    // tests for addReaction
    @Test
    public void testaddReaction() {
        OneMeasureSong.getReactions().clear();
        int reaction = 2;
        String comment = "yes";
        User author = userList.getUsers().get(0);
        OneMeasureSong.addReaction(reaction, comment, author);
        assertTrue(reaction == OneMeasureSong.getReactions().get(0).getRating()
                && comment.equals(OneMeasureSong.getReactions().get(0).getComment())
                && author == OneMeasureSong.getReactions().get(0).getAuthor());
    }

    // tests for removeReaction
    @Test
    public void testRemoveReactionDiffUser() throws Exception {
        song.getReactions().clear();
        song.addReaction(5, "epic", userList.getUsers().get(1));
        // assertEquals(userList.getUsers().get(0), userList.getCurrentUser());
        assertThrows(Exception.class, () -> {
            song.removeReaction(song.getReactions().get(0));
        });
    }

    @Test
    public void testRemoveReactionSameUser() throws Exception {
        song.getReactions().clear();
        song.addReaction(5, "epic", userList.getUsers().get(0));
        song.removeReaction(songList.getSongs().get(0).getReactions().get(0));
        assertEquals(0, song.getReactions().size());
    }

    // tests for insertMeasure
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
            OneMeasureSong.removeMeasure(OneMeasureSong.getMeasureGroups().get(0));
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
            OneMeasureSong.insertNote(null);
        });
    }// I literally don't know why this passed

    @Test
    public void testInsertNoteValid() {
        OneMeasureSong.insertNote(OneMeasureSong.getMeasures(instrument).get(0).getNotes().get(0));
        assertEquals(Pitch.A,
                OneMeasureSong.getMeasures(instrument).get(0).getNotes().get(0).getPitch());
    }

    // // test for noteUp
    @Test
    public void testNoteUpNull() {
        assertThrows(Exception.class, () -> {
            OneMeasureSong.noteUp(null);
        });
    }

    @Test
    public void testNoteUpValid() throws Exception {
        OneMeasureSong.insertNote(OneMeasureSong.getMeasures(instrument).get(0).getNotes().get(0));
        OneMeasureSong.noteUp(OneMeasureSong.getMeasures(instrument).get(0).getNotes().get(0));
        assertEquals(Pitch.B,
                OneMeasureSong.getMeasures(instrument).get(0).getNotes().get(0).getPitch());
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
        OneMeasureSong.insertNote(OneMeasureSong.getMeasures(instrument).get(0).getNotes().get(0));
        OneMeasureSong.noteDown(OneMeasureSong.getMeasures(instrument).get(0).getNotes().get(0));
        assertEquals(Pitch.G_SHARP,
                OneMeasureSong.getMeasures(instrument).get(0).getNotes().get(0).getPitch());
    }

    // tests for addInstrument
    @Test
    public void testAddInstrumentNull() {
        assertThrows(Exception.class, () -> {
            OneMeasureSong.addInstrument(null);
        });
    }

    @Test
    public void testAddInstrumentValid() {
        OneMeasureSong.addInstrument(instrumentList.getInstruments().get(1));
        assertEquals(2, OneMeasureSong.getInstruments().size());
    }

    // tests for removeInstrument
    @Test
    public void testRemoveInstrumentNull() {
        assertThrows(Exception.class, () -> {
            OneMeasureSong.removeInstrument(null);
        });
    }

    @Test
    public void testRemoveInstrumentOne() {
        assertThrows(Exception.class, () -> {
            OneMeasureSong.removeInstrument(OneMeasureSong.getInstruments().get(0));
        });
    }

    @Test
    public void testRemoveInstrumentValid() throws Exception {
        OneMeasureSong.addInstrument(instrumentList.getInstruments().get(1));
        OneMeasureSong.removeInstrument(OneMeasureSong.getInstruments().get(0));
        assertEquals(instrumentList.getInstruments().get(1).getInstrumentID(),
        OneMeasureSong.getInstrumentIDs().get(0));
    }
}
