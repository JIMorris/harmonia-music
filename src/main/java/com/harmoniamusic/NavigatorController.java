package com.harmoniamusic;

import java.io.IOException;

import com.model.MusicFacade;

public class NavigatorController {
    private MusicFacade musicFacade = MusicFacade.getInstance();

    //methods for moving from home screen

    private void goToFavoriteAuthors() throws IOException {
        App.setBar("libraryBar");
        App.setData("favoriteAuthorsData");
    }

    private void goToMySongs() throws IOException {
        App.setBar("libraryBar");
        App.setData("mySongsData");
    }

    private void gotoFavoritesongs() throws IOException {
        App.setBar("libraryBar");
        App.setData("favoriteSongsData");
    }

    private void goToPublicSongs() throws IOException { 
        App.setBar("libraryBar");
        App.setData("publicSongsData");
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
