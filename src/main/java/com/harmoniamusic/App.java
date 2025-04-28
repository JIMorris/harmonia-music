package com.harmoniamusic;

import java.io.IOException;

import javafx.application.Application;
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

    
    private static AnchorPane bar;

    
    private static AnchorPane data;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("templates/loginTemplate"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent view = fxmlLoader.load();
        scene.setRoot(view);
        
    }
    
    static void setRootAndInitialize(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent view = loader.load();  // This will also initialize the controller
        scene.setRoot(view);  // Set the scene root
    }
    

    static FXMLLoader setBar(String fxml) throws IOException {
        if (fxml == null) {
            bar.getChildren().clear();
            return null;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent view = fxmlLoader.load();
        bar.getChildren().setAll(view);
        return fxmlLoader;
    }

    static FXMLLoader setData(String fxml) throws IOException {
        if (fxml == null) {
            data.getChildren().clear();
            return null;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent view = fxmlLoader.load();
        data.getChildren().setAll(view);
        return fxmlLoader;
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void setBarPane(AnchorPane barPane) {
        bar = barPane;
    }
    
    public static void setDataPane(AnchorPane dataPane) {
        data = dataPane;
    }
    

    public static void main(String[] args) {
        launch();
    }

}