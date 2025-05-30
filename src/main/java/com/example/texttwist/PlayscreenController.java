package com.example.texttwist;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class PlayscreenController {

    @FXML
    private Label circleOne;
    @FXML
    private Label circleTwo;
    @FXML
    private Label circleThree;
    @FXML
    private Label circleFour;
    @FXML
    private Label circleFive;
    @FXML
    private Label circleSix;

    @FXML
    private Label letterOne;
    @FXML
    private Label letterTwo;
    @FXML
    private Label letterThree;
    @FXML
    private Label letterFour;
    @FXML
    private Label letterFive;
    @FXML
    private Label letterSix;

    @FXML
    private TextArea foundWords;
    @FXML
    private TextArea threeWords;
    @FXML
    private TextArea fourWords;
    @FXML
    private TextArea fiveWords;
    @FXML
    private TextArea sixWords;

    private boolean letterOneAv = true;
    private boolean letterTwoAv = true;
    private boolean letterThreeAv = true;
    private boolean letterFourAv = true;
    private boolean letterFiveAv = true;
    private boolean letterSixAv = true;

    private boolean circleOneAv = true;
    private boolean circleTwoAv = true;
    private boolean circleThreeAv = true;
    private boolean circleFourAv = true;
    private boolean circleFiveAv = true;
    private boolean circleSixAv = true;

    @FXML
    private Label warningLabel;
    @FXML
    private Label timeLabel;
    @FXML
    public Label scoreLabel;

    private List<String> remainingWords;
    private String userWord = "";
    private char[] letters;
    private Trie allWords;
    private Trie possibleWords;
    private int timer = 15;
    private int score = 0;

    Timeline timeline;

    public void setLetters() throws FileNotFoundException {
        Random rand = new Random();
        letters = new char[6];
        String weightedAlph = "aaaaaaaaeeeeeeeeeiiiiiiiooooooouuu" + "bbccddffgghhjjkkllmmnnpqrrssttvvwwxxyyz";
        for (int i = 0; i < letters.length; i++) {
            letters[i] = weightedAlph.charAt(rand.nextInt(weightedAlph.length()));
        }
        foundWords.setEditable(false);
        scoreLabel.setText("Score: " + score);

        timeLabel.setText("Time Remaining: " + timer);
        circleOne.setText(Character.toString(letters[0]).toUpperCase());
        circleTwo.setText(Character.toString(letters[1]).toUpperCase());
        circleThree.setText(Character.toString(letters[2]).toUpperCase());
        circleFour.setText(Character.toString(letters[3]).toUpperCase());
        circleFive.setText(Character.toString(letters[4]).toUpperCase());
        circleSix.setText(Character.toString(letters[5]).toUpperCase());

        letterOne.setText("");
        letterTwo.setText("");
        letterThree.setText("");
        letterFour.setText("");
        letterFive.setText("");
        letterSix.setText("");

        threeWords.setText("");
        fourWords.setText("");
        fiveWords.setText("");
        sixWords.setText("");

        getWords();
        missingWords();
        startRound();

    }
    public void missingWords() {
        for (String word : remainingWords) {
            String spaces = "";
            int length = word.length();
            for (int i = 0; i < length; i++) {
                spaces += "_ ";
            }
            spaces += "\n";
            if (length == 3) {
                threeWords.appendText(spaces);
            } else if (length == 4) {
                fourWords.appendText(spaces);
            } else if (length == 5) {
                fiveWords.appendText(spaces);
            } else if (length == 6) {
                sixWords.appendText(spaces);
            }
        }
    }
    public void startRound() {
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        event -> {
                            --timer;
                            timeLabel.setText("Time remaining: " + timer);
                            if (timer <= 0) {
                                timeline.stop();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("scorescreen.fxml"));
                                Parent root = null;
                                try {
                                    root = loader.load();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                ScorescreenController controller = loader.getController();
                                controller.displayScore(score);
                                root.setStyle("-fx-background-color: rgba(24,117,193,255);");
                                Stage stage = (Stage) timeLabel.getScene().getWindow();
                                Scene myScene = new Scene(root);
                                stage.setScene(myScene);
                                stage.show();
                            }
                        })
        );
        timeline.setCycleCount(120);
        timeline.play();
    }

    public void enter(ActionEvent event) {
        if (userWord.length() < 3) {
            warningLabel.setText("Word is must be at least 3 characters!");
        } else {
            if (possibleWords.isWord(userWord)) {
                if (remainingWords.contains(userWord)) {
                    score += userWord.length() * 500;
                    scoreLabel.setText("Score: " + score);


                    remainingWords.remove(userWord);
                    removeSpaces(userWord);
                    foundWords.appendText(userWord + " \n");
                    warningLabel.setText("Valid word!");
                } else {
                    warningLabel.setText("You have already used this word!");
                }
            } else {
                warningLabel.setText("Invalid word!");
            }

            userWord = "";

            clear(event);
        }
    }

    public void clear(ActionEvent event) {
        circleOne.setText(Character.toString(letters[0]).toUpperCase());
        circleTwo.setText(Character.toString(letters[1]).toUpperCase());
        circleThree.setText(Character.toString(letters[2]).toUpperCase());
        circleFour.setText(Character.toString(letters[3]).toUpperCase());
        circleFive.setText(Character.toString(letters[4]).toUpperCase());
        circleSix.setText(Character.toString(letters[5]).toUpperCase());

        letterOne.setText("");
        letterTwo.setText("");
        letterThree.setText("");
        letterFour.setText("");
        letterFive.setText("");
        letterSix.setText("");

        letterOneAv = true;
        letterTwoAv = true;
        letterThreeAv = true;
        letterFourAv = true;
        letterFiveAv = true;
        letterSixAv = true;

        circleOneAv = true;
        circleTwoAv = true;
        circleThreeAv = true;
        circleFourAv = true;
        circleFiveAv = true;
        circleSixAv = true;
    }

    public void removeSpaces(String userWord) {
        String spaces = "";
        if (userWord.length() == 3) {
            spaces = threeWords.getText();
            threeWords.setText(spaces.substring(0, spaces.length() - 7));
        } else if (userWord.length() == 4) {
            spaces = fourWords.getText();
            fourWords.setText(spaces.substring(0, spaces.length() - 9));
        } else if (userWord.length() == 5) {
            spaces = fiveWords.getText();
            fiveWords.setText(spaces.substring(0, spaces.length() - 11));
        } else if (userWord.length() == 6) {
            spaces = sixWords.getText();
            sixWords.setText(spaces.substring(0, spaces.length() - 13));
        }
    }

    public void getWords() throws FileNotFoundException {
        String chars = "";
        for (int i = 0; i < letters.length; i++) {
            chars += letters[i];
        }
        allWords = new Trie();
        allWords.loadFromFile("ospd.txt");
        possibleWords = new Trie();
        Stack<String> wordStack = new Stack<>();
        buildPossibleWords(chars, wordStack, allWords, possibleWords);

        remainingWords = new ArrayList<>();
        String words = possibleWords.toString();
        Scanner scnr = new Scanner(words);
        while (scnr.hasNext()) {
            remainingWords.add(scnr.next());
        }


    }

    private void buildPossibleWords(String userLetters, Stack<String> wordStack,
                                          Trie allWords, Trie possibleWords) {
        String word = createString(wordStack);
        if (!allWords.isPrefix(word)) {
            return;
        }
        if (allWords.isWord(word) && word.length() >= 3) {
            possibleWords.insert(word);
        }

        for (int i = 0; i < userLetters.length(); i++) {
            wordStack.push(userLetters.charAt(i) + "");
            buildPossibleWords(userLetters.substring(0,i) + userLetters.substring(i+1),
                    wordStack, allWords, possibleWords);
            wordStack.pop();
        }
    }

    private String createString(Stack<String> wordStack) {
        Stack<String> clone = (Stack<String>) wordStack.clone();
        String result = "";
        while (!clone.empty()) {
            result = clone.pop() + result;
        }
        return result;
    }

    public void pressLetterOne(MouseEvent event) {
        if (circleOneAv) {
            if (letterOneAv) {
                userWord = userWord + Character.toString(letters[0]);
                letterOneAv = false;
                letterOne.setText(Character.toString(letters[0]).toUpperCase());
                circleOneAv = false;
                circleOne.setText("");
            } else if (letterTwoAv) {
                userWord = userWord + Character.toString(letters[0]);
                letterTwoAv = false;
                letterTwo.setText(Character.toString(letters[0]).toUpperCase());
                circleOneAv = false;
                circleOne.setText("");
            } else if (letterThreeAv) {
                userWord = userWord + Character.toString(letters[0]);
                letterThreeAv = false;
                letterThree.setText(Character.toString(letters[0]).toUpperCase());
                circleOneAv = false;
                circleOne.setText("");
            } else if (letterFourAv) {
                userWord = userWord + Character.toString(letters[0]);
                letterFourAv = false;
                letterFour.setText(Character.toString(letters[0]).toUpperCase());
                circleOneAv = false;
                circleOne.setText("");
            } else if (letterFiveAv) {
                userWord = userWord + Character.toString(letters[0]);
                letterFiveAv = false;
                letterFive.setText(Character.toString(letters[0]).toUpperCase());
                circleOneAv = false;
                circleOne.setText("");
            } else if (letterSixAv) {
                userWord = userWord + Character.toString(letters[0]);
                letterSixAv = false;
                letterSix.setText(Character.toString(letters[0]).toUpperCase());
                circleOneAv = false;
                circleOne.setText("");
            }
        }
    }

    public void pressLetterTwo(MouseEvent event) {
        if (circleTwoAv) {
            if (letterOneAv) {
                userWord = userWord + Character.toString(letters[1]);
                letterOneAv = false;
                letterOne.setText(Character.toString(letters[1]).toUpperCase());
                circleTwo.setText("");
                circleTwoAv = false;
            } else if (letterTwoAv) {
                userWord = userWord + Character.toString(letters[1]);
                letterTwoAv = false;
                letterTwo.setText(Character.toString(letters[1]).toUpperCase());
                circleTwo.setText("");
                circleTwoAv = false;
            } else if (letterThreeAv) {
                userWord = userWord + Character.toString(letters[1]);
                letterThreeAv = false;
                letterThree.setText(Character.toString(letters[1]).toUpperCase());
                circleTwo.setText("");
                circleTwoAv = false;
            } else if (letterFourAv) {
                userWord = userWord + Character.toString(letters[1]);
                letterFourAv = false;
                letterFour.setText(Character.toString(letters[1]).toUpperCase());
                circleTwo.setText("");
                circleTwoAv = false;
            } else if (letterFiveAv) {
                userWord = userWord + Character.toString(letters[1]);
                letterFiveAv = false;
                letterFive.setText(Character.toString(letters[1]).toUpperCase());
                circleTwo.setText("");
                circleTwoAv = false;
            } else if (letterSixAv) {
                userWord = userWord + Character.toString(letters[1]);
                letterSixAv = false;
                letterSix.setText(Character.toString(letters[1]).toUpperCase());
                circleTwo.setText("");
                circleTwoAv = false;
            }
        }
    }

    public void pressLetterThree(MouseEvent event) {
        if (circleThreeAv) {
            if (letterOneAv) {
                userWord = userWord + Character.toString(letters[2]);
                letterOneAv = false;
                letterOne.setText(Character.toString(letters[2]).toUpperCase());
                circleThree.setText("");
                circleThreeAv = false;
            } else if (letterTwoAv) {
                userWord = userWord + Character.toString(letters[2]);
                letterTwoAv = false;
                letterTwo.setText(Character.toString(letters[2]).toUpperCase());
                circleThree.setText("");
                circleThreeAv = false;
            } else if (letterThreeAv) {
                userWord = userWord + Character.toString(letters[2]);
                letterThreeAv = false;
                letterThree.setText(Character.toString(letters[2]).toUpperCase());
                circleThree.setText("");
                circleThreeAv = false;
            } else if (letterFourAv) {
                userWord = userWord + Character.toString(letters[2]);
                letterFourAv = false;
                letterFour.setText(Character.toString(letters[2]).toUpperCase());
                circleThree.setText("");
            } else if (letterFiveAv) {
                userWord = userWord + Character.toString(letters[2]);
                letterFiveAv = false;
                letterFive.setText(Character.toString(letters[2]).toUpperCase());
                circleThree.setText("");
                circleThreeAv = false;
            } else if (letterSixAv) {
                userWord = userWord + Character.toString(letters[2]);
                letterSixAv = false;
                letterSix.setText(Character.toString(letters[2]).toUpperCase());
                circleThree.setText("");
                circleThreeAv = false;
            }
        }
    }

    public void pressLetterFour(MouseEvent event) {
        if (circleFourAv) {
            if (letterOneAv) {
                userWord = userWord + Character.toString(letters[3]);
                letterOneAv = false;
                letterOne.setText(Character.toString(letters[3]).toUpperCase());
                circleFour.setText("");
                circleFourAv = false;
            } else if (letterTwoAv) {
                userWord = userWord + Character.toString(letters[3]);
                letterTwoAv = false;
                letterTwo.setText(Character.toString(letters[3]).toUpperCase());
                circleFour.setText("");
                circleFourAv = false;
            } else if (letterThreeAv) {
                userWord = userWord + Character.toString(letters[3]);
                letterThreeAv = false;
                letterThree.setText(Character.toString(letters[3]).toUpperCase());
                circleFour.setText("");
                circleFourAv = false;
            } else if (letterFourAv) {
                userWord = userWord + Character.toString(letters[3]);
                letterFourAv = false;
                letterFour.setText(Character.toString(letters[3]).toUpperCase());
                circleFour.setText("");
                circleFourAv = false;
            } else if (letterFiveAv) {
                userWord = userWord + Character.toString(letters[3]);
                letterFiveAv = false;
                letterFive.setText(Character.toString(letters[3]).toUpperCase());
                circleFour.setText("");
                circleFourAv = false;
            } else if (letterSixAv) {
                userWord = userWord + Character.toString(letters[3]);
                letterSixAv = false;
                letterSix.setText(Character.toString(letters[3]).toUpperCase());
                circleFour.setText("");
                circleFourAv = false;
            }
        }
    }

    public void pressLetterFive(MouseEvent event) {
        if (circleFiveAv) {
            if (letterOneAv) {
                userWord = userWord + Character.toString(letters[4]);
                letterOneAv = false;
                letterOne.setText(Character.toString(letters[4]).toUpperCase());
                circleFive.setText("");
                circleFiveAv = false;
            } else if (letterTwoAv) {
                userWord = userWord + Character.toString(letters[4]);
                letterTwoAv = false;
                letterTwo.setText(Character.toString(letters[4]).toUpperCase());
                circleFive.setText("");
                circleFiveAv = false;
            } else if (letterThreeAv) {
                userWord = userWord + Character.toString(letters[4]);
                letterThreeAv = false;
                letterThree.setText(Character.toString(letters[4]).toUpperCase());
                circleFive.setText("");
                circleFiveAv = false;
            } else if (letterFourAv) {
                userWord = userWord + Character.toString(letters[4]);
                letterFourAv = false;
                letterFour.setText(Character.toString(letters[4]).toUpperCase());
                circleFive.setText("");
                circleFiveAv = false;
            } else if (letterFiveAv) {
                userWord = userWord + Character.toString(letters[4]);
                letterFiveAv = false;
                letterFive.setText(Character.toString(letters[4]).toUpperCase());
                circleFive.setText("");
                circleFiveAv = false;
            } else if (letterSixAv) {
                userWord = userWord + Character.toString(letters[4]);
                letterSixAv = false;
                letterSix.setText(Character.toString(letters[4]).toUpperCase());
                circleFive.setText("");
                circleFiveAv = false;
            }
        }
    }

    public void pressLetterSix(MouseEvent event) {
        if (circleSixAv) {
            if (letterOneAv) {
                userWord = userWord + Character.toString(letters[5]);
                letterOneAv = false;
                letterOne.setText(Character.toString(letters[5]).toUpperCase());
                circleSix.setText("");
                circleSixAv = false;
            } else if (letterTwoAv) {
                userWord = userWord + Character.toString(letters[5]);
                letterTwoAv = false;
                letterTwo.setText(Character.toString(letters[5]).toUpperCase());
                circleSix.setText("");
                circleSixAv = false;
            } else if (letterThreeAv) {
                userWord = userWord + Character.toString(letters[5]);
                letterThreeAv = false;
                letterThree.setText(Character.toString(letters[5]).toUpperCase());
                circleSix.setText("");
                circleSixAv = false;
            } else if (letterFourAv) {
                userWord = userWord + Character.toString(letters[5]);
                letterFourAv = false;
                letterFour.setText(Character.toString(letters[5]).toUpperCase());
                circleSix.setText("");
                circleSixAv = false;
            } else if (letterFiveAv) {
                userWord = userWord + Character.toString(letters[5]);
                letterFiveAv = false;
                letterFive.setText(Character.toString(letters[5]).toUpperCase());
                circleSix.setText("");
                circleSixAv = false;
            } else if (letterSixAv) {
                userWord = userWord + Character.toString(letters[5]);
                letterSixAv = false;
                letterSix.setText(Character.toString(letters[5]).toUpperCase());
                circleSix.setText("");
                circleSixAv = false;
            }
        }
    }
}

