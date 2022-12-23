module com.example.triviagame {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.triviagame to javafx.fxml;
    exports com.example.triviagame;
}