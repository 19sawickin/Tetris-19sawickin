package tetris;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

public class PaneOrganizer {
    private BorderPane _root;
    private HBox _bottomPane;

    public PaneOrganizer() {
        _root = new BorderPane();
        _bottomPane = new HBox();
        this.addButton();
        new Game(_root, _bottomPane);

    }

    public void addButton() {
        Button b1 = new Button("Quit");
        b1.setOnAction(new tetris.PaneOrganizer.QuitHandler());
        _bottomPane.getChildren().add(b1);
        _bottomPane.setFocusTraversable(false);
        //b1.setFocusTraversable(false);
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
