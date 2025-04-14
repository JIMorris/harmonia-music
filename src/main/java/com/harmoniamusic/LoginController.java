package com.harmoniamusic;

import java.io.IOException;

import com.model.MusicFacade;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController {

    @FXML
    private Label Register;

    @FXML
    private TextField password_txt;

    @FXML
    private Button primaryButton;

    @FXML
    private TextField username_txt;

    @FXML
    void LogIn(MouseEvent event) {
        String username = username_txt.getText();
        String password = password_txt.getText();
        MusicFacade musicFacade = MusicFacade.getInstance();
        //musicFacade.login(username, password);
    }

    @FXML
    void signUp(MouseEvent event) throws IOException {
        App.setRoot("secondary");
    }

}
