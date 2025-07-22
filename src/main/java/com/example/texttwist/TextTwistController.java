package com.example.texttwist;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Optional;


public class TextTwistController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void timedButton(ActionEvent event) throws IOException {
        showRulesDialog(true);
        
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
        showRulesDialog(false);
        
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

    private void showRulesDialog(boolean isTimed) {
        Alert rulesAlert = new Alert(Alert.AlertType.INFORMATION);
        rulesAlert.setTitle("Text Twist - Game Rules");
        rulesAlert.setHeaderText("Welcome to Text Twist!");
        
        VBox content = new VBox(10);
        content.setPadding(new Insets(10));
        
        Text title = new Text("How to Play");
        title.setFont(Font.font("System", FontWeight.BOLD, 16));
        
        Text rules = new Text(
            "OBJECTIVE:\n" +
            "• Rearrange the 6 letters to form as many words as possible\n" +
            "• Words must be at least 3 letters long\n" +
            "• Find the 6-letter word to advance to the next round\n\n" +
            
            "CONTROLS:\n" +
            "• Click letters or use keyboard to spell words\n" +
            "• Press ENTER to submit a word\n" +
            "• Use BACKSPACE to remove letters\n" +
            "• Click 'Shuffle' to rearrange the letters\n" +
            "• Click 'Clear' to reset your current word\n\n" +
            
            "GAME MODES:\n" +
            "• TIMED: You have 60 seconds per round\n" +
            "• UNTIMED: Play at your own pace\n\n" +
            
            "SCORING:\n" +
            "• 3-letter words: 1,500 points\n" +
            "• 4-letter words: 2,000 points\n" +
            "• 5-letter words: 2,500 points\n" +
            "• 6-letter words: 3,000 points\n\n" +
            
            "TIP: You must find the 6-letter word to advance!"
        );
        rules.setFont(Font.font("System", 12));
        
        content.getChildren().addAll(title, rules);
        
        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setPrefSize(500, 400);
        scrollPane.setFitToWidth(true);
        
        rulesAlert.getDialogPane().setContent(scrollPane);
        rulesAlert.getDialogPane().setPrefSize(550, 450);
        
        rulesAlert.getButtonTypes().setAll(ButtonType.OK);
        rulesAlert.getDialogPane().lookupButton(ButtonType.OK).setStyle(
            "-fx-background-color: #4f9dff; -fx-text-fill: white; -fx-font-weight: bold;"
        );
        
        String modeText = isTimed ? "TIMED" : "UNTIMED";
        rulesAlert.setContentText("You selected " + modeText + " mode. Good luck!");
        
        rulesAlert.showAndWait();
    }
}