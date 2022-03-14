package MemoryGame;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;


public class Controller implements Initializable {
    @FXML private Button button;
    @FXML private Label infoLabel;
    @FXML private Label gameOverLabel;
    @FXML private Label score1;
    @FXML private Label score2;

    @FXML private ImageView card1;
    @FXML private ImageView card2;
    @FXML private ImageView card3;
    @FXML private ImageView card4;
    @FXML private ImageView card5;
    @FXML private ImageView card6;
    @FXML private ImageView card7;
    @FXML private ImageView card8;
    @FXML private ImageView card9;
    @FXML private ImageView card10;
    @FXML private ImageView card11;
    @FXML private ImageView card12;
    private ArrayList<ImageView> cards; 

    private Image cardBG = new Image(getClass().getResourceAsStream("img/BG.png"));
    private Image cardA = new Image(getClass().getResourceAsStream("img/CardA.png"));
    private Image cardB = new Image(getClass().getResourceAsStream("img/CardB.png"));
    private Image cardC = new Image(getClass().getResourceAsStream("img/CardC.png"));
    private Image cardD = new Image(getClass().getResourceAsStream("img/CardD.png"));
    private Image cardE = new Image(getClass().getResourceAsStream("img/CardE.png"));
    private Image cardF = new Image(getClass().getResourceAsStream("img/CardF.png"));
    private ArrayList<Image> deck;
    
    private int player1Score;
    private int player2Score;
    private  int pairsFound;
    private boolean player1Turn;
    ArrayList<ImageView> selectedCards;
    private PauseTransition wait;
    private PauseTransition pause;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetDeck();
        shuffle();
        cards  = new ArrayList<>(Arrays.asList(card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11, card12));
        selectedCards = new ArrayList<>();
        player1Score = 0;
        player2Score = 0;
        pairsFound = 0;
        player1Turn = true;
        wait = new PauseTransition(Duration.seconds(1));
        pause = new PauseTransition(Duration.seconds(1));
    }

    /**
     * Reveals the face of the card clicked.
     * @param event mouseclick
     */
    @FXML
    public void click(MouseEvent event) {   
        button.setVisible(false);

        if (selectedCards.size() < 2) {
            ImageView currentCard = (ImageView) event.getTarget();
            currentCard.setImage(getCard(cards.indexOf(currentCard)));
            selectedCards.add(currentCard);
        }
        checkForDuplicates();
        if (selectedCards.size() == 2) {
            checkForMatch();
        }
    }

    /**
     * Check if the current 2 cards selected are a match or not.
     * Check if the game is over.
     */
    private void checkForMatch() {
        // Not a match
        if(!equalsRGB(selectedCards.get(0), selectedCards.get(1))) {
            infoLabel.setText("Not a match");
            pause.setOnFinished(event -> {
                selectedCards.forEach(card -> card.setImage(cardBG));
                selectedCards.clear();
                displayTurn();
            });
            pause.play();
        }
        // Match found
        else {
            infoLabel.setText("Match!");
            setScore();
            pairsFound++;
            pause.setOnFinished(event -> {
                selectedCards.forEach(card -> card.setVisible(false));
                selectedCards.clear();
                displayTurn();
            });
            pause.play();
        }        
        if (pairsFound == 6) {
            wait.setOnFinished(event -> GameOver());
            wait.play();
        }
    }

    /**
     * Check if the image of the imageView object are equals, and
     * if the same imageView has been selected twice
     * @param card1 1st card the player has currently selected
     * @param card2 2nd card the player has currently selected
     * @return true if the pair of cards matches
     */
    public boolean equalsRGB(ImageView card1, ImageView card2) {
        if (card1 == card2) {
            return false;
        }
        else {
            int card1RGBValue = card1.getImage().getPixelReader().getArgb(0,0);
            int card2RGBValue = card2.getImage().getPixelReader().getArgb(0,0);
            return card1RGBValue == card2RGBValue ? true : false;
        }
    }

    /**
     * Displays to the infoLabel which players turn it is.
     */
    private void displayTurn() {
        player1Turn = !player1Turn;
        infoLabel.setText(getPlayerName()+ "'s turn");
    }

    /**
     * Shows the final result of the game and displays
     * the animation + restart game button.
     */
    private void GameOver() {
        infoLabel.setText(getFinalResult());
        gameOverLabel.setText("Game over");
        button.setVisible(true);
        showAllCardsEnding(0);
        animation();
    }

    /**
     * Recursive method, to show each card with a delay on the game over scene.
     * @param i index of the cards in the cards array.
     */
    private void showAllCardsEnding(int i) {
        PauseTransition wait = new PauseTransition(Duration.millis(250));
        if (i < cards.size())
            wait.setOnFinished(event -> {
                cards.get(i).setDisable(true);
                cards.get(i).setVisible(true);
                showAllCardsEnding(i + 1);
            });
            wait.play();
    }

    /**
     * Plays the scaling animation of infoLabel
     */
    private void animation() {
        ScaleTransition scale = new ScaleTransition();
        scale.setDuration(Duration.seconds(1));
        scale.setByX(0.9f);
        scale.setByY(0.9f);
        scale.setNode(infoLabel);
        scale.play();    
    }

    /**
     * Restarts the game, even the button action is clicked
     * @param event button click
     */
    @FXML
    public void restartGame(ActionEvent event) {
        resetDeck();
        shuffle();
        showCards();
        player1Score = 0;
        player2Score = 0;
        pairsFound = 0;
        player1Turn = true;
        selectedCards.clear();
        cards.forEach(card -> card.setDisable(false));
        score1.setText(String.valueOf(player1Score));
        score2.setText(String.valueOf(player2Score));
        infoLabel.setScaleX(1);
        infoLabel.setScaleY(1);
        infoLabel.setText(getPlayerName() + "'s turn");
        gameOverLabel.setText("");
    } 

    /**
     * Resets the cards image to the background, and so the player can click on them
     */
    private void showCards() {
        cards.forEach(card -> {
            card.setImage(cardBG);
            card.setVisible(true);
        });
    }

    /**
     *  Gets the final result of the game
     * @return the final result of the game
     */
    private String getFinalResult() {
        if (player1Score == player2Score) 
            return "Draw!";
        else
            return player1Score > player2Score ? "Player one won!" : "Player two won!";
    }

    /**
     * Check the deck for a specific card
     * @param i index of the card
     * @return the image of the card at the index i
     */
    public Image getCard(int i) {
        return deck.get(i);
    }

    /**
     * Get the current players name as a String
     * @return a String of the current players name
     */
    private String getPlayerName() {
        return player1Turn ? "Player one" : "Player two";
    }

    /**
     * Sets the scoreLabels
     */
    private void setScore() {
        if (player1Turn) {
            player1Score++;
            score1.setText(String.valueOf(player1Score));
        }
        else {
            player2Score++;
            score2.setText(String.valueOf(player2Score));
        }
    }

    /**
     * Initilizes the deck array back, to an non-shuffled order.
     */
    public void resetDeck() {
        deck = new ArrayList<Image>(Arrays.asList(cardA, cardA, cardB, cardB, cardC, cardC, cardD, cardD, cardE, cardE, cardF, cardF));
    }

    /**
     * Shuffles the deck array in a random order.
     */
    public void shuffle() {
        Collections.shuffle(deck);
    }

    /**
     * Handles the case where you double click one card
     */
    private void checkForDuplicates() {
        if (selectedCards.size() == 2 ) {
            if (selectedCards.get(0) == selectedCards.get(1)) {
                selectedCards.remove(1);
            }
        }
    }
}

