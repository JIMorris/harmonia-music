package com.harmoniamusic;

import java.io.IOException;

import com.model.MusicFacade;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SignupController {
    private MusicFacade musicFacade = MusicFacade.getInstance();

    @FXML
    private void GoToLogIn() throws IOException {
        App.setData("loginData");
        // this somehow switches to the login stuff
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
    private void SignUp() {
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
                App.setData("homeData");
            } catch (Exception e) {
                // I DON'T KNOW WHAT THIS EXCEPTION SHOULD THROW
                e.printStackTrace();
            }
        }
    }

}
