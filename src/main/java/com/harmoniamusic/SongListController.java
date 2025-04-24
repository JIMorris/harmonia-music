package com.harmoniamusic;

import java.io.IOException;
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

    @FXML
    private Accordion songListAccordion;

    public void loadSongs(int type) {
        ArrayList<Song> songs = null;
        switch (type) {
            case 1:
                songs = musicFacade.openMySongs();
                for (Song song : songs) {
                    VBox pane1Content = new VBox(10);
                    Button playButton = new Button("play song");
                    playButton.setOnMouseClicked(e -> {
                        SongEditPlayController.openSong(song);
                    });
                    Button addFav = new Button(
                            musicFacade.getUserList().getCurrentUser().isFavoriteSong(song) ? "remove from favorite"
                                    : "add to favorites");
                    addFav.setOnMouseClicked(e -> {
                        musicFacade.toggleFavorite(song);
                        if (musicFacade.getUserList().getCurrentUser().isFavoriteSong(song))
                            addFav.setText("remove from favorite");
                         else
                            addFav.setText("add to favorites");
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
                        setDescription.setText(song.getDescription());
                        song.setDescription(setDescription.getText());
                    });
                    Button editSong = new Button("edit song");
                    editSong.setOnMouseClicked(e -> {
                        SongEditPlayController.editSong(song);
                    });
                    Button copySong = new Button("copy song");
                    copySong.setOnMouseClicked(e -> {
                        SongEditPlayController.editSong(musicFacade.copySong(song));
                    });
                    Button deleteSong = new Button("delete song");
                    deleteSong.setOnMouseClicked(e -> {
                        musicFacade.getSongList().removeSong(song);
                        // TODO will this work for the accordion?
                    });
                    Button publishSong = new Button(song.isPublished() ? "unpublish song" : "publish song");
                    publishSong.setOnMouseClicked(e -> {
                        song.changePublish();
                        if (song.isPublished()) {
                            publishSong.setText("unpublish song");
                        } else
                            publishSong.setText("publish song");
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
                    songListAccordion.getPanes().add(pane1);
                }
                break;
            case 2:
                songs = musicFacade.openFavorites();
                for (Song song : songs) {
                    VBox pane1Content = new VBox(10);
                    Button playButton = new Button("play song");
                    playButton.setOnMouseClicked(e -> {
                        SongEditPlayController.openSong(song);
                    });
                    Button addFav = new Button(
                            musicFacade.getUserList().getCurrentUser().isFavoriteSong(song) ? "remove from favorite"
                                    : "add to favorites");
                    addFav.setOnMouseClicked(e -> {
                        musicFacade.toggleFavorite(song);
                        if (musicFacade.getUserList().getCurrentUser().isFavoriteSong(song))
                            addFav.setText("remove from favorite");
                         else
                            addFav.setText("add to favorites");
                    });
                    Button copySong = new Button("copy song");
                    copySong.setOnMouseClicked(e -> {
                        SongEditPlayController.editSong(musicFacade.copySong(song));
                    });
                    Label publishSong = new Label(song.isPublished() ? "unpublished" : "published");
                    pane1Content.getChildren().addAll(
                            new Label("Ratings: " + song.getAverageRating()),
                            playButton,
                            addFav,
                            new Label("Difficulty: " + song.getDifficulty()),
                            new Label("Description \n" + song.getDescription()),
                            new Label("Genres \n" + song.getGenres()),
                            copySong,
                            publishSong);
                    TitledPane pane1 = new TitledPane(song.getTitle() + " - " + song.getAuthor().getUsername(),
                            pane1Content);
                    songListAccordion.getPanes().add(pane1);
                }
                break;
            case 3:
                songs = musicFacade.openPublicSongs();
                for (Song song : songs) {
                    VBox pane1Content = new VBox(10);
                    Button playButton = new Button("play song");
                    playButton.setOnMouseClicked(e -> {
                        SongEditPlayController.openSong(song);
                    });
                    Button addFav = new Button(
                            musicFacade.getUserList().getCurrentUser().isFavoriteSong(song) ? "remove from favorite"
                                    : "add to favorites");
                    addFav.setOnMouseClicked(e -> {
                        musicFacade.toggleFavorite(song);
                        if (musicFacade.getUserList().getCurrentUser().isFavoriteSong(song))
                            addFav.setText("remove from favorite");
                         else
                            addFav.setText("add to favorites");
                    });
                    Button copySong = new Button("copy song");
                    copySong.setOnMouseClicked(e -> {
                        SongEditPlayController.editSong(musicFacade.copySong(song));
                    });
                    Label publishSong = new Label(song.isPublished() ? "unpublished" : "published");
                    pane1Content.getChildren().addAll(
                            new Label("Ratings: " + song.getAverageRating()),
                            playButton,
                            addFav,
                            new Label("Difficulty: " + song.getDifficulty()),
                            new Label("Description \n" + song.getDescription()),
                            new Label("Genres \n" + song.getGenres()),
                            copySong,
                            publishSong);
                    TitledPane pane1 = new TitledPane(song.getTitle() + " - " + song.getAuthor().getUsername(),
                            pane1Content);
                    songListAccordion.getPanes().add(pane1);
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void start(Stage arg0) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }

}
