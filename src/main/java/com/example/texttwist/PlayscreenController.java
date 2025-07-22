package com.example.texttwist;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class PlayscreenController implements Initializable {

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
    private int timer = 60;
    private int score = 0;

    private boolean isUntimed = false;

    private int currentRound = 1;
    private String sixLetterWord = "";
    private boolean foundSixLetterWord = false;

    Timeline timeline;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (foundWords != null && foundWords.getScene() != null) {
            foundWords.getScene().getRoot().requestFocus();
        }
    }

    @FXML
    public void handleKeyPressed(KeyEvent event) {
        KeyCode code = event.getCode();
        
        if (code == KeyCode.BACK_SPACE) {
            handleBackspace();
        } else if (code == KeyCode.ENTER) {
            enter(new ActionEvent());
        } else if (code.isLetterKey()) {
            String letter = code.getName().toLowerCase();
            handleLetterInput(letter.charAt(0));
        }
        event.consume();
    }

    private void handleBackspace() {
        if (userWord.length() > 0) {
            char lastChar = userWord.charAt(userWord.length() - 1);
            userWord = userWord.substring(0, userWord.length() - 1);
            
            if (!letterSixAv) {
                letterSix.setText("");
                letterSixAv = true;
                restoreLetterToCircle(lastChar);
            } else if (!letterFiveAv) {
                letterFive.setText("");
                letterFiveAv = true;
                restoreLetterToCircle(lastChar);
            } else if (!letterFourAv) {
                letterFour.setText("");
                letterFourAv = true;
                restoreLetterToCircle(lastChar);
            } else if (!letterThreeAv) {
                letterThree.setText("");
                letterThreeAv = true;
                restoreLetterToCircle(lastChar);
            } else if (!letterTwoAv) {
                letterTwo.setText("");
                letterTwoAv = true;
                restoreLetterToCircle(lastChar);
            } else if (!letterOneAv) {
                letterOne.setText("");
                letterOneAv = true;
                restoreLetterToCircle(lastChar);
            }
        }
    }

    private void restoreLetterToCircle(char letter) {
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] == letter) {
                switch (i) {
                    case 0:
                        if (!circleOneAv) {
                            circleOne.setText(Character.toString(letter).toUpperCase());
                            circleOneAv = true;
                            return;
                        }
                        break;
                    case 1:
                        if (!circleTwoAv) {
                            circleTwo.setText(Character.toString(letter).toUpperCase());
                            circleTwoAv = true;
                            return;
                        }
                        break;
                    case 2:
                        if (!circleThreeAv) {
                            circleThree.setText(Character.toString(letter).toUpperCase());
                            circleThreeAv = true;
                            return;
                        }
                        break;
                    case 3:
                        if (!circleFourAv) {
                            circleFour.setText(Character.toString(letter).toUpperCase());
                            circleFourAv = true;
                            return;
                        }
                        break;
                    case 4:
                        if (!circleFiveAv) {
                            circleFive.setText(Character.toString(letter).toUpperCase());
                            circleFiveAv = true;
                            return;
                        }
                        break;
                    case 5:
                        if (!circleSixAv) {
                            circleSix.setText(Character.toString(letter).toUpperCase());
                            circleSixAv = true;
                            return;
                        }
                        break;
                }
            }
        }
    }

    private void handleLetterInput(char inputChar) {
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] == inputChar) {
                switch (i) {
                    case 0:
                        if (circleOneAv) {
                            pressLetterOne(null);
                            return;
                        }
                        break;
                    case 1:
                        if (circleTwoAv) {
                            pressLetterTwo(null);
                            return;
                        }
                        break;
                    case 2:
                        if (circleThreeAv) {
                            pressLetterThree(null);
                            return;
                        }
                        break;
                    case 3:
                        if (circleFourAv) {
                            pressLetterFour(null);
                            return;
                        }
                        break;
                    case 4:
                        if (circleFiveAv) {
                            pressLetterFive(null);
                            return;
                        }
                        break;
                    case 5:
                        if (circleSixAv) {
                            pressLetterSix(null);
                            return;
                        }
                        break;
                }
            }
        }
    }

    public void setLetters() throws FileNotFoundException {
        boolean hasSixLetterWord = false;
        int attempts = 0;
        final int MAX_ATTEMPTS = 1000;
        
        while (!hasSixLetterWord && attempts < MAX_ATTEMPTS) {
            attempts++;
            generateRandomLetters();
            hasSixLetterWord = checkForSixLetterWord();
        }
        
        if (!hasSixLetterWord) {
            letters = new char[]{'a', 'r', 't', 'i', 's', 't'};
        }
        
        foundSixLetterWord = false;
        foundWords.setEditable(false);
        scoreLabel.setText("Score: " + score);

        timer = isUntimed ? 0 : 60;
        timeLabel.setText(isUntimed ? "Untimed Mode" : formatTime(timer));
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
        if (isUntimed) {
            timeLabel.setText("Untimed Mode");
        } else {
            startRound();
        }
    }
    public void missingWords() {
        for (String word : remainingWords) {
            String spaces = "";
            int length = word.length();
            for (int i = 0; i < length; i++) {
                spaces += "■ ";
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
    private String formatTime(int seconds) {
        int m = seconds / 60;
        int s = seconds % 60;
        return String.format("Time: %02d:%02d", m, s);
    }
    public void startRound() {
        if (foundWords.getScene() != null) {
            foundWords.getScene().getRoot().requestFocus();
        }
        
        if (timeline != null) {
            timeline.stop();
        }
        
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        event -> {
                            --timer;
                            timeLabel.setText(formatTime(timer));
                            if (timer <= 0) {
                                timeline.stop();
                                
                                if (foundSixLetterWord) {
                                    try {
                                        advanceToNextRound();
                                    } catch (FileNotFoundException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                } else {
                                    endGame();
                                }
                            }
                        })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
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

                    if (userWord.length() == 6) {
                        foundSixLetterWord = true;
                        warningLabel.setText("6-letter word found! Round will end when timer expires.");
                    } else {
                        warningLabel.setText("Valid word!");
                    }

                    remainingWords.remove(userWord);
                    removeSpaces(userWord);
                    foundWords.appendText(userWord + " \n");
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
                circleThreeAv = false;
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

    /**
     * Shuffle the six available letters to help the user spot new words.
     * Keeps the same letters but rearranges their order, then resets the UI just like Clear.
     */
    @FXML
    public void shuffle(ActionEvent event) {
        List<Character> temp = new ArrayList<>();
        for (char c : letters) {
            temp.add(c);
        }
        Collections.shuffle(temp);
        for (int i = 0; i < letters.length; i++) {
            letters[i] = temp.get(i);
        }

        userWord = "";
        clear(event);
    }

    /**
     * Called by the TextTwistController when the user selects the Untimed option.
     */
    public void setUntimed(boolean untimed) {
        this.isUntimed = untimed;
    }

    /**
     * Generate 6 random letters using weighted distribution
     */
    private void generateRandomLetters() {
        Random rand = new Random();
        letters = new char[6];
        String weightedAlph = "aaaaaaaaa"  +
                             "bb"        +
                             "cc"        +
                             "dddd"      +
                             "eeeeeeeeeeee" +
                             "ff"        +
                             "ggg"       +
                             "hh"        +
                             "iiiiiiiii" +
                             "j"         +
                             "k"         +
                             "llll"      +
                             "mm"        +
                             "nnnnnn"    +
                             "oooooooo"  +
                             "pp"        +
                             "q"         +
                             "rrrrrr"    +
                             "ssss"      +
                             "tttttt"    +
                             "uuuu"      +
                             "vv"        +
                             "ww"        +
                             "x"         +
                             "yy"        +
                             "z";
        for (int i = 0; i < letters.length; i++) {
            letters[i] = weightedAlph.charAt(rand.nextInt(weightedAlph.length()));
        }
    }

    /**
     * Check if the current letter combination can form at least one 6-letter word
     */
    private boolean checkForSixLetterWord() throws FileNotFoundException {
        String chars = "";
        for (char c : letters) {
            chars += c;
        }
        
        if (allWords == null) {
            allWords = new Trie();
            allWords.loadFromFile("ospd.txt");
        }
        
        Trie tempPossibleWords = new Trie();
        Stack<String> wordStack = new Stack<>();
        buildPossibleWords(chars, wordStack, allWords, tempPossibleWords);
        
        String words = tempPossibleWords.toString();
        Scanner scnr = new Scanner(words);
        while (scnr.hasNext()) {
            String word = scnr.next();
            if (word.length() == 6) {
                sixLetterWord = word;
                scnr.close();
                return true;
            }
        }
        scnr.close();
        return false;
    }

    /**
     * Advance to the next round with new letters
     */
    private void advanceToNextRound() throws FileNotFoundException {
        if (timeline != null) {
            timeline.stop();
        }
        
        currentRound++;
        timer = 60;
        resetDisplayForNewRound();
        setLetters();
         
         if (!isUntimed) {
             startRound();
         }
         
         if (foundWords.getScene() != null) {
             foundWords.getScene().getRoot().requestFocus();
         }
    }

    /**
     * Reset UI elements for a new round
     */
    private void resetDisplayForNewRound() {
        foundWords.setText("Found Words:\n");
        threeWords.setText("3 Letter Words:\n       \n       \n       \n");
        fourWords.setText("4 Letter Words:\n         \n         \n         \n");
        fiveWords.setText("5 Letter Words:\n           \n           \n           \n");
        sixWords.setText("6 Letter Words:\n             \n             \n             \n");
        warningLabel.setText("");
    }

    /**
     * End the game and show score screen
     */
    private void endGame() {
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
        Stage stage = (Stage) scoreLabel.getScene().getWindow();
        Scene myScene = new Scene(root);
        stage.setScene(myScene);
        stage.show();
    }
}

