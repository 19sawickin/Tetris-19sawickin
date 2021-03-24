package tetris;

import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

public class Square {

    private Rectangle _square;
    private BorderPane _root;

    public Square(int i, int j, BorderPane root) {
        _root = root;
        _square = new Rectangle(Constants.SQUARE_WIDTH, Constants.SQUARE_WIDTH);
        _square.setX(i*Constants.SQUARE_WIDTH);
        _square.setY(j*Constants.SQUARE_WIDTH);
        _square.setStroke(Color.BLACK);
        _square.setFill(Color.GRAY);
        _root.getChildren().add(_square);
    }
}
