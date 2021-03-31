package tetris;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class PaneOrganizer {
    private BorderPane _root;
    private Label _pauseLabel;
    private Label _gameOverLabel;

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

    public void addButton(HBox bottomPane) {
        Button b1 = new Button("Quit");
        b1.setOnAction(new tetris.PaneOrganizer.QuitHandler());
        bottomPane.getChildren().add(b1);
        bottomPane.setFocusTraversable(false);
        b1.setFocusTraversable(false);
    }

    public void addLabel(HBox bottomPane) {
        _pauseLabel = new Label("PAUSE");
        _pauseLabel.setTextFill(Color.RED);
        _pauseLabel.setVisible(false);
        // gameOverLabel
        _gameOverLabel = new Label("GAME OVER");
        _gameOverLabel.setTextFill(Color.RED);
        _gameOverLabel.setVisible(false);
        bottomPane.getChildren().addAll(_pauseLabel, _gameOverLabel);
        bottomPane.setSpacing(50);
    }

    public BorderPane getRoot() {
        return _root;
    }

    private class QuitHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {
            System.exit(0);
        }
    }

}
