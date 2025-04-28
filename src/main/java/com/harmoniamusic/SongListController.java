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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SongListController extends Application {
    private static MusicFacade musicFacade = MusicFacade.getInstance();

    @FXML
    private Accordion songListAccordion;

    @FXML
    private Label libraryTitle;

    public void changeTitle(int type) {
        switch (type) {
            case 1:
                libraryTitle.setText("My Songs");
                break;
            case 2:
                libraryTitle.setText("My Favorites");
                break;
            case 3:
                libraryTitle.setText("Public Songs");
                break;
            default:
                break;
        }
    }

    public void loadSongs(int type) {
        ArrayList<Song> songs = null;
        switch (type) {
            case 1:
                songs = musicFacade.openMySongs();
                for (Song song : songs) {
                    VBox pane1Content = new VBox(10);
                    TitledPane pane1 = new TitledPane(song.getTitle() + " - " + song.getAuthor().getUsername(),
                            pane1Content);

                    Label ratings = new Label ("Ratings " + song.getAverageRating());
                    Label ratingLabel = new Label ("add rating");
                    TextField addRating = new TextField();
                    Label invalid = new Label("ratings must be between 1-5");
                    invalid.setVisible(false);
                    addRating.setPromptText("1-5");
                    addRating.textProperty().addListener((observable, oldValue, newValue) -> {
                        try {
                            int rating = Integer.parseInt(newValue);
                            if (rating >= 1 && rating <= 5) {
                                song.addReaction(rating, null, musicFacade.getUserList().getCurrentUser());
                                ratings.setText("Ratings " + song.getAverageRating());
                                addRating.setText("");
                                invalid.setVisible(false);
                            } else {
                                invalid.setVisible(true);
                            }
                            addRating.setText("");
                        } catch (NumberFormatException e) {
                        }
                    });
                    HBox ratingInputRow = new HBox(10);
                    ratingInputRow.getChildren().addAll(ratingLabel, addRating, invalid);

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
                    setDescription.textProperty().addListener((observable, oldValue, newValue) -> {
                        song.setDescription(newValue);
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
                        songListAccordion.getPanes().remove(pane1);
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
                            ratings,
                            ratingInputRow,
                            playButton,
                            addFav,
                            Diff,
                            setDiff,
                            description,
                            setDescription,
                            new Label("Genres: " + song.getGenreLabels()),
                            editSong,
                            copySong,
                            deleteSong,
                            publishSong);
                    songListAccordion.getPanes().add(pane1);
                }
                break;
            case 2:
                songs = musicFacade.openFavorites();
                for (Song song : songs) {
                    VBox pane1Content = new VBox(10);

                    Label ratings = new Label ("Ratings " + song.getAverageRating());
                    Label ratingLabel = new Label ("add rating");
                    TextField addRating = new TextField();
                    Label invalid = new Label("ratings must be between 1-5");
                    invalid.setVisible(false);
                    addRating.setPromptText("1-5");
                    addRating.textProperty().addListener((observable, oldValue, newValue) -> {
                        try {
                            int rating = Integer.parseInt(newValue);
                            if (rating >= 1 && rating <= 5) {
                                song.addReaction(rating, null, musicFacade.getUserList().getCurrentUser());
                                ratings.setText("Ratings " + song.getAverageRating());
                                addRating.setText("");
                                invalid.setVisible(false);
                            } else {
                                invalid.setVisible(true);
                            }
                            addRating.setText("");
                        } catch (NumberFormatException e) {
                        }
                    });
                    HBox ratingInputRow = new HBox(10);
                    ratingInputRow.getChildren().addAll(ratingLabel, addRating, invalid);

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
                    Label publishSong = new Label(song.isPublished() ? "published" : "unpublished");
                    pane1Content.getChildren().addAll(
                            ratings,
                            ratingInputRow,
                            playButton,
                            addFav,
                            new Label("Difficulty: " + song.getDifficulty()),
                            new Label("Description \n" + song.getDescription()),
                            new Label("Genres: " + song.getGenreLabels()),
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

                    Label ratings = new Label ("Ratings " + song.getAverageRating());
                    Label ratingLabel = new Label ("add rating");
                    TextField addRating = new TextField();
                    Label invalid = new Label("ratings must be between 1-5");
                    invalid.setVisible(false);
                    addRating.setPromptText("1-5");
                    addRating.textProperty().addListener((observable, oldValue, newValue) -> {
                        try {
                            int rating = Integer.parseInt(newValue);
                            if (rating >= 1 && rating <= 5) {
                                song.addReaction(rating, null, musicFacade.getUserList().getCurrentUser());
                                ratings.setText("Ratings " + song.getAverageRating());
                                addRating.setText("");
                                invalid.setVisible(false);
                            } else {
                                invalid.setVisible(true);
                            }
                            addRating.setText("");
                        } catch (NumberFormatException e) {
                        }
                    });
                    HBox ratingInputRow = new HBox(10);
                    ratingInputRow.getChildren().addAll(ratingLabel, addRating, invalid);

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
                    Label publishSong = new Label(song.isPublished() ? "published" : "unpublished");
                    pane1Content.getChildren().addAll(
                            ratings,
                            ratingInputRow,
                            playButton,
                            addFav,
                            new Label("Difficulty: " + song.getDifficulty()),
                            new Label("Description \n" + song.getDescription()),
                            new Label("Genres: " + song.getGenreLabels()),
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
