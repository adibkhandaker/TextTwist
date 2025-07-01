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
import java.util.Random;


public class TextTwistController {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    public void timedButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("playscreen.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene myScene = new Scene(root);
        stage.setScene(myScene);
        stage.show();
        PlayscreenController controller = loader.getController();
        controller.setLetters();
        controller.clear(event);

    }

    @FXML
    public void untimedButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("playscreen.fxml"));
        root = loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene myScene = new Scene(root);
        stage.setScene(myScene);
        stage.show();
        PlayscreenController controller = loader.getController();
        controller.setUntimed(true);
        controller.setLetters();
        controller.clear(event);
    }

}