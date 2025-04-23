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
    private TextField username_txt1;
    
    @FXML
    private TextField password_txt1;
    
    @FXML
    private TextField first_txt;
    
    @FXML
    private TextField last_txt;
    
    @FXML
    private Button primaryButton;
    
    @FXML
    private Label logInLabel;
    
    @FXML
    private void GoToLogIn() throws IOException {
        App.setRoot("templates/loginTemplate");
    }

    @FXML
    private void SignUp() {
        String newUsername = username_txt1.getText();
        String newPassword = password_txt1.getText();
        String newFirstName = first_txt.getText();
        String newLastName = last_txt.getText();
        try {
            musicFacade.signup(newUsername, newPassword, newFirstName, newLastName);
            App.setRoot("homeTemplate");
            App.setData("homeData");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage arg0) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }

}
