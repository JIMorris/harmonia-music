package com.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class SongTest {
    private UserList users = UserList.getInstance();
    private ArrayList<User> userList = users.getUsers();
    private Instrument instrument;
    private ArrayList<Genre> genres = new ArrayList<Genre>();
    private User userOne = new User("jjjKoolKat", "2kool4Dis", "John", "Johnson");
    private User userTwo = new User("epicpulledPorkLover", "puLLedp0rkAyuP",
            "leJaorius", "Porkington");
    private Song defaultSong = new Song("pick of destiny", userOne, "epic song",
            genres, 5, 100, Key.A_FLAT_MAJOR,
            4, 4, instrument);

    public void setup() {
        userList.clear();
        userList.add(userOne);
        userList.add(userTwo);
        instrument = new Instrument(null, null, null);
        genres.add(Genre.BLUES);
    }

    public void tearDown() throws Exception {
        UserList.getInstance().getUsers().clear();
        users.logout();
    }

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
        assertThrows(IllegalArgumentException.class, () -> {
            Song songNullAuthor = new Song("pick of destiny", null, "best song ever",
                    genres, 1, 100, Key.A_FLAT_MAJOR,
                    4, 4, instrument);
        });
    }

    @Test
    void testSongNullDescription() {
        Song songNullDescription = new Song("pick of destiny", userOne, null,
                genres, 1, 100, Key.A_FLAT_MAJOR,
                4, 4, instrument);
        assertNotNull(songNullDescription);
    }

    @Test
    void testSongNullGenre() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song songNullGenre = new Song("pick of destiny", userOne, "epic song",
                    null, 1, 100, Key.A_FLAT_MAJOR,
                    4, 4, instrument);
        });
    }

    @Test
    void testSongInvalidDifficultyLess() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song songInvalidDifficultyLess = new Song("pick of destiny", userOne, "epic song",
                    genres, 0, 100, Key.A_FLAT_MAJOR,
                    4, 4, instrument);
        });
    }

    @Test
    void testSongInvalidDifficultyMore() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song songInvalidDifficultyMore = new Song("pick of destiny", userOne, "epic song",
                    genres, 5, 100, Key.A_FLAT_MAJOR,
                    4, 4, instrument);
        });
    }

    @Test
    void testSongInvalidTempoLess() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song songInvalidTempoLess = new Song("pick of destiny", userOne, "epic song",
                    genres, 5, 0, Key.A_FLAT_MAJOR,
                    4, 4, instrument);
        });
    }

    @Test
    void testSongInvalidTempoMore() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song songInvalidTempoMore = new Song("pick of destiny", userOne, "epic song",
                    genres, 5, 500000, Key.A_FLAT_MAJOR,
                    4, 4, instrument);
        });
    }

    @Test
    void testSongNullKey() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song songNullKey = new Song("pick of destiny", userOne, "epic song",
                    genres, 5, 100, null,
                    4, 4, instrument);
        });
    }

    @Test
    void testSongInvalidTimeNum() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song songInvalidTimeNum = new Song("pick of destiny", userOne, "epic song",
                    genres, 5, 100, Key.A_FLAT_MAJOR,
                    0, 4, instrument);
        });
    }

    @Test
    void testSongInvalidTimeDenom() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song songInvalidTimeDenom = new Song("pick of destiny", userOne, "epic song",
                    genres, 5, 100, Key.A_FLAT_MAJOR,
                    4, 0, instrument);
        });
    }

    @Test
    void testSongNullInstrument() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song songNullInstrument = new Song("pick of destiny", userOne, "epic song",
                    genres, 5, 100, Key.A_FLAT_MAJOR,
                    4, 4, null);
        });
    }

    @Test
    void testSongValidParams() {
        Song songValidParams = new Song("pick of destiny", userOne, "epic song",
                genres, 5, 100, Key.A_FLAT_MAJOR,
                4, 4, instrument);
        assertNotNull(songValidParams);
    }

    @Test
    void testSongCopyNullSong() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song songNullSong = new Song(null, userOne);
        });
    }

    @Test
    void testSongCopyNullAuthor() {
        assertThrows(IllegalArgumentException.class, () -> {
            Song songNullSong = new Song(defaultSong, null);
        });
    }

    @Test
    void testSongCopyValidParam() {
        Song defaultCopy = new Song(defaultSong, userOne);
        assertNotNull(defaultCopy);
    }



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
