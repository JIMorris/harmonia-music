package com.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// Created and tested by Simion Cartis
public class SongListTest {
    SongList songList;
    UserList userList;
    private ArrayList<Genre> genres = new ArrayList<Genre>();
    private Instrument instrument;
    private InstrumentList instrumentList;

    @Before
    public void setUp() throws Exception {
        genres.add(Genre.BLUES);
        instrumentList = InstrumentList.getInstance();
        instrumentList.setInstruments(DataLoader.getInstance().loadInstruments());
        instrument = instrumentList.getInstruments().get(0);

        userList = UserList.getInstance();
        userList.setUsers(DataLoader.getInstance().loadUsers());
        userList.setCurrentUser(userList.getUsers().get(5));

        songList = SongList.getInstance();
        songList.setSongs(DataLoader.getInstance().loadSongs());
    }

    // @After
    // public void tearDown() {
    //     genres.clear();

    // }

    // test for getSong
    @Test
    public void testGetSongNull() {
        assertThrows(Exception.class, () -> {
            songList.getSong(null);
        });
    }

    @Test
    public void testGetSongInvalidID() {
        UUID uuid = UUID.fromString("98a053f8-9814-41ca-941a-9f8976ea4be2");
        assertNull(songList.getSong(uuid));
    }

    @Test
    public void testGetSongValidID() {
        UUID uuid = UUID.fromString("98a053f8-9814-41ca-941a-9f8976ea4be6");
        assertEquals(songList.getSongs().get(0), songList.getSong(uuid));
    }

    // test for newSong
    @Test
    public void testNewSongNullTitle() {
        assertThrows(Exception.class, () -> {
            songList.newSong(null, "cool", genres, 1, 100, Key.A_MAJOR, new int[] { 4, 4 }, instrument);
        });
    }

    @Test
    public void testNewSongNulldescription() {
        Song newSong = songList.newSong("epic", null, genres, 1, 100, Key.A_MAJOR, new int[] { 4, 4 }, instrument);
        assertEquals(songList.getSongs().get(songList.getSongs().size() - 1), newSong);

    }

    @Test
    public void testNewSongNullGenres() {
        assertThrows(Exception.class, () -> {
            songList.newSong("epic", "cool", null, 1, 100, Key.A_MAJOR, new int[] { 4, 4 }, instrument);
        });
    }

    @Test
    public void testNewSongInvalidDifficultyLess() {
        assertThrows(Exception.class, () -> {
            songList.newSong("epic", "cool", genres, 0, 100, Key.A_MAJOR, new int[] { 4, 4 }, instrument);
        });
    }

    @Test
    public void testNewSongInvalidDifficultyMore() {
        assertThrows(Exception.class, () -> {
            songList.newSong("epic", "cool", genres, 50, 100, Key.A_MAJOR, new int[] { 4, 4 }, instrument);
        });
    }

    @Test
    public void testNewSongInvalidtempoLess() {
        assertThrows(Exception.class, () -> {
            songList.newSong("epic", "cool", genres, 2, 0, Key.A_MAJOR, new int[] { 4, 4 }, instrument);
        });
    }

    @Test
    public void testNewSongInvalidtempoMore() {
        assertThrows(Exception.class, () -> {
            songList.newSong("epic", "cool", genres, 2, 100000, Key.A_MAJOR, new int[] { 4, 4 }, instrument);
        });
    }

    @Test
    public void testNewSongNullKey() {
        assertThrows(Exception.class, () -> {
            songList.newSong("epic", "cool", genres, 2, 100, null, new int[] { 4, 4 }, instrument);
        });
    }

    @Test
    public void testNewSonginvalidTimeSignatureNum() {
        assertThrows(Exception.class, () -> {
            songList.newSong("epic", "cool", genres, 2, 100, Key.A_MAJOR, new int[] { 0, 4 }, instrument);
        });
    }

    @Test
    public void testNewSongMissingTimeSignatureDenom() {
        assertThrows(Exception.class, () -> {
            songList.newSong("epic", "cool", genres, 2, 100, Key.A_MAJOR, new int[] {4}, instrument);
        });
    }

    @Test
    public void testNewSonginvalidTimeSignatureDenom() {
        assertThrows(Exception.class, () -> {
            songList.newSong("epic", "cool", genres, 2, 100, Key.A_MAJOR, new int[] { 4, 0 }, instrument);
        });
    }

    @Test
    public void testNewSongNullInstrument() {
        assertThrows(Exception.class, () -> {
            songList.newSong("epic", "cool", genres, 2, 100, Key.A_MAJOR, new int[] { 4, 4 }, null);
        });
    }

    // test for copySong
    @Test
    public void testCopySongNull() {
        assertThrows(Exception.class, () -> {
            songList.copySong(null);
        });
    }

    @Test
    public void testCopySongValid() {
        Song song = songList.copySong(songList.getSongs().get(0));
        assertEquals(songList.getSongs().get(songList.getSongs().size() - 1), song);
    }

    // tests for removeSong
    @Test
    public void testremoveSongNull() {
        assertThrows(Exception.class, () -> {
            songList.removeSong(null);
        });
    }

    @Test
    public void testRemoveSongZeroSongs() {
        Song song = songList.getSongs().get(0);
        songList.getSongs().clear();
        assertThrows(Exception.class, () -> {
            songList.removeSong(song);
        });
    }

    @Test
    public void testRemoveSongNotAuthor() throws Exception {
        // userList.logout();
        userList.login(userList.getUsers().get(0).getUsername(), userList.getUsers().get(0).getPassword());
        assertThrows(Exception.class, () -> {
            songList.removeSong(songList.getSongs().get(0));
        });
    }

    @Test
    public void testRemoveSongValid() throws Exception {
        Song removedSong = songList.getSongs().get(0);
        songList.removeSong(songList.getSongs().get(0));

        for (int i = 0; i < songList.getSongs().size(); ++i) {
            assertNotEquals(removedSong, songList.getSongs().get(i));
        }
    }
    // getPublicSongs test

    @Test
    public void testgetPublicSongsNotPublic() {
        Song publishedSong = songList.getSongs().get(0);
        Song notPublished = new Song(songList.getSongs().get(0), userList.getUsers().get(0));
        songList.getSongs().clear();
        songList.getSongs().add(notPublished);
        songList.getSongs().add(publishedSong);
        assertFalse(songList.getPublicSongs().contains(notPublished));

    }

    @Test
    public void testgetPublicSongsValid() {
        Song publishedSong = songList.getSongs().get(0);
        songList.getSongs().clear();
        songList.getSongs().add(publishedSong);
        assertTrue(songList.getPublicSongs().contains(publishedSong));
    }

    // openMySongs tests
    @Test
    public void testgetMySongsEmpty() throws Exception {
        //userList.logout();
        userList.login(userList.getUsers().get(0).getUsername(), userList.getUsers().get(0).getPassword());
        Song notMySong = songList.getSongs().get(0);
        songList.getSongs().clear();
        songList.getSongs().add(notMySong);
        assertFalse(songList.openMySongs().contains(notMySong));
    }

    @Test
    public void testGetMySongsValid() {
        Song mySong = songList.getSongs().get(0);
        songList.getSongs().clear();
        songList.getSongs().add(mySong);
        assertTrue(songList.openMySongs().contains(mySong));
    }

    // tests for openMyFavorites
    @Test
    public void testOpenMyFavoritesNoFavorites() throws Exception {
        Song notMyFav = songList.getSongs().get(0);
        songList.getSongs().add(notMyFav);
        assertFalse(songList.openFavorites().contains(notMyFav));
    }

    @Test
    public void testOpenMyFavoritesValid() throws Exception {
        Song favSong = songList.getSongs().get(0);
        userList.getCurrentUser().toggleFavoriteSong(favSong);
        songList.getSongs().add(favSong);
        assertTrue(songList.openFavorites().contains(favSong));
    }

    // tests for filterSongs
    @Test
    public void testFilterNullTitle() {
        assertThrows(Exception.class, () -> {
            songList.filterSongs(null, "Mary Had A Little Lamb");
        });
    }

    @Test
    public void testFilterNullFilter() {
        assertThrows(Exception.class, () -> {
            songList.filterSongs("title", null);
        });
    }

    // tests for filter by title
    @Test
    public void testFilterBySongTitleNoMatch() {

        assertTrue(songList.filterSongs("title", "gsgregehrwg").isEmpty());
    }

    @Test
    public void testFilterBySongTitleNotMatchingCaps() {
        assertEquals(songList.getSongs().get(0).getSongID(),
                songList.filterSongs("title", "MARY HAD A LITTLE LAMB").get(0).getSongID());
    }

    @Test
    public void testFilterBySongTitleMatchingCaps() {
        assertEquals(songList.getSongs().get(0).getSongID(),
                songList.filterSongs("title", "Mary Had A Little Lamb").get(0).getSongID());
    }

    @Test
    public void testFilterBySongTitleNoMatchingCapsForTitleParam() {
        assertEquals(songList.getSongs().get(0).getSongID(),
                songList.filterSongs("TITLE", "Mary Had A Little Lamb").get(0).getSongID());
    }

    // tests for filter by title
    @Test
    public void testFilterByGenreNoMatch() {
        assertTrue(songList.filterSongs("genre", "EDM").isEmpty());
    }

    @Test
    public void testFilterByGenreNotMatchingCaps() {
        assertTrue(songList.filterSongs("genre", "POP").contains(songList.getSongs().get(0)));
    }

    @Test
    public void testFilterByGenreMatchingCaps() {
        assertTrue(songList.filterSongs("genre", "Pop").contains(songList.getSongs().get(0)));
    }

    @Test
    public void testFilterByGenreNoMatchingCapsForTitleParam() {
        assertTrue(songList.filterSongs("GENRE", "Pop").contains(songList.getSongs().get(0)));
    }

    @Test
    public void testFilterByGenreCheckIfBadGenreNotAdded() {
        assertFalse(songList.filterSongs("genre", "Jazz").contains(songList.getSongs().get(0)));
    }

    @Test
    public void testFilterByBPMOneBPM() {
        assertThrows(Exception.class, () -> {
            songList.filterSongs("BPM", "100");
        });
    }

    @Test
    public void testFilterByBPMNoMatch() {
        assertTrue(songList.filterSongs("BPM", "200 300").isEmpty());
    }

    @Test
    public void testFilterByBPMMatch() {
        assertTrue(songList.filterSongs("BPM", "100 150").contains(songList.getSongs().get(0)));
    }

    @Test
    public void testFilterByBPMMatchUpperLim() {
        assertTrue(songList.filterSongs("BPM", "100 120").contains(songList.getSongs().get(0)));
    }

    @Test
    public void testFilterByBPMMatchLowerLim() {
        assertTrue(songList.filterSongs("BPM", "120 220").contains(songList.getSongs().get(0)));
    }

    @Test
    public void testFilterByBPMIncorrectOrder() {
        assertThrows(Exception.class, () -> {
            songList.filterSongs("BPM", "100 90");
        });
    }

    @Test
    public void testFilterByBPMNoMatchingCapsForTitleParam() {
        assertTrue(songList.filterSongs("bpm", "100 150").contains(songList.getSongs().get(0)));
    }

    // tests for filtering by BPM.

    @Test
    public void testFilterByDifficultyNotNum() {
        assertThrows(Exception.class, () -> {
            songList.filterSongs("difficulty", "h");
        });
    }

    @Test
    public void testFilterByDifficultyNoMatch() {
        assertTrue(songList.filterSongs("difficulty", "3").isEmpty());
    }

    @Test
    public void testFilterByDifficultyMatch() {
        assertTrue(songList.filterSongs("difficulty", "1").contains(songList.getSongs().get(0)));
    }

    @Test
    public void testFilterByDifficultyNoMatchingCapsForTitleParam() {
        assertTrue(songList.filterSongs("DIFFICULTY", "1").contains(songList.getSongs().get(0)));
    }

    // tests for filterByAuthor

    @Test
    public void testFilterByAuthorNoUserMatch() {
        assertTrue(songList.filterSongs("author", "johnny cornsworth").isEmpty());
    }

    @Test
    public void testFilterByAuthorNotAuthor() {
        User newUser = new User("john0000", "fl1ppinburgers45", "john", "johnsworth");
        userList.getUsers().add(newUser);
        assertTrue(songList.filterSongs("author", "john0000").isEmpty());
    }

    @Test
    public void testFilterByAuthorIncorrectCase() {
        assertTrue(songList.filterSongs("author", "jimorris").isEmpty());
    }

    @Test
    public void testFilterByAuthorValid() {
        assertTrue(songList.filterSongs("author", "JIMorris").contains(songList.getSongs().get(0)));
    }

    @Test
    public void testFilterByAuthorNoMatchingCapsForTitleParam() {
        assertTrue(songList.filterSongs("AUTHOR", "JIMorris").contains(songList.getSongs().get(0)));
    }
}
