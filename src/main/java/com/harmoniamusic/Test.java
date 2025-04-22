package com.harmoniamusic;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Test extends Application {
    @Override
    public void start(Stage primaryStage) {


        VBox pane1Content = new VBox(10);
        pane1Content.getChildren().addAll(
            new Label("User: John Doe"),
            new Label("Email: john@example.com"),
            new Button("Edit Profile")
        );

        TitledPane pane1 = new TitledPane("Pane 1", pane1Content);
        TitledPane pane2 = new TitledPane("Pane 2", new VBox());
        TitledPane pane3 = new TitledPane("Pane 3", new VBox());

        Accordion accordion = new Accordion();
        accordion.getPanes().addAll(pane1, pane2, pane3);

        Scene scene = new Scene(accordion, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Expanding Pane Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}