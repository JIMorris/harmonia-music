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

public class SettingsController {
    private MusicFacade musicFacade = MusicFacade.getInstance();

    @FXML
    private Button LogoutButton()
    //TODO


    private void goToHome() throws IOException {
        App.setBar("homeBar");
        App.setData("homeData");
    }
}
