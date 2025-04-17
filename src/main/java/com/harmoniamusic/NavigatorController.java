package com.harmoniamusic;

import java.io.IOException;

import com.model.MusicFacade;

public class NavigatorController {
    private MusicFacade musicFacade = MusicFacade.getInstance();

    //methods for moving from home screen

    private void goToFavoriteAuthor() {
        //TODO
    }

    private void goToMySongs() {
        //TODO
    }

    private void gotoFavoritesongs() {
        //TODO
    }

    private void goToPublicSongs() {
        //TODO
    }

    //methods for top bar

    private void goToHome() throws IOException {
        App.setRoot("homeTemplate");
        //TODO
    }

    private void goToSettings() throws IOException {
        //TODO
    }
}
