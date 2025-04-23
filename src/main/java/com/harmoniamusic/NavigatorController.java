package com.harmoniamusic;

import java.io.IOException;

import com.model.MusicFacade;

import javafx.fxml.FXML;

public class NavigatorController {
    private MusicFacade musicFacade = MusicFacade.getInstance();

    //methods for moving from home screen

    @FXML
    private void goToFavoriteAuthors() throws IOException {
        App.setBar("topbar/libraryBar");
        App.setData("data/favoriteAuthorsData");
    }

    @FXML
    private void goToMySongs() throws IOException {
        App.setBar("topbar/libraryBar");
        App.setData("data/mySongsData");
        SongListController.loadSongs(1);
    }

    @FXML
    private void gotoFavoritesongs() throws IOException {
        App.setBar("topbar/libraryBar");
        App.setData("data/favoriteSongsData");
        SongListController.loadSongs(2);
    }

    @FXML
    private void goToPublicSongs() throws IOException { 
        App.setBar("topbar/libraryBar");
        App.setData("data/publicSongsData");
        SongListController.loadSongs(3);
    }

    //methods for top bar

    @FXML
    private void goToHome() throws IOException {
        App.setBar("topbar/homeBar");
        App.setData("data/homeData");
    }

    @FXML
    private void goToSettings() throws IOException {
        App.setBar("topbar/settingsBar");
        App.setData("data/settingsData");
    }
}
