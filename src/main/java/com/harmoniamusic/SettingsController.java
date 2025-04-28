package com.harmoniamusic;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import com.model.MusicFacade;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

public class SettingsController {
    private MusicFacade musicFacade = MusicFacade.getInstance();

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField username;
    
    @FXML
    private TextField password;
    
    @FXML
    private Button LogoutButton;

    @FXML
    private Button deleteAccount;

    @FXML
    private Label changeSuccess;

    @FXML
    private void goToHome() throws IOException {
        App.setRoot("templates/homeTemplate");
        App.setData("data/homeData");
        App.setBar("topbar/homeBar");
    }

    @FXML
    private void saveChanges() {
        if (!firstName.getText().isBlank()) {
            String newFirst = firstName.getText();
            musicFacade.getCurrentUser().setFirstName(newFirst);
            changeSuccess.setVisible(true);
        }
        if (!lastName.getText().isBlank()) {
            String newLast = lastName.getText();
            musicFacade.getCurrentUser().setLastName(newLast);
            changeSuccess.setVisible(true);
        }
        if (!username.getText().isBlank()) {
            String newUsername = username.getText();
            musicFacade.getCurrentUser().setUsername(newUsername);
            changeSuccess.setVisible(true);
        }
        if (!password.getText().isBlank()) {
            String newPassword = password.getText();
            musicFacade.getCurrentUser().setPassword(newPassword);
            changeSuccess.setVisible(true);
        }
    }

    @FXML
    private void logout() throws IOException {
        App.setRoot("templates/loginTemplate");
        App.setData(null);
        App.setBar(null);
        try {
            musicFacade.logout();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteAccount() throws IOException {
        musicFacade.getUserList().getUsers().remove(musicFacade.getUserList().getCurrentUser());
        App.setRoot("templates/loginTemplate");
        App.setData(null);
        App.setBar(null);
        try {
            musicFacade.logout();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
