package com.harmoniamusic;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class HomeTemplateController {

    @FXML
    private AnchorPane bar;

    @FXML
    private AnchorPane data;

    @FXML
    public void initialize() {
        App.setBarPane(bar);
        App.setDataPane(data);
    }
}

