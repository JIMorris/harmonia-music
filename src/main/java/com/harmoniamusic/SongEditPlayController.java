package com.harmoniamusic;

import java.io.IOException;

import com.model.Chord;
import com.model.Instrument;
import com.model.MusicFacade;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SongEditPlayController extends Application {

    MusicFacade musicFacade;
    Chord selectedChord;
    Instrument selectedInstrument;

    @FXML
    private ComboBox<Chord> chordSelect;

    @FXML
    private ComboBox<Instrument> instrumentSelect;

    @FXML
    private TextField tempoText;

    @Override
    public void start(Stage stage) throws IOException {
        musicFacade = MusicFacade.getInstance();
        initializeChordDropdown();
        initializeInstrumentDropdown();
        initializeBPMText();
    }

    @FXML
    private void goToHome() throws IOException {
        App.setRoot("homeTemplate");
        App.setBar("homeBar");
        App.setData("homeData");
    }

    @FXML
    private void play(){
        musicFacade.playSong();
    }

    @FXML
    private void pause(){
        musicFacade.stopSong();
    }

    @FXML
    private void combineNotes() throws Exception{
        musicFacade.combineNotes(); //TODO Exception
    }

    @FXML
    private void splitNotes2()  throws Exception{
        musicFacade.splitNote(2); //TODO Exception
    }

    @FXML
    private void splitNotes4() throws Exception{
        musicFacade.splitNote(4); //TODO Exception
    }

    @FXML
    private void noteUp() throws Exception{
        musicFacade.noteUp(); //TODO Exception
    }

    @FXML
    private void insertNote(){
        musicFacade.insertNote();
    }

    @FXML
    private void removeNote(){
        musicFacade.deleteNote();
    }

    @FXML 
    private void noteDown() throws Exception{
        musicFacade.noteDown(); //TODO Exception
    }

    @FXML
    private void insertChord(){
        musicFacade.insertChord();
    }

    @FXML
    private void removeChord(){
        musicFacade.deleteNote();
    }

    @FXML
    private void insertMeasure(){
        musicFacade.insertMeasure();
    }

    @FXML
    private void removeMeasure() throws Exception{
        musicFacade.removeMeasure(); //TODO Exception
    }

    @FXML
    private void openSongInfo() throws IOException {
        // TODO
    }

    private void initializeChordDropdown(){
        for(Chord chord : Chord.values()){
            chordSelect.getItems().add(chord);
        }

        chordSelect.setValue(Chord.A_FLAT_MAJ);
        chordSelect.setOnAction(e -> {
            selectedChord = chordSelect.getValue();
            musicFacade.setChord(selectedChord);
        });
    }

    private void initializeInstrumentDropdown(){
        for(Instrument instrument : musicFacade.getInstruments()){
            instrumentSelect.getItems().add(instrument);
        }

        instrumentSelect.setValue(musicFacade.getInstruments().get(0));
        instrumentSelect.setOnAction(e -> {
            selectedInstrument = instrumentSelect.getValue();
            musicFacade.selectInstrument(selectedInstrument);
        });
    }

    private void initializeBPMText() {
        int tempo = musicFacade.getBPM();
        tempoText.setText(Integer.toString(tempo));

        tempoText.setOnAction(e -> {
            int newTempo = Integer.parseInt(tempoText.getText());
            try {
                musicFacade.setBPM(newTempo); //TODO Exception
            } catch (Exception ex) {
                int originalTempo = musicFacade.getBPM();
                tempoText.setText(Integer.toString(tempo));
                System.out.println(e);
            }
             
        });
    }
}
