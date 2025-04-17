package com.harmoniamusic;

import java.io.IOException;

import com.model.MusicFacade;

public class NavigatorController {
    private MusicFacade musicFacade = MusicFacade.getInstance();

    //methods for moving from home screen

    private void goToFavoriteAuthor() throws IOException {
        App.setBar("libraryBar");
        App.setData("favoriteAuthorsData");
    }

    private void goToMySongs() throws IOException {
        App.setBar("libraryBar");
        App.setData("mySongsData");
        SongListController.loadsongs(1);
    }

    private void gotoFavoritesongs() throws IOException {
        App.setBar("libraryBar");
        App.setData("favoriteSongsData");
        SongListController.loadsongs(2);
    }

    private void goToPublicSongs() throws IOException { 
        App.setBar("libraryBar");
        App.setData("publicSongsData");
        SongListController.loadsongs(3);
    }

    //methods for top bar

    private void goToHome() throws IOException {
        App.setBar("homeBar");
        App.setData("homeData");
    }

    private void goToSettings() throws IOException {
        App.setBar("settingsBar");
        App.setData("settingsData");
    }
}
