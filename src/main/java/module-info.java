module com.harmoniamusic {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens com.harmoniamusic to javafx.fxml;
    exports com.harmoniamusic;

    opens com.model to javafx.fxml;
    exports com.model;
}
