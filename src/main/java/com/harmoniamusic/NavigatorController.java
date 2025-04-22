package com.harmoniamusic;

import java.io.IOException;

import com.model.MusicFacade;

import javafx.fxml.FXML;

public class NavigatorController {
    private MusicFacade musicFacade = MusicFacade.getInstance();

    //methods for moving from home screen

    @FXML
    private void goToFavoriteAuthors() throws IOException {
        App.setBar("libraryBar");
        App.setData("favoriteAuthorsData");
    }

    @FXML
    private void goToMySongs() throws IOException {
        App.setBar("libraryBar");
        App.setData("mySongsData");
        SongListController.loadsongs(1);
    }

    @FXML
    private void gotoFavoritesongs() throws IOException {
        App.setBar("libraryBar");
        App.setData("favoriteSongsData");
        SongListController.loadsongs(2);
    }

    @FXML
    private void goToPublicSongs() throws IOException { 
        App.setBar("libraryBar");
        App.setData("publicSongsData");
        SongListController.loadsongs(3);
    }

    //methods for top bar

    @FXML
    private void goToHome() throws IOException {
        App.setBar("homeBar");
        App.setData("homeData");
    }

    @FXML
    private void goToSettings() throws IOException {
        App.setBar("settingsBar");
        App.setData("settingsData");
    }
}
