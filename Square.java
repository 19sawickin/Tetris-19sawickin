package tetris;

import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

public class Square {

    private Rectangle _square;
    private Pane _gamePane;

    public Square(Pane gamePane, Color color) {
        _square = new Rectangle(Constants.SQUARE_WIDTH, Constants.SQUARE_WIDTH);
        _square.setStroke(Color.BLACK);
        _square.setFill(color);
        gamePane.getChildren().add(_square);
    }

    public Rectangle getSquare() {
        return _square;
    }

    public double getY() {
        return _square.getY();
    }
    public double getX() {
        return _square.getX();
    }
    public void setX(double xVal) {
        _square.setX(xVal);
    }
    public void setY(double yVal) {
        _square.setY(yVal);
    }
}
