package tetris;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * The PaneOrganizer is responsible for keeping track of all of the
 * different panes. This includes the root pane, the bottomPane and
 * the gamePane. The quit button and labels for 'pause' and 'game over'
 * are contained in the bottomPane, and all of the pieces and the board
 * are contained in the gamePane.
 */
public class PaneOrganizer {
    private BorderPane _root;
    private Label _pauseLabel;
    private Label _gameOverLabel;

    /**
     * The PaneOrganizer constructor is responsible for adding all of the panes
     * to the root pane and creating the Game class.
     */
    public PaneOrganizer() {
        _root = new BorderPane();
        Pane gamePane = new Pane();
        HBox bottomPane = new HBox();
        this.addButton(bottomPane);
        this.addLabel(bottomPane);
        _root.setBottom(bottomPane);
        _root.setCenter(gamePane);
        gamePane.setFocusTraversable(true);
        new Game(_root, _pauseLabel, _gameOverLabel);
    }

    /**
     * The addButton method is responsible for adding the quit button to the
     * bottomPane.
     */
    public void addButton(HBox bottomPane) {
        Button b1 = new Button("Quit");
        b1.setOnAction(new tetris.PaneOrganizer.QuitHandler());
        bottomPane.getChildren().add(b1);
        bottomPane.setFocusTraversable(false);
        b1.setFocusTraversable(false);
    }

    /**
     * The addLabel method is responsible for adding the 'pause' and 'game over'
     * labels to the bottomPane. Their visibility is set to false and only made
     * visible when either 'P' is pressed or the game ends.
     */
    public void addLabel(HBox bottomPane) {
        // pause label
        _pauseLabel = new Label("PAUSE");
        _pauseLabel.setTextFill(Color.RED);
        _pauseLabel.setVisible(false);
        // gameOverLabel
        _gameOverLabel = new Label("GAME OVER");
        _gameOverLabel.setTextFill(Color.RED);
        _gameOverLabel.setVisible(false);
        // both are added to pane
        bottomPane.getChildren().addAll(_pauseLabel, _gameOverLabel);
        bottomPane.setSpacing(50);
    }

    /**
     * This method is an accessor that returns the root pane.
     */
    public BorderPane getRoot() {
        return _root;
    }

    /**
     * The quit handler is responsible for exiting the game when the 'quit'
     * button is pressed.
     */
    private class QuitHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {
            System.exit(0);
        }
    }

}
