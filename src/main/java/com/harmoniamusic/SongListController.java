package com.harmoniamusic;

import java.util.ArrayList;

import com.model.MusicFacade;
import com.model.Song;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SongListController extends Application {
    private static MusicFacade musicFacade = MusicFacade.getInstance();

    // @FXML
    // private static ListView<Song> listView;
    // public static void loadsongs(int type) {
    // // ArrayList<Song> songs;
    // //ListView<Song> listView = null;
    // ObservableList<Song> songs;
    // switch (type) {
    // case 1:
    // songs = FXCollections.observableArrayList(musicFacade.openMySongs());
    // listView = new ListView<>(songs);
    // break;
    // case 2:
    // songs = FXCollections.observableArrayList(musicFacade.openFavorites());
    // listView = new ListView<>(songs);
    // break;
    // case 3:
    // songs = FXCollections.observableArrayList(musicFacade.openPublicSongs());
    // listView = new ListView<>(songs);
    // break;

    // default:
    // break;
    // }
    // // VBox root = new VBox(listView);
    // // Scene scene = new Scene(root, 300, 200);
    // }
    @FXML
    private static Accordion accordion;

    public static void loadSongs(int type) {
        ArrayList<Song> songs = null;
        accordion = new Accordion();
        switch (type) {
            case 1:
                songs = musicFacade.openMySongs();
                for (Song song : songs) {
                    VBox pane1Content = new VBox(10);
                    pane1Content.getChildren().addAll(
                            new Label("Difficulty " + song.getDifficulty()),
                            new Label("Description \n" + song.getDescription()),
                            new Label("Genres \n" + song.getGenres()));
                    TitledPane pane1 = new TitledPane(song.getTitle() + " - " + song.getAuthor().getUsername(),
                            pane1Content);
                    accordion.getPanes().add(pane1);
                }
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

    public void songDropdown() {
    }

    @Override
    public void start(Stage arg0) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }

}
