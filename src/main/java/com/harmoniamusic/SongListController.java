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
import javafx.scene.control.TextField;
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
                    Button playButton = new Button("play song");
                    playButton.setOnMouseClicked(e -> {
                        //TODO
                    });
                    Button addFav =  new Button("add to favorites");
                    addFav.setOnMouseClicked(e -> {
                        musicFacade.toggleFavorite(song);
                    });
                    Label Diff = new Label("Difficulty ");
                    TextField setDiff = new TextField(String.valueOf(song.getDifficulty()));
                    setDiff.setOnAction(e -> {
                        int newDiff = Integer.parseInt(setDiff.getText());
                        song.setDifficulty(newDiff);
                        setDiff.setText(String.valueOf(song.getDifficulty()));
                    });
                    Label description = new Label("Description");
                    TextField setDescription = new TextField(song.getDescription());
                    setDescription.setOnAction(e -> {
                        song.setDescription(setDescription.getText());
                        setDescription.setText(song.getDescription());
                    });
                    // Label Genre = new Label("Genres");
                    // TextField setGenre = new TextField(song.viewGenres());
                    // setDescription.setOnAction(e -> {
                    //     song.set(setDescription.getText());
                    //     setDescription.setText(song.getDescription());
                    // });
                    Button editSong = new Button("edit song");
                    editSong.setOnMouseClicked(e -> {
                        //TODO
                    });
                    Button copySong = new Button("copy song");
                    copySong.setOnMouseClicked(e -> {
                        //TODO
                    });
                    Button deleteSong = new Button("delete song");
                    deleteSong.setOnMouseClicked(e -> {
                        musicFacade.getSongList().removeSong(song);
                        //TODO ???
                    });
                    Button publishSong = new Button("publish song");
                    deleteSong.setOnMouseClicked(e -> {
                        song.changePublish();
                        if (song.isPublished()) {
                            publishSong.setText("unpublish song");
                        } else
                            publishSong.setText("publsh song");
                    });

                    pane1Content.getChildren().addAll(
                            new Label("Ratings: " + song.getAverageRating()),
                            playButton,
                            addFav,
                            Diff,
                            setDiff,
                            description,
                            setDescription,
                            new Label("Genres \n" + song.getGenres()),
                            editSong,
                            copySong,
                            deleteSong,
                            publishSong);
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
