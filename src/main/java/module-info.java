module com.harmoniamusic {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.harmoniamusic to javafx.fxml;
    exports com.harmoniamusic;
}
