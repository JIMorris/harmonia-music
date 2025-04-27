package com.harmoniamusic;

import java.io.IOException;
import java.util.ArrayList;

import com.model.MusicFacade;
import com.model.Song;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NavigatorController extends Application {
    private MusicFacade musicFacade = MusicFacade.getInstance();

    // methods for moving from home screen

    @FXML
    private Accordion songListAccordion;

    @FXML
    public void initialize() {
        System.out.println("Initialized, songListAccordion: " + (songListAccordion != null) + "\n");
    }

    @FXML
    private void goToFavoriteAuthors() throws IOException {
        App.setBar("topbar/libraryBar");
        App.setData("data/favoriteAuthorsData");
    }

    @FXML
    private void goToMySongs() throws IOException {
        App.setBar("topbar/libraryBar");
        FXMLLoader test = App.setData("data/libraryData");
        SongListController controller = test.getController();
        controller.loadSongs(1);
        controller.changeTitle(1);
    }

    @FXML
    private void gotoFavoritesongs() throws IOException {
        App.setBar("topbar/libraryBar");
        FXMLLoader test = App.setData("data/libraryData");
        SongListController controller = test.getController();
        controller.loadSongs(2);
        controller.changeTitle(2);
    }

    @FXML
    private void goToPublicSongs() throws IOException {
        App.setBar("topbar/libraryBar");
        App.setData("data/libraryData");
        FXMLLoader test = App.setData("data/libraryData");
        SongListController controller = test.getController();
        controller.loadSongs(3);
        controller.changeTitle(3);
    }

    @FXML
    private void createSong() throws IOException {
        App.setBar(null);
        App.setData(null);
        App.setRootAndInitialize("templates/newSongTemplate");
    }

    // methods for top bar

    @FXML
    private void logout() throws IOException {
        App.setRoot("templates/loginTemplate");
        App.setData(null);
        App.setBar(null);
        try {
            musicFacade.logout();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    @FXML
    private void goToHome() throws IOException {
        App.setRoot("templates/homeTemplate");
        App.setBar("topbar/homeBar");
        App.setData("data/homeData");
    }

    @FXML
    private void goToSettings() throws IOException {
        App.setRoot("templates/settingsTemplate");
        App.setBar("topbar/settingsBar");
        App.setData("data/settingsData");
      //  App.setBar(null);
    }

    @Override
    public void start(Stage arg0) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }
}
