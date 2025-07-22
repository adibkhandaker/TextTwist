module com.example.texttwist {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.texttwist to javafx.fxml;
    exports com.example.texttwist;
}