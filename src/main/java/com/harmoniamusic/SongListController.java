package com.harmoniamusic;

import java.util.ArrayList;

import com.model.MusicFacade;
import com.model.SongList;
import com.model.UserList;
import com.model.Song;

public class SongListController {
    private static MusicFacade musicFacade = MusicFacade.getInstance();

    public static void loadsongs(int type) {
        ArrayList<Song> songs;
        switch (type) {
            case 1:
                songs = musicFacade.openMySongs();
                break;
            case 2:
                songs = musicFacade.openFavorites();
                break;
            case 3:
                songs = musicFacade.openPublicSongs();
                break;

            default:
                break;
        }

    }
}
