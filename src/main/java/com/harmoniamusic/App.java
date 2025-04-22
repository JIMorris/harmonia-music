package com.harmoniamusic;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @FXML
    private static AnchorPane bar;

    @FXML
    private static AnchorPane data;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("loginTemplate"), 640, 480);
        stage.setScene(scene);
        stage.show();
       // setData("loginData");
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static void setBar(String fxml) throws IOException {
        Parent view = loadFXML(fxml);
        bar.getChildren().setAll(view);
    }

    static void setData(String fxml) throws IOException {
        Parent view = loadFXML(fxml);
        data.getChildren().setAll(view);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}