package com.harmoniamusic;

import java.io.IOException;

import com.model.MusicFacade;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignupController extends Application {
    private MusicFacade musicFacade = MusicFacade.getInstance();

    
    @FXML
    private TextField username_txt;
    
    @FXML
    private TextField password_txt;
    
    @FXML
    private TextField first_txt;
    
    @FXML
    private TextField last_txt;
    
    @FXML
    private Button primaryButton;
    
    @FXML
    private Label logInLabel;

    @FXML
    private Label signupErrorLabel;
    
    @FXML
    private void GoToLogIn() throws IOException {
        App.setRoot("templates/loginTemplate");
    }

    @FXML
    private void SignUp() {
        String newUsername = username_txt.getText();
        String newPassword = password_txt.getText();
        String newFirstName = first_txt.getText();
        String newLastName = last_txt.getText();
        if (newUsername.isBlank() || newPassword.isBlank() || newFirstName.isBlank() || newLastName.isBlank()) {
            signupErrorLabel.setVisible(true);
            return;
        }
        try {
            musicFacade.signup(newUsername, newPassword, newFirstName, newLastName);
            App.setRoot("templates/homeTemplate");
            App.setData("data/homeData");
            App.setBar("topbar/homeBar");
        } catch (Exception e) {
           signupErrorLabel.setVisible(true);
        }
    }

    @Override
    public void start(Stage arg0) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }

}
