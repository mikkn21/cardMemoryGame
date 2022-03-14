package MemoryGame;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class Main {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button;

    @FXML
    private ImageView card1;

    @FXML
    private ImageView card10;

    @FXML
    private ImageView card11;

    @FXML
    private ImageView card12;

    @FXML
    private ImageView card2;

    @FXML
    private ImageView card3;

    @FXML
    private ImageView card4;

    @FXML
    private ImageView card5;

    @FXML
    private ImageView card6;

    @FXML
    private ImageView card7;

    @FXML
    private ImageView card8;

    @FXML
    private ImageView card9;

    @FXML
    private GridPane gridPane;

    @FXML
    void click(MouseEvent event) {

    }

    @FXML
    void restartGame(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert button != null : "fx:id=\"button\" was not injected: check your FXML file 'Main.fxml'.";
        assert card1 != null : "fx:id=\"card1\" was not injected: check your FXML file 'Main.fxml'.";
        assert card10 != null : "fx:id=\"card10\" was not injected: check your FXML file 'Main.fxml'.";
        assert card11 != null : "fx:id=\"card11\" was not injected: check your FXML file 'Main.fxml'.";
        assert card12 != null : "fx:id=\"card12\" was not injected: check your FXML file 'Main.fxml'.";
        assert card2 != null : "fx:id=\"card2\" was not injected: check your FXML file 'Main.fxml'.";
        assert card3 != null : "fx:id=\"card3\" was not injected: check your FXML file 'Main.fxml'.";
        assert card4 != null : "fx:id=\"card4\" was not injected: check your FXML file 'Main.fxml'.";
        assert card5 != null : "fx:id=\"card5\" was not injected: check your FXML file 'Main.fxml'.";
        assert card6 != null : "fx:id=\"card6\" was not injected: check your FXML file 'Main.fxml'.";
        assert card7 != null : "fx:id=\"card7\" was not injected: check your FXML file 'Main.fxml'.";
        assert card8 != null : "fx:id=\"card8\" was not injected: check your FXML file 'Main.fxml'.";
        assert card9 != null : "fx:id=\"card9\" was not injected: check your FXML file 'Main.fxml'.";
        assert gridPane != null : "fx:id=\"gridPane\" was not injected: check your FXML file 'Main.fxml'.";

    }

}
