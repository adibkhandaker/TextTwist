package com.example.texttwist;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ScorescreenController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label yourScore;

    public void displayScore(int score) {
        yourScore.setText("YOUR SCORE: " + score);
    }

    public void pressMainMenu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TextTwistApplication.class.getResource("texttwist.fxml"));
        root = fxmlLoader.load();
        root.setStyle("-fx-background-color: rgba(24,117,193,255);");
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Text Twist");
        stage.setScene(scene);
        stage.show();
    }

    public void pressPlayAgain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("playscreen.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        PlayscreenController controller = loader.getController();
        controller.setLetters();
        controller.clear(event);
    }
}
