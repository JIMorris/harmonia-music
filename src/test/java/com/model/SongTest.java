package com.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.Test;

public class SongTest {
    private Instrument instrument;
    private ArrayList<Instrument> instruments = new ArrayList<Instrument>();
    private ArrayList<Genre> genres = new ArrayList<Genre>();

    private User userOne;
    private User userTwo;

    private Song defaultSong;
    private Reaction reaction;
    private ArrayList<Reaction> reactions = new ArrayList<Reaction>();

    private ArrayList<MeasureGroup> measures = new ArrayList<MeasureGroup>();

    public void setup() {
        userOne = new User("jjjKoolKat", "2kool4Dis", "John", "Johnson");
        userTwo = new User("epicpulledPorkLover", "puLLedp0rkAyuP",
                "leJaorius", "Porkington");

        instrument = new Instrument(null, null, null);
        genres.add(Genre.BLUES);

        defaultSong = new Song("pick of destiny", userOne, "epic song",
                genres, 5, 100, Key.A_FLAT_MAJOR,
                4, 4, instrument);

        reaction = new Reaction(5, "this pretty cool", userOne);
        reactions.add(reaction);
    }

    public void tearDown() throws Exception {
        genres.clear();
    }

    // test for parameterized constructor
    @Test
    void testSongNullTitle() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song songNullTitle = new Song(null, userOne, "best song ever",
                    genres, 1, 100, Key.A_FLAT_MAJOR,
                    4, 4, instrument);
        });
    }

    @Test
    void testSongNullAuthor() {
        Song song = new Song("pick of destiny", null, "best song ever",
                genres, 1, 100, Key.A_FLAT_MAJOR,
                4, 4, instrument);
        assertNotNull(song);
    }

    @Test
    void testSongNullDescription() {
        Song song = new Song("pick of destiny", userOne, null,
                genres, 1, 100, Key.A_FLAT_MAJOR,
                4, 4, instrument);
        assertNotNull(song);
    }

    @Test
    void testSongNullGenre() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song song = new Song("pick of destiny", userOne, "epic song",
                    null, 1, 100, Key.A_FLAT_MAJOR,
                    4, 4, instrument);
        });
    }

    @Test
    void testSongInvalidDifficultyLess() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song song = new Song("pick of destiny", userOne, "epic song",
                    genres, 0, 100, Key.A_FLAT_MAJOR,
                    4, 4, instrument);
        });
    }

    @Test
    void testSongInvalidDifficultyMore() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song song = new Song("pick of destiny", userOne, "epic song",
                    genres, 5, 100, Key.A_FLAT_MAJOR,
                    4, 4, instrument);
        });
    }

    @Test
    void testSongInvalidTempoLess() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song song = new Song("pick of destiny", userOne, "epic song",
                    genres, 5, 0, Key.A_FLAT_MAJOR,
                    4, 4, instrument);
        });
    }

    @Test
    void testSongInvalidTempoMore() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song song = new Song("pick of destiny", userOne, "epic song",
                    genres, 5, 500000, Key.A_FLAT_MAJOR,
                    4, 4, instrument);
        });
    }

    @Test
    void testSongNullKey() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song song = new Song("pick of destiny", userOne, "epic song",
                    genres, 5, 100, null,
                    4, 4, instrument);
        });
    }

    @Test
    void testSongInvalidTimeNum() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song song = new Song("pick of destiny", userOne, "epic song",
                    genres, 5, 100, Key.A_FLAT_MAJOR,
                    0, 4, instrument);
        });
    }

    @Test
    void testSongInvalidTimeDenom() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song song = new Song("pick of destiny", userOne, "epic song",
                    genres, 5, 100, Key.A_FLAT_MAJOR,
                    4, 0, instrument);
        });
    }

    @Test
    void testSongNullInstrument() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song song = new Song("pick of destiny", userOne, "epic song",
                    genres, 5, 100, Key.A_FLAT_MAJOR,
                    4, 4, null);
        });
    }

    @Test
    void testSongAllParams() {
        Song song = new Song("pick of destiny", userOne, "epic song",
                genres, 5, 100, Key.A_FLAT_MAJOR,
                4, 4, instrument);
        assertNotNull(song);
    }

    // tests for copy constructor
    @Test
    void testSongCopyNullSong() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song song = new Song(null, userOne);
        });
    }

    @Test
    void testSongCopyNullAuthor() {
        Song song = new Song(defaultSong, null);
        assertNotNull(song);
    }

    @Test
    void testSongCopyAllParams() {
        Song defaultCopy = new Song(defaultSong, userTwo);
        assertNotNull(defaultCopy);
    }

    // Tests for Json constructor
    @Test
    void testSongJsonNullUUID() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song song = new Song(null, "kickin' chicken'", userOne, "love me some chicken",
                    genres, 2, reactions, false, 100,
                    Key.A_MAJOR, 4, 4, measures, instruments);
        });
    }
    @Test
    void testSongJsonNullTitle() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song song = new Song(UUID.randomUUID(), null, userOne, "love me some chicken",
                    genres, 2, reactions, false, 100,
                    Key.A_MAJOR, 4, 4, measures, instruments);
        });
    }

    @Test
    void testSongJsonNullAuthor() {
        Song song = new Song(UUID.randomUUID(), "kickin' chicken'", null, "love me some chicken",
        genres, 2, reactions, false, 100,
        Key.A_MAJOR, 4, 4, measures, instruments);
    }

    @Test
    void testSongJsonNullDescription() {
        Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userOne, null,
        genres, 2, reactions, false, 100,
        Key.A_MAJOR, 4, 4, measures, instruments);
    }

    @Test
    void testSongJsonNullGenre() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userOne, "love me some chicken",
                    null, 2, reactions, false, 100,
                    Key.A_MAJOR, 4, 4, measures, instruments);
        });
    }

    @Test
    void testSongJsonInvalidDifficultyLess() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userOne, "love me some chicken",
            genres, -1, reactions, false, 100,
            Key.A_MAJOR, 4, 4, measures, instruments);
        });
    }

    @Test
    void testSongJsonInvalidDifficultyMore() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userOne, "love me some chicken",
                    genres, 1000, reactions, false, 100,
                    Key.A_MAJOR, 4, 4, measures, instruments);
        });
    }

    @Test
    void testSongjsonNullReaction() {
        Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userOne, "love me some chicken",
                    genres, 2, null, false, 100,
                    Key.A_MAJOR, 4, 4, measures, instruments);
        assertNotNull(song);
    }

    @Test
    void testSongJsonInvalidTempoLess() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userOne, "love me some chicken",
            genres, 2, reactions, false, 0,
            Key.A_MAJOR, 4, 4, measures, instruments);
        });
    }

    @Test
    void testSongJsonInvalidTempoMore() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userOne, "love me some chicken",
            genres, 2, reactions, false, 10000,
            Key.A_MAJOR, 4, 4, measures, instruments);
        });
    }

    @Test
    void testSongJsonNullKey() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userOne, "love me some chicken",
            genres, 2, reactions, false, 100,
            null, 4, 4, measures, instruments);
        });
    }

    @Test
    void testSongJsonInvalidTimeNum() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userOne, "love me some chicken",
            genres, 2, reactions, false, 100,
            Key.A_MAJOR, 0, 4, measures, instruments);
        });
    }

    @Test
    void testSongJsonInvalidTimeDenom() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userOne, "love me some chicken",
            genres, 2, reactions, false, 100,
            Key.A_MAJOR, 4, 0, measures, instruments);
        });
    }

    @Test
    void testSongJsonNullInstruments() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userOne, "love me some chicken",
            genres, 2, reactions, false, 100,
            Key.A_MAJOR, 4, 4, measures, null);
        });
    }

    @Test
    void testSongJsonAllParams() {
        Song song = new Song(UUID.randomUUID(), "kickin' chicken'", userOne, "love me some chicken",
        genres, 2, reactions, false, 100,
        Key.A_MAJOR, 4, 4, measures, instruments);
        assertNotNull(song);
    }
    
    // test for SetTempo
    @Test
    void testInvalidSetTempoLess() {
        assertThrows(IllegalArgumentException.class, () -> {
            defaultSong.setTempo(0);
        });
    }

    @Test
    void testInvalidSetTempoMore() {
        assertThrows(IllegalArgumentException.class, () -> {
            defaultSong.setTempo(50000);
        });
    }

    @Test
    void testValidSetTempo() throws Exception {
        int newTempo = 150;
        defaultSong.setTempo(newTempo);
        assertEquals(newTempo, defaultSong.getTempo());
    }
}
