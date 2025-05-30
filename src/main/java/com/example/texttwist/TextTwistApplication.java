package com.example.texttwist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;



public class TextTwistApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TextTwistApplication.class.getResource("texttwist.fxml"));
        Parent root = fxmlLoader.load();
        root.setStyle("-fx-background-color: rgba(24,117,193,255);");
        Scene scene = new Scene(root);
        stage.setTitle("Text Twist");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}