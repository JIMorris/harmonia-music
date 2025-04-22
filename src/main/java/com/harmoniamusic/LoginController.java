package com.harmoniamusic;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.model.MusicFacade;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginController extends Application{
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
private Label loginErrorLabel;

    @FXML
    private void LogIn() {
        String username = username_txt.getText();
        String password = password_txt.getText();
        try {
            musicFacade.login(username, password);
            App.setRoot("homeTemplate");
            App.setData("homeData");
        } catch (Exception e) {
            loginErrorLabel.setVisible(true);
        }
    }

    @FXML
    private void GoToSignUp() throws IOException {
        App.setData("signupData");
        // this somehow switches to the signup stuff
    }

    @Override
    public void start(Stage arg0) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }

    //   @FXML
    // private BorderPane root;

   // @Override
//     public void initialize(URL location, ResourceBundle resources) {
//         BorderPane root = null;
//         try {
//            Parent header = FXMLLoader.load(getClass().getResource("login.fxml"));
//            // Parent sidebar = FXMLLoader.load(getClass().getResource("sidebar.fxml"));
//             Parent content = FXMLLoader.load(getClass().getResource("log.fxml"));
            

//             root.setTop(header);
//             //root.setLeft(sidebar);
//             root.setCenter(content);

//         } catch (IOException e) {
//             e.printStackTrace();
//         }
// }

}
