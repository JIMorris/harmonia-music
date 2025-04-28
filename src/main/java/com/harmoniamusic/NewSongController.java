package com.harmoniamusic;

import java.io.IOException;
import java.util.ArrayList;

import com.model.Genre;
import com.model.Instrument;
import com.model.Key;
import com.model.MusicFacade;
import com.model.Song;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewSongController extends Application {

    MusicFacade musicFacade;
    Genre selectedGenre;
    Key selectedKey;
    Instrument selectedInstrument;
    int selectedDifficulty;

    @FXML
    private TextField titleText;

    @FXML
    private TextField descriptionText;

    @FXML
    private ComboBox<Genre> genreSelect;

    @FXML
    private ComboBox<Key> keySelect;

    @FXML
    private ComboBox<Instrument> instrumentSelect;

    @FXML
    private ComboBox<Integer> difficultySelect;

    @Override
    public void start(Stage stage) throws IOException {
    }

    @FXML
    public void initialize() {
        musicFacade = MusicFacade.getInstance();
        titleText.setPromptText("Enter title here...");
        descriptionText.setPromptText("Enter description here...");
        initializeGenreDropdown();
        initializeKeyDropdown();
        initializeInstrumentDropdown();
        initializeDifficultyDropdown();
    }

    @FXML
    private void createNewSong() throws IOException {
        ArrayList<Genre> genres = new ArrayList<>();
        genres.add(selectedGenre);
        Song newSong = musicFacade.newSong(titleText.getText(), descriptionText.getText(), genres, selectedDifficulty, 120, selectedKey, selectedInstrument);
        musicFacade.openSong(newSong);
        SongEditPlayController.editSong(newSong);
    }

    @FXML
    private void goBack() throws IOException {
        App.setRoot("templates/homeTemplate");
        App.setBar("topbar/homeBar");
        App.setData("data/homeData");
    }

    private void initializeGenreDropdown(){
        genreSelect.getItems().clear();
        for(Genre genre : Genre.values()){
            genreSelect.getItems().add(genre);
        }

        selectedGenre = Genre.CLASSICAL;
        genreSelect.setValue(selectedGenre);
        genreSelect.setOnAction(e -> {
            selectedGenre = genreSelect.getValue();
        });
    }

    private void initializeKeyDropdown(){
        keySelect.getItems().clear();
        for(Key key : Key.values()){
            keySelect.getItems().add(key);
        }

        selectedKey = Key.A_FLAT_MAJOR;
        keySelect.setValue(selectedKey);
        keySelect.setOnHidden(e -> {
            selectedKey = keySelect.getValue();
        });
    }

    private void initializeInstrumentDropdown(){
        instrumentSelect.getItems().clear();
        for(Instrument instrument : musicFacade.getAllInstruments()){
            instrumentSelect.getItems().add(instrument);
        }

        selectedInstrument = musicFacade.getAllInstruments().get(0);
        instrumentSelect.setValue(selectedInstrument);
        instrumentSelect.setOnHidden(e -> {
            selectedInstrument = instrumentSelect.getValue();
        });

        instrumentSelect.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Instrument instrument, boolean empty) {
                super.updateItem(instrument, empty);
                setText(empty || instrument == null ? null : instrument.getName());
            }
        });
        instrumentSelect.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Instrument instrument, boolean empty) {
                super.updateItem(instrument, empty);
                setText(empty || instrument == null ? null : instrument.getName());
            }
        });
    }

    private void initializeDifficultyDropdown(){
        difficultySelect.getItems().clear();
        for(int i=1; i<6; i++){
            difficultySelect.getItems().add(i);
        }

        selectedDifficulty = 1;
        difficultySelect.setValue(selectedDifficulty);
        difficultySelect.setOnHidden(e -> {
            selectedDifficulty = difficultySelect.getValue();
        });
    }
}
