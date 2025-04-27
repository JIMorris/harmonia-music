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
        System.out.println("\nTHIS METHOD EXISTS AND IS HEREE \n");
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
        App.setRoot("musicTemplate");
        App.setBar("songEditorBar");
        App.setData("songEditorData");
    }

    @FXML
    private void goBack() throws IOException {
        App.setRoot("templates/homeTemplate");
        App.setBar("topbar/homeBar");
        App.setData("data/homeData");
    }

    private void initializeGenreDropdown(){
        genreSelect = new ComboBox<>();
        for(Genre genre : Genre.values()){
            genreSelect.getItems().add(genre);
        }

        genreSelect.setValue(Genre.CLASSICAL);
        genreSelect.setOnAction(e -> {
            selectedGenre = genreSelect.getValue();
        });
    }

    private void initializeKeyDropdown(){
        keySelect = new ComboBox<>();
        for(Key key : Key.values()){
            keySelect.getItems().add(key);
        }

        keySelect.setValue(Key.A_FLAT_MAJOR);
        keySelect.setOnAction(e -> {
            selectedKey = keySelect.getValue();
        });
    }

    private void initializeInstrumentDropdown(){
        instrumentSelect = new ComboBox<>();
        for(Instrument instrument : musicFacade.getAllInstruments()){
            instrumentSelect.getItems().add(instrument);
        }

        instrumentSelect.setValue(musicFacade.getAllInstruments().get(0));
        instrumentSelect.setOnAction(e -> {
            selectedInstrument = instrumentSelect.getValue();
        });
    }

    private void initializeDifficultyDropdown(){
        difficultySelect = new ComboBox<>();
        for(int i=1; i<6; i++){
            difficultySelect.getItems().add(i);
        }

        difficultySelect.setValue(1);
        instrumentSelect.setOnAction(e -> {
            selectedDifficulty = difficultySelect.getValue();
        });
    }
}
