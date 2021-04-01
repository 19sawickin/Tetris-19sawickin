package tetris;

import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

/**
 * The Square class is a wrapper class for a rectangle and is responsible for
 * forming the individual squares that the piece is comprised of as well as the
 * border. These squares are really just rectangle nodes.
 */
public class Square {

    private Rectangle _square;

    /**
     * Every time a square is created, it is added to the gamePane and the
     * outline is set to black.
     */
    public Square(Pane gamePane, Color color) {
        _square = new Rectangle(Constants.SQUARE_WIDTH, Constants.SQUARE_WIDTH);
        _square.setStroke(Color.BLACK);
        _square.setFill(color);
        gamePane.getChildren().add(_square);
    }

    /**
     * Getter method that returns a square.
     */
    public Rectangle getSquare() {
        return _square;
    }

    /**
     * This method returns the y-value of a square
     */
    public double getY() {
        return _square.getY();
    }

    /**
     * This method returns the x-value of a square
     */
    public double getX() {
        return _square.getX();
    }

    /**
     * This method sets the x-value of a square
     */
    public void setX(double xVal) {
        _square.setX(xVal);
    }

    /**
     * This method sets the y-value of a square
     */
    public void setY(double yVal) {
        _square.setY(yVal);
    }
}
