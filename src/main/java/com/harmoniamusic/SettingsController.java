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
    private Button LogoutButton;
    // Need to implement logic here


    private TextField password;

    @FXML
    private TextField username;

    @FXML

    private void goToHome() throws IOException {
        App.setRoot("templates/homeTemplate");
        App.setData("data/homeData");
        App.setBar("topbar/homeBar");
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

    @FXML
    private void changePassword() {
        String newPassword = password.getText();
        musicFacade.getUserList().getCurrentUser().setPassword(newPassword);
    }

    private void changeUserName() {
        String newUsername = username.getText();
        musicFacade.getUserList().getCurrentUser().setUsername(newUsername);
    }
} 
