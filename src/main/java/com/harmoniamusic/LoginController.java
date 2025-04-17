package com.harmoniamusic;

import java.io.IOException;

import com.model.MusicFacade;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController {
    private MusicFacade musicFacade = MusicFacade.getInstance();

    @FXML
    private Label Register;

    @FXML
    private TextField password_txt;

    @FXML
    private Button primaryButton;

    @FXML
    private TextField username_txt;

    @FXML
    private void LogIn(MouseEvent event) {
        String username = username_txt.getText();
        String password = password_txt.getText();
        try {
            musicFacade.login(username, password);
            App.setRoot("homeTemplate");
        } catch (Exception e) {
            // DO SOMETHING
        }
    }

    @FXML
    private void GoToSignUp(MouseEvent event) throws IOException {
        // this somehow switches to the signup stuff
    }

    @FXML
    private void GoToLogIn(MouseEvent event) throws IOException {
        // this somehow switches to the signup stuff
    }

    @FXML
    private TextField newUsername_txt;

    @FXML
    private TextField newPassword_txt;

    @FXML
    private TextField ConfirmPassword_txt;

    @FXML
    private TextField newFirstName_txt;

    @FXML
    private TextField newLastName_txt;

    @FXML
    private void SignUp(MouseEvent event) {
        String newUsername = newUsername_txt.getText();
        String newPassword = newPassword_txt.getText();
        String ConfirmPassword = ConfirmPassword_txt.getText();
        String newFirstName = newFirstName_txt.getText();
        String newLastName = newLastName_txt.getText();
        if (!newPassword.equals(ConfirmPassword)) {
            newPassword_txt.clear();
            ConfirmPassword_txt.clear();
            // DO SOMETHING TO DISPLAY MISMATCHING PASSWORDS
        } else {
            try {
                musicFacade.signup(newUsername, ConfirmPassword, newFirstName, newLastName);
                App.setRoot("homeTemplate");
            } catch (Exception e) {
                // I DON'T KNOW WHAT THIS EXCEPTION SHOULD THROW
                e.printStackTrace();
            }
        }
    }

}
