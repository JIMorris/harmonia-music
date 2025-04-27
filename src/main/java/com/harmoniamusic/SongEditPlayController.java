package com.harmoniamusic;

import java.io.IOException;
import java.util.ArrayList;

import com.model.Chord;
import com.model.Instrument;
import com.model.Measure;
import com.model.MusicFacade;
import com.model.Note;
import com.model.Song;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SongEditPlayController extends Application {

    static MusicFacade musicFacade;
    static SongEditPlayController barController;
    static SongEditPlayController dataController;


    Chord currentChord;
    Instrument currentInstrument;

    @FXML
    private ComboBox<Chord> chordSelect;

    @FXML
    private ComboBox<Instrument> instrumentSelect;

    @FXML
    private TextField tempoText;

    @FXML
    private GridPane sheetMusic;

    @FXML
    private Text title;

    private static boolean editing;
    private int col;
    private static final int SCALE = 10;

    public static void openSong(Song song){
        musicFacade = MusicFacade.getInstance();
        musicFacade.openSong(song);
        try {
            App.setRoot("templates/musicTemplate");
            barController = App.setBar("topbar/songPlayerBar").getController();
            dataController = App.setData("data/songPlayerData").getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        dataController.title.setText(song.getTitle());
        editing = false;
        update();
    }

    public static void editSong(Song song){
        musicFacade = MusicFacade.getInstance();
        musicFacade.openSong(song);
        try {
            App.setRoot("templates/musicTemplate");
            barController = App.setBar("topbar/songEditorBar").getController();
            dataController = App.setData("data/songEditorData").getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        dataController.title.setText(song.getTitle());
        editing = true;
        update();
    }

    private static Image getMusicImage(String file){
        String filePath = "sheetMusic/" + file + ".png";
        return new Image(App.class.getResource(filePath).toString());
    }

    private static void update(){
        dataController.updateSheetMusic();
        barController.updateBar();
    }

    private void updateSheetMusic() {
        sheetMusic.getChildren().clear();
        sheetMusic.getRowConstraints().clear();
        sheetMusic.getColumnConstraints().clear();

        col = -1;

        sheetMusic.setGridLinesVisible(false);

        for (int i = 0; i < 16; i++) {
            RowConstraints rc = new RowConstraints(1*SCALE);
            rc.setMinHeight(1*SCALE);
            sheetMusic.getRowConstraints().add(rc);
        }

        sheetMusicSetup();

        ArrayList<Measure> measures = musicFacade.getMeasures();
        for(Measure measure : measures){
            sheetMusicMeasure(measure);
        }
    }

    private void sheetMusicMeasure(Measure measure){
        ArrayList<Note> notes = measure.getNotes();

        for(Note note : notes){
            sheetMusicNote(note);
        }

        sheetMusicBarLine();
    }

    private void sheetMusicNote(Note note){
        sheetMusic.getColumnConstraints().add(new ColumnConstraints(6*SCALE));
        col++;
        sheetMusicStaffLines();

        String iconName = note.getMusicIcon();
        Image icon = getMusicImage(iconName);

        ImageView noteImage;

        if(musicFacade.getAudioPlayer().isCurrentNote(note))
            icon = selectNote(icon);

        noteImage = new ImageView(icon);
        
        noteImage.setFitWidth(4*SCALE);
        noteImage.setFitHeight(12*SCALE);

        noteImage.setOnMouseClicked(e -> {
            musicFacade.selectNote(note);
            update();
        });

        StackPane noteCell = new StackPane();
        noteCell.getChildren().add(noteImage);
        StackPane.setAlignment(noteImage, Pos.CENTER);

        int row = note.getMusicPosition();
        sheetMusic.add(noteCell, col, row);
    }

    public WritableImage selectNote(Image inputImage) {
        Color newColor = new Color(0, 0.5, 1, 1);

        int width = (int) inputImage.getWidth();
        int height = (int) inputImage.getHeight();

        WritableImage outputImage = new WritableImage(width, height);
        PixelReader reader = inputImage.getPixelReader();
        PixelWriter writer = outputImage.getPixelWriter();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = reader.getColor(x, y);
                if (color.getOpacity() > 0) {
                    writer.setColor(x, y, newColor);
                } else {
                    writer.setColor(x, y, Color.TRANSPARENT);
                }
            }
        }


        return outputImage;
    }

    private void sheetMusicStaffLines(){
        for (int i=4; i<13; i+=2) {
            Pane staffLine = new Pane();
            staffLine.setPrefHeight(2);
            staffLine.setMaxHeight(2);
            staffLine.setStyle("-fx-background-color: black;");

            StackPane cell = new StackPane();
            cell.getChildren().add(staffLine);
            StackPane.setAlignment(staffLine, Pos.CENTER);

            sheetMusic.add(cell, col, i);
        }
    }

    private void sheetMusicBarLine(){
        sheetMusic.getColumnConstraints().add(new ColumnConstraints(2));
        col++;

        for(int i=4; i<13; i++){
            Pane staffLine = new Pane();
            staffLine.setPrefWidth(2);
            staffLine.setMaxWidth(2);
            staffLine.setStyle("-fx-background-color: black;");
    
            StackPane cell = new StackPane();
            cell.getChildren().add(staffLine);

            StackPane.setAlignment(staffLine, Pos.CENTER);

            sheetMusic.add(cell, col, i);
        }
    }

    private void sheetMusicSetup(){
        sheetMusicBarLine();
        sheetMusicClef();
        // sheetMusicKeySignature();
        sheetMusicTimeSignature();
        sheetMusic.getColumnConstraints().add(new ColumnConstraints(4*SCALE));
        col++;
        sheetMusicStaffLines();
    }

    private void sheetMusicClef(){
        sheetMusic.getColumnConstraints().add(new ColumnConstraints(10*SCALE));
        col++;
        sheetMusicStaffLines();

        ImageView note = new ImageView(getMusicImage("trebleClef"));
        note.setFitWidth(6*SCALE);
        note.setFitHeight(15*SCALE);
        StackPane noteCell = new StackPane();
        noteCell.getChildren().add(note);
        StackPane.setAlignment(note, Pos.CENTER);
        sheetMusic.add(noteCell, col, 8);
    }

    private void sheetMusicKeySignature(){
        sheetMusic.getColumnConstraints().add(new ColumnConstraints(20*SCALE));
        col++;
        sheetMusicStaffLines();

        ImageView note = new ImageView(getMusicImage("error"));
        note.setFitWidth(10*SCALE);
        note.setFitHeight(10*SCALE);
        StackPane noteCell = new StackPane();
        noteCell.getChildren().add(note);
        StackPane.setAlignment(note, Pos.CENTER);
        sheetMusic.add(noteCell, col, 8);
    }

    private void sheetMusicTimeSignature(){
        sheetMusic.getColumnConstraints().add(new ColumnConstraints(10*SCALE));
        col++;
        sheetMusicStaffLines();

        ImageView note = new ImageView(getMusicImage("timeSignature"));
        note.setFitWidth(4*SCALE);
        note.setFitHeight(8*SCALE);
        StackPane noteCell = new StackPane();
        noteCell.getChildren().add(note);
        StackPane.setAlignment(note, Pos.CENTER);
        sheetMusic.add(noteCell, col, 8);
    }

    private void updateBar(){
        updateInstrumentDropdown();
        if(editing){
            updateChordDropdown();
            updateBPMText();
        }
    }

    private void updateChordDropdown(){
        chordSelect.getItems().clear();
        chordSelect.getItems().addAll(Chord.values());
        chordSelect.setValue(musicFacade.getAudioPlayer().getCurrentChord());

        chordSelect.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Chord chord, boolean empty) {
                super.updateItem(chord, empty);
                setText(empty || chord == null ? null : chord.label);
            }
        });
        chordSelect.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Chord chord, boolean empty) {
                super.updateItem(chord, empty);
                setText(empty || chord == null ? null : chord.label);
            }
        });

        chordSelect.setOnHidden(e -> {
            Chord selection = chordSelect.getSelectionModel().getSelectedItem();
            if (selection != currentChord) {
                currentChord = chordSelect.getValue();
                musicFacade.setChord(currentChord);
                update();
            }
        });
    }

    private void updateInstrumentDropdown(){
        instrumentSelect.getItems().clear();
        instrumentSelect.getItems().addAll(musicFacade.getInstruments());
        instrumentSelect.setValue(musicFacade.getAudioPlayer().getCurrentInstrument());

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


        instrumentSelect.setOnHidden(e -> {
            Instrument selection = instrumentSelect.getSelectionModel().getSelectedItem();
            if (selection != currentInstrument) {
                currentInstrument = instrumentSelect.getValue();
                musicFacade.selectInstrument(currentInstrument);
                update();
            }
        });
    }

    private void updateBPMText() {
        int tempo = musicFacade.getBPM();
        tempoText.setText(Integer.toString(tempo));

        tempoText.setOnInputMethodTextChanged(e -> {
            try {
                int newTempo = Integer.parseInt(tempoText.getText());
                musicFacade.setBPM(newTempo);
            } catch (Exception ex) {
                tempoText.setText(Integer.toString(tempo));
                System.out.println(e);
            }
        });
    }

    @FXML
    private void goToHome() throws IOException {
        musicFacade.stopSong();
        App.setRoot("templates/homeTemplate");
        App.setBar("topbar/homeBar");
        App.setData("data/homeData");
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
        musicFacade.combineNotes();
        update();
    }

    @FXML
    private void splitNotes2()  throws Exception{
        musicFacade.splitNote(2);
        update();
    }

    @FXML
    private void splitNotes4() throws Exception{
        musicFacade.splitNote(4);
        update();
    }

    @FXML
    private void noteUp() throws Exception{
        musicFacade.noteUp();
        update();
    }

    @FXML
    private void insertNote(){
        musicFacade.insertNote();
        update();
    }

    @FXML
    private void removeNote(){
        musicFacade.deleteNote();
        update();
    }

    @FXML 
    private void noteDown() throws Exception{
        musicFacade.noteDown();
        update();
    }

    @FXML
    private void insertChord(){
        musicFacade.insertChord();
        update();
    }

    @FXML
    private void removeChord(){
        musicFacade.deleteNote();
        update();
    }

    @FXML
    private void insertMeasure(){
        musicFacade.insertMeasure();
        update();
    }

    @FXML
    private void removeMeasure() throws Exception{
        musicFacade.removeMeasure();
        update();
    }

    @Override
    public void start(Stage stage) throws IOException {
        
    }
}
