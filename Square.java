package tetris;

import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

public class Square {

    private Rectangle _square;
    private BorderPane _root;

    public Square(BorderPane root, Color color) {
        _root = root;
        _square = new Rectangle(Constants.SQUARE_WIDTH, Constants.SQUARE_WIDTH);
        _square.setStroke(Color.BLACK);
        _square.setFill(color);
        _root.getChildren().add(_square);
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
